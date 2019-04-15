package com.nationmc.game.game;

import java.util.HashMap;
import java.util.Map;

public class GameTypeConverter {
    public static Map<GameType, String> toText = new HashMap<>();

    public static GameType getGameType(String toGameType)
    {

        if (toGameType.equalsIgnoreCase("skywars"))
        {
            return GameType.SKYWARS;
        } else
        {
            return null;
        }
    }

    public static String getGameName(GameType gameType)
    {
        return toText.get(gameType);
    }
}
