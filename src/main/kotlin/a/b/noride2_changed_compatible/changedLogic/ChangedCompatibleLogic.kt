package a.b.noride2_changed_compatible.changedLogic

import a.b.noride2.utils.extension_properties.extension_properties.eGetPersistentData
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
    if (ModList.get().isLoaded("changed")) {
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
        TransfurContext.hazard(TransfurCause.SYRINGE)
    )
    return this
}

infix fun LivingEntity.transfur(transfurType: String): LivingEntity {
    return this changedModTransfur transfurType
}