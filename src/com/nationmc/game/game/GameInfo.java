package com.nationmc.game.game;

public class GameInfo {
    private static GameType setGameType;
    private static boolean gameState;

    //check if the game is active
    public static boolean isGameActive()
    {
        return gameState;
    }

    public static void setGameState(boolean b)
    {
        gameState = b;

    }

    //get the game that is set
    public static GameType getGame()
    {
        return setGameType;
    }

    //setting the game to be started to stopped
    public static void setGame(GameType game)
    {
        setGameType = game;
    }
}
