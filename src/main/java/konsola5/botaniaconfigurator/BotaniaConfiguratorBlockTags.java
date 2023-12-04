package konsola5.botaniaconfigurator;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.MOD_ID;

public class BotaniaConfiguratorBlockTags {
    public static final TagKey<Block> CLAYCONIA_CONVERTABLE = TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, "clayconia_convertable"));
}
