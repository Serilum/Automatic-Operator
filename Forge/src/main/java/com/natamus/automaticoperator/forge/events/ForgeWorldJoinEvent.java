package com.natamus.automaticoperator.forge.events;

import com.natamus.automaticoperator.events.WorldJoinEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeWorldJoinEvent {
	@SubscribeEvent()
	public void onSpawn(PlayerEvent.PlayerLoggedInEvent e) {
		Player player = e.getPlayer();
		WorldJoinEvent.onPlayerLoggedIn(player.getLevel(), player);
	}
}
