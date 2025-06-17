package met.freehij.hardcore.mixin;

import met.freehij.hardcore.Hardcore;
import met.freehij.hardcore.utils.Common;
import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.Option;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Shadow public World world;
    @Shadow public GameOptions options;

    @Inject(at = @At("TAIL"), method = "init")
    private void init(CallbackInfo ci) {
        Hardcore.trueDifficulty = options.difficulty;
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo ci) {
        if (world != null && (Common.getModdedWorldProperties().isHardcore() || Hardcore.hardcore)) {
            Common.getModdedWorldProperties().setHardcore(true);
            if (this.options.difficulty != 3) Hardcore.trueDifficulty = this.options.difficulty;
            this.options.difficulty = 3;
        }
    }

    @Inject(at = @At("HEAD"), method = "method_2115")
    private void setWorld(World world, String worldName, PlayerEntity player, CallbackInfo ci) {
        if (world == null) this.options.difficulty = Hardcore.trueDifficulty;
    }
}
