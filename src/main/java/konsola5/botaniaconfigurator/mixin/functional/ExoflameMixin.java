package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
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
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/api/block/ExoflameHeatable;boostBurnTime()V", remap = false),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ExoflameBlockEntity;addMana(I)V")))
    private int configureCost(int original) {
        return ConfigFile.exoflameManaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.exoflameRangeXZ, -ConfigFile.exoflameRangeY, -ConfigFile.exoflameRangeXZ);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.exoflameRangeXZ, ConfigFile.exoflameRangeY, ConfigFile.exoflameRangeXZ);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.exoflameRangeXZ;
    }
}
