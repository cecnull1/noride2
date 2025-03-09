package a.b.noride2.logic

import a.b.noride2.Noride2Utils
import a.b.noride2.Utils.EUtils
import a.b.noride2.enchantment.*
import a.b.noride2.utils.compoundTag.putAny
import a.b.noride2_changed_compatible.enchantment.QiangZhiTransfur
import constant.Constant
import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.FluidTags
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.phys.Vec3
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sin

// 实体逻辑
fun noride2EntityTickLogic(entity: Entity, persistentData: CompoundTag, speed: Double) {
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
            val speed1 = eGetPersistentData(entity).getInt(Constant.NBTKeys.NUM_QIAOCHI_QU_DONG_LEVEL).toDouble()
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
    fun zhiNengXiaChengLogic(entity: Entity) {
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
    fun piaoFuLogic(entity: Entity) {
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_NO_GRAVITY)) {
            val motionVector: Vec3 = entity.deltaMovement
            entity.deltaMovement = Vec3(
                motionVector.x,
                max(motionVector.y, 0.0),
                motionVector.z
            )
        }
    }

    // 船移 部分1
    fun chuangYiLogic(entity: Entity) {
        val entityPersistentData = eGetPersistentData(entity)
        if (entityPersistentData.getBoolean(Constant.NBTKeys.IS_CHUANG_YI)) {
            val vehicle = entity.vehicle
            if (vehicle != null && vehicle is Boat) {
                eGetPersistentData(vehicle).putAny(
                    Constant.NBTKeys.IS_CHUANG_YI,
                    entityPersistentData.getBoolean(Constant.NBTKeys.IS_CHUANG_YI)
                )
            }
        }
    }

    // 方法调用
    if (entity is Player) vehicleLogic(entity)
    if (entity is LivingEntity) fallFly(entity)
    zhiNengXiaChengLogic(entity)
    vehicleLogic2(entity)
    jiaohuaLogic(entity)
    ziYouChuangXingLogic(entity)
    piaoFuLogic(entity)
}

// 船逻辑
fun noride2BoatEntityTickLogic(boatEntity: Boat) {
    val speed = 0.03f
    val speed2 = 1f
    val speed3: Float
    val entities = boatEntity.passengers
    if (entities.isNotEmpty()) {
        val entity = entities[0]
        if (entity is Player) {
            if (EUtils.hasSpecificEnchantment(entity, ChuangYi.CHUANG_YI.get())) {
                val newVel: Vec3 = boatEntity.lookAngle.multiply(1.0, 0.0, 1.0).normalize()
                    .multiply(speed.toDouble(), speed.toDouble(), speed.toDouble())
                if (newVel.lengthSqr() <= 64) {
                    boatEntity.deltaMovement = newVel.add(
                        boatEntity.deltaMovement.multiply(speed2.toDouble(), 0.0, speed2.toDouble())
                    )
                }
            }
            if (EUtils.hasSpecificEnchantment(entity, ChuangHua.CHUANG_HUA.get())) {
                speed3 = if (boatEntity.isInWaterOrBubble) {
                    0.1f
                } else if (boatEntity.isOnGround) {
                    0.7f
                } else {
                    0.1175f
                }
                boatEntity.deltaMovement = boatEntity.deltaMovement
                    .multiply(speed3.toDouble(), boatEntity.deltaMovement.y, speed3.toDouble())
                    .add(boatEntity.deltaMovement)
            }
        }
    }
}

