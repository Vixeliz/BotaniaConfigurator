package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.VinculotusBlockEntity;

@Debug(export = true)
@Mixin(VinculotusBlockEntity.class)
public class VinculotusMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.vinculotusManaCapacity;
    }

    @ModifyConstant(method = "onEndermanTeleport", constant = @Constant(intValue = 50))
    private static int configureCost1(int original){
        return ConfigFile.vinculotusManaCost;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Circle;<init>(Lnet/minecraft/core/BlockPos;D)V"
    ), index = 1)
    private double configureRange1(double radius) {
        return ConfigFile.vinculotusRadius;
    }

    @ModifyConstant(method = "onEndermanTeleport",constant = @Constant(floatValue = 64),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/helper/MathHelper;pointDistanceSpace(DDDDDD)F", remap = false),
                    to = @At(value = "INVOKE",target = "Ljava/util/List;add(Ljava/lang/Object;)Z")))
    private static float configureRange2(float original){
        return (float) ConfigFile.vinculotusRadius;
    }
}
