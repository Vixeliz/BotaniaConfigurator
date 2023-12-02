package konsola5.botaniaconfigurator;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.MOD_ID;

public class ConfigModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> ConfigFile.getScreen(parent, MOD_ID);
    }
}
