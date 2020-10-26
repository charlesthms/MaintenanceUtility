package fr.helirium.ServerUtility.listeners;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerLogin implements Listener {


    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {

        final Player player = joinEvent.getPlayer();

        if (Main.maintenanceEnable) {

            if (!player.isOp() && !Main.getAuthorized().contains(player.getUniqueId())) {
                player.kickPlayer(Messages.PLAYER_MAINTENANCE.getMessage());
            }

        }
    }
    
}
