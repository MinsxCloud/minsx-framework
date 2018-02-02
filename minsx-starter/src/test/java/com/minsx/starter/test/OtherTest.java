package com.minsx.starter.test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.minsx.common.util.Node;
import com.minsx.starter.test.jpa.Query;
import org.junit.Test;

public class OtherTest {

    @Test
    public void test() {

        Node<Query> mainNode = new Node<>();

        Node<Query> nodeA = new Node<>();
        nodeA.addChild(new Query("name", "like", "wang"));
        nodeA.addChild(new Query("age", "equals", 16));
        mainNode.addChildNode(nodeA);

        Node<Query> nodeB = new Node<>();
        nodeB.addChild(new Query("name", "like", "he"));
        nodeB.addChild(new Query("age", "equals", 18));
        mainNode.addChildNode(nodeB);

        String value = JSON.toJSONString(mainNode);
        System.out.println(value);

        Node<Query> newNode = (Node<Query>) JSON.parseObject(value, Node.class);

        System.out.println(JSON.toJSONString(newNode));


    }


}
