package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.block_entity.RunicAltarBlockEntity;

import static konsola5.botaniaconfigurator.BotaniaConfigurator.LOGGER;

@Debug(export = true)
@Mixin(RunicAltarBlockEntity.class)
public abstract class RunicAltarMixin {
    // Intentionally making it not hot-changeable.
    private static final boolean canLoseMana = ConfigFile.runicAltarLosesMana;

    private static final String TAG_NOT_RECEIVED_MANA_FOR = "notReceivedManaFor";
    private static final String TAG_PREV_MANA = "prevMana";

    private int notReceivedManaForTicks = 0;
    private int prevMana = 0;
    @Shadow(remap = false)
    public int manaToGet;
    @Shadow(remap = false)
    private int mana;
    @Shadow(remap = false)
    public int signal;

    @Shadow public Level getManaReceiverLevel() {
        return null;
    }

    @Shadow(remap = false) public void receiveMana(int mana) {}

    // Stupid way to inject into a non-static method
    @Inject(method = "tickCooldown", at = @At("HEAD"), remap = false)
    private void decayManaWhenUnused(CallbackInfo ci) {
        Level level = getManaReceiverLevel();
        if (canLoseMana && manaToGet > 0 && signal == 1) {
            if (mana > 0 && mana < manaToGet) {
                if (mana <= prevMana) notReceivedManaForTicks++;
                else notReceivedManaForTicks = 0;
                if (notReceivedManaForTicks > ConfigFile.runicAltarTicksBeforeLoss && level.getGameTime() % ConfigFile.runicAltarManaLossInterval == 0) {
                    receiveMana(-Math.min(ConfigFile.runicAltarManaLoss, mana));
                    if (level.getGameTime() % Math.max(10, ConfigFile.runicAltarManaLossInterval) == 0) {
                        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((RunicAltarBlockEntity)(Object)this);
                    }
                }
            }
            prevMana = mana;
        }
    }

    @Inject(method = "writePacketNBT", at = @At("TAIL"))
    private void addToNBT(CompoundTag tag, CallbackInfo ci) {
        LOGGER.info("canLoseMana: " + canLoseMana);
        if (canLoseMana) {
            tag.putInt(TAG_NOT_RECEIVED_MANA_FOR, notReceivedManaForTicks);
            tag.putInt(TAG_PREV_MANA, prevMana);
        }
    }

    @Inject(method = "readPacketNBT", at = @At("TAIL"))
    private void readFromNBT(CompoundTag tag, CallbackInfo ci) {
        if (canLoseMana) {
            notReceivedManaForTicks = tag.getInt(TAG_NOT_RECEIVED_MANA_FOR);
            prevMana = tag.getInt(TAG_PREV_MANA);
        }
    }
}
