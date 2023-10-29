package vip.study.parent.api.model;

import lombok.Data;

@Data
public class LinkNode {
    public int val; // ֵ
    public LinkNode next; //�ڵ�
    public LinkNode() {};

    public LinkNode(int val){
        this.val = val;
    }

    public LinkNode(int val, LinkNode next) {
        this.val = val; this.next = next;
    }
}
