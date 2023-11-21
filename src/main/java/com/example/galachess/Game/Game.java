package com.example.galachess.Game;

import com.example.galachess.Figures.Figure;
import com.example.galachess.Figures.FigureType;
import javafx.scene.paint.Color;

import java.util.Arrays;

public class Game {
    private Figure[][] field = new Figure[10][10];
    private Figure[][] checkField = new Figure[10][10];
    private GameStatus status = new GameStatus(Status.FREE);

    public Game() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if ((i == 0 && j == 0) || (i == 0 && j == 9) || (i == 9 && j == 0) || (i == 9 && j == 9)) {
                    this.field[i][j] = new Figure(FigureType.KING, i == 0? Color.BLACK: Color.WHITE);
                    this.checkField[i][j] = new Figure(FigureType.KING, i == 0? Color.BLACK: Color.WHITE);
                    continue;
                }
                this.field[i][j] = new Figure(FigureType.SPACE);
                this.checkField[i][j] = new Figure(FigureType.SPACE);
            }
        }
    }

    public Figure getCell(int row, int col) {
        return this.field[row][col];
    }

    public int move(int row, int col) {
        if (this.status.getStatus() == Status.FREE) {
            if (this.field[row][col].getType() == FigureType.SPACE) {
                return 0;
            }
            this.status.setStatus(Status.BUSY);
            this.status.setCol(col);
            this.status.setRow(row);
            return 0;
        } else {
            switch (this.field[status.getRow()][status.getCol()].getType()) {
                case KING -> {
                    return moveKing(this.status.getRow(), this.status.getCol(), row, col);
                }
                default -> {
                    return 0;
                }
            }
        }
    }

    private int moveKing(int startRow, int startCol, int endRow, int endCol) {
        if ((Math.abs(startCol - endCol) == 1 && Math.abs(startRow - endRow) <= 1) || (Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) <= 1) || (3 < startCol && startCol < 6 && 3 < startRow && startRow < 6 && this.checkField[endRow][endCol].getType() == FigureType.SPACE)) {
            Figure temp = this.field[startRow][startCol];
            this.field[endRow][endCol] = new Figure(temp.getType(), temp.getColor());
            this.field[startRow][startCol] = new Figure(FigureType.SPACE);
            this.status = new GameStatus(Status.FREE);
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        for (int i = 0; i < this.field.length; i++) {
            for (int j = 0; j < this.field.length; j++) {
                System.out.println(this.field[i][j]);
            }
        }
        return "";
    }

    public Figure[][] getField() {
        return field;
    }
}
