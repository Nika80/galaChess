package ru.vsu.cs.karmanova_v_v;

import ru.vsu.cs.karmanova_v_v.presentation.command.*;
import ru.vsu.cs.karmanova_v_v.model.figures.FigureType;
import ru.vsu.cs.karmanova_v_v.model.config.GameConfig;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.model.player.BotPlayer;
import ru.vsu.cs.karmanova_v_v.model.player.RealPlayer;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static GameConfig obtainConfig() {
        Player firstPlayer = getPlayer("Первый", FigureType.WHITE);
        Player secondPlayer = getPlayer("Второй", FigureType.BLACK);
        return new GameConfig(firstPlayer, secondPlayer, getStartPlayerColor());
    }

    private static Player getPlayer(String playerNumber, FigureType color) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(playerNumber + " игрок будет (1 - бот, 2 - человек): ");
        int playerType = scanner.nextInt();
        System.out.print("Имя игрока: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        if (playerType == 1) {
            return new BotPlayer(name, color);
        }
        return new RealPlayer(name, color);
    }
    private static FigureType getStartPlayerColor() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Какой игрок должен начать?(По умолчанию - Белые) (Б/Ч): ");
        String colorString = scanner.nextLine();
        if (Objects.equals(colorString, "Ч")) {
            return FigureType.BLACK;
        }
        return FigureType.WHITE;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GameConfig config = obtainConfig();

        Game game = new Game(config);

        ChessCommand makeMoveCommand = new MakeMoveCommand(game);
        ChessCommand printBoardCommand = new PrintBoardCommand(game);
        ChessCommand revertMoveCommand = new RevertMoveCommand(game);
        CommandInvoker invoker = new CommandInvoker (makeMoveCommand, printBoardCommand, revertMoveCommand);

        System.out.println("Справка по управлению");
        System.out.println("1 - напечатать поле");
        System.out.println("2 - сделать ход");
        System.out.println("3 - откатить ход (если ходов нет - ничего не делать)");


        while (!game.isFinished()) {
            System.out.print("Введите команду: ");
            int command;
            try {
                command = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                command = 0;
            }

            switch (command) {
                case 0 -> System.out.println("Неверный ввод команды!");
                case 1 -> invoker.printBoard();
                case 2 -> invoker.makeMove();
                case 3 -> invoker.revertMove();
            }
        }
        scanner.close();
        System.out.println("Игра окончена!");
    }
}