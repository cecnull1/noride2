package a.b.noride2.utils.function

import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.ai.attributes.Attributes

fun inEntityNBT(
    entity: Entity,
    key: String
): Boolean {
    val persistentData = entity.persistentData
    val hasKey = persistentData.contains(key)
    val keyValue = if (hasKey) persistentData.getBoolean(key) else false

    // 检查键是否存在且值为 true
    if (hasKey && keyValue) {
        // 清除指定键
        persistentData.remove(key)
        return true
    }
    return false
}