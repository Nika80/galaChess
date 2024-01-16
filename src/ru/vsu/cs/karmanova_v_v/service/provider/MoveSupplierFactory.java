package ru.vsu.cs.karmanova_v_v.service.provider;

import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.figures.Bishop;
import ru.vsu.cs.karmanova_v_v.model.figures.King;
import ru.vsu.cs.karmanova_v_v.model.figures.Pawn;
import ru.vsu.cs.karmanova_v_v.model.figures.Rook;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;

import java.util.ArrayList;
import java.util.List;

public class MoveSupplierFactory {
    private static MoveSupplierFactory instance;

    public static MoveSupplierFactory getInstance() {
        if (instance == null) {
            instance = new MoveSupplierFactory();
        }
        return instance;
    }

    private MoveSupplierFactory(){
    }

    public MoveSupplier create(Board board, Cell tileWithPiece) {
        if (!tileWithPiece.isOccupied()) return null;
        if (tileWithPiece.getPiece().getClass() == Bishop.class) {
            return new BishopMoveSupplier(board, tileWithPiece);
        }
        if (tileWithPiece.getPiece().getClass() == King.class) {
            return new KingMoveSupplier(board, tileWithPiece);
        }
        if (tileWithPiece.getPiece().getClass() == Pawn.class) {
            return new PawnMoveSupplier(board, tileWithPiece);
        }
        if (tileWithPiece.getPiece().getClass() == Rook.class) {
            return new RookMoveSupplier(board, tileWithPiece);
        }
        return new MoveSupplier(board, tileWithPiece) {
            @Override
            public List<MoveVariant> getAvailableMoves() {
                return new ArrayList<>();
            }
        };
    }
}