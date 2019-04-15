package com.nationmc.game.games.skywars;

import com.nationmc.game.game.GameType;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class SkywarsMapLoader {


    /*public static void downloadMap(Player player) {
        BufferedInputStream in;
        FileOutputStream fout;
        try
        {
            in = new BufferedInputStream(new URL("http://www.mediafire.com/folder/53uetg62uxi7h/Skywars").openStream());
            fout = new FileOutputStream("Skywars_Map");
            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1)
            {
                fout.write(data, 0, count);
            }
            in.close();
            fout.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        createWorld(player);
    }*/

    public static void createWorld(World waitingWorld, GameType gameType)
    {
        if (gameType.equals(GameType.SKYWARS))
        {
            File source = new File("Skywars");
            File dest = new File("Skywars1");
            if (!dest.exists())
            {
                try
                {
                    FileUtils.forceMkdir(new File("Skywars1"));
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            try {
                FileUtils.copyDirectory(source, dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bukkit.createWorld(new WorldCreator("Skywars1"));
            World world = Bukkit.getWorld("Skywars1");
            for (Player p : waitingWorld.getPlayers())
            {
                p.teleport(new Location(world, world.getSpawnLocation().getX(), world.getSpawnLocation().getY(), world.getSpawnLocation().getZ()));
            }
        } else
        {
            throw new NullPointerException();
        }

    }
}