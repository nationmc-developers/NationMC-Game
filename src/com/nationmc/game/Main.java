package com.nationmc.game;

import com.nationmc.game.command.game_management.GameCommandReciever;
import com.nationmc.game.game.GameType;
import com.nationmc.game.game.GameTypeConverter;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        System.out.println("NationMC Game Disabling...");
    }
    @Override
    public void onEnable() {
        getCommand("game").setExecutor(new GameCommandReciever());
        System.out.println("NationMC Game Enabling...");
        GameTypeConverter.toText.put(GameType.SKYWARS, "Skywars");
    }
}