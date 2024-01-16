package ru.vsu.cs.karmanova_v_v.model.figures;

import ru.vsu.cs.karmanova_v_v.model.player.Player;

public class King extends Piece {


    public King(Player player) {
        super("King", player);
    }

    @Override
    public char getCharValue() {
        return '♚';
    }
}
