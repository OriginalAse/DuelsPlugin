package me.rishabhvenu.duelsplugin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.rishabhvenu.aseplugin.AsePlugin;
import me.rishabhvenu.aseplugin.commands.CommandRegistry;
import me.rishabhvenu.duelsplugin.commands.AcceptCommand;
import me.rishabhvenu.duelsplugin.commands.DuelCommand;
import me.rishabhvenu.duelsplugin.commands.StatsCommand;
import me.rishabhvenu.duelsplugin.configs.ArenaConfig;
import me.rishabhvenu.duelsplugin.configs.KitConfig;
import me.rishabhvenu.duelsplugin.duel.DuelManager;
import me.rishabhvenu.duelsplugin.invitation.InvitationManager;
import me.rishabhvenu.duelsplugin.listeners.PlayerDeath;
import me.rishabhvenu.duelsplugin.listeners.PlayerJoin;
import me.rishabhvenu.duelsplugin.listeners.PlayerMove;
import me.rishabhvenu.duelsplugin.listeners.PlayerQuit;
import org.bukkit.entity.Player;

import java.util.Map;

public final class DuelsPlugin extends AsePlugin {
    private static DuelsPlugin instance;
    private static Database database;
    private static KitConfig kitConfig;
    private static ArenaConfig arenaConfig;
    private static InvitationManager invitationManager;
    private static DuelManager duelManager;
    private final static Map<String, Kit> kits = Maps.newHashMap();
    private final static Map<Player, PlayerCache> cache = Maps.newHashMap();

    public DuelsPlugin() {
        super(true);
    }

    @Override
    public void onStart() {
        instance = this;
        database = new Database();
        kitConfig = new KitConfig();
        arenaConfig = new ArenaConfig();
        invitationManager = new InvitationManager();
        duelManager = new DuelManager();
        registerKits();
        registerListeners();
        registerCommands();
    }

    private void registerKits() {
        for (String kit: kitConfig.getConfigurationSection("kits").getKeys(false)) {
            kits.put(kit, new Kit(kit));
        }
    }

    private void registerListeners() {
        new PlayerJoin();
        new PlayerMove();
        new PlayerDeath();
        new PlayerQuit();
    }

    private void registerCommands() {
        CommandRegistry registry = new CommandRegistry(this);
        registry.registerCommands(new DuelCommand());
        registry.registerCommands(new AcceptCommand());
        registry.registerCommands(new StatsCommand());
    }


    public static DuelsPlugin getInstance() {
        return instance;
    }

    public static Database getDatabase() {
        return database;
    }

    public static KitConfig getKitConfig() {
        return kitConfig;
    }

    public static ArenaConfig getArenaConfig() {
        return arenaConfig;
    }

    public static Map<String, Kit> getKits() {
        return kits;
    }

    public static Map<Player, PlayerCache> getCache() {
        return cache;
    }

    public static InvitationManager getInvitationManager() {
        return invitationManager;
    }

    public static DuelManager getDuelManager() {
        return duelManager;
    }

}
