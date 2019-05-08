package hash;

import sun.reflect.generics.tree.Tree;

import java.util.TreeMap;

public class HashTable<K,V> {

    private final int[] capacity = {
            53,97,193,389,769,1543,3079,6151,12289,24593,49157,98317,196613,
            393241,786433,1572869,3145739,6291469,12582917,25165843,50331653,
            100663319,201326611,402653189,805306457,1610612741
    };

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int initCapacityIndex = 0;


    private TreeMap<K,V>[] hashTable;
    private int M;
    private int size;

    public HashTable(){
        this.M = capacity[initCapacityIndex];
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }


    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){return size;}

    /**
     * 向hash表中添加元素
     * @param key
     * @param value
     */
    public void add(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key,value);
        }else{
            map.put(key,value);
            size++;
            //扩容
            if(size >= upperTol * M && initCapacityIndex + 1 < capacity.length){
                initCapacityIndex ++;
                resize(capacity[initCapacityIndex]);
            }
        }
    }

    /**
     * 在hash表中删除一个元素
     * @param key
     * @return
     */
    public V remove(K key){
        TreeMap<K,V> map = hashTable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            //缩容
            if(size < lowerTol * M && initCapacityIndex-1 >= 0){
                initCapacityIndex--;
                resize(capacity[initCapacityIndex]);
            }
        }
        return ret;
    }

    private void resize(int recapacity){
        TreeMap<K,V>[] newHashTable = new TreeMap[recapacity];
        for (int i = 0; i < recapacity; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = recapacity;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K,V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }

    /**
     *修改hash表的元素的值
     * @param key
     * @param value
     */
    public void set(K key,V value){
        TreeMap<K,V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "dont exist");
        }
        map.put(key,value);
    }

    public boolean contains(K key){
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashTable[hash(key)].get(key);
    }
}
