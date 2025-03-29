package a.b.noride2.logic

import a.b.noride2.Noride2Utils
import a.b.noride2.enchantment.Enchantments
import a.b.noride2.utils.EUtils
import a.b.noride2.utils.compoundTag.nbt
import a.b.noride2.utils.compoundTag.putAny
import a.b.noride2_changed_compatible.changedLogic.changedModTransfur
import a.b.noride2_changed_compatible.enchantment.QiangZhiTransfur
import a.b.noride2_changed_compatible.enchantment.YongJiuShouHua
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
    // 方法调用
    if (entity is Player) vehicleLogic(entity)
    if (entity is Player) qiangZhiCFly(entity)
    if (entity is LivingEntity) fallFly(entity, entity)
    zhiNengXiaChengLogic(entity)
    vehicleLogic2(entity, speed)
    jiaohuaLogic(entity, persistentData)
    ziYouChuangXingLogic(entity, persistentData, speed)
    piaoFuLogic(entity)
    chuangYiLogic(entity)
    chuangHuaLogic(entity)
    yongJiuTransfurLogic(entity)
}

// 玩家逻辑
fun noride2PlayerTickLogic(player: Player) {
    // 常量声明
    val persistentData = player.persistentData

    // 变量声明
    val modPersistentData = persistentData.getCompound(Constant.MODDatas.MOD_ID)


    // 鞘翅驱动
    val isQiaoChiQuDong = EUtils.hasSpecificEnchantment(player, Enchantments.Qiaochiqudong.QIAOCHI_QU_DONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIAOCHI_QU_DONG, isQiaoChiQuDong)
    val qiaoChiQuDongLEVEL = EUtils.getHighestEnchantmentLevel(player, Enchantments.Qiaochiqudong.QIAOCHI_QU_DONG.get())
    modPersistentData.putInt(Constant.NBTKeys.NUM_QIAOCHI_QU_DONG_LEVEL, qiaoChiQuDongLEVEL)


    // 自动移动
    val isAUTOMOVE = EUtils.hasSpecificEnchantment(player, Enchantments.Zidongyidong.ZIDONG_YI_DONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_AUTO_MOVE, isAUTOMOVE)
    val autoMoveLEVEL = EUtils.getHighestEnchantmentLevel(player, Enchantments.Zidongyidong.ZIDONG_YI_DONG.get())
    modPersistentData.putInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL, autoMoveLEVEL)


    // 脚滑
    val isJiaoHua = EUtils.hasSpecificEnchantment(player, Enchantments.Jiaohua.JIAOHUA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_JIAO_HUA, isJiaoHua)


    // 不下沉
    val isBuXiaChen = EUtils.hasSpecificEnchantment(player, Enchantments.BuXiaCheng.BUXIA_CHENG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_BUXIA_CHENG, isBuXiaChen)


    // 只能下沉
    val isZhiNengXiaChen = EUtils.hasSpecificEnchantment(player, Enchantments.ZhiNengXiaCheng.ZHINENG_XIA_CHENG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_ZHI_NENG_XIA_CHENG, isZhiNengXiaChen)


    // 飘浮（不是漂浮）
    val isNoGravity = EUtils.hasSpecificEnchantment(player, Enchantments.PiaoFu.PIAOFU.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_NO_GRAVITY, isNoGravity)


    // 强制爬梯
    val isQiangZhiPaTi = EUtils.hasSpecificEnchantment(player, Enchantments.QiangZhiPaTi.QIANGZHI_PA_TI.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIANGZHI_PA_TI, isQiangZhiPaTi)


    // 强制鞘翅飞行
    val isFallFly = EUtils.hasSpecificEnchantment(player, Enchantments.Qiangzhifeixing.QIANGZHI_FEI_XING.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_FALL_FLY, isFallFly)


    // 强制游泳
    val isSwimming = EUtils.hasSpecificEnchantment(player, Enchantments.QiangZhiYouYong.QIANGZHI_YOU_YONG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_SWIMMING, isSwimming)


    // 自由穿行
    val isZiYouChuangXing = EUtils.hasSpecificEnchantment(player, Enchantments.ZiYouChuangXing.ZIYOU_CHUANGXING.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING, isZiYouChuangXing)

    // 船移
    val isChuangYi = EUtils.hasSpecificEnchantment(player, Enchantments.ChuangYi.CHUANG_YI.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_CHUANG_YI, isChuangYi)

    // 船滑
    val isChuangHua = EUtils.hasSpecificEnchantment(player, Enchantments.ChuangHua.CHUANG_HUA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_CHUANG_HUA, isChuangHua)

    // 强制TRANSFUR
    val isTransfur = EUtils.hasSpecificEnchantment(player, QiangZhiTransfur.QIANG_ZHI_TRANSFUR.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_TRANSFUR, isTransfur)

    // 永久兽化
    val isYongJiuTransfur = EUtils.hasSpecificEnchantment(player, YongJiuShouHua.YONGJIU_SHOUHUA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_YONGJIU_SHOUHUA, isYongJiuTransfur)

    // 强制疾跑
    val isQiangZhiJiPao = EUtils.hasSpecificEnchantment(player, Enchantments.QiangZhiJiPao.QIANGZHI_JI_PAO.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIANGZHI_JIPAO, isQiangZhiJiPao)

    // 无法下乘
    val isWuFaXiaCheng = EUtils.hasSpecificEnchantment(player, Enchantments.Wufaxiacheng.WUFA_XIA_CHENG.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_WU_FA_XIA_CHENG, isWuFaXiaCheng)

    // 不在水中
    val isNotInWater = EUtils.hasSpecificEnchantment(player, Enchantments.NotInWater.NOT_IN_WATER.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_NOT_IN_WATER, isNotInWater)

    // 不在熔岩中
    val isNotInLava = EUtils.hasSpecificEnchantment(player, Enchantments.NotInLava.NOT_IN_LAVA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_NOT_IN_LAVA, isNotInLava)

    // 在水中
    val isInWater = EUtils.hasSpecificEnchantment(player, Enchantments.InWater.IN_WATER.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_IN_WATER, isInWater)

    // 在熔岩中
    val isInLava = EUtils.hasSpecificEnchantment(player, Enchantments.InLava.IN_LAVA.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_IN_LAVA, isInLava)

    // 强制创造飞行
    val isQiangZhiChuangJianFly = EUtils.hasSpecificEnchantment(player, Enchantments.QiangZhiCFly.QIANGZHI_C_FLY.get())
    modPersistentData.putBoolean(Constant.NBTKeys.IS_QIANGZHI_C_FLY, isQiangZhiChuangJianFly)

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

