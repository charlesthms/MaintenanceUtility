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

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("PlayerUUID");

        for (String item : section.getKeys(true)){

            String uuid = (String) section.get(item);
            Main.authorized.add(UUID.fromString(uuid));

        }
    }

}
