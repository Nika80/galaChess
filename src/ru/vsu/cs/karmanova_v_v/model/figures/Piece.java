package ru.vsu.cs.karmanova_v_v.model.figures;

import ru.vsu.cs.karmanova_v_v.model.player.Player;

public abstract class Piece {

    private final String name;
    private final Player player;

    public Piece(String name, Player player) {
        this.name = name;
        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }

    public FigureType getType() {
        return player.getColor();
    }

    public String getName() {
        return name;
    }

    public char getCharValue() {
        return 'a';
    }
}
