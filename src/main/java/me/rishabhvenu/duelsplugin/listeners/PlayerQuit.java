package me.rishabhvenu.duelsplugin.listeners;

import me.rishabhvenu.aseplugin.managers.ListenerManager;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit extends ListenerManager {
    public PlayerQuit() {
        super(DuelsPlugin.getInstance());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        DuelsPlugin.getCache().get(player).save();
        DuelsPlugin.getCache().remove(player);
    }
}
