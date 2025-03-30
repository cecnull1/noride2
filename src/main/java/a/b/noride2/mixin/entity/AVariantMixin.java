package a.b.noride2.mixin.entity;

import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.LatexType;
import net.ltxprogrammer.changed.entity.VisionType;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TransfurVariant.Builder.class)
public class AVariantMixin {
    @Inject(method = "glide()Lnet/ltxprogrammer/changed/entity/variant/TransfurVariant$Builder;", at = @At("RETURN"), cancellable = true, remap = false)
    public <T extends ChangedEntity> void glide(CallbackInfoReturnable<TransfurVariant.Builder<T>> cir) {
        cir.setReturnValue(cir.getReturnValue()
                .jumpStrength(1)
                .extraJumps(Integer.MAX_VALUE)
                .breatheMode(TransfurVariant.BreatheMode.ANY)
                .canClimb()
                .stepSize(1.0F)
                .faction(LatexType.NEUTRAL)
                .visionType(VisionType.NIGHT_VISION)
                .reducedFall()
        );
    }
}
