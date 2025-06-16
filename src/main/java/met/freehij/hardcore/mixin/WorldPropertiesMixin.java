package met.freehij.hardcore.mixin;

import met.freehij.hardcore.utils.IWorldProperties;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldProperties.class)
public abstract class WorldPropertiesMixin implements IWorldProperties {
    @Unique
    private boolean hardcore;

    @Inject(method = "<init>(Lnet/minecraft/nbt/NbtCompound;)V", at = @At("TAIL"))
    private void onConstructFromNbt(NbtCompound nbt, CallbackInfo ci) {
        this.hardcore = nbt.getBoolean("hardcore");
    }

    @Inject(method = "<init>(JLjava/lang/String;)V", at = @At("TAIL"))
    private void onNewWorld(long seed, String name, CallbackInfo ci) {
        this.hardcore = false;
    }

    @Inject(method = "<init>(Lnet/minecraft/world/WorldProperties;)V", at = @At("TAIL"))
    private void onCopy(WorldProperties original, CallbackInfo ci) {
        this.hardcore = ((IWorldProperties) original).isHardcore();
    }

    @Inject(method = "updateProperties", at = @At("TAIL"))
    private void onSave(NbtCompound nbt, NbtCompound playerNbt, CallbackInfo ci) {
        nbt.putBoolean("hardcore", this.hardcore);
    }

    @Override
    public boolean isHardcore() {
        return this.hardcore;
    }

    @Override
    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }
}
