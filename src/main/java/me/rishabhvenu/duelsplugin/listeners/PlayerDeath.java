package me.rishabhvenu.duelsplugin.listeners;

import me.rishabhvenu.aseplugin.managers.ListenerManager;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.duel.Duel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeath extends ListenerManager {
    public PlayerDeath() {
        super(DuelsPlugin.getInstance());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (DuelsPlugin.getDuelManager().hasDuel(player)) {
            Duel duel = DuelsPlugin.getDuelManager().getDuel(player);
            duel.end(player);
        }
    }
}
