package ru.vsu.cs.karmanova_v_v.service.provider;

import ru.vsu.cs.karmanova_v_v.model.board.*;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;

import java.util.ArrayList;
import java.util.List;

public class RookMoveSupplier extends MoveSupplier {
    public RookMoveSupplier(Board board, Cell tile) {
        super(board, tile);
    }

    @Override
    public List<MoveVariant> getAvailableMoves() {
        List<MoveVariant> result = new ArrayList<>();
        Cell startTile = tile;

        int swipe = (startTile.getCellType() == CellType.YELLOW_DARK || startTile.getCellType() == CellType.YELLOW_LIGHT) ? 1 : 0;

        propagateInDirection(result, CellDirection.UP + swipe, startTile , false, 0);
        propagateInDirection(result, CellDirection.RIGHT + swipe, startTile, false, 0);
        propagateInDirection(result, CellDirection.DOWN + swipe, startTile, false, 0);
        propagateInDirection(result, CellDirection.LEFT + swipe, startTile, false, 0);

        return result;
    }

    private void propagateInDirection(List<MoveVariant> variants, int direction, Cell prevTile, boolean changeType, int stepCount) {
        Cell sideTile = prevTile.getNeighbours().get(direction);


        if (sideTile == null) return;
        if (sideTile.isOccupied() && sideTile.getPiece().getPlayer() == board.getCurrentPlayer()) return;

        boolean quadWarning = true;

        char sideX = sideTile.getCoordinate().getX();
        int sideY = sideTile.getCoordinate().getY();
        if ((sideY == 5 || sideY == 6) && (sideX == 'e' || sideX == 'f')) {
            quadWarning = false;
        }



        CellType prevType = prevTile.getCellType();
        CellType currentType = sideTile.getCellType();

        int swipe = (sideTile.getCellType() == CellType.YELLOW_DARK || sideTile.getCellType() == CellType.YELLOW_LIGHT) ? 1 : 0;


        if (!sideTile.isOccupied()) {
            if (currentType.isOpposite(prevType)) {
                if (!changeType) {
                    if (stepCount == 0) {
                        if (quadWarning) variants.add(getMoveVariantByCoordinates(tile.getCoordinate(), sideTile.getCoordinate()));

                        propagateInDirection(variants, CellDirection.UP + swipe, sideTile, true, 1);
                        propagateInDirection(variants, CellDirection.RIGHT + swipe, sideTile, true, 1);
                        propagateInDirection(variants, CellDirection.DOWN + swipe, sideTile, true, 1);
                        propagateInDirection(variants, CellDirection.LEFT + swipe, sideTile, true, 1);
                    } else {
                        if (quadWarning) variants.add(getMoveVariantByCoordinates(tile.getCoordinate(), sideTile.getCoordinate()));
                        addTileInDirection(variants, sideTile, CellDirection.UP + swipe);
                        addTileInDirection(variants, sideTile, CellDirection.RIGHT + swipe);
                        addTileInDirection(variants, sideTile, CellDirection.DOWN + swipe);
                        addTileInDirection(variants, sideTile, CellDirection.LEFT + swipe);
                    }
                }
            } else {
                stepCount++;
                if (quadWarning) variants.add(getMoveVariantByCoordinates(tile.getCoordinate(), sideTile.getCoordinate()));

                propagateInDirection(variants, direction, sideTile, changeType, stepCount);
            }
        }
    }

    private void addTileInDirection(List<MoveVariant> variants, Cell prevCell, int direction) {
        Cell sideTile = prevCell.getNeighbours().get(direction);

        if (sideTile == null) return;
        if (sideTile.isOccupied() && sideTile.getPiece().getPlayer() == board.getCurrentPlayer()) return;

        boolean quadWarning = true;

        char sideX = sideTile.getCoordinate().getX();
        int sideY = sideTile.getCoordinate().getY();
        if ((sideY == 5 || sideY == 6) && (sideX == 'e' || sideX == 'f')) {
            quadWarning = false;
        }


        CellType prevType = prevCell.getCellType();
        CellType currentType = sideTile.getCellType();

        if (currentType.isOpposite(prevType)) {
            return;
        }

        Coordinate start = tile.getCoordinate();
        Coordinate end = sideTile.getCoordinate();
        MoveVariant variant = getMoveVariantByCoordinates(start, end);
        if (quadWarning) variants.add(variant);
    }
}