package com.natamus.automaticoperator.forge.events;

import com.natamus.automaticoperator.events.WorldJoinEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeWorldJoinEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeWorldJoinEvent.class);

		PlayerEvent.PlayerLoggedInEvent.BUS.addListener(ForgeWorldJoinEvent::onSpawn);
	}

	@SubscribeEvent()
	public static void onSpawn(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getEntity();
		WorldJoinEvent.onPlayerLoggedIn(player.level(), player);
	}
}
