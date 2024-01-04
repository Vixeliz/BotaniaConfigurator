package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.PollidisiacBlockEntity;

@Mixin(PollidisiacBlockEntity.class)
public class PollidisiacMixin {
    /**
     * @author KonSola5
     * @reason Make max mana configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.pollidisiacManaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 12),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/PollidisiacBlockEntity;getMana()I")))
    private int configureCost1(int original){
        return ConfigFile.pollidisiacManaCost;
    }

    @Redirect(method = "consumeFoodItemAndMana", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/PollidisiacBlockEntity;addMana(I)V"))
    public void modifyCost2(PollidisiacBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.pollidisiacManaCost);
    }

    @Redirect(method = "getItems", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"))
    private AABB configureRange1(AABB instance, double value) {
        final int RANGE = ConfigFile.pollidisiacRange;
        return instance.inflate(RANGE);
    }

    @Redirect(method = "getAnimals", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(D)Lnet/minecraft/world/phys/AABB;"))
    private AABB configureRange2(AABB instance, double value) {
        final int RANGE = ConfigFile.pollidisiacRange;
        return instance.inflate(RANGE);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange5(int range) {
        return ConfigFile.pollidisiacRange;
    }

    @ModifyArg(method = "getSecondaryRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1)
    private int configureRange6(int range) {
        return ConfigFile.pollidisiacRange;
    }
}
