package org.gt.headhunterkingdoms.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.gt.headhunterkingdoms.Objects.AllMain;

public class GlobalEvent implements Listener {
    AllMain main;
    public GlobalEvent(AllMain main){
        this.main = main;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        main.getSQLgetter().createPlayer(player);

        main.getApi().createPlayerData(player.getUniqueId(), player.getName());
    }
}
