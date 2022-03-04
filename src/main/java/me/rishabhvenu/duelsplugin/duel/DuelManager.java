package me.rishabhvenu.duelsplugin.duel;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

public class DuelManager {
    private final List<Duel> duels = Lists.newArrayList();

    public Duel getDuel(Player player) {
        for (Duel duel: duels) {
            if (duel.getPlayer1() == player || duel.getPlayer2() == player) {
                return duel;
            }
        }
        return null;
    }

    public void addDuel(Duel duel) {
        duels.add(duel);
    }

    public boolean hasDuel(Player player) {
        for (Duel duel: duels) {
            if (duel.getPlayer1() == player || duel.getPlayer2() == player) {
                return true;
            }
        }
        return false;
    }
}
