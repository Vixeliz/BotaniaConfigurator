package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.NarslimmusBlockEntity;

import java.util.Objects;

@Mixin(NarslimmusBlockEntity.class)
public class NarslimmusMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.narslimmusDecays) {
            if (!Objects.requireNonNull(((NarslimmusBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.narslimmusDecayTime) {
                    ((NarslimmusBlockEntity) (Object) this).getLevel().destroyBlock(((NarslimmusBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((NarslimmusBlockEntity) (Object) this).getLevel(), ((NarslimmusBlockEntity) (Object) this).getBlockPos())) {
                        ((NarslimmusBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((NarslimmusBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.narslimmusManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make Mana Generation Rate modifiable.
     */
    @Overwrite(remap = false)
    private static int manaForSize(int size) {
        size = Math.min(size, 4);
        return (ConfigFile.narslimmusBaseManaGenerationRate * (int) Math.pow(2, size));
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.narslimmusRange, -ConfigFile.narslimmusRange, -ConfigFile.narslimmusRange);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.narslimmusRange + 1, ConfigFile.narslimmusRange + 1, ConfigFile.narslimmusRange + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.narslimmusRange;
    }
}
