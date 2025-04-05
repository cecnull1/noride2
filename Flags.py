class TorsoType:
    GENERIC = 0  # 通用
    CHISELED = 1  # 雕刻
    FEMALE = 2  # 女性
    HEAVY = 3  # 重型

class HairType:
    BALD = 0  # 光头
    SHORT = 1  # 短发
    LONG = 2  # 长发

class EarType:
    WOLF = 0  # 狼耳
    CAT = 1  # 猫耳
    DRAGON = 2  # 龙耳
    SHARK = 3  # 鲨鱼耳

class TailType:
    WOLF = 0  # 狼尾
    CAT = 1  # 猫尾
    DRAGON = 2  # 龙尾
    SHARK = 3  # 鲨鱼尾

class LegType:
    BIPEDAL = 0  # 双足
    CENTAUR = 1  # 半人马
    MERMAID = 2  # 美人鱼

class ArmType:
    GENERIC = 0  # 通用
    WYVERN = 1  # 喷火龙
    SHARK = 2  # 鲨鱼

class ScaleType:
    NORMAL = 0  # 正常
    BUFF = 1  # 强壮
    SMALL = 2  # 小型

def 计算_flags(躯干类型编号, 发型类型编号, 耳朵类型编号, 尾巴类型编号, 腿部类型编号, 手臂类型编号, 体型类型编号):
    """
    根据给定的特征编号计算Flags值
    :param 躯干类型编号: 躯干类型编号
    :param 发型类型编号: 发型类型编号
    :param 耳朵类型编号: 耳朵类型编号
    :param 尾巴类型编号: 尾巴类型编号
    :param 腿部类型编号: 腿部类型编号
    :param 手臂类型编号: 手臂类型编号
    :param 体型类型编号: 体型类型编号
    :return: 计算出的Flags值
    """
    flags = 0
    flags |= 躯干类型编号 & 0xf  # 躯干类型：0-3位
    flags |= (发型类型编号 & 0xf) << 4  # 发型类型：4-7位
    flags |= (耳朵类型编号 & 0xf) << 8  # 耳朵类型：8-11位
    flags |= (尾巴类型编号 & 0xf) << 12  # 尾巴类型：12-15位
    flags |= (腿部类型编号 & 0xf) << 16  # 腿部类型：16-19位
    flags |= (手臂类型编号 & 0xf) << 20  # 手臂类型：20-23位
    flags |= (体型类型编号 & 0xf) << 24  # 体型类型：24-27位
    return flags

# 示例：根据给定的特征编号计算Flags
躯干类型编号 = TorsoType.FEMALE
发型类型编号 = HairType.LONG
耳朵类型编号 = EarType.WOLF
尾巴类型编号 = TailType.WOLF
腿部类型编号 = LegType.MERMAID
手臂类型编号 = ArmType.SHARK
体型类型编号 = ScaleType.NORMAL

flags = 计算_flags(躯干类型编号, 发型类型编号, 耳朵类型编号, 尾巴类型编号, 腿部类型编号, 手臂类型编号, 体型类型编号)
print(f"计算出的Flags值为: {flags}")