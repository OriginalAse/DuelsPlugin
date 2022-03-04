package me.rishabhvenu.duelsplugin.listeners;

import me.rishabhvenu.aseplugin.managers.ListenerManager;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove extends ListenerManager {
    public PlayerMove() {
        super(DuelsPlugin.getInstance());
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
       Player player = event.getPlayer();
        if (DuelsPlugin.getDuelManager().hasDuel(player) && DuelsPlugin.getDuelManager().getDuel(player).getStationary()) {
            event.setCancelled(true);
        }
    }
}
