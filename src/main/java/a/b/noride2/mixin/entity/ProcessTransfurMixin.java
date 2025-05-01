package a.b.noride2.mixin.entity;

import constant.Constant;
import net.ltxprogrammer.changed.process.ProcessTransfur;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static a.b.noride2.utils.extension_properties.extension_properties.eGetPersistentData;

@Mixin(ProcessTransfur.class)
public class ProcessTransfurMixin {
    @Inject(method = "removePlayerTransfurVariant", at = @At("HEAD"), cancellable = true, remap = false)
    private static void removePlayerTransfurVariant(Player player, CallbackInfo ci) {
        CompoundTag nbt = eGetPersistentData(player);
        if (
                nbt.getBoolean(Constant.NBTKeys.IS_YONGJIU_TRANSFUR) || 
                        nbt.getBoolean(Constant.NBTKeys.IS_TRANSFUR) ||
                        player.getPersistentData().getBoolean(Constant.NBTKeys.BetterNeonMod.NORIDE2_BN_C_JH_TF)) ci.cancel();
    }
}
