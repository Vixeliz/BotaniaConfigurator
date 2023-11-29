package konsola5.botaniaconfigurator;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotaniaConfigurator implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("botaniaconfigurator");

	public static final String MOD_ID = "botaniaconfigurator";

	@Override
	public void onInitialize() {
		try {
//			BotaniaConfiguratorConfig.cleanupOldConfig();
			ConfigFile.HANDLER.load();
//			BotaniaConfiguratorConfig.HANDLER.instance().validate();
		} catch (Exception ex) {
			LOGGER.error("Error loading BotaniaConfigurator config, restoring defaults!", ex);
		}
		ConfigFile.HANDLER.save();
		LOGGER.info("Unharcoding Botania by leeching into literally every Botania flower.");
	}
}