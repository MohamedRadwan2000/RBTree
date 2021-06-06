package com.company;

public class RBTree implements IRedBlackTree {
    INode root=new Node();
    @Override
    public INode getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return root.isNull();
    }
    @Override
    public void clear() {
        this.root=new Node();
    }

    @Override
    public Object search(Comparable key) {
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }
}