// 传递赋值
private fun chuanDiFuZhi(entity: Entity, stringKey: String) {
    val entityPersistentData = eGetPersistentData(entity)
    if (entityPersistentData.getBoolean(stringKey)) {
        val vehicle = entity.vehicle
        if (vehicle != null && vehicle is Boat) {
            eGetPersistentData(vehicle).putAny(
                stringKey,
                entityPersistentData.getBoolean(stringKey)
            )
        }
    }
}

// 自动移动 部分1
private fun vehicleLogic(player: Player) {
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
private fun vehicleLogic2(entity: Entity, speed: Double) {
    if (!eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_AUTO_MOVE)) return
    ziDongYiDong(entity, entity.deltaMovement, entity.lookAngle, speed,
        (eGetPersistentData(entity).getInt(Constant.NBTKeys.IS_AUTO_MOVE_LEVEL)/16.0+1) * 1.0005
    )
}

// 鞘翅驱动
private fun fallFly(entity: Entity, livingEntity: LivingEntity) {
    if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_QIAOCHI_QU_DONG) && livingEntity.isFallFlying) {
        val motionVector: Vec3 = livingEntity.deltaMovement
        val rotationVector: Vec3 = livingEntity.lookAngle
        val speed1 = eGetPersistentData(entity).getInt(Constant.NBTKeys.NUM_QIAOCHI_QU_DONG_LEVEL).toDouble()
        Noride2Utils.applySpeedAdjustment(livingEntity, motionVector, rotationVector, speed1, true)
    }
}

