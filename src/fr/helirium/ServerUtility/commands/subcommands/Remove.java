package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class Remove extends SubCommand {

    private final Main plugin;

    public Remove(Main mainInstance) {
        this.plugin = mainInstance;
    }

    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getDescription() {
        return "Supprime la joueur de la liste de maintenance.";
    }

    @Override
    public String getSyntax() {
        return "/maintenance remove <player>";
    }

    @Override
    public void runCommand(CommandSender sender, String[] args) {

        if (sender.hasPermission("maintenance.admin")) {
            if (args.length == 2) {

                final String playername = args[1];
                final Player player1 = Bukkit.getPlayer(playername);

                if (player1 != null) {

                    final UUID uuid = player1.getUniqueId();

                    if (Main.getAuthorized().contains(uuid)) {

                        Main.getAuthorized().remove(uuid);
                        resetConfig(playername);
                        PlayerManager.kickNonAuhorized();

                        sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + playername + " supprimé de la liste de maintenance.");
                    } else {
                        sender.sendMessage(Messages.PREFIX.getMessage() + ChatColor.AQUA + playername + " n'est pas dans la liste de maintenance.");
                    }
                } else {
                    sender.sendMessage(Messages.PLAYER_OFFLINE.getMessage());
                }

            } else {
                sender.sendMessage(Messages.WRONG_ARG.getMessage() + getSyntax());
            }
        } else {
            sender.sendMessage(Messages.PLAYER_NONOP.getMessage());
        }
    }


    private void resetConfig(String playername) {
        plugin.getConfig().set("PlayersUUID." + playername, null);
        plugin.saveConfig();
    }


    @Override
    public ArrayList<String> getSubcommandArgs(CommandSender sender, String[] args) {
        if (args.length == 2) {
            ArrayList<String> subArgs = new ArrayList<>();
            subArgs.add("<pseudo>");
            return subArgs;
        }
        return null;
    }
}
