package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.ClayconiaBlockEntity;

@Mixin(ClayconiaBlockEntity.Mini.class)
public class ClayconiaMiniMixin {
    @Inject(method = "getRange",
            at = @At("HEAD"),remap = false,cancellable = true)
    private void configureRangeXZ(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.HANDLER.instance().getFunctional().getClayconia().rangeXZMini);
    }

    @Inject(method = "getRangeY",
            at = @At("HEAD"),remap = false,cancellable = true)
    private void configureRangeY(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.HANDLER.instance().getFunctional().getClayconia().rangeYMini);
    }
}
