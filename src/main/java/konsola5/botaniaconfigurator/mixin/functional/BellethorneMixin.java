package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.BellethornBlockEntity;

@Mixin(BellethornBlockEntity.class)
public class BellethorneMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.bellethorneManaCapacity;
    }

    @Inject(method = "getManaCost",at = @At("RETURN"),remap = false,cancellable = true)
    private void configManaCost(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(ConfigFile.bellethorneManaCost);
    }

    @Inject(method = "getRange",at = @At("RETURN"),remap = false,cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir){
        cir.setReturnValue(ConfigFile.bellethorneRange);
    }
}
