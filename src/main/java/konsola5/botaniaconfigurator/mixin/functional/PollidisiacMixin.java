package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.common.block.flower.functional.MedumoneBlockEntity;
import vazkii.botania.common.block.flower.functional.PollidisiacBlockEntity;

@Mixin(PollidisiacBlockEntity.class)
public class PollidisiacMixin {
    /**
     * @author KonSola5
     * @reason Make max mana configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 12),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/PollidisiacBlockEntity;getMana()I")),
            remap = false)
    private int configureCost1(int original){
        return ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().manaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/PollidisiacBlockEntity;addMana(I)V"), remap = false)
    public void modifyCost2(PollidisiacBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().manaCost);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0), remap = false)
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1), remap = false)
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 2), remap = false)
    private BlockPos configureRange3(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
        return instance.offset(-RANGE, -RANGE, -RANGE);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 3), remap = false)
    private BlockPos configureRange4(BlockPos instance, int dx, int dy, int dz) {
        final int RANGE = ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
        return instance.offset(RANGE + 1, RANGE + 1, RANGE + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange5(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
    }

    @ModifyArg(method = "getSecondaryRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange6(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getPollidisiac().range;
    }
}
