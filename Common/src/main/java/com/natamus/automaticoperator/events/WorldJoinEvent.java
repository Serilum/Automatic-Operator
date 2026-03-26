package com.natamus.automaticoperator.events;

import com.mojang.authlib.GameProfile;
import com.natamus.automaticoperator.config.ConfigHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.NameAndId;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class WorldJoinEvent {
	public static void onPlayerLoggedIn(Level level, Player player) {
		if (!ConfigHandler.enableAutomaticOperator) {
			return;
		}

		if (level.isClientSide()) {
			return;
		}

		if (ConfigHandler.onlyRunOnDedicatedServers) {
			if (!level.getServer().isDedicatedServer()) {
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
		PlayerList playerList = level.getServer().getPlayerList();
		GameProfile playerGameProfile = serverPlayer.getGameProfile();
		NameAndId nameAndId = new NameAndId(playerGameProfile);
		if (playerList.isOp(nameAndId)) {
			return;
		}

		playerList.op(nameAndId);

		for (ServerPlayer listPlayer : playerList.getPlayers()) {
			listPlayer.sendSystemMessage(Component.translatable("commands.op.success", new Object[]{serverPlayer.getName()}));
		}
	}
}
