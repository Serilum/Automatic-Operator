package com.natamus.automaticoperator.forge.events;

import com.natamus.automaticoperator.events.WorldJoinEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeWorldJoinEvent {
	@SubscribeEvent()
	public static void onSpawn(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		WorldJoinEvent.onPlayerLoggedIn(player.level(), player);
	}
}
