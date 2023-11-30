package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.JiyuuliaBlockEntity;

@Mixin(JiyuuliaBlockEntity.Mini.class)
public class JiyuuliaMiniMixin {
// Jiyuulia inherits from Tangleberrie.
    /**
     * @author KonSola5
     * @reason Make Range configurable.
     */
    @Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.jiyuuliaRangeMini;
    }
}