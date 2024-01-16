package ru.vsu.cs.karmanova_v_v.service.provider;

import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;

import java.util.List;

public abstract class MoveSupplier {
    protected final Cell tile;
    protected final Board board;
    public MoveSupplier(Board board, Cell tile) {
        this.tile = tile;
        this.board = board;
    }
    public Coordinate getCoordinate() {
        return tile.getCoordinate();
    }

    protected MoveVariant getMoveVariantByCoordinates(Coordinate start, Coordinate end){
        int moveX = end.getX() - start.getX();
        int moveY = end.getY() - start.getY();
        return new MoveVariant(moveX, moveY);
    }

    public abstract List<MoveVariant> getAvailableMoves();
}
