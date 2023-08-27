package org.gt.headhunterkingdoms.Objects;

import me.yic.xconomy.api.XConomyAPI;
import org.bukkit.plugin.java.JavaPlugin;
import org.gt.headhunterkingdoms.API.ConfigManager;
import org.gt.headhunterkingdoms.MySQL.SQLgetter;

public class AllMain {
    JavaPlugin plugin;
    ConfigManager configManager;
    SQLgetter sqLgetter;
    XConomyAPI api;
    public AllMain(JavaPlugin javaPlugin, ConfigManager configManager, SQLgetter sqLgetter, XConomyAPI api){
        this.plugin = javaPlugin;
        this.configManager = configManager;
        this.sqLgetter = sqLgetter;
        this.api = api;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public SQLgetter getSQLgetter() {
        return sqLgetter;
    }

    public XConomyAPI getApi(){
        return api;
    }
}
