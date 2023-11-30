package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.TangleberrieBlockEntity;

@Mixin(TangleberrieBlockEntity.Mini.class)
public class TangleberrieMiniMixin {


    /**
     * @author KonSola5
     * @reason Make Range configurable.
     */
    @Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.tangleberrieRangeMini;
    }

    /**
     * @author KonSola5
     * @reason Make max distance configurable.
     */
    @Overwrite(remap = false)
    public double getMaxDistance() {
        return ConfigFile.tangleberrieMaxDistanceMini;
    }
}
