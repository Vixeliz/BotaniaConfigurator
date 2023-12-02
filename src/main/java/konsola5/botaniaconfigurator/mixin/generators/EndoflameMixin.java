package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.EndoflameBlockEntity;

import java.util.Objects;

@Debug(export = true)
@Mixin(EndoflameBlockEntity.class)
public class EndoflameMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.endoflameManaCapacity;
    }

    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.endoflameDecays) {
            if (!Objects.requireNonNull(((EndoflameBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.endoflameDecayTime) {
                    ((EndoflameBlockEntity) (Object) this).getLevel().destroyBlock(((EndoflameBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((EndoflameBlockEntity) (Object) this).getLevel(), ((EndoflameBlockEntity) (Object) this).getBlockPos())) {
                        ((EndoflameBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((EndoflameBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                    }
                    ci.cancel();
                }
            }
        }
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/EndoflameBlockEntity;addMana(I)V"))
    private void configureGenerationRate(EndoflameBlockEntity instance, int i) {
        instance.addMana(ConfigFile.endoflameManaGenerationRate);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.endoflameRange;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.endoflameRange;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 32000),
            slice = @Slice(
            from = @At(value = "INVOKE",target = "Lnet/minecraft/world/item/ItemStack;getCount()I"),
            to = @At(value = "INVOKE",target = "Lvazkii/botania/common/helper/EntityHelper;shrinkItem(Lnet/minecraft/world/entity/item/ItemEntity;)V")))
    private static int configureFuelCap(int constant){
        return ConfigFile.endoflameFuelCap;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.endoflameRange;
    }
}
