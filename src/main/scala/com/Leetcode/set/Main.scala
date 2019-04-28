package com.Leetcode.set

import java.util

import scala.collection.mutable

class Main {

  def main(args: Array[String]): Unit = {

  }

  /**
    * 804. 唯一摩尔斯密码词asdw
    * @param words
    * @return
    */
  private val map: Array[String] = Array(".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
    "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...",
    "-", "..-", "...-", ".--", "-..-", "-.--", "--..")
  def uniqueMorseRepresentations(words: Array[String]): Int = {
    var set = new mutable.HashSet[String]()
    for (elem <- words) {
      var tp = new StringBuffer();
      for(i <- elem.toCharArray){
        tp.append(map(i-'a'))
      }
      set.add(tp.toString)
    }
    set.size
  }

  def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    var set = new mutable.HashSet[Int]()
    for (elem <- nums1) {
      set.add(elem)
    }
    var l = new util.ArrayList[Int]()
    for (elem <- nums2) {
      if(set.contains(elem)){
        l.add(elem)
        set.remove(elem)
      }
    }
    var list = new Array[Int](l.size())
    for(i <- Range(0,l.size())){
        list(i) = l.get(i)
    }
    list
  }

}
