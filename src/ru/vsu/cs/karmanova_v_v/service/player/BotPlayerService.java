package ru.vsu.cs.karmanova_v_v.service.player;

import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.board.Cell;
import ru.vsu.cs.karmanova_v_v.model.board.CellFinder;
import ru.vsu.cs.karmanova_v_v.model.board.Coordinate;
import ru.vsu.cs.karmanova_v_v.model.move.MoveVariant;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.service.move.MoveManager;
import ru.vsu.cs.karmanova_v_v.service.provider.MoveSupplier;
import ru.vsu.cs.karmanova_v_v.service.provider.MoveSupplierFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class BotPlayerService implements PlayerService {
	protected final Player player;
	protected final Board board;
	protected final CellFinder tileFinder;
	protected final MoveManager moveManager;
	private final Random random;
    public BotPlayerService(MoveManager moveManager, Board board, Player player){
	    this.moveManager = moveManager;
	    this.player = player;
	    this.board = board;
	    this.tileFinder = new CellFinder(board);
	    this.random = new Random();
    }
    @Override
    public void makeMove() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

        Cell tile = getTileWithPieceWithMoves();
        MoveSupplier provider = MoveSupplierFactory.getInstance().create(board, tile);
        List<MoveVariant> moves = provider.getAvailableMoves();
        MoveVariant moveVariant = moves.get(random.nextInt(0, moves.size()));
        Coordinate start = tile.getCoordinate();
        Coordinate end = getEndCoordinate(start, moveVariant);
        moveManager.playMove(start, end);
    }

    private Cell getTileWithPieceWithMoves() {
        List<Coordinate> coordinates = tileFinder.getPiecesCoordinates(player.getColor());
        AtomicReference<Cell> result = new AtomicReference<>();
        coordinates.forEach(coordinate -> {
            Cell tile = tileFinder.getTile(coordinate);
            if (!MoveSupplierFactory.getInstance().create(board, tile).getAvailableMoves().isEmpty()) {
                result.set(tile);
            }
        });
        return result.get();
    }

	protected Coordinate getEndCoordinate(Coordinate start, MoveVariant move) {
	    return new Coordinate((char) (start.getX() + move.getX()),
	            start.getY() + move.getY());
	}
}