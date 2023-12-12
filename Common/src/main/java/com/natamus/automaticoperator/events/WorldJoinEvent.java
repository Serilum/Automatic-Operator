package com.natamus.automaticoperator.events;

import com.mojang.authlib.GameProfile;
import com.natamus.automaticoperator.config.ConfigHandler;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WorldJoinEvent {
	public static void onPlayerLoggedIn(Level world, Player player) {
		if (!ConfigHandler.enableAutomaticOperator) {
			return;
		}

		if (world.isClientSide) {
			return;
		}

		if (ConfigHandler.onlyRunOnDedicatedServers) {
			if (!world.getServer().isDedicatedServer()) {
				return;
			}
		}
		
		if (ConfigHandler.onlyMakeSpecificPlayerNamesOP) {
			boolean foundName = false;

			String playerName = player.getName().getString();
			for (String configName : ConfigHandler.specificOperatorPlayerNames.split(",")) {
				if (playerName.equals(configName.trim())) {
					foundName = true;
					break;
				}
			}

			if (!foundName) {
				return;
			}
		}

		ServerPlayer serverPlayer = (ServerPlayer)player;
		PlayerList playerList = serverPlayer.getServer().getPlayerList();
		GameProfile playerGameProfile = serverPlayer.getGameProfile();
		if (playerList.isOp(playerGameProfile)) {
			return;
		}

		playerList.op(playerGameProfile);

		for (ServerPlayer listPlayer : playerList.getPlayers()) {
			listPlayer.sendMessage(new TranslatableComponent("commands.op.success", new Object[]{serverPlayer.getName()}), listPlayer.getUUID());
		}
	}
}
