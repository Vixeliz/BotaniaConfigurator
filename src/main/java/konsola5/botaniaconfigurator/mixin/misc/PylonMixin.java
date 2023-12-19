package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.block.PylonBlock;

@Debug(export = true)
@Mixin(PylonBlock.Variant.class)
public class PylonMixin {

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 8.0f))
    private static float modifyManaPylonEnchantPower(float original) {
        return ConfigFile.manaPylonEnchantingPower;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 15.0f, ordinal = 0))
    private static float modifyNaturaPylonEnchantPower(float original) {
        return ConfigFile.naturaPylonEnchantingPower;
    }

    @ModifyConstant(method = "<clinit>", constant = @Constant(floatValue = 15.0f, ordinal = 1))
    private static float modifyGaiaPylonEnchantPower(float original) {
        return ConfigFile.gaiaPylonEnchantingPower;
    }
}
