package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.FallenKanadeBlockEntity;

@Mixin(FallenKanadeBlockEntity.class)
public class FallenKanadeMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getFallenKanade().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 120),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/FallenKanadeBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")),
            remap = false)
    private int configureCost1(int original){
        return ConfigFile.HANDLER.instance().getFunctional().getFallenKanade().manaCost;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = -120),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/FallenKanadeBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original){
        return -ConfigFile.HANDLER.instance().getFunctional().getFallenKanade().manaCost;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getFallenKanade().range;
    }
}
