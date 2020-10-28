package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Status extends SubCommand {

    Main plugin;

    public Status(Main main) {
        this.plugin = main;
    }

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public String getDescription() {
        return "Donne l'état actuel de la maintenance";
    }

    @Override
    public String getSyntax() {
        return "/maintenance state";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")){
            if (args.length == 1) {
                boolean maintenanceStatus = plugin.maintenanceEnable;
                sender.sendMessage(Messages.PREFIX.getMessage() + Messages.MAINTENANCE_STATUS.getMessage() + ChatColor.GOLD +(maintenanceStatus ? "ON" : "OFF"));
            } else {
                sender.sendMessage(Messages.WRONG_ARG.getMessage());
            }
        } else {
            sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }

    }

    @Override
    public ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
