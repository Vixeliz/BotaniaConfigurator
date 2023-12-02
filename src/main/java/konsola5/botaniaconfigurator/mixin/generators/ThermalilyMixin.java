package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.block.flower.generating.ThermalilyBlockEntity;

@Mixin(ThermalilyBlockEntity.class)
public class ThermalilyMixin {
    private static final int[] COOLDOWN_ROLLS = ConfigFile.thermalilyCooldownList();
    private static final int COOLDOWN_ROLL_TOTAL;
    static {
        int acc = 0;
        for (var i : COOLDOWN_ROLLS) {
            acc += i;
        }
        COOLDOWN_ROLL_TOTAL = acc;
    }
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.thermalilyManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public static int rollNewCooldownStrength(RandomSource random) {
        var total = random.nextInt(COOLDOWN_ROLL_TOTAL);
        var index = 0;
        while (total >= COOLDOWN_ROLLS[index]) {
            total -= COOLDOWN_ROLLS[index];
            index++;
        }
        return index + 1;
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/FluidGeneratorBlockEntity;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/tags/TagKey;II)V"))
    private static void configureGenerationRates(Args args) {
        args.set(4, ConfigFile.thermalilyBurnTime);
        args.set(5, ConfigFile.thermalilyManaGenerationRate);
    }

    @ModifyConstant(method = "getCooldownTime", constant = @Constant(intValue = 400), remap = false)
    private int configureRadius(int constant){
        return ConfigFile.thermalilyCooldownFactor;
    }
}
