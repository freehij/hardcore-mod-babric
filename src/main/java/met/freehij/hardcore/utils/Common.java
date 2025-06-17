package met.freehij.hardcore.utils;

import met.freehij.hardcore.mixin.WorldAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;

public class Common {
    @Environment(EnvType.CLIENT)
    public static Minecraft getMinecraft() {
        return (Minecraft) FabricLoaderImpl.INSTANCE.getGameInstance();
    }

    @Environment(EnvType.SERVER)
    public static MinecraftServer getMinecraftServer() {
        return (MinecraftServer) FabricLoaderImpl.INSTANCE.getGameInstance();
    }

    @Environment(EnvType.CLIENT)
    public static IWorldProperties getModdedWorldProperties() {
        return ((IWorldProperties)((WorldAccessor)getMinecraft().world).getProperties());
    }
}
