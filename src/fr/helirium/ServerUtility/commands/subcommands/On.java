package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.ConfigManager;
import fr.helirium.ServerUtility.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


public class On extends SubCommand {

    Main plugin;

    public static Long onTime;

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

        if (args.length == 2) {
            // Temps au moment de l'éxécution de la commande + le délai
            onTime = System.currentTimeMillis() / 1000 + hoursToSeconds(args[1]);

            if (sender.isOp()) {

                if (args[0].equalsIgnoreCase("on")) {

                    plugin.maintenanceEnable = true;
                    new ConfigManager(plugin).setStatus();
                    PlayerManager.kickNonAuhorized();

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "maintenance off");
                    }, hoursToSeconds(args[1]) * 20L);

                    sender.sendMessage(Messages.MAINTENANCE_ENABLED.getMessage());
                }
            } else {
                sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
            }

        } else if (args.length == 1) {
            plugin.maintenanceEnable = true;
            new ConfigManager(plugin).setStatus();
            PlayerManager.kickNonAuhorized();

            sender.sendMessage(Messages.MAINTENANCE_ENABLED.getMessage());
        } else {
            sender.sendMessage(Messages.WRONG_ARG.getMessage() + ChatColor.DARK_RED + "<" + getSyntax() + ">");
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

}

