package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;

import java.lang.annotation.Target;
import java.util.Objects;

@Mixin(DandelifeonBlockEntity.class)
public class DandelifeonMixin extends GeneratingFlowerBlockEntity {
    @Shadow
    private int radius;

    private int passiveDecayTicks;

    public DandelifeonMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

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

    @Override
    public int getColor() {
        return 0x9c0a7e;
    }

    @ModifyConstant(method = "setBlockForGeneration",constant = @Constant(intValue = 60),
            slice = @Slice(
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z")))
    private static int configureGenerationRate(int constant){
        return ConfigFile.dandelifeonManaPerGeneration;
    }


    /**
     * @author Vixeliz
     * @reason Am unsure how to use modify constant in this case so have jank :) (to make speed configurrable)
     */
    @Overwrite(remap = false)
    private boolean shouldTick(long gameTime) {
        return (!overgrowthBoost && gameTime % ConfigFile.dandelifeonDelay == 0 || overgrowthBoost && (gameTime + ConfigFile.dandelifeonDelay / 2) % ConfigFile.dandelifeonDelay == 0)
                && getLevel().hasNeighborSignal(getBlockPos());
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), radius);
    }
}
