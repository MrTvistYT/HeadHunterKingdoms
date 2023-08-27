package org.gt.headhunterkingdoms.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.gt.headhunterkingdoms.API.InventoryManager;
import org.gt.headhunterkingdoms.Objects.AllMain;
import org.jetbrains.annotations.NotNull;

public class HeadBagCommand implements CommandExecutor {
    AllMain main;

    public HeadBagCommand(AllMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player player)){
            return false;
        }

        player.openInventory(InventoryManager.getPack(player, main));

        return false;
    }
}
