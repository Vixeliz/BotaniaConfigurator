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

    @ModifyVariable(method = "tickFlower", remap = false, at = @At(value = "STORE", target = "Lvazkii/botania/common/block/flower/functional/LooniumBlockEntity;getMana()I"), ordinal = 0)
    private int configureCost1(int mana) {
        return ConfigFile.looniumManaCost;
    }

    @Redirect(method = "spawnMob", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/LooniumBlockEntity;addMana(I)V"))
    private void configureCost2(LooniumBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.looniumManaCost);
    }
}
