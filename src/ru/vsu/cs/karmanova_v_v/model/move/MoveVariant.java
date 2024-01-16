package ru.vsu.cs.karmanova_v_v.model.move;

public class MoveVariant {
    private final int x;
    private final int y;

    public MoveVariant(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}