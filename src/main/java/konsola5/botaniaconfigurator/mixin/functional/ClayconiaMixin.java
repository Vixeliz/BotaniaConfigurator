package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import vazkii.botania.common.block.flower.functional.ClayconiaBlockEntity;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.LOGGER;

@Mixin(ClayconiaBlockEntity.class)
public class ClayconiaMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.clayconiaManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 80),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;getCoordsToPut()Lnet/minecraft/core/BlockPos;")))
    private int configureCost1(int original) {
        return ConfigFile.clayconiaManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -80),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;addMana(I)V")))
    private int configureCost2(int original) {
        return -ConfigFile.clayconiaManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 5),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;getLevel()Lnet/minecraft/world/level/Level;"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;getCoordsToPut()Lnet/minecraft/core/BlockPos;")))
    private int configureDelay(int original) {
        return ConfigFile.clayconiaDelay;
    }

    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    private void clayconiaBuff(CallbackInfo ci, BlockPos coords) {
        if (ConfigFile.clayconiaBuff) {
            ((ClayconiaBlockEntity)(Object)this).getLevel().setBlockAndUpdate(coords, Blocks.CLAY.defaultBlockState());
            ci.cancel();
        }
    }

    /**
     * @author KonSola5
     * @reason Make Clayconia Horizontal Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.clayconiaRangeXZ;
    }

    /**
     * @author KonSola5
     * @reason Make Clayconia Vertical Range modifiable.
     */
    @Overwrite(remap = false)
    public int getRangeY() {
        return ConfigFile.clayconiaRangeY;
    }
}
