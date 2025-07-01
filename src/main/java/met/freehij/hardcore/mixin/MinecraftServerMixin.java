package met.freehij.hardcore.mixin;

import met.freehij.hardcore.Hardcore;
import met.freehij.hardcore.utils.IWorldProperties;
import net.minecraft.class_391;
import net.minecraft.class_73;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow public class_391 field_2840;
    @Shadow public class_73[] field_2841;

    @Inject(at = @At("TAIL"), method = "method_2166")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        if (field_2840.method_1248("hardcore", false)) {
            for (class_73 world : field_2841) {
                ((IWorldProperties)((WorldAccessor)world).getProperties()).setHardcore(true);
            }
            Hardcore.hardcore = true;
        }
    }

    @Inject(at = @At("HEAD"), method = "method_2171")
    private void tick(CallbackInfo info) {
        if (field_2840.method_1248("hardcore", false)) {
            for (class_73 world : field_2841) {
                world.field_213 = 3;
            }
        }
    }
}
