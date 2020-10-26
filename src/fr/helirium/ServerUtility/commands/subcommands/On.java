package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class On extends SubCommand {

    @Override
    public String getName() {
        return "on";
    }

    @Override
    public String getDescription() {
        return "Active la maintenance du serveur.";
    }

    @Override
    public String getSyntax() {
        return "/maintenance on";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (args.length == 1) {
            if (sender.isOp()) {

                if (args[0].equalsIgnoreCase("on")) {

                    Main.maintenanceEnable = true;
                    sender.sendMessage(Messages.MAINTENANCE_ENABLED.getMessage());
                    Manager.kickNonAuhorized();

                }
            } else {
                sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
            }
        } else {
            sender.sendMessage(Messages.WRONG_ARG.getMessage() + ChatColor.DARK_RED + "<" + getSyntax() + ">");
        }
    }

}


