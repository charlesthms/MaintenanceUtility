package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Off extends SubCommand {

    Main plugin;

    public Off (Main mainInstance) {
        this.plugin = mainInstance;
    }

    @Override
    public String getName() {
        return "Off";
    }

    @Override
    public String getDescription() {
        return "Desactivation manuelle de la maintenance.";
    }

    @Override
    public String getSyntax() {
        return "/maintenance off";
    }

    @Override
    public void runCommand(CommandSender player, String[] args) {
        if (player.isOp()) {
            if (args[0].equalsIgnoreCase("off")){

                plugin.maintenanceEnable = false;
                new ConfigManager(plugin).setStatus();

                player.sendMessage(Messages.MAINTENANCE_DISABLED.getMessage());

            } else if (args.length > 1){

                player.sendMessage(Messages.WRONG_ARG.name() + ChatColor.DARK_RED + "<" + getSyntax() + ">");

            }
        } else {
            player.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }
    }
}
