package Leetcode.hash;

public class Train {

    public static void main(String[] args) {

    }

    /**
     * 387. 字符串中的第一个唯一字符
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) -'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
