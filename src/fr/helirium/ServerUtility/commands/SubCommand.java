package fr.helirium.ServerUtility.commands;

import fr.helirium.ServerUtility.commands.subcommands.List;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public abstract class SubCommand {

    public abstract String getName();

    public abstract String getDescription();

    public abstract String getSyntax();

    public abstract void runCommand(CommandSender sender, String[] args);

    public abstract ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args);

}
