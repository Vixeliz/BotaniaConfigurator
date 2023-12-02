package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.generating.ShulkMeNotBlockEntity;

@Mixin(ShulkMeNotBlockEntity.class)
public class ShulkMeNotMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.shulkMeNotManaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(doubleValue = 64.0), remap = false)
    private double configureRadius(double constant){
        return ConfigFile.shulkMeNotRadius;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"))
    private AABB configureRadius2(AABB instance, double value) {
        return instance.inflate(ConfigFile.shulkMeNotRadius);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Circle;<init>(Lnet/minecraft/core/BlockPos;D)V"
    ), index = 1)
    private double configureRange3(double radius) {
        return ConfigFile.shulkMeNotRadius;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/ShulkMeNotBlockEntity;getMaxMana()I", ordinal = 0))
    private int configureGenerationRate(ShulkMeNotBlockEntity instance) {
        return ConfigFile.shulkMeNotManaGenerationRate;
    }
}
