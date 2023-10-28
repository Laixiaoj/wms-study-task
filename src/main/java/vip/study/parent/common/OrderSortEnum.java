package vip.study.parent.common;


public enum OrderSortEnum {
    PRE(1, "前序遍历"),
    MIDD(2, "中序遍历"),
    LAST(3, "后序遍历"),
    LEVEL(4, "层次遍历");

    private int value;
    private String desc;

    @Override
    public String toString() {
        return "OrderSortEnum{" +
                "value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }



    OrderSortEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static int byDesc(String desc) {
        for (OrderSortEnum value : values()) {
            if (value.desc.equals(desc)){
                return value.getValue();
            }
        }
        return -1;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

}
