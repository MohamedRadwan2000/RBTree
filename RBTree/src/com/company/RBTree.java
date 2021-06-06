package com.company;

public class RBTree <T extends Comparable<T>, V>implements IRedBlackTree {
    Node root;

    public RBTree() {
        this.root = new Node(null,null,INode.BLACK);
    }

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
        this.root=new Node(null,null,INode.BLACK);
    }

    @Override
    public Object search(Comparable key) {
        return null;
    }

    @Override
    public boolean contains(Comparable key) {
        return false;
    }

    public void leftRotate (Node x){
        //node x is the parent of node y and y is the right child of x
        Node y = (Node) x.getRightChild();

        x.setRightChild(y.getLeftChild());
        if(!y.getLeftChild().isNull())
            y.getLeftChild().setParent(x);

        //make the old parent of x new parent of y
        y.setParent(x.getParent());

        //if x is the root then make y the new root
        if (x.getParent().isNull()){
            this.root = y ;
        }
        else if (x.equals(x.getParent().getLeftChild()))
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setLeftChild(x);
        x.setParent(y);
    }

    public void rightRotate(Node x){
        //node x is the parent of node y and y is the left child of x
        Node y = (Node) x.getLeftChild();

        x.setLeftChild(y.getRightChild());
        if(!y.getRightChild().isNull())
            y.getRightChild().setParent(x);

        //make the old parent of x new parent of y
        y.setParent(x.getParent());

        //if x is the root then make y the new root
        if (x.getParent().isNull()){
            this.root = y ;
        }
        else if (x.equals(x.getParent().getLeftChild()))
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setRightChild(x);
        x.setParent(y);
    }


    @Override
    public void insert(Comparable key,Object value) {

        Node<T,V> n = new Node(key, value,INode.RED);

        //basic case if the tree is empty then make it the root and color it black
        if (this.isEmpty()) {
            this.root = n;
            n.setColor(INode.BLACK);
            n.setRightChild(new Node(null,null,INode.BLACK));
            n.setLeftChild(new Node(null,null,INode.BLACK));
            return;
        }


        //first find the appropriate position for this node as if it was a BST
        Node<T,V> x = this.root;
        while (!x.isNull()){
            if (x.getKey().compareTo(n.getKey()) > 0)
                x = (Node) x.getLeftChild();
            else
                x = (Node) x.getRightChild();
        }

        //now x should be the parent of our new node
        x = (Node) x.getParent();
        n.setParent(x);

        if (x.getKey().compareTo(n.getKey()) > 0)
            x.setLeftChild(n);
        else
            x.setRightChild(n);

        //adding black null nodes to the end of it
        n.setRightChild(new Node(null,null,INode.BLACK));
        n.setLeftChild(new Node(null,null,INode.BLACK));
        //now the new node in it's place and we need to fix the tree
        fixInsert(n);
    }

    public void fixInsert(Node n){
        //base case if parent is black then tree is already fixed
        if(n.getParent().getColor() == INode.BLACK)
            return;

        while (n.getParent() != null && n.getParent().getColor() == INode.RED){
            //G : grand parent , U : uncle
            Node G = (Node) n.getParent().getParent() , U;

            //if P is the right child of G
            if (n.getParent().equals(G.getRightChild())){
                U = (Node) G.getLeftChild();

                if (U.getColor() == INode.RED){
                    //in this case P and U = RED then change P U
                    n.getParent().setColor(INode.BLACK);
                    U.setColor(INode.BLACK);
                    if (!G.equals(root)){
                        G.setColor(INode.RED);
                        //move the problem to the grandparent
                        n = G ;
                    }
                }
                else{
                    if (n.equals(n.getParent().getLeftChild())){
                        rightRotate((Node) n.getParent());
                        //make n point on the leaf node again to make leftRotate in the next step
                        n = (Node) n.getRightChild();
                    }
                    n.getParent().setColor(INode.BLACK);
                    n.getParent().getParent().setColor(INode.RED);
                    leftRotate((Node) n.getParent().getParent());
                }
            }

            //if P is the left child of G
            else{
                U = (Node) G.getRightChild();

                if (U != null && U.getColor() == INode.RED){
                    //in this case P and U = RED then change P U
                    n.getParent().setColor(INode.BLACK);
                    U.setColor(INode.BLACK);
                    if (!G.equals(root)) {
                        G.setColor(INode.RED);
                        //move the problem to the grandparent
                        n = G;
                    }
                }

                else{
                    if (n.equals(n.getParent().getRightChild())) {
                        leftRotate((Node) n.getParent());
                        //make n point on the leaf node again to make rightRotate in the next step
                        n = (Node) n.getLeftChild();
                    }
                    n.getParent().setColor(INode.BLACK);
                    G.setColor(INode.RED);
                    rightRotate((Node) n.getParent().getParent());
                }
            }
        }
        //we can delete this line but i still don't know why :))
        root.setColor(INode.BLACK);
    }



    @Override
    public boolean delete(Comparable key) {
        return false;
    }

}
