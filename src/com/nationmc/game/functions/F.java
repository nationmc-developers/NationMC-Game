package com.nationmc.game.functions;

import com.nationmc.game.game.GameType;
import com.nationmc.game.game.GameTypeConverter;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;

public class F {
    public static String getdividerWithSpace()
    {
        return "> ";
    }
    public static ChatColor getBodyColor()
    {
        return ChatColor.GRAY;
    }
    public static ChatColor getHeaderColor()
    {
        return ChatColor.BLUE;
    }
    public static ChatColor getValueColor()
    {
        return ChatColor.GOLD;
    }
    public static ChatColor getErrorColor() { return ChatColor.RED; }
    public static String header(String header)
    {
        return getHeaderColor() + header + getdividerWithSpace();
    }
    public  static String getGameAnnounceColor()
    {
        return ChatColor.AQUA + "" + ChatColor.BOLD;
    }
    public static String body(String body)
    {
        return getBodyColor() + body;
    }
    public static String getAdminHeader(String header)
    {
        return ChatColor.translateAlternateColorCodes('&', "&cAdmin &f (" + (header) + ")&7: ");
    }
    public static String getGamePrefix()
    {
        return "Game";
    }





    public static String main(String header, String body)
    {
        return header(header) + body(body);
    }
    public static String error(String header, String error)
    {
        return header(header) + getErrorColor() + error;
    }
    public static String value(String value, String body)
    {
        if (body.equalsIgnoreCase(".") || body.startsWith("'"))
        {
            return " " + getValueColor() + value + getBodyColor() + body;
        } else
        {
            return " " + getValueColor() + value + " " + getBodyColor() + body;
        }

    }
    public static String miniAnnouncement(String header, String body)
    {
        return header(header) + ChatColor.AQUA + body;
    }
    public static String helpListFormat(Object object, Object object2)
    {
        return header("") + ChatColor.GOLD + object + " " + ChatColor.GRAY + object2 + ".";
    }
    public static String eventModule(String module, boolean state)
    {
        if (state)
        {
            return header("Settings") + ChatColor.WHITE + module + body(": ") + ChatColor.GREEN + "True" + body(".");
        } else
        {
            return header("Settings") + ChatColor.WHITE + module + body(": ") + ChatColor.RED + "False" + body(".");
        }

    }
    public static String adminAction(String header, String body)
    {
        return getAdminHeader(header) + body;
    }

    public static String help(String commandName, List<String> command, List<String> instructions)
    {
        StringBuilder s = new StringBuilder();
        s.append(main("Help", "Listing Commands for: " + ChatColor.GOLD + commandName + ChatColor.GRAY + ". \n"));
        for (int i = 0; i < command.size(); i++)
        {
            s.append(helpListFormat(command.toArray()[i], instructions.toArray()[i]) + "\n");
        }
        return s.toString();
    }

    public static void gameStateChangeAnnouncement(GameType gameType, World world)
    {
        pingWorld(world);
        for (Player p : world.getPlayers())
        {
            p.sendMessage(getGameAnnounceColor() + "The game has been set to " + GameTypeConverter.getGameName(gameType) + ".");
        }
    }

    public static void gameStartStop(boolean b, World world)
    {
        pingWorld(world);
        for (Player p : world.getPlayers())
        {
            if (b)
            {
                p.sendMessage(getGameAnnounceColor() + "The game has been start.");
            } else
            {
                p.sendMessage(getGameAnnounceColor() + "The game has been stopped");
            }
        }
    }



    public static void ping(Player p)
    {
        p.playSound(p.getLocation(), Sound.NOTE_PLING, 2F, 1F);
    }
    public static void pingAll()
    {
        for(Player p : Bukkit.getOnlinePlayers())
        {
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 2F, 1F);
        }
    }
    public static void pingWorld(World w)
    {
        for (Player p : w.getPlayers())
        {
            p.playSound(p.getLocation(), Sound.NOTE_PLING, 2F, 1F);
        }
    }
}
