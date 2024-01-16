package ru.vsu.cs.karmanova_v_v.service.move;


import ru.vsu.cs.karmanova_v_v.model.board.CellFinder;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.figures.Piece;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.figures.King;
import ru.vsu.cs.karmanova_v_v.model.move.Move;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.service.provider.MoveSupplierFactory;

import java.util.List;

public class MoveManager {
    private final Board board;
    private final CellFinder cellFinder;

    private final CenterChecker centerChecker;


    public MoveManager(Board board, CellFinder cellFinder, CenterChecker centerChecker) {
        this.board = board;
        this.cellFinder = cellFinder;
        this.centerChecker = centerChecker;
    }

    public void playMove(Coordinate from, Coordinate to) {
        if (!isValidMove(from, to)) {
            return;
        }

        Cell fromTile = cellFinder.getTile(from);
        Cell toTile = cellFinder.getTile(to);
        var toPiece = toTile.getPiece();

        if (toPiece instanceof King) {
            Player player = toPiece.getPlayer();
            player.setKingsCount(player.getKingsCount()-1);
        }

        Piece pieceToMove = fromTile.getPiece();
        Move move = new Move(fromTile.getCoordinate(), toTile.getCoordinate(), pieceToMove, toPiece);
        board.getMoveStack().offerLast(move);
        toTile.setPiece(pieceToMove);
        fromTile.empty();

        checkCentralSquare();

        // TODO: пешка ходит на середину только когда там есть вражеская или какой-нибудь король
    }

    private void checkCentralSquare() {
        int galaBlackCount = centerChecker.countKingsInCentralSquare(FigureType.BLACK);
        int galaWhiteCount = centerChecker.countKingsInCentralSquare(FigureType.WHITE);
        int countGalaBlack = board.getPlayerByColor(FigureType.BLACK).getKingsCount();
        int countGalaWhite = board.getPlayerByColor(FigureType.WHITE).getKingsCount();
        System.out.println(galaBlackCount);
        if (galaBlackCount == countGalaBlack && galaWhiteCount == countGalaWhite) {
            System.out.println("Ничья!");
            board.setFinished(true);
        } else if (galaBlackCount > 0 && countGalaWhite == 0) {
            System.out.println("Чёрные выиграли!");
            board.setFinished(true);
        } else if (galaWhiteCount > 0 && countGalaBlack == 0) {
            System.out.println("Белые выиграли!");
            board.setFinished(true);
        } else if (galaBlackCount == 2) {
            System.out.println("Черные выиграли!");
            board.setFinished(true);
        } else if (galaWhiteCount == 2) {
            System.out.println("Белые выиграли!");
            board.setFinished(true);
        }
    }


    public boolean isValidMove(Coordinate from, Coordinate to) {
        Cell fromTile = cellFinder.getTile(from);
        Cell toTile = cellFinder.getTile(to);
        Piece fromPiece = fromTile.getPiece();

        if (fromPiece == null) return false;

        if (fromPiece.getPlayer() != board.getCurrentPlayer()) return false;

        return isValidMoveForPiece(from, to);
    }

    private boolean isValidMoveForPiece(Coordinate from, Coordinate to) {
        Cell fromTile = cellFinder.getTile(from);
        List<MoveVariant> validMoves = MoveSupplierFactory.getInstance().create(board, fromTile).getAvailableMoves();

	    int xMove = to.getX() - from.getX();
        int yMove = to.getY() - from.getY();

        for (MoveVariant move : validMoves) {
            if (!(move.getX() == xMove && move.getY() == yMove)) continue;
            return true;
        }
        return false;
    }

}


