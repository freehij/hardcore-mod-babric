package met.freehij.hardcore.mixin;

import met.freehij.hardcore.utils.Common;
import net.minecraft.class_182;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {
	@Inject(at = @At("TAIL"), method = "init()V")
	private void init(CallbackInfo info) {
		if (Common.getModdedWorldProperties().isHardcore()) {
			this.buttons.clear();
			this.buttons.add(new ButtonWidget(2, this.width / 2 - 100, this.height / 4 + 96,
					Common.getMinecraft().isWorldRemote() ? "Leave server" : "Delete world"));
		}
	}

	@Inject(at = @At("TAIL"), method = "render")
	public void render(CallbackInfo info) {
		if (Common.getModdedWorldProperties().isHardcore()) {
			this.drawCenteredTextWithShadow(this.textRenderer, "You cannot respawn in hardcore mode!", this.width / 2,
					144, 0xffffff);
		}
	}

	@Inject(at = @At("HEAD"), method = "buttonClicked", cancellable = true)
	protected void buttonClicked(ButtonWidget button, CallbackInfo info) {
		if (!Common.getMinecraft().world.isRemote && Common.getModdedWorldProperties().isHardcore() && button.id == 2) {
			info.cancel();
			String worldName = ((WorldAccessor)Common.getMinecraft().world).getProperties().getName();
			this.minecraft.setWorld(null);
			this.minecraft.setScreen(new TitleScreen());
			class_182 saveLoader = Common.getMinecraft().method_2127();
			saveLoader.method_1003();
			saveLoader.method_1006(worldName);
		}
	}
}
