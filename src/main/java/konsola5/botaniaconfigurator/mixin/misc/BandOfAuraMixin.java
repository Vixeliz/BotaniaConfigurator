package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.item.equipment.bauble.BandOfAuraItem;

@Mixin(BandOfAuraItem.class)
public class BandOfAuraMixin {
    private static final int intervalMultiplier = ConfigFile.auraBandIntervalMultiplier;

    @ModifyConstant(method = "<init>", constant = @Constant(intValue = 5), remap = false)
    private int modifyIntervalMultiplier(int original) {
        return intervalMultiplier;
    }

    @ModifyArg(method = "onWornTick",at = @At(value = "INVOKE", target = "Lvazkii/botania/api/mana/ManaItemHandler;dispatchManaExact(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;IZ)Z"), index = 2)
    private int configRadius(int manaToSend){
        return ConfigFile.auraBandManaGenerationRate;
    }

}
