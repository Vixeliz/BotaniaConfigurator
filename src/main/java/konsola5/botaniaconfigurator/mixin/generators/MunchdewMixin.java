package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.functional.TangleberrieBlockEntity;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;
import vazkii.botania.common.block.flower.generating.KekimurusBlockEntity;
import vazkii.botania.common.block.flower.generating.MunchdewBlockEntity;

import java.util.Objects;

@Mixin(MunchdewBlockEntity.class)
public class MunchdewMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.munchdewDecays) {
            if (!Objects.requireNonNull(((MunchdewBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.munchdewDecayTime) {
                    ((MunchdewBlockEntity) (Object) this).getLevel().destroyBlock(((MunchdewBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((MunchdewBlockEntity) (Object) this).getLevel(), ((MunchdewBlockEntity) (Object) this).getBlockPos())) {
                        ((MunchdewBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((MunchdewBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.munchdewManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 160),
            remap = false)
    private int configureGenerationRate1(int constant){
        return ConfigFile.munchdewManaGenerationRate;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/MunchdewBlockEntity;addMana(I)V"))
    private void configureGenerationRate2(MunchdewBlockEntity instance, int i) {
        instance.addMana(ConfigFile.munchdewManaGenerationRate);
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 4),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/MunchdewBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;getRandom()Lnet/minecraft/util/RandomSource;")))
    private int configureDelay1(int constant) {
        return ConfigFile.munchdewDelay;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 5),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/MunchdewBlockEntity;addMana(I)V")))
    private int configureDelay2(int constant) {
        return ConfigFile.munchdewDelay + 1;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 1600),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/MunchdewBlockEntity;addMana(I)V")))
    private int configureCooldown(int constant) {
        return ConfigFile.munchdewCooldown;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.munchdewRangeXZ, 0, -ConfigFile.munchdewRangeXZ);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.munchdewRangeXZ, ConfigFile.munchdewRangeY, ConfigFile.munchdewRangeXZ);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.munchdewRangeXZ;
    }
}
