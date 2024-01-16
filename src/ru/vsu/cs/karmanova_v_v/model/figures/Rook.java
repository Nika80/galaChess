package ru.vsu.cs.karmanova_v_v.model.figures;

import ru.vsu.cs.karmanova_v_v.model.player.Player;

public class Rook extends Piece {


    public Rook(Player player) {
        super("Rook", player);
    }


    @Override
    public char getCharValue() {
        return '♜';
    }
}