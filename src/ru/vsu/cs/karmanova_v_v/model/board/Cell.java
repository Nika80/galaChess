package ru.vsu.cs.karmanova_v_v.model.board;

import ru.vsu.cs.karmanova_v_v.model.figures.Piece;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final Coordinate coordinate;
    private final List<Cell> connections;
    private Piece piece;
    private final CellType cellType;

    private boolean isInitial;

    private final boolean isHighlighted;

    public boolean isHighlighted() {
        return isHighlighted;
    }

    public boolean isInitial() {
        return isInitial;
    }

    public void setInitial(boolean initial) {
        isInitial = initial;
    }

    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
        connections = new ArrayList<>();
        isInitial = false;
        isHighlighted = false;
        piece = null;
        this.cellType = calculateCellType();
    }

    private CellType calculateCellType() {
        char x = coordinate.getX();
        int y = coordinate.getY();

        // Проверка принадлежности к средней зоне
        boolean isInMiddleZone = (x >= 'e' && x <= 'f') || (y >= 5 && y <= 6);

        boolean contrast = (x - 'a' + 1 + y) % 2 == 1;

        if (isInMiddleZone && contrast) {
            return CellType.YELLOW_LIGHT;
        } else if (isInMiddleZone) {
            return CellType.YELLOW_DARK;
        } else if (contrast) {
            return CellType.ORANGE_LIGHT;
        } else {
            return CellType.ORANGE_DARK;
        }
    }

    public void addConnection(Cell cell) {
        connections.add(cell);
    }

    public List<Cell> getNeighbours() {
        return connections;
    }

    public boolean isOccupied() {
        return getPiece() != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void empty() {
        piece = null;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }
}
