package ru.vsu.cs.karmanova_v_v.presentation.command;

import ru.vsu.cs.karmanova_v_v.Game;

public class MakeMoveCommand implements ChessCommand {
    private final Game game;

    public MakeMoveCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.makeMove();
    }
}
