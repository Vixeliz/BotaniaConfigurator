package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.BergamuteBlockEntity;

@Mixin(BergamuteBlockEntity.class)
public class BergamuteMixin {
    @ModifyArg(method = "getRadius",at = @At(value = "INVOKE", target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Circle;<init>(Lnet/minecraft/core/BlockPos;D)V"), index = 1)
    private double configRadius(double radius){
        return ConfigFile.HANDLER.instance().getFunctional().getBergamute().radius;
    }

    // If Botania for some reason changes the RANGE, check this. Originally, RANGE = 4.
    @ModifyConstant(method = "getBergamutesNearby", constant = @Constant(doubleValue = 16.0), // RANGE * RANGE
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;distToCenterSqr(DDD)D")),
            remap = false)
    private static double configRadius2(double constant) {
        return Math.pow(ConfigFile.HANDLER.instance().getFunctional().getBergamute().radius, 2);
    }
}
