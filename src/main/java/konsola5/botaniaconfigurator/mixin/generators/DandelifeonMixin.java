package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;

@Debug(export = true)
@Mixin(DandelifeonBlockEntity.class)
public class DandelifeonMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.dandelifeonManaCapacity;
    }

    @ModifyConstant(method = "setBlockForGeneration",constant = @Constant(intValue = 60),
            slice = @Slice(
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z")))
    private static int configureGenerationRate(int constant){
        return ConfigFile.dandelifeonManaPerGeneration;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(longValue = 10),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/DandelifeonBlockEntity;getLevel()Lnet/minecraft/world/level/Level;", ordinal = 0),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/DandelifeonBlockEntity;getBlockPos()Lnet/minecraft/core/BlockPos;")))
    private static long configureSpeed(long constant){
        return ConfigFile.dandelifeonDelay;
    }
}
