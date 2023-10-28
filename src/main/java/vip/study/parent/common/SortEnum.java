package vip.study.parent.common;

public enum SortEnum {

    SELECT(1, "选择排序"),
    INSERT(2, "插入排序"),
    BUBBLE(3, "冒泡排序"),
    QUICK(4, "快速排序"),
    MERGE(5, "归并排序");

    private int value;
    private String desc;

    SortEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SortEnum{" +
                "value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static int byDesc(String desc) {
        for (SortEnum value : values()) {
            if (value.desc.equals(desc)) return value.getValue();
        }
        return -1;
    }



}



