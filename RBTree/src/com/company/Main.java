package com.company;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void  readTree(Node k){
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(k);
        int space = 25 ;
        while (queue.size() != 0)
        {
            LinkedList<Node> queue1 = new LinkedList<Node>();
            for (int i = 0; i < space; i++) {
                System.out.print(" ");
            }
            while(queue.size() != 0){
                Node s = queue.poll();

                if (s == null){
                    for (int i = 0; i < 10; i++) {
                        System.out.print(" ");
                    }
                    continue;
                }


                System.out.print(s.getKey()+""+(s.getColor()?"R":"B")+" ");
                for (int i = 0; i < 2; i++) {
                    System.out.print(" ");
                }
                if(s.getLeftChild() != null) {
                    queue1.add((Node) s.getLeftChild());
                }
                else
                    queue1.add(null);
                if (s.getRightChild() != null)
                    queue1.add((Node) s.getRightChild());
                else
                    queue1.add(null);
            }
            System.out.print("\n");
            queue = queue1 ;
            space-=2;
        }
    }

    public static void main(String[] args) {
        RBTree<Integer,Integer> b = new RBTree<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0 ; i < n ;i++){
            int a = sc.nextInt();
            b.insert(a,1);
        }
        readTree((Node) b.getRoot());
        System.out.print("delete : ");
        int d = sc.nextInt();
        System.out.println(b.search(9));
        System.out.println(b.getSize());
        System.out.print(b.delete(d));
        System.out.println(b.getSize());
        readTree((Node) b.getRoot());

    }
}