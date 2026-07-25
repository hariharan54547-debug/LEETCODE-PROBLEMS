class MyHashSet {
    private Bucket[] buckets;
    private static final int SIZE = 769; // a prime number

    public MyHashSet() {
        buckets = new Bucket[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new Bucket();
        }
    }
    
    private int hash(int key) {
        return key % SIZE;
    }
    
    public void add(int key) {
        int bucketIndex = hash(key);
        buckets[bucketIndex].add(key);
    }
    
    public void remove(int key) {
        int bucketIndex = hash(key);
        buckets[bucketIndex].remove(key);
    }
    
    public boolean contains(int key) {
        int bucketIndex = hash(key);
        return buckets[bucketIndex].contains(key);
    }
    
    // Each bucket is a simple linked list to handle collisions
    private class Bucket {
        private LinkedList<Integer> container;
        
        public Bucket() {
            container = new LinkedList<Integer>();
        }
        
        public void add(int key) {
            int index = container.indexOf(key);
            if (index == -1) {
                container.addLast(key);
            }
        }
        
        public void remove(int key) {
            container.remove(Integer.valueOf(key));
        }
        
        public boolean contains(int key) {
            return container.contains(key);
        }
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
