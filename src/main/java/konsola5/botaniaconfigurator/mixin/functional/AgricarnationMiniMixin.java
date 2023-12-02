package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.AgricarnationBlockEntity;

@Mixin(AgricarnationBlockEntity.Mini.class)
public class AgricarnationMiniMixin {
    /**
     * @author KonSola5
     * @reason Make Agricarnation Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.agricarnationRangeMini;
    }
}
