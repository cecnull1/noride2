package a.b.noride2.logic

import a.b.noride2.Noride2Utils
import constant.Constant
import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.phys.Vec3
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sin

fun noride2TickLogic(entity: Entity, persistentData: CompoundTag, speed: Double) {
    // 自动移动 部分1
    fun vehicleLogic(player: Player) {
        val modPersistentData: CompoundTag
        val vehicle: Entity
        val playerModPersistentData = eGetPersistentData(player)
        if (player.vehicle != null) {
            vehicle = getLastRiddenEntity(player)
            modPersistentData = eGetPersistentData(vehicle)
            modPersistentData.putBoolean(
                Constant.NBTKeys.IS_AUTO_MOVE,
                playerModPersistentData.getBoolean(Constant.NBTKeys.IS_AUTO_MOVE)
            )
            modPersistentData.putInt(
                Constant.NBTKeys.IS_AUTO_MOVE_LEVEL,
                playerModPersistentData.getInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL)
            )
        }
    }

    // 自动移动 部分2
    fun vehicleLogic2(entity: Entity) {
        if (!eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_AUTO_MOVE)) return
        ziDongYiDong(entity, entity.deltaMovement, entity.lookAngle, speed,
            (eGetPersistentData(entity).getInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL)/16.0+1) * 1.0005
        )
    }

    // 鞘翅驱动
    fun fallFly(livingEntity: LivingEntity) {
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_QIAOCHI_QU_DONG) && livingEntity.isFallFlying) {
            val motionVector: Vec3 = livingEntity.deltaMovement
            val rotationVector: Vec3 = livingEntity.lookAngle
            val speed1 = eGetPersistentData(entity).getInt(Constant.NBTKeys.IS_QIAOCHI_QU_DONG_LEVEL).toDouble()
            Noride2Utils.applySpeedAdjustment(livingEntity, motionVector, rotationVector, speed1, true)
        }
    }

    // 脚滑
    fun jiaohuaLogic(entity: Entity) {
        if (persistentData.getBoolean(Constant.NBTKeys.IS_JIAO_HUA)) {
            Noride2Utils.JH_Code(entity)
            Noride2Utils.JH_Code(entity)
        }
    }

    // 自由穿行
    fun ziYouChuangXingLogic(entity: Entity) {
        if (persistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING) && (isUnderWater(entity) || isUnderLava(entity))
        ) {
            ziDongYiDong(entity, entity.deltaMovement, entity.lookAngle, speed, 1.0005)
            //VectorCalc1(1.1175, entity, entity.getDeltaMovement());
        }
    }

    // 只能下沉
    fun zhiNengXiaCheng(entity: Entity) {
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_ZHI_NENG_XIA_CHENG)) {
            val motionVector: Vec3 = entity.deltaMovement
            if (entity.isInWaterOrBubble) {
                entity.deltaMovement = Vec3(
                    motionVector.x,
                    -abs(motionVector.y),
                    motionVector.z
                )
            }
        }
    }

    // 飘浮
    fun piaoFu(entity: Entity) {
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_NO_GRAVITY)) {
            val motionVector: Vec3 = entity.deltaMovement
            entity.deltaMovement = Vec3(
                motionVector.x,
                max(motionVector.y, 0.0),
                motionVector.z
            )
        }
    }

    // 方法调用
    if (entity is Player) vehicleLogic(entity)
    if (entity is LivingEntity) fallFly(entity)
    zhiNengXiaCheng(entity)
    vehicleLogic2(entity)
    jiaohuaLogic(entity)
    ziYouChuangXingLogic(entity)
    piaoFu(entity)
}

fun ziDongYiDong(entity: Entity, movement: Vec3, rotation: Vec3, speed: Double, speed2: Double) {
    entity.deltaMovement = movement.add(
        Vec3(
            sin(rotation.x) / speed * speed2,
            0.0,
            sin(rotation.z) / speed * speed2
        )
    )
}

fun isUnderLava(entity: Entity): Boolean {
    return entity.isEyeInFluid(FluidTags.LAVA)
}

fun isUnderWater(entity: Entity): Boolean {
    return entity.isEyeInFluid(FluidTags.WATER)
}

fun getLastRiddenEntity(entity: Entity?): Entity {
    requireNotNull(entity) { "Entity is null" }
    entity.vehicle ?: return entity
    var vehicle: Entity = entity
    while (true) vehicle = vehicle.vehicle ?: break
    return vehicle
}

fun eGetPersistentData(entity: Entity): CompoundTag {
    return entity.persistentData.getCompound(Constant.MODDatas.MOD_ID)
}