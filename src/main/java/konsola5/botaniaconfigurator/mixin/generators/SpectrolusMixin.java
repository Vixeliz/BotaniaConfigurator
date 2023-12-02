package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.SpectrolusBlockEntity;

import java.util.Objects;

@Mixin(SpectrolusBlockEntity.class)
public class SpectrolusMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.spectrolusDecays) {
            if (!Objects.requireNonNull(((SpectrolusBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.spectrolusDecayTime) {
                    ((SpectrolusBlockEntity) (Object) this).getLevel().destroyBlock(((SpectrolusBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((SpectrolusBlockEntity) (Object) this).getLevel(), ((SpectrolusBlockEntity) (Object) this).getBlockPos())) {
                        ((SpectrolusBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((SpectrolusBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.spectrolusManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 1),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/animal/Sheep;getColor()Lnet/minecraft/world/item/DyeColor;"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/animal/Sheep;isBaby()Z", ordinal = 1)))
    private int configureManaPerBabySheep(int constant) {
        return ConfigFile.spectrolusManaPerBabySheep;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 5000),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/animal/Sheep;getColor()Lnet/minecraft/world/item/DyeColor;"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/animal/Sheep;isBaby()Z", ordinal = 1)))
    private int configureManaPerSheep(int constant) {
        return ConfigFile.spectrolusManaPerSheep;
    }

    @ModifyArg(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/SpectrolusBlockEntity;addManaAndCycle(I)V", ordinal = 1), index = 0)
    private int configureManaPerWool(int toAdd) {
        return ConfigFile.spectrolusManaPerWool;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0))
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(-ConfigFile.spectrolusRange, -ConfigFile.spectrolusRange, -ConfigFile.spectrolusRange);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1))
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        return instance.offset(ConfigFile.spectrolusRange + 1, ConfigFile.spectrolusRange + 1, ConfigFile.spectrolusRange + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.spectrolusRange;
    }
}
