package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
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

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;getMana()I"))
    public int modifyCost1(HopperhockBlockEntity instance) {
        return instance.getMana() - ConfigFile.hopperhockManaCostPerPull;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HopperhockBlockEntity;addMana(I)V"))
    public void modifyCost2(HopperhockBlockEntity instance, int mana) {
        instance.addMana(-ConfigFile.hopperhockManaCostPerPull);
    }

    /**
     * @author KonSola5
     * @reason Make Hopperhock Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ((HopperhockBlockEntity)(Object)this).getMana() > ConfigFile.hopperhockManaCostPerPull - 1 ?
                ConfigFile.hopperhockRangeMana :
                ConfigFile.hopperhockRange;
    }
}
