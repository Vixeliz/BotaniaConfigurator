package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.BubbellBlockEntity;

@Mixin(BubbellBlockEntity.Mini.class)
public class BubbellMiniMixin {
    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.bubbellRangeMini);
    }
}
