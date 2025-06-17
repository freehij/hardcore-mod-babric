package met.freehij.hardcore.mixin;

import met.freehij.hardcore.utils.Common;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public class OptionsGuiMixin extends Screen {
    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo info) {
        if (Common.getMinecraft().world != null && Common.getModdedWorldProperties().isHardcore()) {
            ButtonWidget button = ((ButtonWidget)this.buttons.get(4));
            button.active = false;
            button.text = "Difficulty: Hardcore";
        }
    }
}