// 脚滑
private fun jiaohuaLogic(entity: Entity, persistentData: CompoundTag) {
    if (persistentData.getBoolean(Constant.NBTKeys.IS_JIAO_HUA)) {
        Noride2Utils.JH_Code(entity)
        Noride2Utils.JH_Code(entity)
    }
}

// 自由穿行
private fun ziYouChuangXingLogic(entity: Entity, persistentData: CompoundTag, speed: Double) {
    if (persistentData.getBoolean(Constant.NBTKeys.IS_ZIYOU_CHUANXING) && (isUnderWater(entity) || isUnderLava(entity))
    ) {
        ziDongYiDong(entity, entity.deltaMovement, entity.lookAngle, speed, 1.0005)
        //VectorCalc1(1.1175, entity, entity.getDeltaMovement());
    }
}

// 只能下沉
private fun zhiNengXiaChengLogic(entity: Entity) {
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
private fun piaoFuLogic(entity: Entity) {
    if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_NO_GRAVITY)) {
        val motionVector: Vec3 = entity.deltaMovement
        entity.deltaMovement = Vec3(
            motionVector.x,
            max(motionVector.y, 0.0),
            motionVector.z
        )
    }
}


// 船移
private fun chuangYiLogic(entity: Entity) {
    // 处理源
    chuanDiFuZhi(entity, Constant.NBTKeys.IS_CHUANG_YI)
    // 处理船
    if (entity is Boat) {
        val speed4 = 0.03
        val speed2 = 1
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_CHUANG_YI)) {
            val newVel: Vec3 = entity.lookAngle.multiply(1.0, 0.0, 1.0).normalize()
                .multiply(speed4, speed4, speed4)
            if (newVel.lengthSqr() <= 64) {
                entity.deltaMovement = newVel.add(
                    entity.deltaMovement.multiply(speed2.toDouble(), 0.0, speed2.toDouble())
                )
            }
        }
    }
}

// 船滑
private fun chuangHuaLogic(entity: Entity) {
    // 处理源
    chuanDiFuZhi(entity, Constant.NBTKeys.IS_CHUANG_HUA)
    // 处理船
    if (entity is Boat) {
        if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_CHUANG_HUA)) {
            val speed3 = if (entity.isInWaterOrBubble) {
                0.1f
            } else if (entity.isOnGround) {
                0.7f
            } else {
                0.1175f
            }
            entity.deltaMovement = entity.deltaMovement
                .multiply(speed3.toDouble(), entity.deltaMovement.y, speed3.toDouble())
                .add(entity.deltaMovement)
        }
    }
}

// 永久Transfur
private fun yongJiuTransfurLogic(entity: Entity) {
    val modPersistenceData = eGetPersistentData(entity)
    val entityNbt = entity.nbt
    if (modPersistenceData.getBoolean(Constant.NBTKeys.IS_YONGJIU_SHOUHUA)) {
        val transfurType = entityNbt.getString(Constant.NBTKeys.TRANSFUR_VARIANT)
        if (transfurType != Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT &&
            modPersistenceData.getString(Constant.NBTKeys.TRANSFUR_TYPE) != transfurType) {
            modPersistenceData.putString(Constant.NBTKeys.TRANSFUR_TYPE, transfurType)
        }
    }
    if (modPersistenceData.getString(Constant.NBTKeys.TRANSFUR_TYPE) != Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT) {
        entity as LivingEntity changedModTransfur modPersistenceData.getString(Constant.NBTKeys.TRANSFUR_TYPE)
    }
}

// 强制创造飞行
private fun qiangZhiCFly(player: Player) {
    if (eGetPersistentData(player).getBoolean(Constant.NBTKeys.IS_QIANGZHI_C_FLY)) {
        player.abilities.flying = true
    }
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

// 获取实体的MOD持久化数据
fun eGetPersistentData(entity: Entity): CompoundTag {
    return entity.persistentData.getCompound(Constant.MODDatas.MOD_ID)
}