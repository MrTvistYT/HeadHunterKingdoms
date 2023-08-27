package org.gt.headhunterkingdoms.MySQL;

import org.bukkit.entity.Player;
import org.gt.headhunterkingdoms.Objects.AllMain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.gt.headhunterkingdoms.MySQL.SQLManager.connection;

public class SQLgetter {
    SQLManager manager;
    AllMain main;
    public SQLgetter(SQLManager manager) {
        this.manager = manager;
    }


    public void createTable() {
        PreparedStatement ps;
        try {
            ps = manager.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS headHunter "
                    + "(nickName VARCHAR(100), "
                    + "karma INT(10) DEFAULT 0, license BOOLEAN DEFAULT false, "
                    + "tasked BOOLEAN DEFAULT false, "
                    + "storedHeads TEXT, "
                    + "taskCoolDown DATETIME, "
                    + "lastSeen INT DEFAULT 0, "
                    + "PRIMARY KEY (nickName))");

            ps.executeUpdate();
        } catch (SQLException er) {
            er.printStackTrace();
        }

        try {
                ps = manager.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS headHunterTasks "
                    + "(nickName VARCHAR(100), "
                    + "money INT(10) DEFAULT 0, byPlayer BOOLEAN DEFAULT false, "
                    + "PRIMARY KEY (nickName))");

            ps.executeUpdate();
        } catch (SQLException er) {
            er.printStackTrace();
        }
    }


    public void createPlayer(Player player) {
        try {
            if (!exists("headHunter", player)) {
                PreparedStatement ps2 = manager.getConnection().prepareStatement("INSERT IGNORE INTO headHunter (nickName, karma, license, tasked) VALUES (?, ?, ?, ?, ?)");
                ps2.setString(1, player.getName());
                ps2.setInt(2, 0);
                ps2.setBoolean(3, false);
                ps2.setBoolean(4, false);
                ps2.setString(5, "");

                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String table, Player player) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE nickName=?");
            ps.setString(1, player.getName());

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                //player is found
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getValue(Player player, String kind) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT " + kind + " FROM headHunter WHERE nickName=?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            int val;
            if (rs.next()) {
                val = rs.getInt(kind);
                return val;
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return 0;
    }
    public int getValue(String player, String table, String kind) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT " + kind + " FROM " + table + " WHERE nickName=?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            int val;
            if (rs.next()) {
                val = rs.getInt(kind);
                return val;
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return 0;
    }
    public int getValue(String player, String kind) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT " + kind + " FROM headHunter WHERE nickName=?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            int val;
            if (rs.next()) {
                val = rs.getInt(kind);
                return val;
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return 0;
    }
    public String getString(String player, String kind) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT " + kind + " FROM headHunter WHERE nickName=?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            String str;
            if (rs.next()) {
                str = rs.getString(kind);
                return str;
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return "";
    }


    public boolean getBoolean(Player player, String table, String kind) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT " + kind + " FROM " + table + " WHERE nickName=?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            boolean val;
            if (rs.next()) {
                val = rs.getBoolean(kind);
                return val;
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return false;
    }
    public void setValue(Player player, String kind, int value) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("UPDATE headHunter SET " + kind + "= ? WHERE nickName=?");
            ps.setInt(1, value);
            ps.setString(2, player.getName());
            ps.executeUpdate();
        } catch (SQLException er) {
            er.printStackTrace();
        }
    }
    public void setValue(String player, String kind, int value) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("UPDATE headHunter SET " + kind + "= ? WHERE nickName=?");
            ps.setInt(1, value);
            ps.setString(2, player);
            ps.executeUpdate();
        } catch (SQLException er) {
            er.printStackTrace();
        }
    }
    public void setMain(AllMain main) {
        this.main = main;
    }

    public String getCommands(){
        String cmds = "";
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT * FROM headHunterExecutor WHERE server = ?");
            ps.setInt(1, 1);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cmds;
    }
    public void createCommand(String command, int server) {
        try {
            if (!exists(command)) {
                PreparedStatement ps2 = manager.getConnection().prepareStatement("INSERT IGNORE INTO headHunterExecutor (command, server) VALUES (?, ?)");
                ps2.setString(1, command);
                ps2.setInt(2, server);

                ps2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean exists(String command) {
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT * FROM headHunterExecutor WHERE command=?");
            ps.setString(1, command);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                //player is found
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addValue(String player, String kind, int value){
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("UPDATE headHunter SET " + kind + "= " + kind + " + ? WHERE nickName=?");
            ps.setInt(1, value);
            ps.setString(2, player);
            ps.executeUpdate();
        } catch (SQLException er) {
            er.printStackTrace();
        }
    }

    public int getTasks(){
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM headHunterTasks");
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
        return 0;
    }
    public List<String> getHeads(Player player){
        String[] val;
        try {
            PreparedStatement ps = manager.getConnection().prepareStatement("SELECT storedHeads FROM headHunter WHERE nickName=?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                val = rs.getString("storedHeads")
                        .split(" ");

                List<String> res = new ArrayList<>();

                Collections.addAll(res, val);

                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addHead(Player player, String head) {
        String command =
                "UPDATE headHunter " +
                "SET storedHeads = " +
                "CASE " +
                "WHEN storedHeads IS NULL OR storedHeads = '' THEN ? " +
                "WHEN storedHeads LIKE '% %' THEN CONCAT(storedHeads, ?) " +
                "WHEN storedHeads LIKE ? THEN storedHeads " +
                "ELSE CONCAT(storedHeads, ?) " +
                "END " +
                "WHERE nickName = ?";

        try {
            PreparedStatement statement = manager.getConnection().prepareStatement(command);

            statement.setString(1, head);
            statement.setString(2, " " + head);
            statement.setString(3, "%" + head + "%");
            statement.setString(4," " +  head);
            statement.setString(5, player.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
