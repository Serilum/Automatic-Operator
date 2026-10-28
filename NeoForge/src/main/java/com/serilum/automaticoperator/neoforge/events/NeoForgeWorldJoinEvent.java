package com.serilum.automaticoperator.neoforge.events;

import com.serilum.automaticoperator.events.WorldJoinEvent;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.bus.api.SubscribeEvent;

public class NeoForgeWorldJoinEvent {
	@SubscribeEvent()
	public static void onSpawn(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		WorldJoinEvent.onPlayerLoggedIn(player.level(), player);
	}
}
