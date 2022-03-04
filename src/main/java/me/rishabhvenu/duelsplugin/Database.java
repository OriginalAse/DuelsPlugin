package me.rishabhvenu.duelsplugin;

import me.rishabhvenu.aseplugin.database.databases.sql.DataType;
import me.rishabhvenu.aseplugin.database.databases.sql.MySQLDatabase;
import me.rishabhvenu.aseplugin.database.databases.sql.Table;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private final MySQLDatabase database;

    public Database() {
        database = new MySQLDatabase(getStr("MySQL.host"), DuelsPlugin.getInstance().getConfig().getInt("MySQL.port"), getStr("MySQL.database"),
                getStr("MySQL.username"), getStr("MySQL.password"));
        createTable();
    }

    public boolean playerExists(Player player) {
        try {
            return database.select("duelsdata", "UUID", "UUID", player.getUniqueId().toString()).next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPlayer(Player player) {
        database.execute("INSERT INTO duelsdata (UUID, WINS, LOSSES, KILLS, DEATHS, WINSTREAK) " +
                "VALUES (\"" + player.getUniqueId() + "\", 0, 0, 0, 0, 0)");
    }

    public int getWins(Player player) {
        try {
            ResultSet rs = database.select("duelsdata", "WINS", "UUID", player.getUniqueId().toString());
            rs.next();
            return rs.getInt("WINS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getLosses(Player player) {
        try {
            ResultSet rs = database.select("duelsdata", "LOSSES", "UUID", player.getUniqueId().toString());
            rs.next();
            return rs.getInt("LOSSES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getKills(Player player) {
        try {
            ResultSet rs = database.select("duelsdata", "KILLS", "UUID", player.getUniqueId().toString());
            rs.next();
            return rs.getInt("KILLS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getDeaths(Player player) {
        try {
            ResultSet rs = database.select("duelsdata", "DEATHS", "UUID", player.getUniqueId().toString());
            rs.next();
            return rs.getInt("DEATHS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getWinStreak(Player player) {
        try {
            ResultSet rs = database.select("duelsdata", "WINSTREAK", "UUID", player.getUniqueId().toString());
            rs.next();
            return rs.getInt("WINSTREAK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setWins(Player player, int wins) {
        database.update("duelsdata", "WINS", String.valueOf(wins), "UUID", player.getUniqueId().toString());
    }

    public void setLosses(Player player, int losses) {
        database.update("duelsdata", "LOSSES", String.valueOf(losses), "UUID", player.getUniqueId().toString());
    }

    public void setKills(Player player, int kills) {
        database.update("duelsdata", "KILLS", String.valueOf(kills), "UUID", player.getUniqueId().toString());
    }

    public void setDeaths(Player player, int deaths) {
        database.update("duelsdata", "DEATHS", String.valueOf(deaths), "UUID", player.getUniqueId().toString());
    }

    public void setWinstreak(Player player, int winstreak) {
        database.update("duelsdata", "WINSTREAK", String.valueOf(winstreak), "UUID", player.getUniqueId().toString());
    }

    private void createTable() {
        if (!database.tableExists("duelsdata")) {
            Table table = database.createTable("duelsdata");
            table.addColumn("UUID", DataType.CHAR, "36");
            table.addColumn("WINS", DataType.INT, "20");
            table.addColumn("LOSSES", DataType.INT, "20");
            table.addColumn("KILLS", DataType.INT, "20");
            table.addColumn("DEATHS", DataType.INT, "20");
            table.addColumn("WINSTREAK", DataType.INT, "20");
            database.createTable(table);
        }
    }

    private String getStr(String path) {
        return DuelsPlugin.getInstance().getConfig().getString(path);
    }
}
