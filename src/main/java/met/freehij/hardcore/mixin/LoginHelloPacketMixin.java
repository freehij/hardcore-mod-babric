package met.freehij.hardcore.mixin;

import met.freehij.hardcore.Hardcore;
import met.freehij.hardcore.utils.Common;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.packet.login.LoginHelloPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Mixin(LoginHelloPacket.class)
public class LoginHelloPacketMixin {
    @Environment(EnvType.SERVER)
    @Inject(at = @At("TAIL"), method = "write")
    private void write(DataOutputStream stream, CallbackInfo info) throws IOException {
        stream.writeBoolean(Common.getMinecraftServer().field_2840.method_1248("hardcore", false));
    }

    @Environment(EnvType.CLIENT)
    @Inject(at = @At("TAIL"), method = "read")
    private void read(DataInputStream stream, CallbackInfo info) throws IOException {
        Hardcore.hardcore = stream.readBoolean();
    }
}
