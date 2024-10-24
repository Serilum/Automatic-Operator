package com.natamus.automaticoperator.neoforge.events;

import com.natamus.automaticoperator.events.WorldJoinEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeWorldJoinEvent {
	@SubscribeEvent()
	public static void onSpawn(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		WorldJoinEvent.onPlayerLoggedIn(player.level(), player);
	}
}
