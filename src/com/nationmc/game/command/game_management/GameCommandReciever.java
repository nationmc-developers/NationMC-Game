package com.nationmc.game.command.game_management;

import com.nationmc.game.functions.F;
import com.nationmc.game.game.GameInfo;
import com.nationmc.game.game.GameType;
import com.nationmc.game.game.GameTypeConverter;
import com.nationmc.game.games.skywars.SkywarsMapLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class GameCommandReciever implements CommandExecutor {
    Player p;
    String HELP;
    World WAITING_WORLD;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        WAITING_WORLD = Bukkit.getWorld("world");
        HELP = F.help("Game Command", Arrays.asList("/game start", "/game stop", "/game set <GameType game>"), Arrays.asList("Start Game", "Stop Game", "Set Game"));
        if (sender instanceof Player)
        {
            p = (Player) sender;
        }
        //game <start/stop/set> <GameType Game>
        if (args[0].equalsIgnoreCase("stop"))
        {
            if (GameInfo.isGameActive())
            {
                GameInfo.setGameState(false);
                F.gameStartStop(false, p.getWorld());
                for (Player player : p.getWorld().getPlayers())
                {

                    player.teleport(new Location(WAITING_WORLD, WAITING_WORLD.getSpawnLocation().getX(), WAITING_WORLD.getSpawnLocation().getY(), WAITING_WORLD.getSpawnLocation().getZ()));
                }
                p.getServer().getWorld(p.getWorld().getName()).getWorldFolder().delete();
            } else
            {
                p.sendMessage(F.error(F.getGamePrefix(), "There is no active game to stop."));
            }
        } else if (args[0].equalsIgnoreCase("start"))
        {
            if (GameInfo.isGameActive())
            {
                p.sendMessage(F.error(F.getGamePrefix(), "A game is already active."));
            } else
            {
                if (GameInfo.getGame() != null)
                {
                    GameInfo.setGameState(true);
                    F.gameStartStop(true, p.getWorld());
                    SkywarsMapLoader.createWorld(p.getWorld(), GameType.SKYWARS);
                } else
                {
                    p.sendMessage(F.error(F.getGamePrefix(), "There is no set game to start."));
                }
            }
        } else if (args[0].equalsIgnoreCase("set"))
        {
            if (args.length == 2)
            {
                GameType gameType = GameTypeConverter.getGameType(args[1]);
                if (gameType != null)
                {
                    GameInfo.setGame(gameType);
                    F.gameStateChangeAnnouncement(gameType, p.getWorld());
                } else
                {
                    p.sendMessage(F.helpListFormat("List of Game", gameType.getDeclaringClass().getEnumConstants()));
                }
            }
        } else
        {
            p.sendMessage(HELP);
        }
        return false;
    }
}
