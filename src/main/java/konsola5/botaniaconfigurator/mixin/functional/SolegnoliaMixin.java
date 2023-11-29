package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.SolegnoliaBlockEntity;

@Mixin(SolegnoliaBlockEntity.class)
public class SolegnoliaMixin {
    /**
     * @author KonSola5
     * @reason Make Radius modifiable.
     */
    @Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.HANDLER.instance().getFunctional().getSolegnolia().radius;
    }
}
