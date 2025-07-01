package met.freehij.hardcore.mixin;

import met.freehij.hardcore.Hardcore;
import net.minecraft.class_335;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
    @Inject(method = "method_1430", at = @At("HEAD"), cancellable = true)
    private void method_1430(class_335 par1, CallbackInfo ci) {
        System.out.println(Hardcore.hardcore);
        if (Hardcore.hardcore) ci.cancel();
    }
}
