package org.gyb.algorithm.lru;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于hashmap + 双向链表实现LRU
 * <p>
 * hashMap用来检索定位到Node，Node用于删除和更新
 * hashMap中的key <p>
 * LRU least recent used 最近最少使用
 * 一般用于缓存中，比如缓存中间件redis
 * <p>
 * <1> 队列不行吗？<p>
 * 不行，队列只能做到先进先出，但是重复用到中间的数据时无法把中间的数据移动到顶端。<p>
 * <2> 单链表不行吗？<p>
 * 单链表能实现新来的放头部，最久不用的在尾部删除。但删除的时候需要遍历到尾部，因为单链表只有头指针。
 * 在用到已经用到过的数据时，还要遍历整合链表，来确定是否用过，然后再遍历到响应位置来剔除的节点，并重新放在头部。这效率可想而知。
 * <p>
 * 这时hashmap的作用就出来了 他可以在单位1的时间判断value的值是否存在，key直接存储节点对象，
 * 能直接定位删除对应的节点(将比节点的父节点指向子节点)。要通过一个节点直接获得父节点的话，通过单链表是不行的。
 * 这时双向链表的作用也提现出来了。能直接定位到父节点。
 * 这效率就很高了。而且由于双向链表有尾指针，所以剔除最后的尾节点也十分方便，快捷。
 * <p>
 * redis中的过期策略采用的是定时过期 + 懒惰过期
 * redis中的淘汰策略有好几种，其中主要使用的是LRU
 *
 * @author xiaofeifei
 * @date 2020-03-15
 * @since
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LRU<K, V> {

    /**
     * 设置最大容量，如果达到了则进行LRU
     */
    int capacity;

    /**
     * 检索表，K记录Node的key, V记录Node的value
     */
    ConcurrentHashMap<K, Node<K, V>> hashMap;

    /**
     * 头结点
     */
    Node<K, V> head;

    /**
     * 尾结点
     */
    Node<K, V> tail;

    public LRU(int capacity) {
        // 初始化hashMap
        this.capacity = capacity;
        hashMap = new ConcurrentHashMap<>(capacity);
        head = new Node<>();
        tail = new Node<>();
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
    }


    @Setter
    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class Node<K, V> {

        K key;

        V data;

        Node<K, V> prev;

        Node<K, V> next;
    }

    /**
     * 添加一个元素
     * 添加时校验容量是否达到阈值，达到了则进行LRU
     * 校验是否重复，重复则直接覆盖
     */
    public void add(K key, V data) {
        // 已存在则直接覆盖
        if (hashMap.contains(key)) {
            Node<K, V> node = hashMap.get(key);
            node.setKey(key);
            node.setData(data);
            return;
        }

        // 不存在则添加
        // 校验是否达到阈值，达到则移除链尾node以及移除hash中的元素
        if (hashMap.size() >= capacity) {
            // 移除hash中的链尾数据
            hashMap.remove(tail.prev.getKey());
            // 移除链尾的node
            tail.prev = tail.prev.prev;
            tail.prev.next = tail;
        }
        Node<K, V> node = new Node<>();
        node.setData(data);
        node.setKey(key);
        // 现在的头部node指向原来的头部node
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        // 同步hash
        hashMap.put(key, node);
    }

    /**
     * 获取一个元素
     * 查询时校验key是否存在，
     * 存在则将当前Node转换到链首
     */
    public Node<K, V> get(K key) {
        Node<K, V> node = hashMap.get(key);
        // 校验key是否存在，不存在则返回为null
        if (Objects.isNull(node)) {
            return null;
        }
        // 设置到链首
        if (head.next == node) {
            return node;
        }

        // 上一个节点的下一个指向下一个节点的上一个 node.prev.next -> node ===> node.prev.next -> node.next
        node.prev.next = node.next;
        // 下一个节点的上一个指向上一个节点的下一个 node.next.prev -> node ===> node.next.prev -> node.prev
        node.next.prev = node.prev;

        // 移动当前节点至链首
        // head的下一个节点的上一个执行当前节点 head.next.prev -> head ===> head.next.prev -> node
        head.next.prev = node;
        // 当前节点的下一个指向head节点的下一个 node.next -> node.next ===> node.next -> head.next
        node.next = head.next;
        // head.next -> head.next ===> head.next -> node
        head.next = node;
        // node.prev -> node.prev ===> node.prev -> head
        node.prev = head;
        return node;
    }

    public static void main(String[] args) {
        LRU<String, String> test = new LRU<>(5);
        test.add("bibi", "bibi");
        test.add("xixi", "xixi");
        test.add("haha", "haha");
        test.add("lala", "lala");
        test.add("bobo", "bobo");
        test.add("biubiu", "biubiu");
        test.add("zizi", "zizi");

        System.out.println("bibi is null ? " + Objects.isNull(test.get("bibi")));
        System.out.println("xixi is null ? " + Objects.isNull(test.get("xixi")));
        System.out.println("haha is null ? " + Objects.isNull(test.get("haha")));
        Node<String, String> haha = test.get("haha");
        System.out.println("haha key: " + haha.getKey() + ", haha data: " + haha.getData());
    }


}