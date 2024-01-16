package ru.vsu.cs.karmanova_v_v.model.config;

import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.player.Player;

public class GameConfig {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private final FigureType firstPlayerColor;

    public GameConfig(Player firstPlayer, Player secondPlayer, FigureType firstPlayerColor) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.firstPlayerColor = firstPlayerColor;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public FigureType getFirstPlayerColor() {
        return firstPlayerColor;
    }
}