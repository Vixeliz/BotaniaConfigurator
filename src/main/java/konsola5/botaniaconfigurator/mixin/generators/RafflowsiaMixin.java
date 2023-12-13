package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.RafflowsiaBlockEntity;

import java.util.Objects;

@Mixin(RafflowsiaBlockEntity.class)
public class RafflowsiaMixin {
    private static final int[] STREAK_OUTPUTS = ConfigFile.rafflowsiaStreakList();

    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.rafflowsiaDecays) {
            if (!Objects.requireNonNull(((RafflowsiaBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.rafflowsiaDecayTime) {
                    ((RafflowsiaBlockEntity) (Object) this).getLevel().destroyBlock(((RafflowsiaBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((RafflowsiaBlockEntity) (Object) this).getLevel(), ((RafflowsiaBlockEntity) (Object) this).getBlockPos())) {
                        ((RafflowsiaBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((RafflowsiaBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return Math.max(ConfigFile.rafflowsiaManaCapacity, getValueForStreak(STREAK_OUTPUTS[STREAK_OUTPUTS.length - 1]));
    }

    /**
     * @author KonSola5
     * @reason Make Rafflowsia's streak modifiable.
     */
    @Overwrite(remap = false)
    private int getMaxStreak() {
        return STREAK_OUTPUTS.length - 1;
    }

    @Shadow(remap = false) private int lastFlowerCount;

    /**
     * @author KonSola5
     * @reason Make Rafflowsia's streak modifiable.
     */
    @Overwrite(remap = false)
    private int getValueForStreak(int index) {
        // special-case repeated first flowers
        if (index != 0) {
            lastFlowerCount = 0;
        }
        return STREAK_OUTPUTS[index] / ++lastFlowerCount;
    }

    @ModifyConstant(method = "tickFlower", remap = false, constant = @Constant(intValue = 11))
    private int configureRange1(int constant) {
        return ConfigFile.rafflowsiaRange * 2 + 1;
    }

    @ModifyConstant(method = "tickFlower", remap = false, constant = @Constant(intValue = 5))
    private int configureRange2(int constant) {
        return ConfigFile.rafflowsiaRange;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"),
            index = 1)
    private int configureRange3(int range) {
        return ConfigFile.rafflowsiaRange;
    }
}
