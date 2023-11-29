package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.BubbellBlockEntity;

@Mixin(BubbellBlockEntity.class)
public class BubbellMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getBubbell().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 4),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost1(int original) {
        return ConfigFile.HANDLER.instance().getFunctional().getBubbell().manaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -4),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/BubbellBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original) {
        return -ConfigFile.HANDLER.instance().getFunctional().getBubbell().manaCost;
    }

    @ModifyConstant(method = "isValidBubbell",constant = @Constant(intValue = 4),remap = false)
    private static int configureCost3(int original) {
        return ConfigFile.HANDLER.instance().getFunctional().getBubbell().manaCost;
    }

    @Inject(method = "getRange", at = @At("RETURN"), remap = false, cancellable = true)
    private void configRange(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigFile.HANDLER.instance().getFunctional().getBubbell().range);
    }
}
