package com.minsx.common.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joker on 2017/5/27.
 */
public class Node<T> implements Serializable {

    private static final long serialVersionUID = 2960635941348388442L;

    private T entity;
    private List<Node<T>> childs;

    public Node(T entity) {
        this.entity = entity;
    }

    public Node() {
    }

    public void addChildNode(Node<T> childNode) {
        if (this.childs == null) {
            this.childs = new ArrayList<Node<T>>();
        }
        this.childs.add(childNode);
    }

    public void addChild(T child) {
        if (this.childs == null) {
            this.childs = new ArrayList<Node<T>>();
        }
        this.childs.add(new Node<>(child));
    }

    public void addChildNodes(List<Node<T>> childNodes) {
        if (this.childs == null) {
            this.childs = new ArrayList<Node<T>>();
        }
        this.childs.addAll(childNodes);
    }

    public void addChilds(List<T> childNodes) {
        if (this.childs == null) {
            this.childs = new ArrayList<Node<T>>();
        }
        if (childNodes == null) {
            return;
        }
        List<Node<T>> nodes = new ArrayList<>();
        childNodes.forEach(childNode -> {
            nodes.add(new Node<>(childNode));
        });
        this.childs.addAll(nodes);
    }


    public void removeChildNode(Node<T> childNode) {
        if (this.childs != null) {
            this.childs.remove(childNode);
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public List<Node<T>> getChilds() {
        return childs;
    }

    public void setChilds(List<Node<T>> childs) {
        this.childs = childs;
    }
}
