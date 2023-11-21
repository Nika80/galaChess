package com.example.galachess.Game;

import javafx.scene.control.Button;

public class CustomButton extends Button {
    private int row;
    private int col;

    public CustomButton(String text) {
        super(text);
    }

    public CustomButton() {
        super();
    }

    public void setRowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
