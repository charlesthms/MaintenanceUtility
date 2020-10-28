package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.ConfigManager;
import fr.helirium.ServerUtility.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;


public class On extends SubCommand {

    Main plugin;

    public static Long onTime;
    public static Integer maintenanceTime;
    public static Boolean format;

    public On(Main mainInstance) {
        this.plugin = mainInstance;
    }


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
        return "/maintenance on <duration>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")) {

            if (!plugin.maintenanceEnable) { // Si la maintenance n'est pas déjà activée
                if (args.length == 2) {

                    onTime = System.currentTimeMillis() / 1000 + hoursToSeconds(args[1]);
                    maintenanceTime = hoursToSeconds(args[1]);
                    format = true;

                    if (args[0].equalsIgnoreCase("on")) {

                        plugin.maintenanceEnable = true;
                        new ConfigManager(plugin).setStatus();
                        PlayerManager.kickNonAuhorized();

                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "maintenance off");
                        }, hoursToSeconds(args[1]) * 20L);

                        sender.sendMessage(Messages.PREFIX.getMessage() + Messages.MAINTENANCE_ENABLED.getMessage());

                    }
                } else if (args.length == 1) {

                    onTime = (long) -1;
                    format = false;

                    plugin.maintenanceEnable = true;
                    new ConfigManager(plugin).setStatus();
                    PlayerManager.kickNonAuhorized();

                    sender.sendMessage(Messages.PREFIX.getMessage() + Messages.MAINTENANCE_ENABLED.getMessage());
                } else {
                    sender.sendMessage(Messages.WRONG_ARG.getMessage() + ChatColor.DARK_RED + "<" + getSyntax() + ">");
                }
            } else {
                sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + "La maintenance est déjà activée.");
            }

        } else {
            sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }
    }


    public static Integer hoursToSeconds(String arg) {
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        String[] args = arg.split(":");

        if (args.length == 3) {
            hours = Integer.parseInt(args[0]) * 60 * 60;
            minutes = Integer.parseInt(args[1]) * 60;
            seconds = Integer.parseInt(args[2]);
        } else {
            System.out.println("Time format error. Please try again.");
        }

        return hours + minutes + seconds;
    }

    public static String secondsToHours(Integer seconds) {
        int sec = seconds % 60;
        int minutes = (seconds / 60) % 60;
        int hours = (seconds / 60) / 60;

        return timeFormat(hours) + ":" + timeFormat(minutes) + ":" + timeFormat(sec);
    }

    public static String timeFormat(Integer value) {
        return (value < 10 ? "0" : "") + value;
    }

    @Override
    public ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args) {
        if (args.length == 2) {
            ArrayList<String> subArgs = new ArrayList<>();
            subArgs.add("00:00:00");
            return subArgs;
        }
        return null;
    }
}

