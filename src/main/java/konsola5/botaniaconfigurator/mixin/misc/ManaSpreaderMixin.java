package konsola5.botaniaconfigurator.mixin.misc;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import java.util.Objects;

@Debug(export = true)
@Mixin(ManaSpreaderBlock.class)
public class ManaSpreaderMixin {

    @WrapOperation(
            method = "<clinit>",
            at = @At(value = "INVOKE", target = "vazkii/botania/common/block/mana/ManaSpreaderBlock$Variant.<init>(Ljava/lang/String;IIIIIFF)V")
            //at = @At(value = "INVOKE", target = "vazkii/botania/common/block/mana/ManaSpreaderBlock$Variant.<init> (Ljava/lang/String;IIIIIIFF)V")
    )
    //private static ManaSpreaderBlock.Variant setManaSpreader(String name, int burstMana, int manaCapacity, int color, int hudColor, int preLossTicks, float lossPerTick, float motionModifier, Operation<ManaSpreaderBlock.Variant> original) {
    private static ManaSpreaderBlock.Variant setManaSpreader(String name, int burstMana, int manaCapacity, int color, int hudColor, int preLossTicks, float lossPerTick, float motionModifier, Operation<ManaSpreaderBlock.Variant> original) {
        /*if (name.equals("MANA")) {
            return original.call("MANA", ConfigFile.manaSpreaderBurst, ConfigFile.manaSpreaderMaxMana, color, hudColor, ConfigFile.manaSpreaderTicks, ConfigFile.manaSpreaderLossRate, ConfigFile.manaSpreaderSpeed);
        } else {
            return original.call(name, burstMana, manaCapacity, color, hudColor, preLossTicks, lossPerTick, motionModifier);
        }*/
        return original.call(name, burstMana, manaCapacity, color, hudColor, preLossTicks, lossPerTick, motionModifier);

        /*if (Objects.equals(newValue.name(), "MANA")) {
            newValue.burstMana = ConfigFile.manaSpreaderBurst;
            newValue.manaCapacity = ConfigFile.manaSpreaderMaxMana;
            newValue.color = 0x20FF20;
            newValue.hudColor = 0x00FF00;
            newValue.preLossTicks = ConfigFile.manaSpreaderTicks;
            newValue.lossPerTick = ConfigFile.manaSpreaderLossRate;
            newValue.motionModifier = ConfigFile.manaSpreaderSpeed;
        }*/
        /*else {
            burstMana = ConfigFile.manaSpreaderBurst;
            manaCapacity = ConfigFile.manaSpreaderMaxMana;
            color = 0x20FF20;
            hudColor = 0x00FF00;
            preLossTicks = ConfigFile.manaSpreaderTicks;
            lossPerTick = ConfigFile.manaSpreaderLossRate;
            motionModifier = ConfigFile.manaSpreaderSpeed;
        }*/
        //return ManaSpreaderBlock.Variant(ConfigFile.manaSpreaderBurst, ConfigFile.manaSpreaderMaxMana, 0x20FF20, 0x00FF00, ConfigFile.manaSpreaderTicks, ConfigFile.manaSpreaderLossRate, ConfigFile.manaSpreaderSpeed);
    }
}