package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.LooniumBlockEntity;

@Mixin(LooniumBlockEntity.class)
public class LooniumMixin {
    /**
     * @author KonSola5
     * @reason Make mana capacity configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.looniumManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 35000),
            remap = false)
    private int configureCost1(int original) {
        return ConfigFile.looniumManaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/LooniumBlockEntity;addMana(I)V"), remap = false)
    private void configureCost2(LooniumBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.looniumManaCost);
    }
}
