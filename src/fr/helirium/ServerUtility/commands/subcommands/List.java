package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.UUID;


public class List extends SubCommand {


    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Affiche la liste des joueurs authorisés pendant les maintenances.";
    }

    @Override
    public String getSyntax() {
        return "/maintenance list";
    }

    @Override
    public void runCommand(CommandSender player, String[] args) {
        OfflinePlayer offlinePlayer;

        for (UUID uuid : Main.getAuthorized()) {

            offlinePlayer = Bukkit.getOfflinePlayer(uuid);

            if (offlinePlayer != null){
                player.sendMessage(offlinePlayer.getName());
            }

        }
    }
}
