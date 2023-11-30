package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.HopperhockBlockEntity;

@Mixin(HopperhockBlockEntity.class)
public class HopperhockMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.hopperhockManaCapacity;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;getMana()I"), remap = false)
    public int modifyCost1(HopperhockBlockEntity instance) {
        return instance.getMana() - ConfigFile.hopperhockManaCostPerPull;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;addMana(I)V"), remap = false)
    public void modifyCost2(HopperhockBlockEntity instance, int mana) {
        instance.addMana(-ConfigFile.hopperhockManaCostPerPull);
    }

    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(((HopperhockBlockEntity)(Object)this).getMana() > ConfigFile.hopperhockManaCostPerPull - 1 ?
                ConfigFile.hopperhockRangeMana :
                ConfigFile.hopperhockRange);
    }
}
