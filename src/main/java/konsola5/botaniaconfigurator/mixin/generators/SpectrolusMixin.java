package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.common.block.flower.generating.SpectrolusBlockEntity;

import java.util.Objects;
import java.util.Random;

@Mixin(SpectrolusBlockEntity.class)
public abstract class SpectrolusMixin extends GeneratingFlowerBlockEntity {
    Random random = new Random();

    // getLevel() is null here, so the world's random can't be used here...

    @SuppressWarnings("unused") // This is only to give the field a different init value.
    @Shadow(remap = false) private DyeColor nextColor = ConfigFile.spectrolusRandomStartingColor ?
            DyeColor.values()[random.nextInt(16)] :
            DyeColor.WHITE;
    private int passiveDecayTicks;

    public SpectrolusMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.spectrolusDecays) {
            if (!Objects.requireNonNull(getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.spectrolusDecayTime) {
                    getLevel().destroyBlock(getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(getLevel(), getBlockPos())) {
                        getLevel().setBlockAndUpdate(getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
