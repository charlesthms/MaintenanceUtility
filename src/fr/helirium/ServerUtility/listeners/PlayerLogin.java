package fr.helirium.ServerUtility.listeners;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.subcommands.On;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class PlayerLogin implements Listener {

    Main plugin;

    public PlayerLogin(Main mainInstance) {
        this.plugin = mainInstance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent joinEvent) {

        final Player player = joinEvent.getPlayer();

        if (plugin.maintenanceEnable) {

            long timeLeft = On.onTime - System.currentTimeMillis() / 1000;
            int correctFormat = (int) timeLeft;

            if (!player.isOp() && !Main.getAuthorized().contains(player.getUniqueId())) {
                player.kickPlayer("Temps restant: " + On.secondsToHours(correctFormat));
            }

        }
    }

}
