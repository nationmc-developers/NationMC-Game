package com.nationmc.game;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        System.out.println("NationMC Game Disabling...");
    }

    @Override
    public void onEnable() {



        System.out.println("NationMC Game Enabling...");
    }
}