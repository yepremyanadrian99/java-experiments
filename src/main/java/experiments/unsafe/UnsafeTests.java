package experiments.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

@SuppressWarnings("ALL")
public class UnsafeTests {

    private static class UnsafeArray {

        private static final int ELEM_SIZE_IN_BYTES = 4;

        private long size;
        private long maxSize;
        private long startAddress;
        private Unsafe unsafe;

        public UnsafeArray(long maxSize) throws NoSuchFieldException, IllegalAccessException {
            this.maxSize = maxSize;
            this.unsafe = getUnsafe();
            this.startAddress = unsafe.allocateMemory(ELEM_SIZE_IN_BYTES * maxSize);
        }

        public void pushBack(int value) {
            unsafe.putInt(startAddress + size++ * ELEM_SIZE_IN_BYTES, value);
        }

        public void pop_back() {
            --size;
        }

        public void resize(long newSize) {
            maxSize = newSize;
            if (size > maxSize) {
                size = maxSize;
            }
            unsafe.reallocateMemory(startAddress, maxSize * ELEM_SIZE_IN_BYTES);
        }

        public void clear() {
            size = 0;
            unsafe.freeMemory(startAddress);
        }

        public int get(long index) {
            return unsafe.getInt(startAddress + index * ELEM_SIZE_IN_BYTES);
        }

        public int tail() {
            return unsafe.getInt(startAddress);
        }

        public long getSize() {
            return size;
        }

        public long getMaxSize() {
            return maxSize;
        }

        private Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        UnsafeArray array = new UnsafeArray(Integer.MAX_VALUE);
        for (long i = 0; i < array.getMaxSize(); ++i) {
            array.pushBack((int) i);
        }
        System.out.println("Size: " + array.getSize());
//        for (long i = 0; i < array.getMaxSize(); ++i) {
//            System.out.print(array.get(i) + " ");
//        }
        System.out.println();
        System.out.println("Removing elements");
        long maxSize = array.getSize();
        for (long i = 0; i < maxSize; ++i) {
            array.pop_back();
        }
        for (long i = 0; i < array.getSize(); ++i) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println("Freeing memory");
        Thread.sleep(10_000);
        array.clear();
        System.out.println();
        System.out.println("Size: " + array.getSize());
    }
}
