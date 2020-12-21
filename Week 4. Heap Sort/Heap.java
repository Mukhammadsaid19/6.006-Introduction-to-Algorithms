import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Heap<Key extends Comparable<Key>> {

    private final ArrayList<Key> heap;
    private int heapSize;

    public Heap(ArrayList<Key> heap) {
        this.heap = heap;
        this.heapSize = 0;
    }

    public int parent(int i){
        return i / 2 - 1;
    }

    public int left(int i){
        return 2 * i + 1;
    }

    public int right(int i){
        return 2 * i + 2;
    }

    public void swap(int i, int j){
        Key temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int largest;

        if (l < heap.size() && heap.get(i).compareTo(heap.get(l)) < 0){
            largest = l;
        } else {
            largest = i;
        }

        if (r < heap.size() && heap.get(largest).compareTo(heap.get(r)) < 0) {
            largest = r;
        }

        if (largest != i){
            swap(i, largest);
            maxHeapify(largest);
        }
    }

    public void buildMaxHeap() {
        heapSize = heap.size();
        for (int i = heapSize / 2; i >= 0; i--) {
            maxHeapify(i);
        }
    }

    public Key peek() {
        return heap.get(0);
    }

    public Key pop() {
        if (heapSize < 0){
            throw new IllegalArgumentException("Heap Underflow");
        }
        Key max = heap.get(0);
        heap.set(0, heap.get(heapSize - 1));
        heapSize--;
        maxHeapify(0);

        return max;
    }

    public void replace(Key k){
        if (heapSize < 0){
            throw new IllegalArgumentException("Heap Underflow");
        }

        Key max = heap.get(0);
        heap.set(0, k);

        maxHeapify(0);
    }

    public void increaseKey(int i, Key k) {
        if (k.compareTo(heap.get(i)) < 0) {
            throw new IllegalArgumentException("New key is smaller than current key");
        }
        heap.set(i, k);
        while (i >= 0 && heap.get(parent(i)).compareTo(heap.get(i)) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < heapSize; i++){
            sb.append(heap.get(i)).append(' ');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<Integer> numbersList = Arrays.asList(4, 1, 3, 2, 16, 9, 10, 14, 8, 7);

        ArrayList<Integer> A = new ArrayList<>(numbersList);

        Heap<Integer> heap = new Heap<>(A);

        heap.buildMaxHeap();

        System.out.println(heap.toString());

        System.out.println(heap.pop());

        System.out.println(heap.toString());
    }
}
