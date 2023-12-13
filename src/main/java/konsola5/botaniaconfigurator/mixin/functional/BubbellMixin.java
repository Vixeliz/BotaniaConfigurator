package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import vazkii.botania.common.block.flower.functional.BubbellBlockEntity;

@Mixin(BubbellBlockEntity.class)
public class BubbellMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.bubbellManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 4),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;addMana(I)V")))
    private int configureCost1(int original) {
        return ConfigFile.bubbellManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -4),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;addMana(I)V")))
    private int configureCost2(int original) {
        return -ConfigFile.bubbellManaCost;
    }

    @ModifyConstant(method = "isValidBubbell",constant = @Constant(intValue = 4))
    private static int configureCost3(int original) {
        return ConfigFile.bubbellManaCost;
    }

    /**
     * @author KonSola5
     * @reason Make Bubbell Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.bubbellRange;
    }
}
