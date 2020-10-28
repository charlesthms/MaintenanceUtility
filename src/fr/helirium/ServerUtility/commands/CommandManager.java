package fr.helirium.ServerUtility.commands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.subcommands.*;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;


import java.util.ArrayList;

public class CommandManager implements CommandExecutor, TabCompleter {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    private ArrayList<String> subArgs = new ArrayList<>();

    private final Main plugin;

    public CommandManager(Main mainInstance) {
        this.plugin = mainInstance;
        // Ajout des commandes dans la liste
        subCommands.add(new On(plugin));
        subCommands.add(new Off(plugin));
        subCommands.add(new Add(plugin));
        subCommands.add(new Remove(plugin));
        subCommands.add(new Status(plugin));
        subCommands.add(new List());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {

        if (args.length > 0) {

            for (SubCommand subCommand : subCommands){
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    subCommand.runCommand(sender, args);
                }
            }

        } else {
            sender.sendMessage("---------- " + Messages.PREFIX.getMessage() + "----------");

            // Display all the commands syntax + description
            for (SubCommand subCommand : subCommands) {
                sender.sendMessage(ChatColor.AQUA + subCommand.getSyntax() + " - " + subCommand.getDescription());
            }
            sender.sendMessage("-----------------------------");
        }
        return true;
    }

    @Override
    public java.util.List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        for (SubCommand subCommand : subCommands) {
            subArgs.add(subCommand.getName());
        }

        if (args.length == 1) {
            // If argument exist then return the suggestions
            return subArgs;
        } else if (args.length >= 2) {

            for (SubCommand subCommand : subCommands){
                if (args[0].equalsIgnoreCase(subCommand.getName())) {
                    return subCommand.getSubcommandArgs(sender, args);
                }
            }

        }

        return null;
    }
}
