package com.nilay;

public class DetectAndRemoveLoop {

    static class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }

    static Node head;
    void detectAndRemove(Node node){
        if (node == null || node.next == null)
            return;

        Node slow = node, fast = node;

        slow = slow.next;
        fast = fast.next.next;

        while (fast != null && fast.next != null) {
            if (slow == fast){
                fast.next.next=null;
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
    }

    void printList(Node node)
    {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        DetectAndRemoveLoop list = new DetectAndRemoveLoop();
        list.head = new Node(50);
        list.head.next = new Node(20);
        list.head.next.next = new Node(15);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(10);
        list.head.next.next.next.next.next = new Node(11);
        list.head.next.next.next.next.next.next = new Node(12);

        // Creating a loop for testing
        head.next.next.next.next.next.next.next = head.next.next;
        list.detectAndRemove(head);
        System.out.println("Linked List after removing loop : ");
        list.printList(head);
    }
}
