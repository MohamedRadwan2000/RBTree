package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        INode<Integer,String> Root=new Node();
        INode<Integer,String> n1=new Node();
        INode<Integer,String> n2=new Node();
        INode<Integer,String> n3=new Node();
        INode fake=new Node();
        n1.setKey(7);n1.setValue("A");
        n1.setRightChild(fake);
        n1.setLeftChild(fake);
        n2.setKey(4);n2.setValue("B");
        n3.setKey(2);n3.setValue("C");
        Root.setKey(5);Root.setValue("G");
        Root.setLeftChild(n3);
        Root.setRightChild(n1);
        n3.setRightChild(n2);
        n3.setLeftChild(fake);
        n2.setLeftChild(fake);
        n2.setRightChild(fake);
        RBTree<Integer,String>T=new RBTree();
        T.


          }
}
