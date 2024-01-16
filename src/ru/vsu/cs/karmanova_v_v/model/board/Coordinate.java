package ru.vsu.cs.karmanova_v_v.model.board;

import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;

import java.util.Objects;

public class Coordinate {
    private final char x;

    private final int y;

    public Coordinate(char x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate getSum(MoveVariant other) {
        char newX = (char) (this.x + other.getX());
        int newY = this.y + other.getY();
        return new Coordinate(newX, newY);
    }


    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Coordinate clone() {
        try {
            return (Coordinate) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return getX() == that.getX() && getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }


    @Override
    public String toString() {
        return x +""+ y;
    }

    public boolean isInCentralSquare() {
        return (this.x == 'e' || this.x == 'f') && (this.y == 5 || this.y == 6);
    }

}
