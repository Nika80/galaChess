

package ru.vsu.cs.karmanova_v_v.service.move;

import ru.vsu.cs.karmanova_v_v.model.board.CellFinder;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.service.provider.MoveSupplierFactory;

import java.util.ArrayList;
import java.util.List;

public class CheckMateChecker {

    public static boolean isMate(Board board, FigureType player, Coordinate KingLocation) {

        CellFinder cellFinder = new CellFinder(board);

        FigureType selfColor = player;


        List<Coordinate> selfPieces = cellFinder.getPiecesCoordinates(selfColor);

        Cell opponentKingTile = cellFinder.getTile(KingLocation);

        List<Coordinate> afterMoveCoordinatesForKing = new ArrayList<>(MoveSupplierFactory.getInstance()
                .create(board, opponentKingTile)
                .getAvailableMoves().stream()
                .map(KingLocation::getSum)
                .toList());

        for (Coordinate pieceCoordinate : selfPieces) {
            MoveSupplierFactory.getInstance()
                    .create(board, cellFinder.getTile(pieceCoordinate)).getAvailableMoves()
                    .stream()
                    .map(pieceCoordinate::getSum)
                    .forEach(afterMoveCoordinatesForKing::remove);
        }
        return afterMoveCoordinatesForKing.isEmpty();
    }

}


