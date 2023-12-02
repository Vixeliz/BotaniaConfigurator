package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.generating.GourmaryllisBlockEntity;
import vazkii.botania.common.block.flower.generating.RafflowsiaBlockEntity;

@Debug(export = true)
@Mixin(RafflowsiaBlockEntity.class)
public class RafflowsiaMixin {
    private static final int[] STREAK_OUTPUTS = ConfigFile.rafflowsiaStreakList();

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
