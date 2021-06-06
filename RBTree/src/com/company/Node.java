package com.company;

public class Node  <T extends Comparable<T>, V>implements INode {
    T key;
    V value;
    INode parent;
    INode left , right ;
    boolean color = RED;

    public Node(T key, V value) {
        this.key = key;
        this.value = value;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public void setParent(INode parent) {
        this.parent=parent;
    }

    @Override
    public INode getParent() {
        return  this.parent;
    }

    @Override
    public void setLeftChild(INode leftChild) {
        this.left=leftChild;
    }

    @Override
    public INode getLeftChild() {
        return this.left;
    }

    @Override
    public void setRightChild(INode rightChild) {
        this.right=rightChild;
    }

    @Override
    public INode getRightChild() {
        return this.right;
    }

    @Override
    public Comparable getKey() {
        return this.key;
    }

    @Override
    public void setKey(Comparable key) {
    this.key=(T)key;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object value) {
       this.value= (V) value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color=color;
    }

    @Override
    public boolean isNull() {
       return this.key==null ;
    }
}
