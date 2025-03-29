package a.b.noride2_changed_compatible.changedLogic

import a.b.noride2.logic.eGetPersistentData
import a.b.noride2.utils.compoundTag.nbt
import a.b.noride2_bn_compatible.eventLogic.bnCLogic1
import constant.Constant
import net.minecraft.world.entity.Entity

fun changedCompatibleLogic(entity: Entity) {
    if (Constant.FML.betterNeonIsLoaded) {

        // 强制TRANSFUR
        fun qiangZhiTransfur(entity: Entity) {
            if (eGetPersistentData(entity).getBoolean(Constant.NBTKeys.IS_TRANSFUR)) {
                bnCLogic1(entity, entity.persistentData)
            }
        }
        qiangZhiTransfur(entity)
    }
}

/**
 * 注：这个方法在有更好的方法之前不会再次更新了
 * Note: This method will not be updated again until there is a better alternative.
 *
 * 该方法可以将实体进行兽化操作
 * This method performs a "transfur" operation on the entity, which seems to involve modifying its NBT data to apply a specific variant.
 *
 * @param transfurType The type of transfur variant to apply to the entity.
 * @receiver The entity to be transfurred.
 *
 * @see Constant.NBTKeys.TRANSFUR_VARIANT for the NBT key used to store the transfur variant.
 * @see Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT for the default value indicating no transfur variant.
 *
 * 示例：
 * Example:
 * <pre>
 *     val entity: Entity = ... // 获取某个实体
 *     entity.transfur("exampleVariantType") // 对实体应用兽化操作
 * </pre>
 *
 * 注意：
 * 注意事项：
 * - 该方法直接操作实体的 NBT 数据，可能存在潜在风险，如导致实体状态异常或与其他功能冲突。
 * - 在使用此方法之前，请确保了解其对实体的具体影响，并谨慎处理。
 * - This method directly manipulates the entity's NBT data, which may pose potential risks, such as causing abnormal entity states or conflicts with other functionalities.
 * - Before using this method, ensure that you understand its specific impact on the entity and proceed with caution.
 */
infix fun Entity.transfur(transfurType: String) {
    val nbtData = this.nbt
    if (nbtData.getString(Constant.NBTKeys.TRANSFUR_VARIANT) == Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT) {
        nbtData.putString(Constant.NBTKeys.TRANSFUR_VARIANT, transfurType)
        this.nbt = nbtData
    }
}