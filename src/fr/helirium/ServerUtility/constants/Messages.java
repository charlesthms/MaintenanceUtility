package fr.helirium.ServerUtility.constants;

import org.bukkit.ChatColor;

public enum Messages {

    MAINTENANCE_ENABLED(ChatColor.LIGHT_PURPLE + "La maintenance est maintenant activée."),
    MAINTENANCE_DISABLED(ChatColor.LIGHT_PURPLE + "La maintenance est maintenant desactivée."),

    WRONG_ARG(ChatColor.RED +"Erreur de syntaxe - "),
    MISSING_ARG(ChatColor.RED + "Argument manquant"),

    PLAYER_KICK(ChatColor.AQUA + "Le serveur entre en maintenance."),
    PLAYER_MAINTENANCE(ChatColor.AQUA + "Le serveur est en maintenance, rééssayer plus tard."),
    PLAYER_NONOP(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande."),
    PLAYER_OFFLINE(ChatColor.RED + "Le joueur n'est pas connecté."),

    UUID_NULL(ChatColor.RED + "La liste est vide.");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
