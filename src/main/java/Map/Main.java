package Map;

import Tree.TreeSet.FileOperation;

import java.util.ArrayList;

public class Main {

    private static double testSet(Map<String,Integer> map,String filename){
        long startTime = System.nanoTime();

//
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            for (String word : words) {
                if(map.contains(word)){
                    map.set(word,map.get(word)+1);
                }else{
                    map.add(word,1);
                }
            }
        }else{
            System.out.println("error");
        }

        long endTime = System.nanoTime();
        return (endTime-startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String filename = "E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\pride-and-prejudice.txt";

        LinkedListMap<String,Integer> linkedListMap = new LinkedListMap<String, Integer>();
        double t2 = testSet(linkedListMap,filename);
        System.out.println("linkedListMap:"+t2+"s");
        System.out.println();
        BSTMap<String,Integer> bstMap = new BSTMap<String, Integer>();
        double t1 = testSet(bstMap,filename);
        System.out.println("bstMap:"+t1+"s");

    }
}
