package ru.vsu.cs.karmanova_v_v.model.move;

import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.figures.Piece;

public class Move {
    private final Coordinate start;
    private final Coordinate end;
    private final Piece killedPiece;
    private final Piece piece;
    public Move(Coordinate start,  Coordinate end, Piece piece, Piece killedPiece) {
        this.start = start;
        this.end = end;
        this.piece = piece;
        this.killedPiece = killedPiece;
    }

    public Piece getPiece() {
        return piece;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }
}