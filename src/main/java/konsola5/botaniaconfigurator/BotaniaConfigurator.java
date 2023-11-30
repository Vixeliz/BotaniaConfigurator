package konsola5.botaniaconfigurator;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotaniaConfigurator implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("botaniaconfigurator");

	public static final String MOD_ID = "botaniaconfigurator";

	@Override
	public void onInitialize() {
		MidnightConfig.init("botaniaconfigurator", ConfigFile.class);
		LOGGER.info("Unharcoding Botania by leeching into literally every Botania flower.");
	}
}