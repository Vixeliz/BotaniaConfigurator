package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.AgricarnationBlockEntity;

@Mixin(AgricarnationBlockEntity.class)
public class AgricarnationMixin {
    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 5),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/AgricarnationBlockEntity;getMana()I")),
            remap = false)
    private int configureCost(int constant) {
        return ConfigFile.agricarnationManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -5),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/AgricarnationBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original) {
        return -ConfigFile.agricarnationManaCost;
    }

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.agricarnationManaCapacity;
    }

    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.agricarnationRange);
    }
}

