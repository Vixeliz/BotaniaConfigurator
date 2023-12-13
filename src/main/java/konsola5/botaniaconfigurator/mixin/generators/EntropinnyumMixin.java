package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.EntropinnyumBlockEntity;

import java.util.Objects;

@Mixin(EntropinnyumBlockEntity.class)
public class EntropinnyumMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.entropinnyumDecays) {
            if (!Objects.requireNonNull(((EntropinnyumBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.entropinnyumDecayTime) {
                    ((EntropinnyumBlockEntity) (Object) this).getLevel().destroyBlock(((EntropinnyumBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((EntropinnyumBlockEntity) (Object) this).getLevel(), ((EntropinnyumBlockEntity) (Object) this).getBlockPos())) {
                        ((EntropinnyumBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((EntropinnyumBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.entropinnyumManaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 3),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/item/PrimedTnt;discard()V"),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/generating/EntropinnyumBlockEntity;sync()V")))
    private static int configureDupedTNTRate(int constant){
        return ConfigFile.entropinnyumDupedTNTGenerationRate;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/EntropinnyumBlockEntity;getMaxMana()I"))
    private int configureGenerationRate(EntropinnyumBlockEntity instance) {
        return ConfigFile.entropinnyumManaGenerationRate;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.entropinnyumRange;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.entropinnyumRange;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.entropinnyumRange;
    }
}
