package ru.vsu.cs.karmanova_v_v.presentation.command;

public class CommandInvoker {
    private final ChessCommand makeMoveCommand;
    private final ChessCommand printBoardCommand;
    private final ChessCommand revertMoveCommand;

    public CommandInvoker(ChessCommand makeMoveCommand, ChessCommand printBoardCommand,
                          ChessCommand revertMoveCommand) {
        this.makeMoveCommand = makeMoveCommand;
        this.printBoardCommand = printBoardCommand;
        this.revertMoveCommand = revertMoveCommand;
    }

    public void printBoard() {
        printBoardCommand.execute();
    }

    public void makeMove() {
        makeMoveCommand.execute();
        printBoardCommand.execute();
    }

    public void revertMove() {
        revertMoveCommand.execute();
        printBoardCommand.execute();
    }
}
