package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.HyacidusBlockEntity;

@Mixin(HyacidusBlockEntity.class)
public class HyacidusMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.hyacidusManaCapacity;
    }


    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 20),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HyacidusBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getMobType()Lnet/minecraft/world/entity/MobType;")),
            remap = false)
    private int configureCost1(int original) {
        return ConfigFile.hyacidusManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -20),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;)Z"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HyacidusBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original) {
        return -ConfigFile.hyacidusManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -6), remap = false)
    private int configureRange1(int original) {
        return -ConfigFile.hyacidusRange;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 7), remap = false)
    private int configureRange2(int original) {
        return ConfigFile.hyacidusRange + 1;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange3(int range) {
        return ConfigFile.hyacidusRange;
    }
}
