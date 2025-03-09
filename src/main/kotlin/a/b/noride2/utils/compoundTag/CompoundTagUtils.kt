package a.b.noride2.utils.compoundTag

import com.google.gson.*
import net.minecraft.nbt.*
import net.minecraft.world.entity.Entity

// 定义扩展属性
var Entity.nbt: CompoundTag
    get() = this.serializeNBT() // 调用 serializeNBT() 作为 getter
    set(value) {
        this.deserializeNBT(value) // 调用 deserializeNBT(value) 作为 setter
    }

fun CompoundTag.putAny(key: String, value: Any? = null, mode: String = "toString"): CompoundTag {
    if (value == null) {
        remove(key) // 如果值为 null，则移除该键
        return this
    }

    when (value) {
        is Int -> putInt(key, value)
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Float -> putFloat(key, value)
        is Double -> putDouble(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is CompoundTag -> put(key, value)
        is LongArray -> putLongArray(key, value)
        is IntArray -> putIntArray(key, value)
        is Short -> putShort(key, value)
        is List<*> -> putList(key, value) // 调用递归方法处理列表
        is ShortArray -> putShortArray(key, value) // 使用替代实现
        is DoubleArray -> putDoubleArray(key, value) // 使用替代实现
        is FloatArray -> putFloatArray(key, value) // 使用替代实现
        else -> {
            when (mode) {
                "toString" -> putString(key, value.toString())
                else -> throw IllegalArgumentException("Unsupported type: ${value.javaClass.name}")
            }
        }
    }
    return this
}

// 递归处理列表并生成 ListTag 的通用方法
private fun convertListToTag(list: List<*>, mode: String? = null): ListTag {
    val listTag = ListTag()
    for (item in list) {
        when (item) {
            is Boolean -> listTag.add(ByteTag.valueOf(item))
            is Int -> listTag.add(IntTag.valueOf(item))
            is String -> listTag.add(StringTag.valueOf(item))
            is Float -> listTag.add(FloatTag.valueOf(item))
            is Double -> listTag.add(DoubleTag.valueOf(item))
            is Long -> listTag.add(LongTag.valueOf(item))
            is Byte -> listTag.add(ByteTag.valueOf(item))
            is Short -> listTag.add(ShortTag.valueOf(item))
            is ByteArray -> listTag.add(ByteArrayTag(item))
            is LongArray -> listTag.add(LongArrayTag(item))
            is IntArray -> listTag.add(IntArrayTag(item))
            is ShortArray -> listTag.add(convertShortArrayToTag(item)) // 替代实现
            is DoubleArray -> listTag.add(convertDoubleArrayToTag(item)) // 替代实现
            is FloatArray -> listTag.add(convertFloatArrayToTag(item)) // 替代实现
            is CompoundTag -> listTag.add(item)
            is List<*> -> listTag.add(convertListToTag(item)) // 递归处理嵌套列表
            else -> {
                when (mode) {
                    "toString" -> listTag.add(StringTag.valueOf(item.toString()))
                    else -> throw IllegalArgumentException("Unsupported type: ${(item?.javaClass?.name)} ")
                }
            }
        }
    }
    return listTag
}

// 公开的 putList 方法
fun CompoundTag.putList(key: String, list: List<*>): CompoundTag {
    if (list.isEmpty()) {
        put(key, ListTag()) // 处理空列表
        return this
    }
    val listTag = convertListToTag(list) // 调用通用方法生成 ListTag
    put(key, listTag) // 将生成的 ListTag 存储到 CompoundTag 中
    return this
}

// 替代实现：将 ShortArray 转换为 ListTag
private fun convertShortArrayToTag(array: ShortArray): ListTag {
    val listTag = ListTag()
    for (item in array) {
        listTag.add(ShortTag.valueOf(item))
    }
    return listTag
}

// 替代实现：将 DoubleArray 转换为 ListTag
private fun convertDoubleArrayToTag(array: DoubleArray): ListTag {
    val listTag = ListTag()
    for (item in array) {
        listTag.add(DoubleTag.valueOf(item))
    }
    return listTag
}

// 替代实现：将 FloatArray 转换为 ListTag
private fun convertFloatArrayToTag(array: FloatArray): ListTag {
    val listTag = ListTag()
    for (item in array) {
        listTag.add(FloatTag.valueOf(item))
    }
    return listTag
}

// 公开的 putShortArray 方法
fun CompoundTag.putShortArray(key: String, array: ShortArray): CompoundTag {
    put(key, convertShortArrayToTag(array))
    return this
}

// 公开的 putDoubleArray 方法
fun CompoundTag.putDoubleArray(key: String, array: DoubleArray): CompoundTag {
    put(key, convertDoubleArrayToTag(array))
    return this
}

// 公开的 putFloatArray 方法
fun CompoundTag.putFloatArray(key: String, array: FloatArray): CompoundTag {
    put(key, convertFloatArrayToTag(array))
    return this
}