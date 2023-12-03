package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import vazkii.botania.fabric.integration.tr_energy.FluxfieldTRStorage;

@Mixin(FluxfieldTRStorage.class)
public class FluxfieldTRStorageMixin {
    @ModifyArg(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lteam/reborn/energy/api/base/SimpleEnergyStorage;<init>(JJJ)V", remap = false
    ), remap = false, index = 0)
    private static long configureTransferRate(long capacity) {
        return ConfigFile.manaFluxfieldCapacity;
    }
}
