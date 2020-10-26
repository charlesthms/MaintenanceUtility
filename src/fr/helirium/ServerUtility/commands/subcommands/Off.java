package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Off extends SubCommand {

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

                Main.maintenanceEnable = false;
                player.sendMessage(Messages.MAINTENANCE_DISABLED.getMessage());

            } else if (args.length > 1){

                player.sendMessage(Messages.WRONG_ARG.name() + ChatColor.DARK_RED + "<" + getSyntax() + ">");

            }
        } else {
            player.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }
    }

    /*@Override
    public void runCommand(Player player, String[] args) {

        if (player.isOp()) {
            if (args[0].equalsIgnoreCase("off")){

                Main.maintenanceEnable = false;
                player.sendMessage(Messages.MAINTENANCE_DISABLED.getMessage());

            } else if (args.length > 1){

                player.sendMessage(Messages.WRONG_ARG.name() + ChatColor.DARK_RED + "<" + getSyntax() + ">");

            }
        } else {
            player.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }

    }*/
}
