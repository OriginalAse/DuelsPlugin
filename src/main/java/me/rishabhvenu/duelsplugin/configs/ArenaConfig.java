package me.rishabhvenu.duelsplugin.configs;

import me.rishabhvenu.aseplugin.managers.CustomConfig;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class ArenaConfig extends CustomConfig {
    public ArenaConfig() {
        super(DuelsPlugin.getInstance(), "arena.yml");
    }

    public Location getPlayer1Spawn() {
        return new Location(Bukkit.getWorld(getString("world")),
                getDouble("player_1_spawn.x"), getDouble("player_1_spawn.y"), getDouble("player_1_spawn.z"));
    }

    public Location getPlayer2Spawn() {
        return new Location(Bukkit.getWorld(getString("world")),
                getDouble("player_2_spawn.x"), getDouble("player_2_spawn.y"), getDouble("player_2_spawn.z"));
    }
}
