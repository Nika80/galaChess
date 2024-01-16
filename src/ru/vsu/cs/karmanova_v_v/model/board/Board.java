package ru.vsu.cs.karmanova_v_v.model.board;

import ru.vsu.cs.karmanova_v_v.model.figures.*;
import ru.vsu.cs.karmanova_v_v.model.move.Move;
import ru.vsu.cs.karmanova_v_v.model.player.Player;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Board {
    public static final int BOARD_SIDE = 10;
    public final Map<Coordinate, Cell> cells;

    private Player currentPlayer;
    private final Player firstPlayer;
    private final Player secondPlayer;
	private boolean isFinished;

    private final Deque<Move> moveStack;

    public Deque<Move> getMoveStack() {
        return moveStack;
    }

    public Board(Player startPlayer, Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.currentPlayer = startPlayer;
        this.moveStack = new LinkedList<>();
        this.isFinished = false;
	    cells = new HashMap<>();
        initializeGraph();
        placeInitialPieces(firstPlayer, secondPlayer, startPlayer.getColor());
    }


    public Cell getCell(Coordinate coord) {
        return cells.get(coord);
    }

    private void initializeGraph() {
        for (int i = 1; i <= BOARD_SIDE; i++) {
            for (char j = 'a'; j <= 'j'; j++) {
                Coordinate key = new Coordinate(j, i);
                Cell vertex = new Cell(key);
                cells.put(key, vertex);
            }
        }

        for (int i = 1; i <= BOARD_SIDE; i++) {
            for (char j = 'a'; j <= 'j'; j++) {
                Coordinate key = new Coordinate(j, i);
                Cell vertex = cells.get(key);

                addEdge(vertex, j, i + 1);
                addEdge(vertex, (char) (j + 1), i + 1);
                addEdge(vertex, (char) (j + 1), i);
                addEdge(vertex, (char) (j + 1), i - 1);
                addEdge(vertex, j, i - 1);
                addEdge(vertex, (char) (j - 1), i - 1);
                addEdge(vertex, (char) (j - 1), i);
                addEdge(vertex, (char) (j - 1), i + 1);
            }
        }
    }

    private void addEdge(Cell from, char toX, int toY) {
        if (toX >= 'a' && toX <= 'j' && toY >= 1 && toY <= BOARD_SIDE) {
            Coordinate toKey = new Coordinate(toX, toY);
            Cell toVertex = cells.get(toKey);
            from.addConnection(toVertex);
        } else {
            from.addConnection(null);
        }
    }

    private void addPiece(char file, int rank, Piece piece) {
        Coordinate key = new Coordinate(file, rank);
        Cell cell = cells.get(key);
        cell.setInitial(true);
        cell.setPiece(piece);
    }

    private void placeInitialPieces(Player firstPlayer,
                                    Player secondPlayer, FigureType firstPlayerColor) {

        Player white;
        Player black;
        if (firstPlayerColor == FigureType.WHITE) {
            white = firstPlayer;
            black = secondPlayer;
        } else {
            white = secondPlayer;
            black = firstPlayer;
        }



        addPiece('a', 10, new King(black));

        addPiece('a', 9, new Rook(black));
        addPiece('b', 10, new Rook(black));

        addPiece('a', 8, new Bishop(black));
        addPiece('b', 9, new Bishop(black));
        addPiece('c', 10, new Bishop(black));

        addPiece('a', 7, new Pawn(black));
        addPiece('b', 8, new Pawn(black));
        addPiece('c', 9, new Pawn(black));
        addPiece('d', 10, new Pawn(black));


        addPiece('j', 10, new King(black));

        addPiece('j', 9, new Bishop(black));
        addPiece('i', 10, new Bishop(black));

        addPiece('j', 8, new Rook(black));
        addPiece('i', 9, new Rook(black));
        addPiece('h', 10, new Rook(black));

        addPiece('j', 7, new Pawn(black));
        addPiece('i', 8, new Pawn(black));
        addPiece('h', 9, new Pawn(black));
        addPiece('g', 10, new Pawn(black));


        addPiece('a', 1, new King(white));

        addPiece('a', 2, new Bishop(white));
        addPiece('b', 1, new Bishop(white));

        addPiece('a', 3, new Rook(white));
        addPiece('b', 2, new Rook(white));
        addPiece('c', 1, new Rook(white));

        addPiece('a', 4, new Pawn(white));
        addPiece('b', 3, new Pawn(white));
        addPiece('c', 2, new Pawn(white));
        addPiece('d', 1, new Pawn(white));


        addPiece('j', 1, new King(white));

        addPiece('j', 2, new Rook(white));
        addPiece('i', 1, new Rook(white));

        addPiece('j', 3, new Bishop(white));
        addPiece('i', 2, new Bishop(white));
        addPiece('h', 1, new Bishop(white));

        addPiece('j', 4, new Pawn(white));
        addPiece('i', 3, new Pawn(white));
        addPiece('h', 2, new Pawn(white));
        addPiece('g', 1, new Pawn(white));


    }

    public void setCurrentPlayer(FigureType newPlayer) {
        currentPlayer = getPlayerByColor(newPlayer);
    }

    public Player getPlayerByColor(FigureType color) {
        if (firstPlayer.getColor().equals(color)) return firstPlayer;
        return secondPlayer;
    }


    public boolean isFinished() {
        return isFinished;
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
