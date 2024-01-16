package ru.vsu.cs.karmanova_v_v.service.player;

import ru.vsu.cs.karmanova_v_v.presentation.util.InputHandler;
import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.CellFinder;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.service.move.MoveManager;
import ru.vsu.cs.karmanova_v_v.service.provider.MoveSupplierFactory;

import java.util.List;
import java.util.Scanner;

public class RealPlayerService implements PlayerService {
	protected final Player player;
	protected final Board board;
	protected final CellFinder tileFinder;
	protected final MoveManager moveManager;
	private final Scanner scanner;
    private final InputHandler handler;
    public RealPlayerService(MoveManager moveManager, Board board, Player player) {
	    this.moveManager = moveManager;
	    this.player = player;
	    this.board = board;
	    this.tileFinder = new CellFinder(board);
	    scanner = new Scanner(System.in);
        handler = new InputHandler();
    }
    @Override
    public void makeMove() {
        while (true) {
            System.out.print("Введите ход (например. A2-A3): ");
            String input = scanner.nextLine();

            if (!handler.isValid(input)) {
                System.out.println("Неверный ввод!");
                System.out.println("Верный ввод в формате: A2-A3");
            } else {
                Coordinate from = handler.getFrom(input);
                Coordinate to = handler.getTo(input);

                Cell fromTile = tileFinder.getTile(from);
                if (fromTile.isOccupied()) {
                    List<MoveVariant> variants = MoveSupplierFactory.getInstance().create(board, fromTile).getAvailableMoves();
                    List<Coordinate> coordinates = variants.stream()
                            .map(it -> {
                                int fromX = fromTile.getCoordinate().getX();
                                int fromY = fromTile.getCoordinate().getY();
                                int moveX = it.getX();
                                int moveY = it.getY();
                                int toX = fromX + moveX;
                                int toY = fromY + moveY;
                                return new Coordinate((char) toX, toY);
                            }).toList();

                    if (!coordinates.contains(to) || (fromTile.getPiece().getPlayer() != board.getCurrentPlayer())) {
//                    for (MoveVariant a: variants) {
//                        System.out.println(a);
//                    }
                        System.out.println("Недопустимый ход!");
                        makeMove();
                    } else {
                        moveManager.playMove(from, to);
                    }
                } else {
                    System.out.println("Клетка без фигуры!");
                    makeMove();
                }
                return;
            }
        }

    }

}