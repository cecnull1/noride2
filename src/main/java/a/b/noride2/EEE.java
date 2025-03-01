package a.b.noride2;

import a.b.noride2.Utils.EUtils;
import constant.Constant;
import a.b.noride2.enchantment.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Constant.MODDatas.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EEE {
    @SubscribeEvent
    public static void eee2(TickEvent.@NotNull PlayerTickEvent event) {
        // 常量声明
        final CompoundTag PersistentData = event.player.getPersistentData();

        // 变量声明
        CompoundTag Mod_PersistentData = PersistentData.getCompound(Constant.MODDatas.MOD_ID);

        // 鞘翅驱动
        if (EUtils.hasSpecificEnchantment(event.player, Qiaochiqudong.QIAOCHI_QU_DONG.get()) && event.player.isFallFlying()) {
            Vec3 MotionVector = event.player.getDeltaMovement();
            Vec3 RotationVector = event.player.getLookAngle();
            double Speed = EUtils.getHighestEnchantmentLevel(event.player, Qiaochiqudong.QIAOCHI_QU_DONG.get()) / 100.;
            applySpeedAdjustment(event.player, MotionVector, RotationVector, Speed, true);
        }

        // 自动移动
        // 实现已转到 EntityMixin.java

        // 脚滑
        final boolean isJIAO_HUA = EUtils.hasSpecificEnchantment(event.player, Jiaohua.JIAOHUA.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.JIAO_HUA, isJIAO_HUA);

        // 不下沉
        final boolean isBUXIA_CHENG = EUtils.hasSpecificEnchantment(event.player, BuXiaCheng.BUXIA_CHENG.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_BUXIA_CHENG, isBUXIA_CHENG);

        // 只能下沉
        if (EUtils.hasSpecificEnchantment(event.player, ZhiNengXiaCheng.ZHINENG_XIA_CHENG.get())) {
            Vec3 MotionVector = event.player.getDeltaMovement();
            if (event.player.isInWaterOrBubble()) {
                event.player.setDeltaMovement(new Vec3(
                        MotionVector.x,
                        -Math.abs(MotionVector.y),
                        MotionVector.z
                ));
            }
        }

        // 飘浮（不是漂浮）
        final boolean isNoGravity = EUtils.hasSpecificEnchantment(event.player, PiaoFu.PIAOFU.get());
        Mod_PersistentData.putBoolean(Constant.NBTKeys.IS_NO_GRAVITY, isNoGravity);
        if (isNoGravity) {
            Vec3 MotionVector = event.player.getDeltaMovement();
            event.player.setDeltaMovement(new Vec3(
                    MotionVector.x,
                    Math.max(MotionVector.y, 0),
                    MotionVector.z
            ));
        }

        // 强制爬梯
        // 实现已转到 LivingEntityMixin.java

        // 强制鞘翅飞行
        // 实现已转到 LivingEntityMixin.java

        // 强制游泳
        Mod_PersistentData.putBoolean(Constant.NBTKeys.SHIZHONG_SWIMMING, EUtils.hasSpecificEnchantment(event.player, QiangZhiYouYong.QIANGZHI_YOU_YONG.get()));

        // 自由穿行
        Mod_PersistentData.putBoolean(Constant.NBTKeys.ZIYOU_CHUANXING, EUtils.hasSpecificEnchantment(event.player, ZiYouChuangXing.ZIYOU_CHUANGXING.get()));

        // 收尾
        PersistentData.put(Constant.MODDatas.MOD_ID, Mod_PersistentData);
    }

    private static void applySpeedAdjustment(@NotNull Entity entity, @NotNull Vec3 motionVector, @NotNull Vec3 rotationVector, double speed, boolean adjustY) {
        Vec3 newMotion = new Vec3(
                motionVector.x + speed * Math.sin(rotationVector.x),
                adjustY ? (motionVector.y + speed * Math.sin(rotationVector.y)) : motionVector.y,
                motionVector.z + speed * Math.sin(rotationVector.z)
        );
        entity.setDeltaMovement(newMotion);
    }

    static public void JH_Code(@NotNull Entity entity) {
        Vec3 MotionVector = entity.getDeltaMovement();
        if (!(entity instanceof Player player) || !player.isFallFlying()) {
            if (!entity.isInWaterOrBubble() && entity.isOnGround()) {
                VectorCalc1(1.35, entity, MotionVector);
            } else if (!entity.isInWaterOrBubble()) {
                VectorCalc1(1.05, entity, MotionVector);
            } else {
                VectorCalc1(1.1175, entity, MotionVector);
            }
        }
    }

    public static void VectorCalc1(double speed, @NotNull Entity entity, Vec3 motionVector) {
        double Speed_y = 1;
        entity.setDeltaMovement(new Vec3(
                motionVector.x * speed,
                motionVector.y * Speed_y,
                motionVector.z * speed
        ));
    }
}
