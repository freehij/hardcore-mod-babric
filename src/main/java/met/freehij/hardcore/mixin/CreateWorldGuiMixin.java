package met.freehij.hardcore.mixin;

import met.freehij.hardcore.utils.Common;
import met.freehij.hardcore.utils.IWorldProperties;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.class_180;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.OptionButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_180.class)
public class CreateWorldGuiMixin extends Screen {
    @Unique private boolean hardcore = false;

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo info) {
        this.buttons.add(new ButtonWidget(127, this.width / 2 - 100, this.height / 4 + 144 + 12,
                "Hardcore: " + (this.hardcore ? "ON" : "OFF")));
    }

    @Inject(at = @At("TAIL"), method = "buttonClicked")
    private void buttonClicked(ButtonWidget button, CallbackInfo info) {
        if (button.id == 127) {
            hardcore = !hardcore;
            button.text = "Hardcore: " + (hardcore ? "ON" : "OFF");
        } else if (button.id == 0) {
            Common.getModdedWorldProperties().setHardcore(hardcore);
        }
    }
}
