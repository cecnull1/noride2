package a.b.noride2.utils.function

import a.b.noride2.utils.infix.InfixFunction.nbtIn
import net.minecraft.world.entity.Entity

infix fun Entity.inEntityPersistentNbtAndRemove(
    key: String
): Boolean {
    val persistentData = this.persistentData
    val hasKey = persistentData nbtIn key
    val keyValue = if (hasKey) persistentData.getBoolean(key) else false

    // 检查键是否存在且值为 true
    if (hasKey && keyValue) {
        // 清除指定键
        persistentData.remove(key)
        return true
    }
    return false
}

infix fun Entity.inEntityPersistentNbt(key: String) : Boolean {
    val persistentData = this.persistentData
    val hasKey = persistentData nbtIn key
    return hasKey
}