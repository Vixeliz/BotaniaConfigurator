package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.block.flower.functional.ExoflameBlockEntity;

@Mixin(ExoflameBlockEntity.class)
public class ExoflameMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.exoflameManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -300),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/api/block/ExoflameHeatable;boostBurnTime()V"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ExoflameBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost(int original) {
        return ConfigFile.exoflameManaCost;
    }

    @ModifyArgs(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0),
            remap = false
    )
    private void configureMinusRange(Args args) {
        int a0 = args.get(0);
        int a1 = args.get(1);
        int a2 = args.get(2);
        args.set(0, -ConfigFile.exoflameRangeXZ);
        args.set(1, -ConfigFile.exoflameRangeY);
        args.set(2, -ConfigFile.exoflameRangeXZ);
    }

    @ModifyArgs(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1),
            remap = false
    )
    private void configurePlusRange(Args args) {
        int a0 = args.get(0);
        int a1 = args.get(1);
        int a2 = args.get(2);
        args.set(0, ConfigFile.exoflameRangeXZ);
        args.set(1, ConfigFile.exoflameRangeY);
        args.set(2, ConfigFile.exoflameRangeXZ);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange(int range) {
        return ConfigFile.exoflameRangeXZ;
    }
}
