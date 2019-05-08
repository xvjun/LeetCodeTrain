package Leetcode.Map;



import java.util.*;

public class Train {

    public static void main(String[] args) {
        int[] a = {1,1,1,2,2,3};
        Train t=  new Train();
        System.out.println(t.topKFrequent(a, 2).toString());
    }


    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if(treeMap.containsKey(num)){
                treeMap.put(num,treeMap.get(num)+1);
            }else{
                treeMap.put(num,1);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> treeMap.get(a) - treeMap.get(b));
        for (Integer key : treeMap.keySet()) {
            if(pq.size() < k){
                pq.add(key);
            }else if(treeMap.get(key) > treeMap.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }
        LinkedList<Integer> rs = new LinkedList<>();
        while(!pq.isEmpty()){
            rs.add(pq.remove());
        }
        return rs;
    }



}
