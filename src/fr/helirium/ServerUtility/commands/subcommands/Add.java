package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Add extends SubCommand {

    private final Main plugin;

    public Add(Main mainInstance) {
        this.plugin = mainInstance;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "Ajoute un joueur a la liste des membres de maintenance";
    }

    @Override
    public String getSyntax() {
        return "/maintenance add <player>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")){
            if (args.length == 2) {
                final String playername = args[1];
                final Player player1 = Bukkit.getPlayer(playername);

                if (player1 != null) {

                    final UUID uuid = player1.getUniqueId();

                    if (!Main.getAuthorized().contains(uuid)) {

                        Main.getAuthorized().add(uuid);
                        ConfigManager cm = new ConfigManager(plugin);
                        cm.writeConfig(playername, uuid, "PlayersUUID.");

                        sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + playername + " ajouté à la liste de maintenance.");
                    } else {
                        sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + playername + " est déjà dans la liste de maintenance.");
                    }
                } else {
                    sender.sendMessage(Messages.PLAYER_OFFLINE.getMessage());
                }
            } else {
                sender.sendMessage(Messages.WRONG_ARG.getMessage() + ChatColor.DARK_RED + "<" + getSyntax() + ">");
            }
        } else{
            sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }
    }


    /*public void writeConfig(String playername, UUID uuid) {
        plugin.getConfig().set("PlayersUUID." + playername, uuid.toString());
        plugin.saveConfig();
    }*/


    @Override
    public ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args) {
        return null;
    }
}
