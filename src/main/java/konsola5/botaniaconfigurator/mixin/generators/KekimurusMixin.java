package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.generating.KekimurusBlockEntity;

@Mixin(KekimurusBlockEntity.class)
public class KekimurusMixin {
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
