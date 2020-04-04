import java.util.Iterator;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {
    static final int possibleLevels = 33;

    static class Entry<E extends Comparable<? super E>> {
        E element;
        Entry[] next;
        Entry prev;

        public Entry(E x, int lev) {
            element = x;
            next = new Entry[lev];
            // add more code if needed
        }

        public E getElement() {
            return element;
        }
    }
    Entry<T> head, tail;
    Entry<T>[] last;
    int maxLevel,size;
    Random random;

    // Constructor
    public SkipList() {
        head = new Entry<T>(null,possibleLevels);
        tail = new Entry<T>(null,possibleLevels);
        last = new Entry[possibleLevels];
        maxLevel = 1;
        size = 0;
        for (int i = 0; i < possibleLevels; i++) {
            head.next[i] = tail;
        }
        random = new Random();
    }
    public int chooseLevel(){
        int level = 1 + Integer.numberOfTrailingZeros(random.nextInt()); //returns minimum 1 level (if random generates 0 integer then it return 32+1 zeroes)
        level = Math.min(level,maxLevel+1);
        if(level > maxLevel) maxLevel = level;
        return level;
    }
    // Add x to list. If x already exists, reject it. Returns true if new node is added to list
    public boolean add(T x) {
        if(contains(x)) //if already contains then return false else populate the last nodes visited in last array
            return false;
        int level = chooseLevel();
        Entry<T> ent = new Entry<T>(x,level);
        for(int i=0;i<level;i++){
            ent.next[i] = last[i].next[i];
            last[i].next[i] = ent;
        }
        ent.prev = last[0];
        ent.next[0].prev = ent;
        size++;
        return true;
    }

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
        return null;
    }

    public void find(T x){
        Entry<T> p = head;
        for(int i=possibleLevels-1; i>=0 ; i--){
            while((p.next[i].element != null) && p.next[i].element.compareTo(x) < 0){
                p = p.next[i];
            }
            last[i] = p;
        }
    }
    // Does list contain x?
    public boolean contains(T x) {
        find(x);
        return last[0].next[0].element!=null && last[0].next[0].element.compareTo(x)==0;
    }

    // Return first element of list
    public T first() {
        return null;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
        return null;
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
        return null;
    }

    // O(n) algorithm for get(n)
    public T getLinear(int n) {
        return null;
    }

    // Optional operation: Eligible for EC.
    // O(log n) expected time for get(n).
    public T getLog(int n) {
        return null;
    }

    // Is the list empty?
    public boolean isEmpty() {
        return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
        return null;
    }

    // Return last element of list
    public T last() {
        return null;
    }


    // Not a standard operation in skip lists. 
    public void rebuild() {

    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
        return null;
    }
    public void printList() {

        Entry node = head.next[0];

        System.out.println("----------START----------");
        while (node != null && node.element != null) {
            for (int i = 0; i < node.next.length; i++) {
                System.out.print(node.element + "\t");
            }
            for (int j = node.next.length; j < possibleLevels; j++) {
                System.out.print("|\t");
            }
            System.out.println();
            node = node.next[0];
        }
        System.out.println("----------END----------");
    }


    // Return the number of elements in the list
    public int size() {
        return 0;
    }
    public static void main(String[] args) {
        SkipList<Long> sl = new SkipList<>();

        for (long i = 0; i < 100; i++) {
            if (i != 45)
                sl.add(i);
        }

        sl.printList();
    }
}