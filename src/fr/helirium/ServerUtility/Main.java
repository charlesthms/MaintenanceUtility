package fr.helirium.ServerUtility;

import fr.helirium.ServerUtility.commands.CommandManager;
import fr.helirium.ServerUtility.listeners.PlayerLogin;
import fr.helirium.ServerUtility.utils.ConfigManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main extends JavaPlugin {

    public boolean maintenanceEnable = new ConfigManager(this).getStatus();
    public static Set<UUID> authorized = new HashSet<>();
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        setup();
        pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerLogin(this), this);

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

    private void setup() {
        ConfigManager configManager = new ConfigManager(this);
        configManager.readConfig();
        configManager.setStatus();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
