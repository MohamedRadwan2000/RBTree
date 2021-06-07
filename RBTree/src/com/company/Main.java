package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void  readTree(Node k){


        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(k);

        while (queue.size() != 0)
        {
            LinkedList<Node> queue1 = new LinkedList<Node>();
            while(queue.size() != 0){
                Node s = queue.poll();
                System.out.print(s.getKey()+""+(s.getColor()?"R":"B")+" ");
                if(s.getLeftChild() != null)
                    queue1.add((Node) s.getLeftChild());
                if (s.getRightChild() != null)
                    queue1.add((Node) s.getRightChild());
            }
            System.out.println("\n");
            queue = queue1 ;
        }
    }

    public static void main(String[] args) {
        RBTree<Integer,Integer> b = new RBTree();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for (int i = 0; i < n; i++) {
            int c= sc.nextInt();
            b.insert(c,1);
        }
        readTree((Node) b.getRoot());
        b.delete(13);
        readTree((Node) b.getRoot());
    }
}
