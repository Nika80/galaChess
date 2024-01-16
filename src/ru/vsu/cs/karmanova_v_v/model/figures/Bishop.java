package ru.vsu.cs.karmanova_v_v.model.figures;

import ru.vsu.cs.karmanova_v_v.model.player.Player;

public class Bishop extends Piece {


    public Bishop(Player player) {
        super("Bishop", player);
    }


    @Override
    public char getCharValue() {
        return '♝';
    }
}
