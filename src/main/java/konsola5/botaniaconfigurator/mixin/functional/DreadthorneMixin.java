package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.DreadthornBlockEntity;

@Mixin(DreadthornBlockEntity.class)
public class DreadthorneMixin {
    @Inject(method = "getManaCost",at = @At("RETURN"),remap = false,cancellable = true)
    private void configManaCost(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(ConfigFile.HANDLER.instance().getFunctional().getDreadthorne().manaCost);
    }
}
