package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.block.flower.generating.KekimurusBlockEntity;

import java.util.Objects;

@Mixin(KekimurusBlockEntity.class)
public class KekimurusMixin {
    private int passiveDecayTicks;
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/GeneratingFlowerBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true, remap = false)
    private void decayFlower(CallbackInfo ci) {
        if (ConfigFile.kekimurusDecays) {
            if (!Objects.requireNonNull(((KekimurusBlockEntity) (Object) this).getLevel()).isClientSide) {
                if (++passiveDecayTicks > ConfigFile.kekimurusDecayTime) {
                    ((KekimurusBlockEntity) (Object) this).getLevel().destroyBlock(((KekimurusBlockEntity) (Object) this).getBlockPos(), false);
                    if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(((KekimurusBlockEntity) (Object) this).getLevel(), ((KekimurusBlockEntity) (Object) this).getBlockPos())) {
                        ((KekimurusBlockEntity) (Object) this).getLevel().setBlockAndUpdate(((KekimurusBlockEntity) (Object) this).getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
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
        return ConfigFile.kekimurusManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 1800), remap = false)
    private int configureGenerationRate(int constant){
        return ConfigFile.kekimurusManaGenerationRate;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 80), remap = false)
    private int configureDelay(int constant) {
        return ConfigFile.kekimurusDelay;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 11), remap = false)
    private int configureRange1(int constant){
        return ConfigFile.kekimurusRange * 2 + 1;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 5), remap = false)
    private int configureRange2(int constant){
        return ConfigFile.kekimurusRange;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange3(int range) {
        return ConfigFile.kekimurusRange;
    }
}
