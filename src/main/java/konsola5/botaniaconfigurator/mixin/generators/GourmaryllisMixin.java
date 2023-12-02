package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;
import vazkii.botania.common.block.flower.generating.GourmaryllisBlockEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Mixin(GourmaryllisBlockEntity.class)
public class GourmaryllisMixin {
    private static final double[] STREAK_MULTIPLIERS = ConfigFile.gourmaryllisStreakList();

    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.gourmaryllisDecays) {
            if (!Objects.requireNonNull(((GourmaryllisBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.gourmaryllisDecayTime) {
                    ((GourmaryllisBlockEntity) (Object) this).getLevel().destroyBlock(((GourmaryllisBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((GourmaryllisBlockEntity) (Object) this).getLevel(), ((GourmaryllisBlockEntity) (Object) this).getBlockPos())) {
                        ((GourmaryllisBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((GourmaryllisBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return Math.max(ConfigFile.gourmaryllisManaCapacity, getDigestingMana(ConfigFile.gourmaryllisMaxFoodValue, STREAK_MULTIPLIERS[STREAK_MULTIPLIERS.length - 1]));
    }

    /**
     * @author KonSola5
     * @reason Make Gourmaryllis' streak modifiable.
     */
    @Overwrite(remap = false)
    private int getMaxStreak() {
        return STREAK_MULTIPLIERS.length - 1;
    }

    @Shadow(remap = false) private int lastFoodCount;

    /**
     * @author KonSola5
     * @reason Make Gourmaryllis' streak modifiable.
     */
    @Overwrite(remap = false)
    private double getMultiplierForStreak(int index) {
        // special-case repeated first foods
        if (index == 0) {
            return 1.0 / ++lastFoodCount;
        } else {
            lastFoodCount = 1;
            return STREAK_MULTIPLIERS[Math.min(STREAK_MULTIPLIERS.length - 1, index)];
        }
    }

    /**
     * @author KonSola5
     * @reason Make Gourmaryllis' food mana factor modifiable.
     */
    @Overwrite(remap = false)
    private static int getDigestingMana(int foodValue, double streakFactor) {
        return (int) (foodValue * foodValue * ConfigFile.gourmaryllisFoodManaFactor * streakFactor);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.gourmaryllisRange;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.gourmaryllisRange;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.gourmaryllisRange;
    }

    /**
     * @author KonSola5
     * @reason Make Gourmaryllis' cooldown factor modifiable.
     */
    @Overwrite(remap = false)
    private static int getCooldown(int foodValue) {
        return foodValue * ConfigFile.gourmaryllisFoodCooldownFactor;
    }
}
