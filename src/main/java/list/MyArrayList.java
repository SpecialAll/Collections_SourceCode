package list;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyArrayList<E> extends AbstractList<E> {

    private static final long serialVersionUID = 8683452581122892189L;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ELEMENTDATA = new Object[0];
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = new Object[0];
    transient Object[] elementData;
    private int size;

    //构造器
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            if (initialCapacity != 0) {
                throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
            }
            //如果传入的默认值大小为0，那么就使用默认的数组
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    //无参构造器
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    //带集合参数的构造器
    public MyArrayList(Collection<? extends E> c) {
        this.elementData = c.toArray();
        if ((this.size = elementData.length) != 0) {
            if (this.elementData.getClass() != Object[].class) {
                this.elementData = Arrays.copyOf(this.elementData, this.size, Object[].class);
            }
        } else {
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }


    static <E> E elementAt(Object[] es, int index) {
        return (E) es[index];
    }

    @Override
    public E get(int i) {
        Objects.checkIndex(i, this.size);
        return (E) this.elementData[i];
    }

    public E set(int index, E element){
        Objects.checkIndex(index, this.size);
        E oldElement = (E) this.elementData[index];
        this.elementData[index] = element;
        return oldElement;
    }

    private void add(E e, Object[] elementData, int s){
        if(s == elementData.length){
            elementData = this.grow();
        }

        elementData[s] = e;
        this.size = s+1;
    }

    public boolean add(E e){
        ++this.modCount;
        this.add(e,this.elementData, this.size);
        return true;
    }
    public void add(int index, E element){
        this.rangeCheckForAdd(index);
        ++this.modCount;
        int s;
        Object[] elementData;
        if((s = this.size) == (elementData = this.elementData).length){
            elementData = this.grow();
        }

        System.arraycopy(elementData , index, elementData, index+1, s-index);
        elementData[index] = element;
        this.size = s + 1;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        int exceptedModcount = this.modCount;
        Object[] es = this.elementData;
        int size = this.size;

        for (int i = 0; exceptedModcount == this.modCount && i < size; i++) {
            action.accept(elementAt(es, i));
        }

        if (exceptedModcount != this.modCount) {
            throw new ConcurrentModificationException();
        }
    }


    @Override
    public Stream<E> stream() {
        return null;
    }

    @Override
    public Stream<E> parallelStream() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return null;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> c) {

    }

    final class MyArrayListSpliterator implements Spliterator<E> {
        private int index;
        private int fence;
        private int expectedModCount;

        MyArrayListSpliterator(int index, int fence, int expectedModCount) {
            this.index = index;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        public int getFence() {
            int hi;
            if ((hi = this.fence) < 0) {
                this.expectedModCount = MyArrayList.this.modCount;
                hi = this.fence = MyArrayList.this.size;
            }
            return hi;
        }

        @Override
        public boolean tryAdvance(Consumer<? super E> action) {
            if (action == null)
                throw new NullPointerException();
            else {
                int hi = this.getFence();
                int i = this.index;
                if (i < hi) {
                    this.index = i + 1;
                    E e = (E) MyArrayList.this.elementData[i];
                    action.accept(e);
                    if (MyArrayList.this.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    } else {
                        return true;
                    }
                } else {
                    return false;
                }
            }
        }

        @Override
        public Spliterator<E> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }

    private Object[] grow(){
        return this.grow(this.size + 1);
    }

    private Object[] grow(int minCapacity) {
        return this.elementData = Arrays.copyOf(this.elementData, this.newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if(newCapacity - minCapacity <= 0){
            if(this.elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
                return  Math.max(10, minCapacity);
            } else if (minCapacity < 0) {
                throw new OutOfMemoryError();
            } else {
                return  minCapacity;
            }
        } else {
            return  newCapacity - 2147483639 <= 0 ? newCapacity : hugeCapacity(minCapacity);
        }
    }

    private static int hugeCapacity(int minCapacity) {
        if(minCapacity < 0){
            throw new OutOfMemoryError();
        } else {
            return minCapacity > 2147483639 ? 2147483647 : 2147483639;
        }
    }

    private void rangeCheckForAdd(int index){
        if(index > this.size || index < 0){
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }
}
