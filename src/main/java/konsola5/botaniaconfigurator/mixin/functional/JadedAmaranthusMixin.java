package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.JadedAmaranthusBlockEntity;

@Mixin(JadedAmaranthusBlockEntity.class)
public class JadedAmaranthusMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.jadedAmaranthusManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 100),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;above()Lnet/minecraft/core/BlockPos;")))
    private int configureCost1(int original) {
        return ConfigFile.jadedAmaranthusManaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -100),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;sync()V")))
    private int configureCost2(int original) {
        return -ConfigFile.jadedAmaranthusManaCost;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange1(int range) {
        return ConfigFile.jadedAmaranthusRange;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 4),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;above()Lnet/minecraft/core/BlockPos;")))
    private int configureRange2(int original) {
        return ConfigFile.jadedAmaranthusRange;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextInt(I)I"),
    slice = @Slice(
            from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;getMana()I"),
            to = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;above()Lnet/minecraft/core/BlockPos;")))
    private int configureRange3(RandomSource instance, int i) {
        return instance.nextInt(ConfigFile.jadedAmaranthusRange * 2 + 1);
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 8),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;above()Lnet/minecraft/core/BlockPos;"),
                    to = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/DyeColor;byId(I)Lnet/minecraft/world/item/DyeColor;")))
    private int configureRange4(int original) {
        return ConfigFile.jadedAmaranthusRange * 2;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 30),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;getLevel()Lnet/minecraft/world/level/Level;"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/JadedAmaranthusBlockEntity;getMana()I")))
    private int configureDelay(int original) {
        return ConfigFile.jadedAmaranthusDelay;
    }
}
