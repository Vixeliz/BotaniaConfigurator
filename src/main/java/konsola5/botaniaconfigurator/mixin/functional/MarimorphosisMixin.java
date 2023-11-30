package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
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
        return ConfigFile.marimorphosisManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getCost() {
        return ConfigFile.marimorphosisManaCost;
    }

    /**
     * @author KonSola5
     * @reason Make mana cost configurable.
     */
    @Overwrite(remap = false)
    public int getDelay() {
        return ConfigFile.marimorphosisDelay;
    }

    /**
     * @author KonSola5
     * @reason Make range (X/Z) configurable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.marimorphosisRangeXZ;
    }

    /**
     * @author KonSola5
     * @reason Make range (Y) configurable.
     */
    @Overwrite(remap = false)
    public int getRangeY() {
        return ConfigFile.marimorphosisRangeY;
    }
}
