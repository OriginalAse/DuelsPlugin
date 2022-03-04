package me.rishabhvenu.duelsplugin.configs;

import me.rishabhvenu.aseplugin.managers.CustomConfig;
import me.rishabhvenu.duelsplugin.DuelsPlugin;

public class KitConfig extends CustomConfig {
    public KitConfig() {
        super(DuelsPlugin.getInstance(), "kits.yml");
    }

    public String getDefaultKit() {
        return getString("default_kit");
    }
}
