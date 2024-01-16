package ru.vsu.cs.karmanova_v_v.presentation.command;

import ru.vsu.cs.karmanova_v_v.Game;

public class RevertMoveCommand implements ChessCommand {
    private final Game game;

    public RevertMoveCommand(Game game) {
        this.game = game;
    }

    @Override
    public void execute() {
        game.revertMove();
    }
}
