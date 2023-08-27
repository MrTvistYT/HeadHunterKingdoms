package org.gt.headhunterkingdoms.API;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
    FileConfiguration conf;
    public ConfigManager(FileConfiguration conf){
        this.conf = conf;
    }
    public String getFormula(){
        return conf.getString("formula");
    }
    public int getJoin(){
        return conf.getInt("default-karma");
    }
    public int getBoardKarma(){
        return conf.getInt("add-board-karma");
    }

    public String getUrl(){
        return conf.getString("mySQL.url");
    }
    public String getUserName(){
        return conf.getString("mySQL.username");
    }
    public String getPassword(){
        return conf.getString("mySQL.password");
    }
    public int getKillClaim(){
        return conf.getInt("add-karma.kill-player");
    }
}
