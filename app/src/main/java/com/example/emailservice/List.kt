package com.example.emailservice

import org.w3c.dom.Node
import java.util.*

//class List : Node {
//    var list: Objects? = null
//    var next: Node? = null
//}
//
//class LinkedList {
//    var head: Node? = null
//    fun insert(item: Objects) {
//        val node = List()
//        node.list = item
//        node.next = null
//        if (head == null) {
//            head = node
//        } else {
//            var n: Node = head
//            while (n.next != null) {
//                n = n.next
//            }
//            n.next = node
//        }
//    }
//
//    fun insertAtStart(data: Int) {
//        val node = Node
//        node.date = data
//        node.next = null
//        node.next = head
//        head = node
//    }
//
//    fun insertAt(index: Int, data: Int) {
//        if (index == 0) {
//            insertAtStart(data)
//        } else {
//            val node = Node
//            node.date = data
//            node.next = null
//            var n = head
//            for (i in 0 until index - 1) {
//                n = n.next
//            }
//            node.next = n.next
//            n.next = node
//        }
//    }
//
//    fun deleteAt(index: Int) {
//        if (index == 0) {
//            head = head.next
//        } else {
//            var n = head
//            var n1: Node? = null
//            for (i in 0 until index - 1) {
//                n = n.next
//            }
//            n1 = n.next
//            n.next = n1.next
//        }
//    }
//
//    fun show() {
//        var n = head
//        while (n.next != null) {
//            System.out.println(n.date)
//            n = n.next
//        }
//        System.out.println(n.date)
//    }
//}