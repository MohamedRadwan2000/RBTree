package com.company;

public class RBTree  <T extends Comparable<T>, V>implements IRedBlackTree {
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
        return search_helper(root,key).getKey();
    }
    @Override
    public boolean contains(Comparable key) {
        return search(key)!=null;
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
        root.setColor(INode.BLACK);
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
    public boolean delete(Comparable key) {
        INode root=search_helper(this.root,key);
        if(root.isNull()){return false;}
        else {
            INode replaced_node;
            if(root.getColor()==INode.BLACK){
                replaced_node=delete_helper(root,key);
                fix_delete(replaced_node);
            }
            else {
                replaced_node=delete_helper(root,key);
               /* if(!replaced_node.isNull()){
                    replaced_node.setColor(INode.RED);
                    replaced_node.getLeftChild().setColor(INode.BLACK);
                    replaced_node.getRightChild().setColor(INode.BLACK);}*/
            }
            return true;
        }
    }
    public INode delete_helper(INode root, Comparable key)
    {   //find node to be deleted
        INode current=search_helper(root,key);
        if(!current.isNull()){
            if (current.getLeftChild().isNull()) {
                if (current.equals(current.getParent().getLeftChild()))
                    current.getParent().setLeftChild(current.getRightChild());
                else {
                    current.getParent().setRightChild(current.getRightChild());
                }
                return current.getRightChild();
            }
            else if (root.getRightChild().isNull()) {
                if (current.equals(current.getParent().getLeftChild()))
                    current.getParent().setLeftChild(current.getLeftChild());
                else {
                    current.getParent().setRightChild(current.getLeftChild());
                }
                return current.getLeftChild();
            }
            else {
                INode NewRoot=minValue(current.getRightChild());
                current.setKey(NewRoot.getKey());
                current.setValue(NewRoot.getValue());
                //current.setColor(NewRoot.getColor());
                INode replaced_node;
                replaced_node=delete_helper(current.getRightChild(),NewRoot.getKey());
                fix_delete(replaced_node);
                return current;
            }
        }
        return null;

    }
    // find a successor of a subtree
    public INode minValue(INode root)
    {
        //int minv = root.key;
        while (!root.getLeftChild().isNull())
        {
            root = root.getLeftChild();
        }
        return root;
    }
    public void fix_delete(INode replaced_node){
        //case1 (deleted node is Black and has Red child)
        if (replaced_node.getColor()==INode.RED){replaced_node.setColor(INode.BLACK);}
        else {
            //case2   (deleted node is Black and the replaced Node is Black also)
            INode S=get_sibling(replaced_node);
            if(S==null|| S.isNull()){return;}
            //case2.1: (x’s sibling S is red)
            if(S.getColor()==INode.RED){
                //switches colors of S and its parent
                S.setColor(INode.BLACK);
                S.getParent().setColor(INode.RED);
                //if S in left
                if(S.equals(S.getParent().getLeftChild())){
                    rightRotate((Node) S.getParent());// rotate S up
                }
                //if S in right
                if(S.equals(S.getParent().getRightChild())){
                    leftRotate((Node) S.getParent());// rotate S up
                }
                fix_delete(replaced_node);
            }
            //case2.2: sibling S is black, and both of S’s children are black.
            else if((S.getLeftChild().getColor()==INode.BLACK)&&(S.getRightChild().getColor()==INode.BLACK)) {
                //Recolor S to red
                S.setColor(INode.RED);
                //if Parent of S is red, recolor it Black and terminate
                if(S.getParent().getColor()==INode.RED){
                    S.getParent().setColor(INode.BLACK);
                }
                //if Parent of S is Black,solve the double-black for parent node
                else {fix_delete(S.getParent());}
            }
            //S (in right)
            else if(S.equals(S.getParent().getRightChild())) {
                //case2.3: S  is black, and S’s right child is red.
                if (S.getRightChild().getColor() == INode.RED) {
                    //recolor S to S's parent color
                    S.setColor(S.getParent().getColor());
                    //recolor S's right child to Black
                    S.getRightChild().setColor(INode.BLACK);
                    //recolor S's parent to Black
                    S.getParent().setColor(INode.BLACK);
                    //left rotation S's parent
                    leftRotate((Node) S.getParent());
                }
                //case3.3: S  is black, and S’s right child is red.
                else if(S.getLeftChild().getColor()==INode.RED){
                    //switch the colors of S and its left child S.left
                    S.setColor(INode.RED);
                    S.getLeftChild().setColor(INode.BLACK);
                    //right rotation on S
                    rightRotate((Node) S);
                    //repeat fix again
                    fix_delete(replaced_node);
                }

            }
            //S in left
            else if(S.equals(S.getParent().getLeftChild())){
                //case2.3: S  is black, and S’s right child is red.
                if (S.getLeftChild().getColor() == INode.RED) {
                    //recolor S to S's parent color
                    S.setColor(S.getParent().getColor());
                    //recolor S's right child to Black
                    S.getLeftChild().setColor(INode.BLACK);
                    //recolor S's parent to Black
                    S.getParent().setColor(INode.BLACK);
                    //left rotation S's parent
                    rightRotate((Node) S.getParent());
                }
                //case3.3: S  is black, and S’s right child is red.
                else if(S.getRightChild().getColor()==INode.RED){
                    //switch the colors of S and its left child S.left
                    S.setColor(INode.RED);
                    S.getRightChild().setColor(INode.BLACK);
                    //left rotation on S
                    leftRotate((Node) S);
                    //repeat fix again
                    fix_delete(replaced_node);
                }
            }
        }
    }
    public INode get_sibling(INode node){
        if(node.getParent()==null){return null;}
        if(node.equals(node.getParent().getLeftChild())){
            return node.getParent().getRightChild();
        }
        else {
            return node.getParent().getLeftChild();
        }

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
        if (x.getParent()==null){
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
        if (x.getParent()==null){
            this.root = y ;
        }
        else if (x.equals(x.getParent().getLeftChild()))
            x.getParent().setLeftChild(y);
        else
            x.getParent().setRightChild(y);

        y.setRightChild(x);
        x.setParent(y);
    }



}
