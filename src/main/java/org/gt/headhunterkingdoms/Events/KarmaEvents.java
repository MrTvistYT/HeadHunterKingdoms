package org.gt.headhunterkingdoms.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.gt.headhunterkingdoms.HeadHunterKingdoms;
import org.gt.headhunterkingdoms.Objects.AllMain;


public class KarmaEvents implements Listener {
    AllMain main;

    public KarmaEvents(AllMain main){
        this.main = main;
    }
    @EventHandler
    public void playerKill(PlayerDeathEvent event){
        Player killer = event.getEntity().getKiller();
        Player player = event.getPlayer();
        if(killer == null){
            return;
        }

        if(main.getSQLgetter().getBoolean(killer,"headHunter","license")) {
            if(main.getSQLgetter().getBoolean(player, "headHunter", "tasked")) {
                if(main.getSQLgetter().getHeads(killer).size() < 28) {
                    main.getSQLgetter().addHead(killer, player.getName());
                }
                else{
                    killer.sendMessage("У вас заполнен кейс с головами");
                }
            }
        }
        HeadHunterKingdoms.addKarma(killer.getName(), -5);
    }
}
