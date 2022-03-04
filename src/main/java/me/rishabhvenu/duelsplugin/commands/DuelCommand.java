package me.rishabhvenu.duelsplugin.commands;

import me.rishabhvenu.aseplugin.commands.Command;
import me.rishabhvenu.aseplugin.utils.ColorUtil;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.invitation.Invitation;
import me.rishabhvenu.duelsplugin.Kit;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class DuelCommand {
    @Command(command = "/duel", disallowNonPlayer = true, overrideParameterLimit = true)
    public void duel(CommandSender sender, String[] args) {
        Player player =  (Player) sender;

        if (args.length == 0 || args.length > 2) {
            player.sendMessage(ColorUtil.translate("&cInvalid usage! /duel <player> [kit]"));
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(ColorUtil.translate("&cPlayer &e" + args[0] + " &ccould not be found!"));
            return;
        }

        if (DuelsPlugin.getInvitationManager().has(player, target)) {
            player.sendMessage(ColorUtil.translate("&cYou have already sent a duel request to this player!"));
            return;
        }

        String name = DuelsPlugin.getKitConfig().getDefaultKit();
        Map<String, Kit> kits = DuelsPlugin.getKits();
        Kit kit = kits.get(name);

        if (args.length > 1) {
            if (kits.containsKey(args[1])) {
                name = args[1];
                kit = kits.get(args[1]);
            } else {
                player.sendMessage(ColorUtil.translate("&cKit &e" + args[1] + " &ccould not be found!"));
                return;
            }
        }

        DuelsPlugin.getInvitationManager().add(new Invitation(player, target, kit));
        player.sendMessage(ColorUtil.translate("&aDuel request sent to &e" + args[0] + " &ausing kit &e" + name + "&a!"));
        target.sendMessage(ColorUtil.translate("&e" + player.getName() +
                " &asent you a duel request using kit &e" + name + "&a! Do /accept &e"
                + player.getName() + " &ato accept"));
    }
}
