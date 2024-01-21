package me.dreamdevs.deadlychestextension.managers;

import lombok.Getter;
import me.dreamdevs.deadlychestextension.DeadlyChest;
import me.dreamdevs.deadlychestextension.DeadlyChestExtensionMain;
import me.dreamdevs.randomlootchest.api.utils.ColourUtil;
import me.dreamdevs.randomlootchest.api.utils.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class DeadChestManager {

	private @Getter final Map<String, DeadlyChest> chestsValues;
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
		for (String key : section.getKeys(false)) {
			DeadlyChest deadlyChest = new DeadlyChest();

			List<PotionEffect> potionEffects = new LinkedList<>();
			deadlyChest.setInstantKill(section.getBoolean(key+".InstantKill", false));
			deadlyChest.setDamage(section.getDouble(key+".Damage", 0.0));
			deadlyChest.setChance(section.getDouble(key+".Chance", 0.5));

			if (!section.getStringList(key+".PotionEffects").isEmpty()) {
				for (String string : section.getStringList(key+".PotionEffects")) {
					String[] strings = string.split(":");

					Optional<PotionEffect> potionEffectOptional = Optional.of(new PotionEffect(PotionEffectType.getByName(strings[0].toUpperCase()), Integer.parseInt(strings[1]), Integer.parseInt(strings[2])));
					potionEffectOptional.ifPresent(potionEffects::add);
				}
			}

			deadlyChest.setPotionEffects(potionEffects);

			chestsValues.put(key, deadlyChest);
		}
	}

	public String getRandomMessage() {
		return messages.get(Util.randomSlot(messages.size()));
	}

	public DeadlyChest getChest(String id) {
		return Optional.ofNullable(id).map(chestsValues::get).orElse(null);
	}

}