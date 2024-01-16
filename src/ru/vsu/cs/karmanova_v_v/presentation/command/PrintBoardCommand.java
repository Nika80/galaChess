package ru.vsu.cs.karmanova_v_v.presentation.command;

import ru.vsu.cs.karmanova_v_v.Game;

public class PrintBoardCommand implements ChessCommand {
    private final Game game;

    public PrintBoardCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.printCurrentState();
    }
}
