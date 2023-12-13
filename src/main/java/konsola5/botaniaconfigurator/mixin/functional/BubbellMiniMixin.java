package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.BubbellBlockEntity;

@Mixin(BubbellBlockEntity.Mini.class)
public class BubbellMiniMixin {
    /**
     * @author KonSola5
     * @reason Make Bubbell Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.bubbellRangeMini;
    }
}
