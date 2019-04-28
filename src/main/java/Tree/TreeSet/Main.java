package Tree.TreeSet;

import java.util.ArrayList;

public class Main {

    private static double testSet(Set<String> set,String filename){
        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename,words)){
            System.out.println("Total words:" + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words:" + set.getSize());
        }

        long endTime = System.nanoTime();
        return (endTime-startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
//        ArrayList<String> word1 = new ArrayList<>();
//        FileOperation.readFile("E:\\Java\\LeetCodeTrain\\src\\main\\java\\Tree\\txt\\a-tale-of-two-cities.txt",word1);
//        System.out.println("total words :"+ word1.size());
//        BSTSet<String> set1 = new BSTSet<>();
//        for (String s : word1) {
//            set1.add(s);
//        }
//        System.out.println("set:"+set1.getSize());
//        LinkedListSet<String> set2 = new LinkedListSet<>();
//        for (String s : word1) {
//            set2.add(s);
//        }
//        System.out.println("set2:"+set2.getSize());
        String filename = "Tree/txt/pride-and-prejudice.txt";

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        double t2 = testSet(linkedListSet,filename);
        System.out.println("Linked set:"+t2+"s");

        BSTSet<String> bstSet = new BSTSet<>();
        double t1 = testSet(bstSet,filename);
        System.out.println("BST set:"+t1+"s");
        System.out.println();

    }

}
