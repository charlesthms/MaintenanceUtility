package fr.helirium.ServerUtility.commands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.subcommands.*;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();

    private final Main plugin;

    public CommandManager(Main mainInstance) {
        this.plugin = mainInstance;
        // Ajout des commandes dans la liste
        subCommands.add(new On());
        subCommands.add(new Off());
        subCommands.add(new Add(plugin));
        subCommands.add(new Remove());
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
            sender.sendMessage(Messages.WRONG_ARG.getMessage());
        }
        return true;
    }
}
