package me.rishabhvenu.duelsplugin.commands;

import me.rishabhvenu.aseplugin.commands.Command;
import me.rishabhvenu.aseplugin.utils.ColorUtil;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.PlayerCache;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand {

    @Command(command = "/stats", overrideParameterLimit = true)
    public void stats(CommandSender sender, String[] args) {

        if (args.length > 1) {
            sender.sendMessage(ColorUtil.translate("&cInvalid usage! /stats [player]"));
            return;
        }

        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.translate("&cYou must be a player to view your own stats!"));
            return;
        }

        Player player = args.length == 0 ? (Player) sender: Bukkit.getPlayerExact(args[0]);

        if (player == null) {
            sender.sendMessage(ColorUtil.translate("&cPlayer &e" + args[0] + " &ccould not be found!"));
            return;
        }

        PlayerCache cache = DuelsPlugin.getCache().get(player);

        sender.sendMessage(ColorUtil.translate("----------------------"));
        sender.sendMessage(ColorUtil.translate("&a" + player.getName() + "'s Stats"));
        sender.sendMessage(ColorUtil.translate("&aWins: &e" + cache.getWins()));
        sender.sendMessage(ColorUtil.translate("&aLosses: &e" + cache.getLosses()));
        sender.sendMessage(ColorUtil.translate("&aKills: &e" + cache.getKills()));
        sender.sendMessage(ColorUtil.translate("&aDeaths: &e" + cache.getDeaths()));
        sender.sendMessage(ColorUtil.translate("&aWinstreak: &e" + cache.getWinstreak()));
        sender.sendMessage(ColorUtil.translate("----------------------"));
    }
}
