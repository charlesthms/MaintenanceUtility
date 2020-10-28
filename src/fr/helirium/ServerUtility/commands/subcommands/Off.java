package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class Off extends SubCommand {

    Main plugin;

    public Off(Main mainInstance) {
        this.plugin = mainInstance;
    }

    @Override
    public String getName() {
        return "off";
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
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")) {

            if (plugin.maintenanceEnable) {
                if (args[0].equalsIgnoreCase("off")) {

                    plugin.maintenanceEnable = false;
                    new ConfigManager(plugin).setStatus();

                    sender.sendMessage(Messages.PREFIX.getMessage() + Messages.MAINTENANCE_DISABLED.getMessage());

                } else if (args.length > 1) {

                    sender.sendMessage(Messages.WRONG_ARG.name() + ChatColor.DARK_RED + "<" + getSyntax() + ">");

                }
            } else {
                sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + "La maintenance est déjà desactivée.");
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
