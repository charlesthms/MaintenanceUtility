package fr.helirium.ServerUtility.constants;

import org.bukkit.ChatColor;

public enum Messages {

    PREFIX(ChatColor.GOLD + "[" + ChatColor.DARK_AQUA + "MaintenanceUtility" + ChatColor.GOLD + "] " + ChatColor.RESET),

    MAINTENANCE_ENABLED(ChatColor.AQUA + "Le serveur entre en maintenance."),
    MAINTENANCE_DISABLED(ChatColor.AQUA + "La maintenance est maintenant terminée ."),
    MAINTENANCE_CURRENT(ChatColor.AQUA + "Une maintenance est en cours.\n Merci de réessayer dans "),
    MAINTENANCE_LIST(ChatColor.AQUA + "----------- " + Messages.PREFIX.getMessage() + ChatColor.AQUA + " -----------"),
    MAINTENANCE_STATUS(ChatColor.AQUA + "Etat de la maintenance: "),

    WRONG_ARG(ChatColor.RED + "Erreur de syntaxe - "),
    MISSING_ARG(ChatColor.RED + "Argument manquant"),

    PLAYER_KICK(ChatColor.AQUA + "Le serveur entre en maintenance pour: "),
    PLAYER_KICK_UNDEFINED(ChatColor.AQUA + "Le serveur entre en maintenance pour une durée indéterminée."),
    PLAYER_MAINTENANCE_UNDEFINED(ChatColor.AQUA + "Une maintenance est en cours pour une durée indéterminé, rééssayer plus tard."),
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
