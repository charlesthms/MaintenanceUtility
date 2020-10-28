package fr.helirium.ServerUtility.utils;

import fr.helirium.ServerUtility.Main;
import org.bukkit.configuration.ConfigurationSection;

import java.util.UUID;

public class ConfigManager {

    private final Main plugin;

    public ConfigManager(Main mainInstance) {
        this.plugin = mainInstance;
    }

    public void readConfig() {

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("PlayersUUID");

        if (section != null) {
            for (String item : section.getKeys(true)) {

                String uuid = (String) section.get(item);
                Main.authorized.add(UUID.fromString(uuid));

            }
        }

    }

    public void writeConfig(String playername, UUID uuid, String path) {
        plugin.getConfig().set(path + playername, uuid.toString());
        plugin.saveConfig();
    }

    public void setStatus() {
        Boolean currentStatus = plugin.maintenanceEnable;
        plugin.getConfig().set("MaintenanceStatus", currentStatus);
        plugin.saveConfig();
    }

    public boolean getStatus() {
        return plugin.getConfig().getBoolean("MaintenanceStatus");
    }

}
