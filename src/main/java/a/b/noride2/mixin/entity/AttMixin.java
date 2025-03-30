package a.b.noride2.mixin.entity;

import net.ltxprogrammer.changed.entity.AttributePresets;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AttributePresets.class)
public class AttMixin {
    @Inject(method = "dragonLike", at = @At("HEAD"), cancellable = true, remap = false)
    private static void dragonLike(AttributeMap map, CallbackInfo ci) {
        ci.cancel();
        map.getInstance(Attributes.MOVEMENT_SPEED).setBaseValue(1.1f);
        map.getInstance(ForgeMod.SWIM_SPEED.get()).setBaseValue(1.48f);
        map.getInstance(Attributes.MAX_HEALTH).setBaseValue(24.0);
    }
}