// 玩家逻辑
fun noride2PlayerTickLogic(player: Player) {
    // 常量声明
    val persistentData = player.persistentData

    // 变量声明
    val modPersistentData = persistentData.getCompound(Constant.MODDatas.MOD_ID)


    // 鞘翅驱动
    val isQiaoChiQuDong = EUtils.hasSpecificEnchantment(player, Qiaochiqudong.QIAOCHI_QU_DONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIAOCHI_QU_DONG, isQiaoChiQuDong)
    val qiaoChiQuDongLEVEL = EUtils.getHighestEnchantmentLevel(player, Qiaochiqudong.QIAOCHI_QU_DONG.get())
    modPersistentData.putInt(Constant.NBTKeys.NUM_QIAOCHI_QU_DONG_LEVEL, qiaoChiQuDongLEVEL)


    // 自动移动
    val isAUTOMOVE = EUtils.hasSpecificEnchantment(player, Zidongyidong.ZIDONG_YI_DONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_AUTO_MOVE, isAUTOMOVE)
    val autoMoveLEVEL = EUtils.getHighestEnchantmentLevel(player, Zidongyidong.ZIDONG_YI_DONG.get())
    modPersistentData.putInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL, autoMoveLEVEL)


    // 脚滑
    val isJiaoHua = EUtils.hasSpecificEnchantment(player, Jiaohua.JIAOHUA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_JIAO_HUA, isJiaoHua)


    // 不下沉
    val isBuXiaChen = EUtils.hasSpecificEnchantment(player, BuXiaCheng.BUXIA_CHENG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_BUXIA_CHENG, isBuXiaChen)


    // 只能下沉
    val isZhiNengXiaChen = EUtils.hasSpecificEnchantment(player, ZhiNengXiaCheng.ZHINENG_XIA_CHENG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_ZHI_NENG_XIA_CHENG, isZhiNengXiaChen)


    // 飘浮（不是漂浮）
    val isNoGravity = EUtils.hasSpecificEnchantment(player, PiaoFu.PIAOFU.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_NO_GRAVITY, isNoGravity)


    // 强制爬梯
    val isQiangZhiPaTi = EUtils.hasSpecificEnchantment(player, QiangZhiPaTi.QIANGZHI_PA_TI.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIANGZHI_PA_TI, isQiangZhiPaTi)


    // 强制鞘翅飞行
    val isFallFly = EUtils.hasSpecificEnchantment(player, Qiangzhifeixing.QIANGZHI_FEI_XING.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_FALL_FLY, isFallFly)


    // 强制游泳
    val isSwimming = EUtils.hasSpecificEnchantment(player, QiangZhiYouYong.QIANGZHI_YOU_YONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_SWIMMING, isSwimming)


    // 自由穿行
    val isZiYouChuangXing = EUtils.hasSpecificEnchantment(player, ZiYouChuangXing.ZIYOU_CHUANGXING.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING, isZiYouChuangXing)

    // 船移
    val isChuangYi = EUtils.hasSpecificEnchantment(player, ChuangYi.CHUANG_YI.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_CHUANG_YI, isChuangYi)

    // 船滑
    val isChuangHua = EUtils.hasSpecificEnchantment(player, ChuangHua.CHUANG_HUA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_CHUANG_HUA, isChuangHua)

    // 强制TRANSFUR
    val isTransfur = EUtils.hasSpecificEnchantment(player, QiangZhiTransfur.QIANG_ZHI_TRANSFUR.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_TRANSFUR, isTransfur)


    // 收尾
    persistentData.put(Constant.MODDatas.MOD_ID, modPersistentData)
}

// 自动移动辅助方法
fun ziDongYiDong(entity: Entity, movement: Vec3, rotation: Vec3, speed: Double, speed2: Double) {
    entity.deltaMovement = movement.add(
        Vec3(
            sin(rotation.x) / speed * speed2,
            0.0,
            sin(rotation.z) / speed * speed2
        )
    )
}

// 获取实体是否在熔岩里面
fun isUnderLava(entity: Entity): Boolean {
    return entity.isEyeInFluid(FluidTags.LAVA)
}

// 获取实体是否在水里面
fun isUnderWater(entity: Entity): Boolean {
    return entity.isEyeInFluid(FluidTags.WATER)
}

// 获取指定实体中骑乘链的末端的实体
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