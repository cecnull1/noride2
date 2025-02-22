package a.b.noride2.mixin.Entity;

import a.b.noride2.EEE;
import a.b.noride2.Utils.EUtils;
import a.b.noride2.constant.Constant;
import a.b.noride2.enchantment.QiangZhiJiPao;
import a.b.noride2.enchantment.Wufaxiacheng;
import a.b.noride2.enchantment.Zidongyidong;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

import static a.b.noride2.EEE.VectorCalc1;
import static a.b.noride2.Utils.MixinUtils.creturn;
import static java.lang.Math.sin;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isSprinting", at = @At("HEAD"), cancellable = true)
    public void isSprinting(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        if (entity instanceof Player player && EUtils.hasSpecificEnchantment(player, QiangZhiJiPao.QIANGZHI_JI_PAO.get())) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isSwimming", at = @At("HEAD"), cancellable = true)
    public void isSwimming(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (entity instanceof Player player && (
                PersistentData.getBoolean(Constant.NBTKeys.SHIZHONG_SWIMMING) ||
                PersistentData.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING) && (noride2$isUnderWater(player) || noride2$isUnderLava(player))
        )) {
            creturn(cir, true);
        }
    }

    @Inject(method = "stopRiding", at = @At("HEAD"), cancellable = true)
    public void stopRiding(CallbackInfo ci) {
        // 无法下乘
        if ((Entity) (Object) this instanceof Player player && EUtils.hasSpecificEnchantment(player, Wufaxiacheng.WUFA_XIA_CHENG.get())) {
            if (player.getVehicle() != null
                    && (
                            player.getVehicle() instanceof LivingEntity LE
                                    && !LE.isAlive()
                                    || player.getVehicle().isRemoved()
                    )
            ) {
                player.setHealth(0);
            }
            if (player.isAlive()) {
                creturn(ci);
            } else {
                if (player.getVehicle() != null) {
                    if (player.getVehicle() instanceof LivingEntity LE) {
                        LE.setHealth(0);
                    }
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        final Entity entity = (Entity) (Object) this;
        final float speed = 8f;
        final List<Entity> entities = List.copyOf(entity.getPassengers());
        CompoundTag Local_1 = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (!entities.isEmpty()) {
            Local_1.putBoolean(Constant.NBTKeys.IS_ZIDONG_YI_DONG,
                    entities.get(0) instanceof Player player &&
                    EUtils.hasSpecificEnchantment(player,Zidongyidong.ZIDONG_YI_DONG.get()) ||
                    entities.get(0).getPersistentData().getCompound(Constant.MODDatas.MOD_ID).getBoolean(Constant.NBTKeys.IS_ZIDONG_YI_DONG));

            Local_1.putDouble(Constant.NBTKeys.ZIDONG_YI_DONG_LEVEL,
                    (entities.get(0) instanceof Player player ? EUtils.getHighestEnchantmentLevel(player, Zidongyidong.ZIDONG_YI_DONG.get()) : 0) +
                            entities.get(0).getPersistentData().getCompound(Constant.MODDatas.MOD_ID).getDouble(Constant.NBTKeys.ZIDONG_YI_DONG_LEVEL));
        } else if (entity instanceof Player player) {
            Local_1.putBoolean(Constant.NBTKeys.IS_ZIDONG_YI_DONG, EUtils.hasSpecificEnchantment(player, Zidongyidong.ZIDONG_YI_DONG.get()));
            Local_1.putDouble(Constant.NBTKeys.ZIDONG_YI_DONG_LEVEL, EUtils.getHighestEnchantmentLevel(player, Zidongyidong.ZIDONG_YI_DONG.get()));
        }
        if (Local_1.getBoolean(Constant.NBTKeys.IS_ZIDONG_YI_DONG)) {
            final double speed2 = Local_1.getDouble(Constant.NBTKeys.ZIDONG_YI_DONG_LEVEL);
            final Vec3 Movement = entity.getDeltaMovement();
            final Vec3 Rotation = entity.getLookAngle();
            if (!(entity instanceof Player player) || !player.isFallFlying()) {
                ZDYD_1(entity, Movement, Rotation, speed, speed2);
            }
        }
        if (Local_1.getBoolean(Constant.NBTKeys.JIAO_HUA)) {
            EEE.JH_Code(entity);
            EEE.JH_Code(entity);
        }
        if (Local_1.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING) && (noride2$isUnderWater(entity) || noride2$isUnderLava(entity))) {
            ZDYD_1(entity, entity.getDeltaMovement(), entity.getLookAngle(), speed, 1.005);
            //VectorCalc1(1.1175, entity, entity.getDeltaMovement());
        }
    }



    @Inject(method = "isNoGravity", at = @At("HEAD"), cancellable = true)
    public void isNoGravity(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (entity instanceof Player player && (
                PersistentData.getBoolean(Constant.NBTKeys.IS_NO_GRAVITY) ||
                PersistentData.getBoolean(Constant.NBTKeys.IS_BUXIA_CHENG) && player.isInWaterOrBubble() ||
                PersistentData.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING) && (noride2$isUnderWater(player) || noride2$isUnderLava(player))
        )) {
            creturn(cir, true);
        }
    }

    @Inject(method = "isInLava", at = @At("HEAD"), cancellable = true)
    public void isInLava(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (PersistentData.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING)) {
            creturn(cir, false);
        }
    }

    @Inject(method = "isInWater", at = @At("HEAD"), cancellable = true)
    public void isInWater(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (PersistentData.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING)) {
            creturn(cir, false);
        }
    }

    @Inject(method = "isOnFire", at = @At("HEAD"), cancellable = true)
    public void isOnFire(CallbackInfoReturnable<Boolean> cir) {
        final Entity entity = (Entity) (Object) this;
        final CompoundTag PersistentData = entity.getPersistentData().getCompound(Constant.MODDatas.MOD_ID);
        if (PersistentData.getBoolean(Constant.NBTKeys.ZIYOU_CHUANXING)) {
            creturn(cir, false);
        }
    }

    @Unique
    public void ZDYD_1(Entity entity, Vec3 movement, Vec3 rotation, float speed, double speed2) {
        entity.setDeltaMovement(movement.add(
                new Vec3(
                        sin(rotation.x)/ speed * speed2,
                        0,
                        sin(rotation.z)/ speed * speed2
                )
        ));
    }

    @Unique
    public boolean noride2$isUnderLava(@NotNull Entity entity) {
        return entity.isEyeInFluid(FluidTags.LAVA);
    }

    @Unique
    public boolean noride2$isUnderWater(@NotNull Entity entity) {
        return entity.isEyeInFluid(FluidTags.WATER);
    }
}
