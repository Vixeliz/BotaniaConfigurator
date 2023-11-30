package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.SolegnoliaBlockEntity;

@Mixin(SolegnoliaBlockEntity.class)
public class SolegnoliaMixin {
    /**
     * @author KonSola5
     * @reason Make Radius modifiable.
     */
    @Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.solegnoliaRadius;
    }
}
