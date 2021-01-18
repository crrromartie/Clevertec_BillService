package ru.clevertec.bill.collection;

import java.util.*;

public class CustomArrayList<E> implements List<E>, Iterable<E> {
    private E[] values;

    public CustomArrayList() {
        values = (E[]) new Object[0];
    }

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public boolean add(E e) {
        E[] temp = values;
        values = (E[]) new Object[temp.length + 1];
        for (int i = 0; i < temp.length; i++) {
            values[i] = temp[i];
        }
        values[values.length - 1] = e;
        return true;
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index < values.length) {
            return values[index];
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + values.length);
        }
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public boolean contains(Object o) {
        int counter = 0;
        for (int i = 0; i < size(); i++) {
            if (o == get(i)) {
                counter++;
            }
        }
        return (counter > 0);
    }

    public void delete(int index) {
        if (index >= 0 && index < values.length) {
            E[] temp = values;
            values = (E[]) new Object[temp.length - 1];
            for (int i = 0; i < index; i++) {
                values[i] = temp[i];
            }
            for (int i = index; i < values.length; i++) {
                values[i] = temp[i + 1];
            }
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + values.length);
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(values, size());
    }

    public void update(int index, E e) {
        values[index] = e;
    }

    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }

    class ArrayIterator<E> implements Iterator<E> {
        private int index = 0;
        private E[] values;

        public ArrayIterator(E[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public E next() {
            return values[index++];
        }
    }

    /**
     * Unsupported list methods
     */

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    /**
     * toString
     */

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                .add("values=" + Arrays.toString(values))
                .toString();
    }
}
