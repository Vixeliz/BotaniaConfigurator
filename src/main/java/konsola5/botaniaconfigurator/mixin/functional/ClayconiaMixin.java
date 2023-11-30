package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.ClayconiaBlockEntity;

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
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;getCoordsToPut()Lnet/minecraft/core/BlockPos;")),
            remap = false)
    private int configureCost1(int original) {
        return ConfigFile.clayconiaManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -80),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/ClayconiaBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original) {
        return -ConfigFile.clayconiaManaCost;
    }

    @Inject(method = "getRange",
            at = @At("HEAD"),remap = false,cancellable = true)
    private void configureRangeXZ(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.clayconiaRangeXZ);
    }

    @Inject(method = "getRangeY",
            at = @At("HEAD"),remap = false,cancellable = true)
    private void configureRangeY(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.clayconiaRangeY);
    }
}
