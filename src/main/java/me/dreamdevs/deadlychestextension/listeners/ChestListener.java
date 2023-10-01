package me.dreamdevs.deadlychestextension.listeners;

import me.dreamdevs.deadlychestextension.DeadlyChestExtensionMain;
import me.dreamdevs.randomlootchest.api.events.ChestOpenEvent;
import me.dreamdevs.randomlootchest.api.utils.Util;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChestListener implements Listener {

	@EventHandler
	public void openChest(ChestOpenEvent event) {
		if (!DeadlyChestExtensionMain.instance.getDeadChestManager().getChestsValues().containsKey(event.getChestGame().getId())) {
			return;
		}

		double chance = DeadlyChestExtensionMain.instance.getDeadChestManager().getChestsValues().get(event.getChestGame().getId());
		if (Util.chance(chance)) {
			event.getPlayer().sendMessage(DeadlyChestExtensionMain.instance.getDeadChestManager().getRandomMessage());

			event.getPlayer().setHealth(0.0D);
		}
	}

}