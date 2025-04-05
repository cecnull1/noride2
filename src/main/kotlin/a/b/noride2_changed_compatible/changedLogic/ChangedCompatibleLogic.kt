package a.b.noride2_changed_compatible.changedLogic

import a.b.noride2.logic.eGetPersistentData
import a.b.noride2.utils.compoundTag.nbt
import a.b.noride2_bn_compatible.eventLogic.bnCLogic1
import constant.Constant
import net.ltxprogrammer.changed.entity.TransfurCause
import net.ltxprogrammer.changed.entity.TransfurContext
import net.ltxprogrammer.changed.init.ChangedRegistry
import net.ltxprogrammer.changed.process.ProcessTransfur
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraftforge.fml.ModList

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
 * 该方法通过指定的兽化类型对实体进行兽化操作。
 *
 * 它使用 `ProcessTransfur.transfur()` 方法完成兽化逻辑，确保与项目的其他部分保持一致。
 * 此方法推荐用于替代直接操作 NBT 数据的方式（如已废弃的 [transfur] 方法）。
 *
 * @param transfurType 兽化类型的字符串表示形式，格式为 "modId:path"。
 *                     例如："changed:example_variant"。
 * @receiver 要进行兽化的实体。
 *
 * 示例：
 * ```kotlin
 * val entity: LivingEntity = ... // 获取某个实体
 * entity.changedModTransfur("changed:example_variant") // 对实体应用兽化操作
 * ```
 *
 * 注意事项：
 * - 确保 `transfurType` 的格式正确，否则可能导致解析错误。
 * - 该方法依赖于 `ProcessTransfur.transfur()` 和 `ChangedRegistry.TRANSFUR_VARIANT`，请确保这些系统的正常运行。
 */
infix fun LivingEntity.changedModTransfur(transfurType: String): LivingEntity {
    if (!ModList.get().isLoaded("changed")) return this
    val modIdAndPath = transfurType.split(':')
    require(modIdAndPath.size == 2) { "Invalid transfurType format. Expected 'modId:path'." }
    val modId = modIdAndPath[0]
    val path = modIdAndPath[1]
    ProcessTransfur.transfur(
        this,
        this.level,
        ChangedRegistry.TRANSFUR_VARIANT.get().getValue(ResourceLocation(modId, path)),
        true,
        TransfurContext.hazard(TransfurCause.GRAB_REPLICATE)
    )
    return this
}

infix fun LivingEntity.transfur(transfurType: String): LivingEntity {
    return this changedModTransfur transfurType
}

/**
 * **已废弃**：此方法已被废弃，请使用 [changedModTransfur] 方法代替。
 *
 * 该方法通过直接修改实体的 NBT 数据来应用兽化类型。
 * 由于直接操作 NBT 数据存在潜在风险（如导致状态异常或与其他功能冲突），因此不再推荐使用。
 *
 * @param transfurType 兽化类型的字符串表示形式。
 * @receiver 要进行兽化的实体。
 *
 * 示例（已废弃，不推荐使用）：
 * ```kotlin
 * val entity: Entity = ... // 获取某个实体
 * entity.transfur("exampleVariantType") // 对实体应用兽化操作（不推荐）
 * ```
 *
 * 注意事项：
 * - 直接操作 NBT 数据可能导致不可预见的问题，建议使用更安全的替代方法。
 * - LtxProgrammer 提到：“通过覆盖玩家的 NBT 数据来进行兽化不是一个受支持的操作。”
 * - 建议改用 [changedModTransfur] 方法以获得更好的兼容性和安全性。
 *
 * @deprecated 自 2025-03-29 起废弃，建议迁移到 [changedModTransfur]。
 */
@Deprecated(
    message = "This method is deprecated. Use changedModTransfur instead.",
    replaceWith = ReplaceWith("this.changedModTransfur(transfurType)"),
    level = DeprecationLevel.WARNING
)
infix fun Entity.transfur(transfurType: String) {
    val nbtData = this.nbt
    if (nbtData.getString(Constant.NBTKeys.TRANSFUR_VARIANT) == Constant.TransfurVariantType.NONE_TRANSFUR_VARIANT) {
        nbtData.putString(Constant.NBTKeys.TRANSFUR_VARIANT, transfurType)
        this.nbt = nbtData
    }
}