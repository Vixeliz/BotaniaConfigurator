package konsola5.botaniaconfigurator.mixin.misc;

import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.common.block.PylonBlock;

@Debug(export = true)
@Mixin(PylonBlock.class)
public class PylonDebugMixin {
    // Apparently, you need a base class mixin to mixin into a subclass.
}
