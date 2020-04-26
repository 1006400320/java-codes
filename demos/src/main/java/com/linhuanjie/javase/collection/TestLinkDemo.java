package com.linhuanjie.javase.collection;

/**
 * 链表Demo
 */
//class Node {
//    private Object data;
//    private Node next;
//
//    public Node(Object data) {
//        this.data = data;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public Node getNext() {
//        return next;
//    }
//
//    public void setNext(Node next) {
//        this.next = next;
//    }
//}

class Link {
    static class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }

        public void addNode(Node newNode){
            if(this.next == null ){
                this.next = newNode;
            }else{
                this.next.addNode(newNode);
            }
        }
    }

    private Node root;
    public void add(Object data){
        // 不可存储空数据
        if(data == null){
            return;
        }
        Node newNode = new Node(data);
        if(root == null){
            // 没有根节点
            root = newNode;
        }else {
            // 有根节点
            root.addNode(newNode);
        }
    }
}

public class TestLinkDemo {
    public static void main(String[] args) {
        Link link = new Link();
        Link.Node rootNode = new Link.Node("1");
        Link.Node n1= new Link.Node("2");
        Link.Node n2 = new Link.Node("3");

    }

    public static void print(Link.Node node){
        if( null != node ){

        }
    }


}
