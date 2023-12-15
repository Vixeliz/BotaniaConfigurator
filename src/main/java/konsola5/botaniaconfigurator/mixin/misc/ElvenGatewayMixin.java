package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.block.block_entity.AlfheimPortalBlockEntity;

@Mixin(AlfheimPortalBlockEntity.class)
public class ElvenGatewayMixin {
    @ModifyArg(method = "lightPylons", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/block_entity/AlfheimPortalBlockEntity;consumeMana(Ljava/util/List;IZ)Z"), index = 1)
    private int configureGatewayOpeningCost(int range) {
        return ConfigFile.elvenGatewayOpeningCost;
    }

    @ModifyArg(method = "resolveRecipes", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/block_entity/AlfheimPortalBlockEntity;consumeMana(Ljava/util/List;IZ)Z"), index = 1)
    private int configureGatewayTradeCost(int range) {
        return ConfigFile.elvenGatewayTradeCost;
    }

    @ModifyConstant(method = "locatePylons", constant = @Constant(intValue = 2), remap = false)
    private int configureMinimumPylons1(int original) {
        return ConfigFile.elvenGatewayMinimumPylons;
    }

    @ModifyConstant(method = "consumeMana", constant = @Constant(intValue = 2), remap = false)
    private int configureMinimumPylons2(int original) {
        return ConfigFile.elvenGatewayMinimumPylons;
    }
}
