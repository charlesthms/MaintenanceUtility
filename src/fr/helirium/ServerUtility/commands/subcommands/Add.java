package fr.helirium.ServerUtility.commands.subcommands;

import fr.helirium.ServerUtility.Main;
import fr.helirium.ServerUtility.commands.SubCommand;
import fr.helirium.ServerUtility.constants.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Add extends SubCommand {

    private final Main plugin;

    public Add (Main mainInstance) {
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
    public void runCommand(CommandSender player, String[] args) {

        if (args.length == 2) {

            final String playername = args[1];
            final Player player1 = Bukkit.getPlayer(playername);

            if (player1 != null) {

                final UUID uuid = player1.getUniqueId();

                if (!Main.getAuthorized().contains(uuid)) {

                    Main.getAuthorized().add(uuid);
                    writeConfig(playername, uuid);

                    player.sendMessage(playername + " ajouté à la liste de maintenance.");
                } else {
                    player.sendMessage(playername + " est déjà dans la liste de maintenance.");
                }
            } else {
                player.sendMessage(Messages.PLAYER_OFFLINE.getMessage());
            }


        } else {

            player.sendMessage(Messages.WRONG_ARG.getMessage() + ChatColor.DARK_RED + "<" + getSyntax() + ">");

        }
    }

    private void writeConfig(String playername, UUID uuid) {
        plugin.getConfig().set("PlayersUUID." + playername, uuid.toString());
        plugin.saveConfig();
    }
}
