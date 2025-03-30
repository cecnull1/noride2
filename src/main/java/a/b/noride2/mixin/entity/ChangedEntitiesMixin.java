package a.b.noride2.mixin.entity;

import net.ltxprogrammer.changed.init.ChangedEntities;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static a.b.noride2.utils.MixinUtilsToJava.creturn;

@Mixin(ChangedEntities.class)
public class ChangedEntitiesMixin {
    @Inject(method = "overworldOnly", at = @At("HEAD"), cancellable = true, remap = false)
    private static void overworldOnly(Level level, CallbackInfoReturnable<Boolean> cir) {
        creturn(cir, true);
    }
}
