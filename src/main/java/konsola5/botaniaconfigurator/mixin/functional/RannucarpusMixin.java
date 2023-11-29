package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.common.block.flower.functional.RannuncarpusBlockEntity;

@Mixin(RannuncarpusBlockEntity.class)
public class RannucarpusMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().manaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make Place Range modifiable.
     */
    @Overwrite(remap = false)
    public int getPlaceRange() {
        return ((RannuncarpusBlockEntity)(Object)this).getMana() > 0 ?
                ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().placementRangeXZMana:
                ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().placementRangeXZ;
    }

    /**
     * @author KonSola5
     * @reason Make Vertical Place Range modifiable.
     */
    @Overwrite(remap = false)
    public int getVerticalPlaceRange() {
        return ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().placementRangeY;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0), remap = false)
    private BlockPos configurePickupRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGEXZ = ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().pickupRangeXZ;
        final int RANGEY = ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().pickupRangeY;
        return instance.offset(-RANGEXZ, -RANGEY, -RANGEXZ);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1), remap = false)
    private BlockPos configurePickupRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGEXZ = ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().pickupRangeXZ;
        final int RANGEY = ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().pickupRangeY;
        return instance.offset(RANGEXZ + 1, RANGEY + 1, RANGEXZ + 1);
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 10),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/RannuncarpusBlockEntity;getLevel()Lnet/minecraft/world/level/Level;", ordinal = 0),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/block/flower/functional/RannuncarpusBlockEntity;getLevel()Lnet/minecraft/world/level/Level;", ordinal = 1)),
            remap = false)
    private int configureDelay(int original){
        return ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().delay;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/RannuncarpusBlockEntity;getMana()I"), remap = false)
    private int configureCost1(RannuncarpusBlockEntity instance) {
        return instance.getMana() + 1 - ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().manaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/RannuncarpusBlockEntity;addMana(I)V"), remap = false)
    private void configureCost2(RannuncarpusBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().manaCost);
    }

    @ModifyConstant(method = "getSecondaryRadius",constant = @Constant(intValue = 2),
            remap = false)
    private int configurePickupRange3(int original){
        return -ConfigFile.HANDLER.instance().getFunctional().getRannucarpus().pickupRangeXZ;
    }
}
