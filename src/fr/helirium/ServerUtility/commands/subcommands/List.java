package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
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
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")){
            OfflinePlayer offlinePlayer;

            sender.sendMessage(Messages.MAINTENANCE_LIST.getMessage());

            for (UUID uuid : Main.getAuthorized()) {

                offlinePlayer = Bukkit.getOfflinePlayer(uuid);

                if (offlinePlayer != null) {
                    sender.sendMessage(ChatColor.GOLD + "- " + ChatColor.AQUA + offlinePlayer.getName());
                }

            }
            sender.sendMessage(ChatColor.AQUA + "----------------------------------------");
        } else {
            sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }

    }

    @Override
    public ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
