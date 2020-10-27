package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.command.CommandSender;

public class Status extends SubCommand {

    Main plugin;

    public Status(Main main) {
        this.plugin = main;
    }

    @Override
    public String getName() {
        return "Status";
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

        if (args.length == 1) {
            boolean maintenanceStatus = plugin.maintenanceEnable;
            sender.sendMessage("State: "+ maintenanceStatus);
        } else {
            sender.sendMessage(Messages.WRONG_ARG.getMessage());
        }
    }
}
