package met.freehij.hardcore.mixin;

import met.freehij.hardcore.utils.Common;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glBindTexture(II)V", shift = At.Shift.AFTER,
            ordinal = 1), method = "render")
    private void ae(CallbackInfo info) {
        if (Common.getModdedWorldProperties().isHardcore())
            GL11.glBindTexture(3553, Common.getMinecraft().textureManager.getTextureId(
                    "/assets/hardcore/textures/gui/icons.png"));
    }
}
