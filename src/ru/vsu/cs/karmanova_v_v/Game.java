package ru.vsu.cs.karmanova_v_v;

import ru.vsu.cs.karmanova_v_v.model.board.CellFinder;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.config.GameConfig;
import ru.vsu.cs.karmanova_v_v.model.move.Move;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.service.move.CenterChecker;
import ru.vsu.cs.karmanova_v_v.service.move.MoveManager;
import ru.vsu.cs.karmanova_v_v.service.player.PlayerService;
import ru.vsu.cs.karmanova_v_v.service.player.PlayerServiceFactory;
import ru.vsu.cs.karmanova_v_v.presentation.util.BoardPrinter;

public class Game {
    private final Board board;
    private final BoardPrinter printer;
    private boolean finished;
    private final CenterChecker centerChecker;
    private Player currentPlayer;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final PlayerService firstPlayerService;
    private final PlayerService secondPlayerService;
    private final CellFinder tileFinder;
    private final MoveManager moveManager;

    public Game(GameConfig config) {
        this.firstPlayer = config.getFirstPlayer();
        this.secondPlayer = config.getSecondPlayer();
        this.currentPlayer = getPlayerByColor(config.getFirstPlayerColor());
        this.board = new Board(currentPlayer, firstPlayer, secondPlayer);
        this.tileFinder = new CellFinder(board);
        this.printer = new BoardPrinter(board);
        this.centerChecker = new CenterChecker(board);
        this.moveManager = new MoveManager(board, tileFinder, centerChecker);
        this.firstPlayerService = PlayerServiceFactory.getInstance()
                .createPlayerService(board, moveManager, firstPlayer);
        this.secondPlayerService = PlayerServiceFactory.getInstance()
                .createPlayerService(board, moveManager, secondPlayer);
        this.finished = false;
    }

    public void makeMove() {
        if (finished) return;
        if (currentPlayer == firstPlayer) {
            firstPlayerService.makeMove();
        } else {
            secondPlayerService.makeMove();
        }
        endTurn();
    }

    public void revertMove() {
        if (board.getMoveStack().isEmpty()) {
            return;
        }
        Move lastMove = board.getMoveStack().peekLast();
        assert lastMove != null;
        tileFinder.setPiece(lastMove.getStart(), lastMove.getPiece());
        tileFinder.setPiece(lastMove.getEnd(), lastMove.getKilledPiece());
        board.getMoveStack().pollLast();
    }

    public Player getPlayerByColor(FigureType color) {
        if (firstPlayer.getColor().equals(color)) return firstPlayer;
        return secondPlayer;
    }

    private void endTurn() {
        currentPlayer = (currentPlayer == firstPlayer)
                ? secondPlayer
                : firstPlayer;
        board.setCurrentPlayer(currentPlayer.getColor());
        finished = board.isFinished();
    }

    public boolean isFinished() {
        return finished;
    }

    public void printCurrentState() {
        printer.printField();
    }
}