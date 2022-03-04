package me.rishabhvenu.duelsplugin.listeners;

import me.rishabhvenu.aseplugin.managers.ListenerManager;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.PlayerCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin extends ListenerManager {
    public PlayerJoin() {
        super(DuelsPlugin.getInstance());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        DuelsPlugin.getCache().put(player, new PlayerCache(player));
    }
}
