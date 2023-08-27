package org.gt.headhunterkingdoms.PlaceHolders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.gt.headhunterkingdoms.HeadHunterKingdoms;
import org.jetbrains.annotations.NotNull;

public class KarmaPlaceHolder extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "headhunter";
    }

    @Override
    public @NotNull String getAuthor() {
        return "_MrTvist_";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public boolean persist(){
        return false;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params){
        if(player == null){
            return "";
        }
        if(params.equals("karma")){
            return String.valueOf(HeadHunterKingdoms.getInstance().getMain().getSQLgetter().getValue(player, "karma"));
        }
        return "";
    }
}
