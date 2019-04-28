package Leetcode.set;

import scala.Int;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class Train {

    public static void main(String[] args) {
        int[] s1 = {1,2,2,1};
        int[] s2 = {2,2};
        System.out.println(intersection(s1,s2).length);
    }

    /**
     * 804. 唯一摩尔斯密码词
     * @param words
     * @return
     */
    private static String[] map = {
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
    };
    public int uniqueMorseRepresentations(String[] words) {
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuffer tp = new StringBuffer();
            for(char i : word.toCharArray()){
                tp.append(map[i-'a']);
            }
            set.add(tp.toString());
        }
        return set.size();
    }

    /**
     * 349. 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i : nums2) {
            if(set.contains(i)){
                res.add(i);
                set.remove(i);
            }
        }
        int[] rs = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            rs[i] = res.get(i);
        }
        return rs;
    }

}
