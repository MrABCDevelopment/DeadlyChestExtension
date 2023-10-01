package me.dreamdevs.deadlychestextension.managers;

import lombok.Getter;
import me.dreamdevs.deadlychestextension.DeadlyChestExtensionMain;
import me.dreamdevs.randomlootchest.api.utils.ColourUtil;
import me.dreamdevs.randomlootchest.api.utils.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeadChestManager {

	private @Getter final Map<String, Double> chestsValues;
	private final FileConfiguration config;
	private @Getter final List<String> messages;

	public DeadChestManager(DeadlyChestExtensionMain extension) {
		chestsValues = new HashMap<>();
		messages = new ArrayList<>();
		config = extension.getConfig();
		load();
	}

	public void load() {
		chestsValues.clear();
		messages.clear();

		config.getStringList("Dead-Messages").stream().map(ColourUtil::colorize).forEach(messages::add);

		ConfigurationSection section = config.getConfigurationSection("Chests");
		for(String key : section.getKeys(false)) {
			chestsValues.put(key, section.getDouble(key));
		}
	}

	public String getRandomMessage() {
		return messages.get(Util.randomSlot(messages.size()));
	}

}