package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.FallenKanadeBlockEntity;

@Mixin(FallenKanadeBlockEntity.class)
public class FallenKanadeMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.fallenKanadeManaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 120),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/FallenKanadeBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z")))
    private int configureCost1(int original){
        return ConfigFile.fallenKanadeManaCost;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = -120),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/FallenKanadeBlockEntity;addMana(I)V")))
    private int configureCost2(int original){
        return -ConfigFile.fallenKanadeManaCost;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange(int range) {
        return ConfigFile.fallenKanadeRange;
    }
}
