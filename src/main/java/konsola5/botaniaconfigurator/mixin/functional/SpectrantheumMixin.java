package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.SpectranthemumBlockEntity;

@Mixin(SpectranthemumBlockEntity.class)
public class SpectrantheumMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getSpectrantheum().manaCapacity;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0), remap = false)
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getSpectrantheum().pickupRange;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1), remap = false)
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getSpectrantheum().pickupRange;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange3(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getSpectrantheum().pickupRange;
    }

    // ""No possible signatures for this injector"". Press X to doubt
    @ModifyVariable(method = "tickFlower", at = @At("STORE"), name = "cost", remap = false)
    private double configureCostMultiplier(double x) {
        return x * ConfigFile.HANDLER.instance().getFunctional().getSpectrantheum().manaCostMultiplier;
    }


}
