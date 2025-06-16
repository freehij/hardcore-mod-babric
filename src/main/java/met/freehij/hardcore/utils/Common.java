package met.freehij.hardcore.utils;

import met.freehij.hardcore.mixin.WorldAccessor;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class Common {
    public static Minecraft getMinecraft() {
        return (Minecraft) FabricLoaderImpl.INSTANCE.getGameInstance();
    }

    public static IWorldProperties getModdedWorldProperties() {
        return ((IWorldProperties)((WorldAccessor)(getMinecraft()).world).getProperties());
    }
}
