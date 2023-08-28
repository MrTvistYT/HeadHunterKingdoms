package org.gt.headhunterkingdoms;

import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.gt.headhunterkingdoms.API.ConfigManager;
import org.gt.headhunterkingdoms.Commands.HeadBagCommand;
import org.gt.headhunterkingdoms.Commands.KarmaCommand;
import org.gt.headhunterkingdoms.Events.*;
import org.gt.headhunterkingdoms.MySQL.SQLManager;
import org.gt.headhunterkingdoms.MySQL.SQLgetter;
import org.gt.headhunterkingdoms.Objects.AllMain;
import org.gt.headhunterkingdoms.PlaceHolders.KarmaPlaceHolder;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public final class HeadHunterKingdoms extends JavaPlugin implements PluginMessageListener {

    private static AllMain main;

    public static HeadHunterKingdoms instance;
    public static HeadHunterKingdoms getInstance(){
        return instance;
    }

    public AllMain getMain(){
        return main;
    }
    @Override
    public void onEnable() {
        instance = this;

        ConfigManager configManager = new ConfigManager(getConfig());

        SQLManager sqlManager = new SQLManager();
        SQLgetter sqLgetter = new SQLgetter(sqlManager);

        XConomyAPI xcapi = new XConomyAPI();

        try {
            sqlManager.setConnection(configManager);
        } catch (SQLException | ClassNotFoundException er) {
            Bukkit.getLogger().info("Database is not connected");
        }

        if (sqlManager.isConnected()) {
            Bukkit.getLogger().info("Database is connected");
            sqLgetter.createTable();
        }

        main = new AllMain(this, configManager, sqLgetter, xcapi);

        sqLgetter.setMain(main);

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new GlobalEvent(main), this);
        Bukkit.getPluginManager().registerEvents(new KarmaEvents(main), this);

        new KarmaPlaceHolder().register();

        getCommand("karma").setExecutor(new KarmaCommand(main));
        getCommand("headBag").setExecutor(new HeadBagCommand(main));

        registerPluginChannel("headhunter:hhevent");
    }

    @Override
    public void onDisable() {
        // do nothing
    }


    public void registerPluginChannel(String channel) {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, channel, this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, channel);
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] bytes) {
        if (channel.equals("headhunter:hhevent")) {
            String cmd = new String(bytes, StandardCharsets.UTF_8);

            String[] args = cmd.split(" ");
            if(cmd.contains("sendLastSeen")){
                Player playerNick = Bukkit.getPlayer(args[1]);

                if(main.getSQLgetter().getBoolean(playerNick, "headHunter", "license")) {
                    int seen = main.getSQLgetter().getValue(playerNick, "lastSeen");
                    int tasks = main.getSQLgetter().getTasks();

                    if (seen < tasks) {
                        playerNick.sendMessage("На доске доступно " + (tasks - seen) + " новых задания");
                    }
                }
            }
            else if(cmd.contains("setLastSeen")){
                Player playerNick = Bukkit.getPlayer(args[1]);

                int tasks = main.getSQLgetter().getTasks();

                main.getSQLgetter().setValue(playerNick, "lastSeen", tasks);
            }
        }
    }

    public static boolean isTasked(Player player){
        return main.getSQLgetter().getBoolean(player, "headHunter", "tasked");
    }
    public static int getKarma(Player player){
        return main.getSQLgetter().getValue(player, "karma");
    }
    public static void setKarma(String player, int amount){
        main.getSQLgetter().setValue(player, "karma", amount);

        main.getSQLgetter().createCommand("update " + player, 1);
    }
    public static void addKarma(String player, int amount){
        main.getSQLgetter().addValue(player, "karma", amount);

        main.getSQLgetter().createCommand("update " + player, 1);
    }
}
