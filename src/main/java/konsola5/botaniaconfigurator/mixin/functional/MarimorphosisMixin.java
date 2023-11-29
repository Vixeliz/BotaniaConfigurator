package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.block.flower.functional.MarimorphosisBlockEntity;

@Mixin(MarimorphosisBlockEntity.class)
public class MarimorphosisMixin {
    // Inherits from Orechid.
    /**
     * @author KonSola5
     * @reason Make mana capacity configurable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getMarimorphosis().manaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getCost() {
        return ConfigFile.HANDLER.instance().getFunctional().getMarimorphosis().manaCost;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getDelay() {
        return ConfigFile.HANDLER.instance().getFunctional().getMarimorphosis().delay;
    }

    /**
     * @author KonSola5
     * @reason Make range (X/Z) configurable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.HANDLER.instance().getFunctional().getMarimorphosis().rangeXZ;
    }

    /**
     * @author KonSola5
     * @reason Make range (Y) configurable.
     */
    @Overwrite(remap = false)
    public int getRangeY() {
        return ConfigFile.HANDLER.instance().getFunctional().getMarimorphosis().rangeY;
    }
}
