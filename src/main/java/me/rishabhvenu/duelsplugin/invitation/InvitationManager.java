package me.rishabhvenu.duelsplugin.invitation;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

public class InvitationManager {
    private final List<Invitation> invitations = Lists.newArrayList();;

    public Invitation get(Player player, Player target) {
        for (Invitation invitation: invitations) {
            if (invitation.getPlayer() == player && invitation.getTarget() == target) {
                return invitation;
            }
        }
        return null;
    }

    public void add(Invitation invitation) {
        invitations.add(invitation);
    }

    public void remove(Invitation invitation) {
        invitations.remove(invitation);
    }

    public boolean has(Player player, Player target) {
        for (Invitation invitation: invitations) {
            if (invitation.getPlayer() == player && invitation.getTarget() == target) {
                return true;
            }
        }
        return false;
    }
}
