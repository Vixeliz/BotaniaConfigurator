package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.block.flower.generating.DandelifeonBlockEntity;
import vazkii.botania.common.block.flower.generating.FluidGeneratorBlockEntity;
import vazkii.botania.common.block.flower.generating.ThermalilyBlockEntity;

import java.util.Objects;

@Mixin(ThermalilyBlockEntity.class)
public abstract class ThermalilyMixin extends FluidGeneratorBlockEntity {
    private static final int[] COOLDOWN_ROLLS = ConfigFile.thermalilyCooldownList();
    private static final int COOLDOWN_ROLL_TOTAL;
    static {
        int acc = 0;
        for (var i : COOLDOWN_ROLLS) {
            acc += i;
        }
        COOLDOWN_ROLL_TOTAL = acc;
    }
    private int passiveDecayTicks;

    protected ThermalilyMixin(BlockEntityType<?> type, BlockPos pos, BlockState state, TagKey<Fluid> consumedFluid, int startBurnTime, int manaPerTick) {
        super(type, pos, state, consumedFluid, startBurnTime, manaPerTick);
    }

    // Thermalily doesn't have a tickFlower method.
    @Override
    public void tickFlower() {
        super.tickFlower();

        if (ConfigFile.thermalilyDecays) {
            if (!Objects.requireNonNull(getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.thermalilyDecayTime) {
                    getLevel().destroyBlock(getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(getLevel(), getBlockPos())) {
                        getLevel().setBlockAndUpdate(getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                    }
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
