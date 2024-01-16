package ru.vsu.cs.karmanova_v_v.service.provider;

import ru.vsu.cs.karmanova_v_v.model.board.*;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;
import ru.vsu.cs.karmanova_v_v.service.move.CenterChecker;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveSupplier extends MoveSupplier {
    public PawnMoveSupplier(Board board, Cell tile) {
        super(board, tile);
        this.centerChecker = new CenterChecker(board);
    }

    private final CenterChecker centerChecker;

    @Override
    public List<MoveVariant> getAvailableMoves() {
        List<MoveVariant> variants = new ArrayList<>();

        int quarter = checkQuarter();
        switch (quarter) {
            case 0:
                addTileInAllDirections(variants);
                break;
            case 1:
                if (tile.getPiece().getType() == FigureType.BLACK) {
                    addOneOrTwoTiles(variants, tile, CellDirection.LEFT_DOWN);
                } else {
                    addTileInAllDirections(variants);
                }
                break;
            case 2:
                if (tile.getPiece().getType() == FigureType.BLACK) {
                    addOneOrTwoTiles(variants, tile, CellDirection.RIGHT_DOWN);
                } else {
                    addTileInAllDirections(variants);
                }
                break;
            case 3:
                if (tile.getPiece().getType() == FigureType.WHITE) {
                    addOneOrTwoTiles(variants, tile, CellDirection.RIGHT_UP);
                } else {
                    addTileInAllDirections(variants);
                }
                break;
            case 4:
                if (tile.getPiece().getType() == FigureType.WHITE) {
                    addOneOrTwoTiles(variants, tile, CellDirection.LEFT_UP);
                } else {
                    addTileInAllDirections(variants);
                }
                break;
        }
        return variants;
    }

    private void addOneOrTwoTiles(List<MoveVariant> variants, Cell startTile, int direction) {
        Cell sideTile = startTile.getNeighbours().get(direction);
        if (sideTile == null) return;
        if (sideTile.isOccupied() && sideTile.getPiece().getPlayer() == board.getCurrentPlayer()) return;
        Coordinate start = tile.getCoordinate();
        Coordinate end = sideTile.getCoordinate();
        MoveVariant variant = getMoveVariantByCoordinates(start, end);
        variants.add(variant);
        CellType type = sideTile.getCellType();

        if (type == CellType.ORANGE_DARK || type == CellType.ORANGE_LIGHT) {
            addOneOrTwoTiles(variants, sideTile, direction);
        }
    }

    private void addTileInAllDirections(List<MoveVariant> variants) {
        addTileInDirection(variants, CellDirection.UP);
        addTileInDirection(variants, CellDirection.RIGHT_UP);
        addTileInDirection(variants, CellDirection.RIGHT);
        addTileInDirection(variants, CellDirection.RIGHT_DOWN);
        addTileInDirection(variants, CellDirection.DOWN);
        addTileInDirection(variants, CellDirection.LEFT_DOWN);
        addTileInDirection(variants, CellDirection.LEFT);
        addTileInDirection(variants, CellDirection.LEFT_UP);
    }

    private void addTileInDirection(List<MoveVariant> variants, int direction) {
        Cell sideTile = tile.getNeighbours().get(direction);
        if (sideTile == null) return;
        if (sideTile.isOccupied() && sideTile.getPiece().getPlayer() == board.getCurrentPlayer()) return;


        Coordinate start = tile.getCoordinate();
        Coordinate end = sideTile.getCoordinate();
        MoveVariant variant = getMoveVariantByCoordinates(start, end);
        if (sideTile.getCoordinate().isInCentralSquare()) {
            if (centerChecker.hasPawnsOrGalasInCentralSquare(tile.getPiece().getType())) {
                variants.add(variant);
            }
        } else {
            variants.add(variant);
        }
    }


    private int checkQuarter() {
        Coordinate coord = tile.getCoordinate();
        char x = coord.getX();
        int y = coord.getY();

        if (x > 'f' && y > 6) {
            return 1;
        } else if (x < 'e' && y > 6) {
            return 2;
        } else if (x < 'e' && y < 5) {
            return 3;
        } else if (x > 'f' && y < 5) {
            return 4;
        } else {
            return 0;
        }
    }
}