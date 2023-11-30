package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.RannuncarpusBlockEntity;

@Mixin(RannuncarpusBlockEntity.Mini.class)
public class RannucarpusMiniMixin {
    /**
     * @author KonSola5
     * @reason Make Place Range modifiable.
     */
    @Overwrite(remap = false)
    public int getPlaceRange() {
        return ((RannuncarpusBlockEntity.Mini)(Object)this).getMana() > 0 ?
                ConfigFile.rannucarpusPlacementRangeXZManaMini:
                ConfigFile.rannucarpusPlacementRangeXZMini;
    }

    /**
     * @author KonSola5
     * @reason Make Vertical Place Range modifiable.
     */
    @Overwrite(remap = false)
    public int getVerticalPlaceRange() {
        return ConfigFile.rannucarpusPlacementRangeYMini;
    }
}
