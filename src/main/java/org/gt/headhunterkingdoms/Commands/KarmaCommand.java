package org.gt.headhunterkingdoms.Commands;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.gt.headhunterkingdoms.HeadHunterKingdoms;
import org.gt.headhunterkingdoms.Objects.AllMain;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class KarmaCommand implements CommandExecutor, TabCompleter {
    AllMain main;
    public KarmaCommand(AllMain main){
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!(commandSender instanceof Player)){
            return false;
        }

        Player player = (Player) commandSender;
        if(strings.length == 0) {
            player.sendMessage("У вас " + PlaceholderAPI.setPlaceholders(player, "%headhunter_karma%") + " кармы.");
            return true;
        }
        if(player.isOp()){
            if(strings.length >= 2){
                switch (strings[0]){
                    case "get":
                        if(strings.length == 2){
                            Player target = Bukkit.getPlayer(strings[1]);
                            if(target == null){
                                player.sendMessage("Данный игрок не на сервере!");
                                return true;
                            }
                            player.sendMessage("У игрока " + target.getName() + " " + HeadHunterKingdoms.getKarma(player) + " кармы");
                        }
                        break;
                    case "add":
                        if(strings.length == 3){
                            Player target = Bukkit.getPlayer(strings[1]);
                            if(target == null){
                                player.sendMessage("Данный игрок не на сервере!");
                                return true;
                            }
                            int amount;

                            try {
                                amount = Integer.parseInt(strings[2]);
                            }
                            catch (NumberFormatException er){
                                player.sendMessage("Вы ввели неверное число!");
                                return true;
                            }

                            HeadHunterKingdoms.addKarma(target.getName(), amount);
                        }
                        break;
                    case "set":
                        if(strings.length == 3){
                            Player target = Bukkit.getPlayer(strings[1]);
                            if(target == null){
                                player.sendMessage("Данный игрок не на сервере!");
                                return true;
                            }
                            int amount;

                            try {
                                amount = Integer.parseInt(strings[2]);
                            }
                            catch (NumberFormatException er){
                                player.sendMessage("Вы ввели неверное число!");
                                return true;
                            }

                            HeadHunterKingdoms.setKarma(target.getName(), amount);
                        }
                        break;
                }
            }

            return true;
        }

        player.sendMessage("У вас " + PlaceholderAPI.setPlaceholders(player, "%headhunter_karma%") + " кармы.");
        return false;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(!commandSender.isOp()){
            return new ArrayList<>();
        }

        if(strings.length == 1){
            return new ArrayList<>(){{
                add("get");
                add("add");
                add("set");
            }};
        }

        return null;
    }
}

