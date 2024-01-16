package ru.vsu.cs.karmanova_v_v.service.player;

import ru.vsu.cs.karmanova_v_v.model.board.Board;
import ru.vsu.cs.karmanova_v_v.model.player.Player;
import ru.vsu.cs.karmanova_v_v.service.move.MoveManager;
import ru.vsu.cs.karmanova_v_v.model.player.BotPlayer;
import ru.vsu.cs.karmanova_v_v.model.player.RealPlayer;

public class PlayerServiceFactory {
    private static PlayerServiceFactory instance;

    public static PlayerServiceFactory getInstance() {
        if (instance == null) {
            instance = new PlayerServiceFactory();
        }
        return instance;
    }
    private PlayerServiceFactory() {
    }
    public PlayerService createPlayerService(Board board, MoveManager moveManager, Player player) {
        if (player.getClass() == BotPlayer.class) {
            return new BotPlayerService(moveManager, board, player);
        }
        if (player.getClass() == RealPlayer.class) {
            return new RealPlayerService(moveManager, board, player);
        }
        return () -> {
        };
    }
}
