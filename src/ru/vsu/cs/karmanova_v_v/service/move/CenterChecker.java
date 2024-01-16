package ru.vsu.cs.karmanova_v_v.service.move;

import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.figures.King;
import ru.vsu.cs.karmanova_v_v.model.figures.Pawn;

public class CenterChecker {
    private final Board board;

    public CenterChecker(Board board) {
        this.board = board;
    }

    public boolean hasPawnsOrGalasInCentralSquare(FigureType player) {
        for (int i = 4; i <= 5; i++) {
            for (char j = 'e'; j <= 'f'; j++) {
                Cell cell = board.getCell(new Coordinate(j, i));
                if (cell != null ) {
                    if (cell.getPiece() instanceof Pawn && cell.getPiece().getType() != player) {
                        return true;
                    } else if (cell.getPiece() instanceof King) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int countKingsInCentralSquare(FigureType player) {
        int kingCount = 0;
        for (int i = 5; i <= 6; i++) {
            for (char j = 'e'; j <= 'f'; j++) {
                Cell cell = board.getCell(new Coordinate(j, i));
                if (cell != null && cell.getPiece() instanceof King && cell.getPiece().getPlayer().getColor() == player) {
                    kingCount++;
                }
            }
        }
        return kingCount;
    }
}