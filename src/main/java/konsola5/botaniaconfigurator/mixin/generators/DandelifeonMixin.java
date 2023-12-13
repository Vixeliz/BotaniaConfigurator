package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;

import java.util.Objects;

@Mixin(DandelifeonBlockEntity.class)
public class DandelifeonMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.dandelifeonDecays) {
            if (!Objects.requireNonNull(((DandelifeonBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.dandelifeonDecayTime) {
                    ((DandelifeonBlockEntity) (Object) this).getLevel().destroyBlock(((DandelifeonBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((DandelifeonBlockEntity) (Object) this).getLevel(), ((DandelifeonBlockEntity) (Object) this).getBlockPos())) {
                        ((DandelifeonBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((DandelifeonBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                    }
                    ci.cancel();
                }
            }
        }
    }

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
