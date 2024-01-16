package ru.vsu.cs.karmanova_v_v.model.player;

import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;

public abstract class Player {
    protected final String name;
    protected final FigureType color;

    private int kingsCount;

    public Player(String name, FigureType color) {
        this.name = name;
        this.color = color;
        this.kingsCount = 2;
    }
    public int getKingsCount() {
        return kingsCount;
    }
    public void setKingsCount(int kingsCount) {
        this.kingsCount = kingsCount;
    }

    public String getName() {
        return name;
    }

    public FigureType getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Player(" + "\"" + name + "\", " + color + ')';
    }
}
