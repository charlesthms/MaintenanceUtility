package fr.helirium.ServerUtility.utils;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.subcommands.On;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static void kickNonAuhorized() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (!player.isOp() && !Main.getAuthorized().contains(player.getUniqueId())){

                if (On.format){
                    String output = On.secondsToHours(On.maintenanceTime);
                    player.kickPlayer(Messages.PLAYER_KICK.getMessage() + output + "h !");
                }else {
                    player.kickPlayer(Messages.PLAYER_KICK_UNDEFINED.getMessage());
                }
            }
        }
    }
}
