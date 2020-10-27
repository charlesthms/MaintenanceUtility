package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import fr.helirium.ServerUtility.utils.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Remove extends SubCommand {

    private final Main plugin;

    public Remove (Main mainInstance) {
        this.plugin = mainInstance;
    }

    @Override
    public String getName() {
        return "Remove";
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
    public void runCommand(CommandSender player, String[] args) {

        if (args.length == 2){

            final String playername = args[1];
            final Player player1 = Bukkit.getPlayer(playername);

            if (player1 != null) {

                final UUID uuid = player1.getUniqueId();

                if (Main.getAuthorized().contains(uuid)) {

                    Main.getAuthorized().remove(uuid);
                    resetConfig(playername);
                    PlayerManager.kickNonAuhorized();

                    player.sendMessage(playername + " supprimé de la liste de maintenance.");
                } else {
                    player.sendMessage(playername + " n'est pas dans la liste de maintenance.");
                }
            } else {
                player.sendMessage(Messages.PLAYER_OFFLINE.getMessage());
            }

        } else {
            player.sendMessage(Messages.WRONG_ARG.getMessage() + getSyntax());
        }
    }

    private void resetConfig(String playername) {
        plugin.getConfig().set("PlayersUUID." + playername, null);
        plugin.saveConfig();
    }
}
