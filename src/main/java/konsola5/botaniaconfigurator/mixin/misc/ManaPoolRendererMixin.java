package konsola5.botaniaconfigurator.mixin.misc;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import vazkii.botania.client.render.block_entity.ManaPoolBlockEntityRenderer;

@Mixin(ManaPoolBlockEntityRenderer.class)
public class ManaPoolRendererMixin {
    @ModifyConstant(method = "render(Lvazkii/botania/common/block/block_entity/mana/ManaPoolBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", constant = @Constant(intValue = 1000000))
    private int modifyManaPoolSizeFallback(int original) {
        return ConfigFile.manaCartCapacity;
    }
}
