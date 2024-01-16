package ru.vsu.cs.karmanova_v_v.service.provider;

import ru.vsu.cs.karmanova_v_v.model.board.CellDirection;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;

import java.util.ArrayList;
import java.util.List;

public class KingMoveSupplier extends MoveSupplier {
    public KingMoveSupplier(Board board, Cell tile) {
        super(board, tile);
    }

    private void addTileInDirection(List<MoveVariant> variants, int direction) {
        Cell sideTile = tile.getNeighbours().get(direction);
        if (sideTile == null) return;
        if (sideTile.isOccupied() && sideTile.getPiece().getPlayer() == board.getCurrentPlayer()) return;
        Coordinate start = tile.getCoordinate();
        Coordinate end = sideTile.getCoordinate();
        MoveVariant variant = getMoveVariantByCoordinates(start, end);
        variants.add(variant);
    }

    @Override
    public List<MoveVariant> getAvailableMoves() {
        List<MoveVariant> variants = new ArrayList<>();
        addTileInDirection(variants, CellDirection.UP);
        addTileInDirection(variants, CellDirection.RIGHT_UP);
        addTileInDirection(variants, CellDirection.RIGHT);
        addTileInDirection(variants, CellDirection.RIGHT_DOWN);
        addTileInDirection(variants, CellDirection.DOWN);
        addTileInDirection(variants, CellDirection.LEFT_DOWN);
        addTileInDirection(variants, CellDirection.LEFT);
        addTileInDirection(variants, CellDirection.LEFT_UP);

        if (isInMiddle()) {
            addTeleportationMoves(variants);
        }

        return variants;
    }


    private boolean isInMiddle() {
        Coordinate coord = tile.getCoordinate();
        char x = coord.getX();
        int y = coord.getY();

        return x >= 'e' && x <= 'f' && y >= 5 && y <= 6;
    }


    private void addTeleportationMoves(List<MoveVariant> moves) {
        for (int i = 1; i <= 10; i++) {
            for (char j = 'a'; j <= 'j'; j++) {
                Coordinate key = new Coordinate(j, i);
                Cell cell = board.getCell(key);
                if (!(cell.isInitial()) && !(cell.isOccupied())) {
                    moves.add(getMoveVariantByCoordinates(tile.getCoordinate(),key));
                }
            }
        }
    }



}
