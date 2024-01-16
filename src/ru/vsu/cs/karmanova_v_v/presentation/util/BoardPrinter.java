package ru.vsu.cs.karmanova_v_v.presentation.util;

import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;

public class BoardPrinter {

    private final Board board;

    public BoardPrinter(Board board) {
        this.board = board;
    }

    public void printField() {
        System.out.print("   ");
        for (char i = 'A'; i <= 'J'; i++) {
            System.out.printf("%s " + " ", i);
        }
        System.out.println();

        for (int y = 10; y >= 1; y--) {
            System.out.print(y + " " + (y == 10 ? "" : " "));
            for (char x = 'a'; x <= 'j'; x++) {
                Coordinate key = new Coordinate(x, y);
                Cell type = board.cells.get(key);
                if (type.getPiece() == null) {
                    if (type.isHighlighted()) {
                        System.out.printf("%c[%s;%sm%s ", (char) 27, "38;2;255;5;255", type.getCellType().getCode(), "♜");
                    } else {
                        System.out.printf("%c[%s;%sm%s ", (char) 27, type.getCellType().getCodeFigure(), type.getCellType().getCode(), "♜");
                    }
                } else {
                    if (type.isHighlighted()) {
                        System.out.printf("%c[%s;%sm%s ", (char) 27, "38;2;255;5;255", type.getCellType().getCode(), "♜");
                    } else {
                        System.out.printf("%c[%s;%sm%s ", (char) 27, type.getPiece().getType().getCode(), type.getCellType().getCode(), type.getPiece().getCharValue());
                    }
                }
            }
            System.out.println((char) 27 + "[m");
        }
    }
}
