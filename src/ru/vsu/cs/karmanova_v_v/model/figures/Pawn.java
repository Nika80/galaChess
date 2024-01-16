package ru.vsu.cs.karmanova_v_v.model.figures;

import ru.vsu.cs.karmanova_v_v.model.player.Player;

public class Pawn extends Piece {


    public Pawn(Player player) {
        super("Pawn", player);
    }


    @Override
    public char getCharValue() {
        return '♟';
    }
}
