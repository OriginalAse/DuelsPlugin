package me.rishabhvenu.duelsplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;


public class Kit {
    private final String name;
    private final ItemStack helmet;
    private final ItemStack chestplate;
    private final ItemStack leggings;
    private final ItemStack boots;
    private final Inventory inv;

    public Kit(String name) {
        this.name = name;
        this.helmet = getItemStack("helmet");
        this.chestplate = getItemStack("chestplate");
        this.leggings = getItemStack("leggings");
        this.boots = getItemStack("boots");
        Inventory inv = Bukkit.createInventory(null, 36);

        if (DuelsPlugin.getKitConfig().contains("kits." + name + ".inventory")) {
            for (String slot : DuelsPlugin.getKitConfig().getConfigurationSection("kits." + name + ".inventory").getKeys(false)) {
                inv.setItem(Integer.parseInt(slot),
                        new ItemStack(Material.valueOf(DuelsPlugin.getKitConfig().getString("kits." + name + ".inventory." + slot + ".material")),
                                DuelsPlugin.getKitConfig().getInt("kits." + name + ".inventory." + slot + ".slot")));
            }
        }

        this.inv = inv;
    }

    public ItemStack getHelmet() {
        return helmet;
    }

    public ItemStack getChestplate() {
        return chestplate;
    }

    public ItemStack getLeggings() {
        return leggings;
    }

    public ItemStack getBoots() {
        return boots;
    }

    public Inventory getInventory() {
        return inv;
    }

    public void equip(Player player) {
        PlayerInventory inv = player.getInventory();

        inv.setHelmet(helmet);
        inv.setChestplate(chestplate);
        inv.setLeggings(leggings);
        inv.setBoots(boots);

        for (int i = 0; i < inv.getContents().length; i++) {
            inv.setItem(i, inv.getContents()[i]);
        }
    }

    private ItemStack getItemStack(String piece) {
        return new ItemStack(Material.valueOf(DuelsPlugin.getKitConfig().getString("kits." + name + ".armor." + piece + ".material")),
                DuelsPlugin.getKitConfig().getInt("kits." + name + ".armor." + piece + ".amount"));
    }
}
