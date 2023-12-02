package konsola5.botaniaconfigurator.mixin.generators;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import vazkii.botania.common.block.flower.generating.HydroangeasBlockEntity;

@Mixin(HydroangeasBlockEntity.class)
public class HydroangeasMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.hydroangeasManaCapacity;
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/FluidGeneratorBlockEntity;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/tags/TagKey;II)V"))
    private static void configureGenerationRates(Args args) {
        args.set(4, ConfigFile.hydroangeasBurnTime);
        args.set(5, ConfigFile.hydroangeasManaGenerationRate);
    }

    // Cuts the code early, very unstable!
    @Inject(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/generating/FluidGeneratorBlockEntity;tickFlower()V", shift = At.Shift.AFTER), cancellable = true)
    private void stopDecay(CallbackInfo ci) {
        if (!ConfigFile.hydroangeasDecays) ci.cancel();
    }

    @ModifyConstant(method = "tickFlower", constant = @Constant(intValue = 72000), remap = false)
    private static int configureDecayTime(int constant){
        return ConfigFile.hydroangeasDecayTime;
    }

    @ModifyConstant(method = "getGenerationDelay", constant = @Constant(intValue = 2),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;isThundering()Z")))
    private static int configureDelayWhenRaining(int constant){
        return ConfigFile.hydroangeasDelayRain;
    }


    @ModifyConstant(method = "getGenerationDelay", constant = @Constant(intValue = 3),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/level/Level;isThundering()Z")))
    private static int configureDelayWhenNotRaining(int constant){
        return ConfigFile.hydroangeasDelay;
    }


}
