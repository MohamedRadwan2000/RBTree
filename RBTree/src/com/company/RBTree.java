package com.company;

public class RBTree implements IRedBlackTree {
    INode root=new Node(null,null);

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
        this.root=new Node(null,null);
    }

    @Override
    public Object search(Comparable key) {
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    public void leftRotate (INode x){
        //node x is the parent of node y and y is the right child of x
        INode y = x.getRightChild();

        x.setRightChild(y.getLeftChild());
        if(!y.getLeftChild().isNull())
            y.getLeftChild().setParent(x);

        //make the old parent of x new parent of y
        y.setParent(x.getParent());

        //if x is the root then make y the new root
        if (x.getParent().isNull()){
            this.root = y ;
        }
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setLeftChild(x);
        x.setParent(y);
    }

    public void rightRotate(INode x){
        //node x is the parent of node y and y is the left child of x
        INode y = (Node) x.getLeftChild();

        x.setLeftChild(y.getRightChild());
        if(!y.getRightChild().isNull())
            y.getRightChild().setParent(x);

        //make the old parent of x new parent of y
        y.setParent(x.getParent());

        //if x is the root then make y the new root
        if (x.getParent().isNull()){
            this.root = y ;
        }
        else if (x == x.getParent().getLeftChild())
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setRightChild(x);
        x.setParent(y);
    }


    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        return false;
    }

}
