package me.dreamdevs.deadlychestextension;

import lombok.Getter;
import me.dreamdevs.deadlychestextension.listeners.ChestListener;
import me.dreamdevs.deadlychestextension.managers.DeadChestManager;
import me.dreamdevs.randomlootchest.api.extensions.Extension;
import me.dreamdevs.randomlootchest.api.utils.Util;

public class DeadlyChestExtensionMain extends Extension {

	public static DeadlyChestExtensionMain instance;
	private @Getter DeadChestManager deadChestManager;

	@Override
	public void onExtensionEnable() {
		instance = this;

		saveDefaultConfig();
		this.deadChestManager = new DeadChestManager(this);
		setup();
	}

	@Override
	public void onExtensionDisable() {
		Util.sendPluginMessage("&cDisabling DeadlyChestExtension...");
	}

	@Override
	public void reloadConfig() {
		super.reloadConfig();

		setup();
	}

	private void setup() {
		deadChestManager.load();
		registerListener(new ChestListener());
		Util.sendPluginMessage("&aLoaded all variables and setup DeadlyChestExtension!");
	}
}