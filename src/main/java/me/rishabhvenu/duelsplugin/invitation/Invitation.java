package me.rishabhvenu.duelsplugin.invitation;

import me.rishabhvenu.aseplugin.utils.ColorUtil;
import me.rishabhvenu.duelsplugin.DuelsPlugin;
import me.rishabhvenu.duelsplugin.Kit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Invitation {
    private final Player player;
    private final Player target;
    private final Kit kit;
    private final BukkitTask task;

    public Invitation(Player player, Player target, Kit kit) {
        this.player = player;
        this.target = target;
        this.kit = kit;
        this.task = Bukkit.getScheduler().runTaskLater(DuelsPlugin.getInstance(), () -> {
            player.sendMessage(ColorUtil.translate("&cYour invitation to &e" + target.getName() + " &chas expired!"));
            target.sendMessage(ColorUtil.translate("&cYour invitation from &e" + player.getName() + " &chas expired!"));
            DuelsPlugin.getInvitationManager().remove(this);
        }, 1200);
    }

    public Player getPlayer() {
        return player;
    }

    public Player getTarget() {
        return target;
    }

    public Kit getKit() {
        return kit;
    }

    public BukkitTask getTask() {
        return task;
    }
}
