package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.common.block.flower.functional.SpectranthemumBlockEntity;

@Mixin(SpectranthemumBlockEntity.class)
public class SpectrantheumMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.spectrantheumManaCapacity;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.spectrantheumPickupRange;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.spectrantheumPickupRange;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.spectrantheumPickupRange;
    }

    // ""No possible signatures for this injector"". Press X to doubt
    @ModifyVariable(method = "tickFlower", at = @At("STORE"), remap = false, name = "cost")
    private double configureCostMultiplier(double x) {
        return x * ConfigFile.spectrantheumManaCostMultiplier;
    }


}
