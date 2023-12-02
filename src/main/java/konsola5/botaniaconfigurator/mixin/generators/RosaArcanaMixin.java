package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;
import vazkii.botania.common.block.flower.generating.RosaArcanaBlockEntity;

import java.util.Objects;

@Mixin(RosaArcanaBlockEntity.class)
public class RosaArcanaMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.rosaArcanaDecays) {
            if (!Objects.requireNonNull(((RosaArcanaBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.rosaArcanaDecayTime) {
                    ((RosaArcanaBlockEntity) (Object) this).getLevel().destroyBlock(((RosaArcanaBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((RosaArcanaBlockEntity) (Object) this).getLevel(), ((RosaArcanaBlockEntity) (Object) this).getBlockPos())) {
                        ((RosaArcanaBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((RosaArcanaBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.rosaArcanaManaCapacity;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.rosaArcanaRange, -ConfigFile.rosaArcanaRange, -ConfigFile.rosaArcanaRange);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.rosaArcanaRange + 1, ConfigFile.rosaArcanaRange + 1, ConfigFile.rosaArcanaRange + 1);
    }

    @ModifyConstant(method = "tickFlower", remap = false, constant = @Constant(intValue = 50))
    private int configureManaGenerationRate(int constant){
        return ConfigFile.rosaArcanaManaGenerationRate;
    }



    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.rosaArcanaRange;
    }
}
