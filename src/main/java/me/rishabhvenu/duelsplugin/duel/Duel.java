package me.rishabhvenu.duelsplugin.duel;

import me.rishabhvenu.aseplugin.utils.ColorUtil;
import me.rishabhvenu.duelsplugin.Database;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.Kit;
import me.rishabhvenu.duelsplugin.PlayerCache;
import me.rishabhvenu.duelsplugin.invitation.Invitation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

public class Duel {
    private final Player player1;
    private final Player player2;
    private final Location player1Pos;
    private final Location player2Pos;
    private boolean stationary = true;

    public Duel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Pos = player1.getLocation();
        this.player2Pos = player2.getLocation();

        Invitation invitation = DuelsPlugin.getInvitationManager().get(player1, player2);
        invitation.getTask().cancel();
        player1.teleport(DuelsPlugin.getArenaConfig().getPlayer1Spawn());
        player2.teleport(DuelsPlugin.getArenaConfig().getPlayer2Spawn());

        Kit kit = invitation.getKit();

        kit.equip(player1);
        kit.equip(player2);
    }


    public void start() {
        new BukkitRunnable() {
            int time = 5;
            @Override
            public void run() {
                if (time == 0) {
                    player1.sendMessage(ColorUtil.translate("&aDuel has started!"));
                    player2.sendMessage(ColorUtil.translate("&aDuel has started!"));
                    stationary = false;
                    cancel();
                    return;
                }
                player1.sendMessage(ColorUtil.translate("&aDuel starts in &e" + time + " seconds"));
                player2.sendMessage(ColorUtil.translate("&aDuel starts in &e" + time + " seconds"));
                time--;
            }
        }.runTaskTimer(DuelsPlugin.getInstance(), 0L, 20L);

    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public boolean getStationary() {
        return stationary;
    }

    public void end(Player dead) {
        Player winner = dead == player1 ? player2 : player1;
        Player loser = dead == player1 ? player1 : player2;
        PlayerCache winnerCache = DuelsPlugin.getCache().get(winner);
        PlayerCache loserCache = DuelsPlugin.getCache().get(loser);

        winnerCache.setWins(winnerCache.getWins() + 1);
        winnerCache.setKills(winnerCache.getKills() + 1);
        winnerCache.setWinstreak(winnerCache.getWinstreak() + 1);
        loserCache.setDeaths(loserCache.getDeaths() + 1);
        loserCache.setLosses(loserCache.getLosses() + 1);
        loserCache.setWinstreak(0);

        winner.sendMessage(ColorUtil.translate("&aYou have won! Your stats have been updated!"));
        loser.sendMessage(ColorUtil.translate("&cYou have lost! Your stats have been updated"));

        player1.teleport(player1Pos);
        player2.teleport(player2Pos);
    }
}
