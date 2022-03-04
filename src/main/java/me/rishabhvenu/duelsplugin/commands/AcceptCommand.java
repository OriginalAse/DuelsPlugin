package me.rishabhvenu.duelsplugin.commands;

import me.rishabhvenu.aseplugin.commands.Command;
import me.rishabhvenu.aseplugin.utils.ColorUtil;
import me.rishabhvenu.duelsplugin.*;
import me.rishabhvenu.duelsplugin.duel.Duel;
import me.rishabhvenu.duelsplugin.invitation.Invitation;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AcceptCommand {
    @Command(command = "/accept", disallowNonPlayer = true, overrideParameterLimit = true)
    public void accept(CommandSender sender, String[] args) {
        Player player = (Player) sender;

        if (args.length != 1) {
            player.sendMessage(ColorUtil.translate("&cInvalid usage! /accept <player>"));
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(ColorUtil.translate("&cThat player does not exist!"));
            return;
        }

        if (!DuelsPlugin.getInvitationManager().has(target, player)) {
            player.sendMessage(ColorUtil.translate("&e" + target.getName() + " &chas not sent you a duel request!"));
            return;
        }

        Duel duel = new Duel(target, player);
        DuelsPlugin.getDuelManager().addDuel(duel);

        duel.start();
    }
}
