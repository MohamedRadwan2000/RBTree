package com.company;

public class RBTree  <T extends Comparable<T>, V>implements IRedBlackTree {
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
        return search_helper(root,key).getKey();
    }
    @Override
    public boolean contains(Comparable key) {
        return search(key)!=null;
    }

    @Override
    public void insert(Comparable key, Object value) {

    }

    @Override
    public boolean delete(Comparable key) {
        INode root=search_helper(this.root,key);
        if(root==null){return false;}
        else {
            delete_helper(root);
            return true;
        }
    }
    public INode search_helper(INode root,Comparable key){
        if(root.isNull()||(root.getKey().compareTo(key)==0)){
            return root;
        }
        if(root.getKey().compareTo(key)>0){
            return search_helper(root.getLeftChild(),key);
        }
        if(root.getKey().compareTo(key)<0){
            return search_helper(root.getRightChild(),key);
        }
   return null;
    }
    public boolean delete_helper(INode root){
return true;
    }
    public void LeftR(INode y){
        INode x=y.getParent();
        x.setRightChild(y.getLeftChild());
        //If the parent of x is NULL, make y as the root of the tree.
        if(x.getParent()==null){
            y.setLeftChild(x);
            x.setParent(y);
        }
        //Else if x is the left child of it's parent, make y as the left child of x's parent.
        else if(x.getParent().getLeftChild().equals(x)){
            x.getParent().setLeftChild(y);
            y.setParent(x.getParent());
        }
        //Else assign y as the right child of x's parent.
        else if(x.getParent().getRightChild().equals(x)){
            x.getParent().setRightChild(y);
            y.setParent(x.getParent());
        }
        //Make y parent of x
        //make x the left child of y
        y.setLeftChild(x);
        x.setParent(y);

    }
    public void RightR(INode y){
        INode x=y.getParent();
        x.setLeftChild(y.getRightChild());
        //If the parent of x is NULL, make y as the root of the tree.
        if(x.getParent()==null){
            y.setRightChild(x);
            x.setParent(y);
        }
        //Else if x is the left child of it's parent, make y as the left child of x's parent.
        else if(x.getParent().getLeftChild().equals(x)){
            x.getParent().setLeftChild(y);
            y.setParent(x.getParent());
        }
        //Else assign y as the right child of x's parent.
        else if(x.getParent().getRightChild().equals(x)){
            x.getParent().setRightChild(y);
            y.setParent(x.getParent());
        }
        //Make y parent of x
        //make x the left child of y
        y.setRightChild(x);
        x.setParent(y);
    }
    public void setRoot(INode root){

    }
}
