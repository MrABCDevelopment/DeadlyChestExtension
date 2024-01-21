package me.dreamdevs.deadlychestextension;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public class DeadlyChest {

	private @Getter @Setter boolean instantKill;
	private @Getter @Setter double damage;
	private @Getter @Setter double chance;
	private @Getter @Setter List<PotionEffect> potionEffects;

}