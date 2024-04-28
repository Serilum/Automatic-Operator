package com.natamus.automaticoperator.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.automaticoperator.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean enableAutomaticOperator = false;
	@Entry public static boolean onlyRunOnDedicatedServers = true;
	@Entry public static boolean onlyMakeSpecificPlayerNamesOP = false;
	@Entry public static String specificOperatorPlayerNames = "Player1,Player2";

	public static void initConfig() {
		configMetaData.put("enableAutomaticOperator", Arrays.asList(
			"If the mod should be enabled. Turned off by default to prevent unwanted behaviour in modpacks."
		));
		configMetaData.put("onlyRunOnDedicatedServers", Arrays.asList(
			"If the mod should only run on dedicated servers."
		));
		configMetaData.put("onlyMakeSpecificPlayerNamesOP", Arrays.asList(
			"If enabled, only player names specified in 'specificOperatorPlayerNames' will be made operators. If disabled, everyone will."
		));
		configMetaData.put("specificOperatorPlayerNames", Arrays.asList(
			"The specific names of players that the mod automatically makes operators. Seperated by a comma ( , )."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}