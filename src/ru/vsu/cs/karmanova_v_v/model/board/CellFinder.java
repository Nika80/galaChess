package ru.vsu.cs.karmanova_v_v.model.board;

import ru.vsu.cs.karmanova_v_v.model.figures.Piece;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;

import java.util.ArrayList;
import java.util.List;

public class CellFinder {
    private final Board board;
    public CellFinder(Board board) {
        this.board = board;
    }


    public Cell getTile(Coordinate coordinate) {
	    return board.getCell(coordinate);
    }

    public Piece getPiece(Coordinate coordinate) {
        Cell tile = board.getCell(coordinate);
        if (tile == null) return null;
        if (tile.getPiece() == null) return null;
        return tile.getPiece();
    }

    public void setPiece(Coordinate coordinate, Piece piece) {
        board.getCell(coordinate).setPiece(piece);
    }


    public List<Coordinate> getPiecesCoordinates(FigureType color) {
        List<Coordinate> locations = new ArrayList<>();
        for (char x = 'a'; x <= 'j'; x++) {
            for (int y = 1; y <= 10; y++) {
                Cell tile = getTile(new Coordinate(x, y));
                if (tile.isOccupied() && tile.getPiece().getType() == color) {
                    locations.add(new Coordinate(x, y));
                }
            }
        }
        return locations;
    }
}