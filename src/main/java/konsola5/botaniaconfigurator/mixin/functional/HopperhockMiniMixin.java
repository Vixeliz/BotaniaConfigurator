package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.HopperhockBlockEntity;

@Mixin(HopperhockBlockEntity.Mini.class)
public class HopperhockMiniMixin {

    /**
     * @author KonSola5
     * @reason Make Hopperhock Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ((HopperhockBlockEntity.Mini)(Object)this).getMana() > ConfigFile.hopperhockManaCostPerPull - 1 ?
                ConfigFile.hopperhockRangeManaMini :
                ConfigFile.hopperhockRangeMini;
    }

}