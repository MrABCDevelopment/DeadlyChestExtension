package me.dreamdevs.deadlychestextension.listeners;

import me.dreamdevs.deadlychestextension.DeadlyChest;
import me.dreamdevs.deadlychestextension.DeadlyChestExtensionMain;
import me.dreamdevs.randomlootchest.api.events.ChestOpenEvent;
import me.dreamdevs.randomlootchest.api.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class ChestListener implements Listener {

	@EventHandler
	public void openChest(ChestOpenEvent event) {
		if (!DeadlyChestExtensionMain.instance.getDeadChestManager().getChestsValues().containsKey(event.getChestGame().getId())) {
			return;
		}

		DeadlyChest deadlyChest = DeadlyChestExtensionMain.instance.getDeadChestManager().getChest(event.getChestGame().getId());

		double chance = deadlyChest.getChance();
		if (Util.chance(chance)) {
			event.getPlayer().sendMessage(DeadlyChestExtensionMain.instance.getDeadChestManager().getRandomMessage());

			if (deadlyChest.isInstantKill()) {
				event.getPlayer().setHealth(0.0D);
			}

			if (deadlyChest.getDamage() > 0) {
				event.getPlayer().setHealth(event.getPlayer().getHealth()-deadlyChest.getDamage());
			}

			if (!deadlyChest.getPotionEffects().isEmpty()) {
				deadlyChest.getPotionEffects().forEach(potionEffect -> event.getPlayer().addPotionEffect(potionEffect));
			}
		}
	}

}