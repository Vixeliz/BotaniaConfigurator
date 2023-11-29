package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.HeiseiDreamBlockEntity;

@Mixin(HeiseiDreamBlockEntity.class)
public class HeiseiDreamMixin {

    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 100),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HeiseiDreamBlockEntity;getMana()I"),
                    to = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;")),
            remap = false)
    private int configureCost1(int original) {
        return ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().manaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -100),
            slice = @Slice(
                    from = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HeiseiDreamBlockEntity;brainwashEntity(Lnet/minecraft/world/entity/Mob;Ljava/util/List;)Z"),
                    to = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/HeiseiDreamBlockEntity;addMana(I)V")),
            remap = false)
    private int configureCost2(int original) {
        return -ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().manaCost;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = -5), remap = false)
    private int configureRange1(int original) {
        return -ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().range;
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 6), remap = false)
    private int configureRange2(int original) {
        return ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().range + 1;
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange3(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getHeiseiDream().range;
    }

}
