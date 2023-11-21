package com.example.galachess.Figures;

import javafx.scene.paint.Color;

public class Figure {
    private FigureType type;
    private Color color;

    public Figure(FigureType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public Figure(FigureType type) {
        this.type = type;
    }

    public FigureType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public String getStringColor() {
        if (color == Color.BLACK) {
            return "black";
        }
        return "white";
    }

    @Override
    public String toString() {
        return "" + type;
    }
}
