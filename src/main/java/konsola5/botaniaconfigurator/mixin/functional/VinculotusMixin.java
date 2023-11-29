package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.VinculotusBlockEntity;
import vazkii.botania.common.helper.MathHelper;

@Mixin(VinculotusBlockEntity.class)
public class VinculotusMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getVinculotus().manaCapacity;
    }

    @ModifyConstant(method = "onEndermanTeleport", constant = @Constant(intValue = 50),
            remap = false)
    private static int configureCost1(int original){
        return ConfigFile.HANDLER.instance().getFunctional().getVinculotus().manaCost;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Circle;<init>(Lnet/minecraft/core/BlockPos;D)V"
    ), index = 1, remap = false)
    private double configureRange1(double radius) {
        return ConfigFile.HANDLER.instance().getFunctional().getVinculotus().radius;
    }

    // A very roundabout way to change the RANGE to an arbitrary value, since @ModifyConstant can't catch the double 64.0.
    @Redirect(method = "onEndermanTeleport", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/helper/MathHelper;pointDistanceSpace(DDDDDD)F"), remap = false)
    private static float configureCost2(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (float) (MathHelper.pointDistanceSpace(x1, y1, z1, x2, y2, z2) + 64 - ConfigFile.HANDLER.instance().getFunctional().getVinculotus().radius);
    }
}
