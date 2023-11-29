package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.DaffomillBlockEntity;

@Mixin(DaffomillBlockEntity.class)
public class DaffomillMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getDaffomill().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(doubleValue = 0.05),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/helper/DelayHelper;canInteractWithImmediate(Lvazkii/botania/api/block_entity/SpecialFlowerBlockEntity;Lnet/minecraft/world/entity/item/ItemEntity;)Z")),
            remap = false)
    private double configureStrength(double original) {
        return ConfigFile.HANDLER.instance().getFunctional().getDaffomill().strength;
    }

    @ModifyVariable(method = "aabbForOrientation", at = @At("STORE"), ordinal = 3, remap = false)
    private int configureWidth(int w) {
        return ConfigFile.HANDLER.instance().getFunctional().getDaffomill().width;
    }

    @ModifyVariable(method = "aabbForOrientation", at = @At("STORE"), ordinal = 4, remap = false)
    private int configureHeight(int h) {
        return ConfigFile.HANDLER.instance().getFunctional().getDaffomill().height;
    }

    @ModifyVariable(method = "aabbForOrientation", at = @At("STORE"), ordinal = 5, remap = false)
    private int configureLength(int l) {
        return ConfigFile.HANDLER.instance().getFunctional().getDaffomill().length;
    }
}
