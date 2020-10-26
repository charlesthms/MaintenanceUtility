package fr.helirium.ServerUtility;

import fr.helirium.ServerUtility.commands.CommandManager;
import fr.helirium.ServerUtility.listeners.PlayerLogin;
import fr.helirium.ServerUtility.utils.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main extends JavaPlugin {

    public static boolean maintenanceEnable = false;
    public static Set<UUID> authorized = new HashSet<>();
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        readUUID();

        pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerLogin(), this);

        getCommand("maintenance").setExecutor(new CommandManager(this));
        loadConfig();
    }

    @Override
    public void onDisable() {
        System.out.println("off");
    }

    public static Set<UUID> getAuthorized() {
        return authorized;
    }

    private void readUUID() {
        new ConfigManager(this).readConfig();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
