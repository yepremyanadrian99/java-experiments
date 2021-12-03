package experiments.datastructure.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import lombok.Setter;

public final class SimpleHashMap<K, V> {

    private static final int BUCKET_SIZE = 16;
    private static final int EMPTY_INDEX = -1;

    private final List<LinkedList<CustomEntry<K, V>>> buckets;
    private int size;

    public SimpleHashMap() {
        this.buckets = new ArrayList<>();
        this.size = 0;
        for (int i = 0; i < BUCKET_SIZE; ++i) {
            this.buckets.add(null);
        }
    }

    @Getter
    @Setter
    public static final class CustomEntry<K, V> {
        private final K key;
        private V value;

        public CustomEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<CustomEntry<K, V>> bucket = getBucket(bucketIndex);
        int elementIndex = getIndexOfElementInBucket(bucket, key);
        if (elementIndex == EMPTY_INDEX) {
            bucket = createBucketWithIndexIfNeeded(bucket, bucketIndex);
            putElementInBucket(bucket, key, value);
            return;
        }
        updateElementInBucketWithIndex(bucket, elementIndex, value);
    }

    public boolean remove(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<CustomEntry<K, V>> bucket = getBucket(bucketIndex);
        int elementIndex = getIndexOfElementInBucket(bucket, key);
        if (elementIndex == EMPTY_INDEX) {
            return false;
        }
        bucket.remove(elementIndex);
        return true;
    }

    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<CustomEntry<K, V>> bucket = getBucket(bucketIndex);
        int elementIndex = getIndexOfElementInBucket(bucket, key);
        if (elementIndex == EMPTY_INDEX) {
            return null;
        }
        return bucket.get(elementIndex).getValue();
    }

    public V getOrDefault(K key, V defaultValue) {
        return Optional.ofNullable(get(key))
                .orElse(defaultValue);
    }

    private LinkedList<CustomEntry<K, V>> createBucketWithIndexIfNeeded(LinkedList<CustomEntry<K, V>> bucket, int index) {
        return Optional.ofNullable(bucket).orElseGet(() -> {
                    buckets.set(index, new LinkedList<>());
                    return buckets.get(index);
                }
        );
    }

    private void putElementInBucket(LinkedList<CustomEntry<K, V>> bucket, K key, V value) {
        bucket.add(new CustomEntry<>(key, value));
        ++size;
    }

    private void updateElementInBucketWithIndex(LinkedList<CustomEntry<K, V>> bucket, int index, V value) {
        bucket.get(index).setValue(value);
    }

    private int getIndexOfElementInBucket(LinkedList<CustomEntry<K, V>> bucket, K key) {
        if (Utils.isEmpty(bucket)) {
            return EMPTY_INDEX;
        }
        for (int i = 0; i < bucket.size(); ++i) {
            CustomEntry<K, V> entry = bucket.get(i);
            if (key == null) {
                if (entry.getKey() == null) {
                    return i;
                }
            } else if (key.equals(entry.getKey())) {
                return i;
            }
        }
        return EMPTY_INDEX;
    }

    private LinkedList<CustomEntry<K, V>> getBucket(int index) {
        if (index < 0 || index >= BUCKET_SIZE) {
            throw new RuntimeException("Invalid buckets index provided");
        }
        return buckets.get(index);
    }

    private int getBucketIndex(K key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & (BUCKET_SIZE - 1);
    }
}
