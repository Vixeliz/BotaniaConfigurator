package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.JiyuuliaBlockEntity;

@Mixin(JiyuuliaBlockEntity.class)
public class JiyuuliaMixin {
// Jiyuulia inherits from Tangleberrie.
/**
 * @author KonSola5
 * @reason Make Range configurable.
 */
@Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.HANDLER.instance().getFunctional().getJiyuulia().range;
    }
}
