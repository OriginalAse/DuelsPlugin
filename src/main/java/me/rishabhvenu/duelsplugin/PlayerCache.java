package me.rishabhvenu.duelsplugin;

import org.bukkit.entity.Player;

public class PlayerCache {
    private final Player player;
    private int wins;
    private int losses;
    private int kills;
    private int deaths;
    private int winstreak;

    public PlayerCache(Player player) {
        this.player = player;
        Database database = DuelsPlugin.getDatabase();

        if (!DuelsPlugin.getDatabase().playerExists(player)) {
            database.addPlayer(player);
        }

        wins = database.getWins(player);
        losses = database.getLosses(player);
        kills = database.getKills(player);
        deaths = database.getDeaths(player);
        winstreak = database.getWinStreak(player);
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getWinstreak() {
        return winstreak;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setWinstreak(int winstreak) {
        this.winstreak = winstreak;
    }

    public void save() {
        Database database = DuelsPlugin.getDatabase();

        database.setWins(player, wins);
        database.setLosses(player, losses);
        database.setKills(player, kills);
        database.setDeaths(player, deaths);
        database.setWinstreak(player, wins);
    }
}
