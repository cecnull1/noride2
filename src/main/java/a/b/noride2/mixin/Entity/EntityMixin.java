package a.b.noride2.mixin.Entity;

import a.b.noride2.logic.Noride2LogicKt;
import constant.Constant;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static a.b.noride2.utils.MixinUtilsToJava.creturn;
import static a.b.noride2.logic.Noride2LogicKt.*;
import static a.b.noride2_bn_compatible.eventLogic.Bn_c_eventKt.tickLogic1;
import static a.b.noride2_changed_compatible.changedLogic.ChangedCompatibleLogicKt.changedCompatibleLogic;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isSprinting", at = @At("HEAD"), cancellable = true)
    public void isSprinting(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        if (entity instanceof Player player && eGetPersistentData(player).getBoolean(Constant.NBTKeys.IS_QIANGZHI_JIPAO)) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = eGetPersistentData(entity);
        if (entity instanceof Player player && (
                PersistentData.getBoolean(Constant.NBTKeys.IS_SWIMMING) ||
                PersistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING) && (Noride2LogicKt.isUnderWater(player) || isUnderLava(player))
        )) {
            creturn(cir, true);
        }
    }




    @Inject(method = "stopRiding", at = @At("HEAD"), cancellable = true)
    public void stopRiding(CallbackInfo ci) {
        // 无法下乘
        if ((Entity) (Object) this instanceof Player player && (
                eGetPersistentData(player).getBoolean(Constant.NBTKeys.IS_WU_FA_XIA_CHENG) ||
                    player.getPersistentData().getBoolean(Constant.NBTKeys.BetterNeonMod.NAI_WU_RAN) ||
                        player.getPersistentData().getBoolean(Constant.NBTKeys.BetterNeonMod.WFXC)
        )) {
            if (player.isAlive() && (player.getVehicle() != null) && player.getVehicle().isAlive()) {
                creturn(ci);
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        final Entity entity = (Entity) (Object) this;
        final float speed = 8f;
        CompoundTag persistentData = eGetPersistentData(entity);
        noride2EntityTickLogic(entity, persistentData, speed);
        changedCompatibleLogic(entity);
        if (entity instanceof Player player) {
            tickLogic1(player, player.getPersistentData());
        }
    }

    @Inject(method = "isNoGravity", at = @At("HEAD"), cancellable = true)
    public void isNoGravity(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = eGetPersistentData(entity);
        if (entity instanceof Player player && (
                PersistentData.getBoolean(Constant.NBTKeys.IS_NO_GRAVITY) ||
                PersistentData.getBoolean(Constant.NBTKeys.IS_BUXIA_CHENG) && player.isInWaterOrBubble() ||
                PersistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING) && (Noride2LogicKt.isUnderWater(player) || isUnderLava(player))
        )) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isInLava", at = @At("HEAD"), cancellable = true)
    public void isInLava(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = eGetPersistentData(entity);
        if (PersistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING) ||
        PersistentData.getBoolean(Constant.NBTKeys.IS_NOT_IN_LAVA)) {
            creturn(cir, false);
        }
        if (PersistentData.getBoolean(Constant.NBTKeys.IS_IN_LAVA)) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isInWater", at = @At("HEAD"), cancellable = true)
    public void isInWater(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = eGetPersistentData(entity);
        if (PersistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING)||
                PersistentData.getBoolean(Constant.NBTKeys.IS_NOT_IN_WATER)) {
            creturn(cir, false);
        }
        if (PersistentData.getBoolean(Constant.NBTKeys.IS_IN_WATER)) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    public void isOnFire(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = eGetPersistentData(entity);
        if (PersistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING)) {
            creturn(cir, false);
        }
    }
}
