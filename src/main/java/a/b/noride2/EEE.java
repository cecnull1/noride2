package a.b.noride2;

import a.b.noride2.enchantment.Qiangzhifeixing;
import a.b.noride2.enchantment.Utils.EUtils;
import a.b.noride2.enchantment.Qiaochiqudong;
import a.b.noride2.enchantment.Utils.FunctionUtils;
import a.b.noride2.enchantment.Wufaxiacheng;
import a.b.noride2.enchantment.Zidongyidong;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Noride2.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EEE {
    @SubscribeEvent
    public static void eee(EntityMountEvent event) {
        if (event.getEntityMounting() instanceof Player player) {
            if (EUtils.hasSpecificEnchantment(player, Wufaxiacheng.WUFA_XIA_CHENG.get())
                    && event.isDismounting()) {
                try {
                    // 尝试调用isRemoved()方法
                    if (event.getEntityBeingMounted().isRemoved()) {
                        player.setHealth(0);
                    } else {
                        event.setCanceled(true);
                    }
                } catch (NoSuchMethodError e) {
                    // 处理NoSuchMethodError异常，例如设置默认行为
                    // 由于isRemoved方法不存在，我们可以在这里设置默认行为
                    // System.err.println("Method isRemoved() does not exist, defaulting to cancel event.");
                }
            }
        }
    }

    @SubscribeEvent
    public static void eee2(TickEvent.PlayerTickEvent event) {
        // 强制鞘翅飞行
        if (EUtils.hasSpecificEnchantment(event.player, Qiangzhifeixing.QIANGZHI_FEI_XING.get())) {
            event.player.startFallFlying();
        }

        // 鞘翅驱动
        if (EUtils.hasSpecificEnchantment(event.player, Qiaochiqudong.QIAOCHI_QU_DONG.get()) && event.player.isFallFlying()) {
            Vec3 MotionVector = event.player.getDeltaMovement();
            Vec3 RotationVector = event.player.getLookAngle();
            double Speed = EUtils.getHighestEnchantmentLevel(event.player, Qiaochiqudong.QIAOCHI_QU_DONG.get()) / 100.;
            applySpeedAdjustment(event.player, MotionVector, RotationVector, Speed, true);
        }

        // 自动移动
        if (EUtils.hasSpecificEnchantment(event.player, Zidongyidong.ZIDONG_YI_DONG.get())) {
            Vec3 MotionVector = event.player.getDeltaMovement();
            Vec3 RotationVector = event.player.getLookAngle();
            double Speed = EUtils.getHighestEnchantmentLevel(event.player, Zidongyidong.ZIDONG_YI_DONG.get()) / 25.;
            applySpeedAdjustment(event.player, MotionVector, RotationVector, Speed, false);
            FunctionUtils.processRiddenEntities(event.player, entity -> applySpeedAdjustment(entity, entity.getDeltaMovement(), entity.getLookAngle(), Speed, false));
        }
    }

    private static void applySpeedAdjustment(Entity entity, Vec3 motionVector, Vec3 rotationVector, double speed, boolean adjustY) {
        Vec3 newMotion = new Vec3(
                motionVector.x + speed * Math.sin(rotationVector.x),
                adjustY ? (motionVector.y + speed * Math.sin(rotationVector.y)) : motionVector.y,
                motionVector.z + speed * Math.sin(rotationVector.z)
        );
        entity.setDeltaMovement(newMotion);
    }
}
