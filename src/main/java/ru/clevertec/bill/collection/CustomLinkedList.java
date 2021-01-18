package ru.clevertec.bill.collection;

import java.util.*;

public class CustomLinkedList<E> implements List<E>, Iterable<E> {
    private Node<E> firstNode;
    private Node<E> lastNode;
    private int size = 0;

    public CustomLinkedList() {
        this.lastNode = new Node<>(firstNode, null, null);
        this.firstNode = new Node<>(null, null, lastNode);
    }

    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object e) {
        int counter = 0;
        for (int i = 0; i < size; i++) {
            if (e == get(i)) {
                counter++;
            }
        }
        return (counter > 0);
    }

    @Override
    public boolean add(E e) {
        Node<E> previous = lastNode;
        previous.setCurrentElement(e);
        lastNode = new Node<E>(previous, null, null);
        previous.setNextElement(lastNode);
        size++;
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (index >= 0 && index < size) {
            if (index == 0) {
                addFirst(e);
            } else if (index == size - 1) {
                addLast(e);
            } else {
                Node<E> target = getNode(index);
                Node<E> preTarget = getNode(index - 1);
                Node<E> replacement = new Node<>(target.previousElement, e, preTarget.nextElement);
                target.setPreviousElement(replacement);
                preTarget.setNextElement(replacement);
                target.setNextElement(getNode(index + 2));
                size++;
            }
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

    public boolean addFirst(E e) {
        Node<E> next = firstNode;
        next.setCurrentElement(e);
        firstNode = new Node<>(null, null, next);
        next.setPreviousElement(firstNode);
        size++;
        return true;
    }

    public boolean addLast(E e) {
        Node<E> previous = lastNode;
        previous.setCurrentElement(e);
        lastNode = new Node<E>(previous, null, null);
        previous.setNextElement(lastNode);
        size++;
        return true;
    }

    public boolean deleteFirst() {
        if (size > 0) {
            if (size == 1) {
                firstNode.setNextElement(lastNode);
                lastNode.setPreviousElement(firstNode);
            } else {
                Node<E> target = getNode(0);
                Node<E> afterTarget = getNode(1);
                afterTarget.setPreviousElement(firstNode);
                firstNode.setNextElement(afterTarget);
                target.setPreviousElement(null);
                target.setNextElement(null);
                target.setCurrentElement(null);
            }
        } else {
            throw new NoSuchElementException("No elements in the list");
        }
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (size > 0) {
            if (size == 1) {
                firstNode.setNextElement(lastNode);
                lastNode.setPreviousElement(firstNode);
            } else {
                Node<E> target = getNode(size - 1);
                Node<E> preTarget = getNode(size - 2);
                preTarget.setNextElement(lastNode);
                lastNode.setPreviousElement(preTarget);
                target.setPreviousElement(null);
                target.setNextElement(null);
                target.setCurrentElement(null);
            }
        } else {
            throw new NoSuchElementException("No elements in the list");
        }
        size--;
        return true;
    }

    public boolean delete(int index) {
        if (index >= 0 && index < size) {
            if (index == 0) {
                deleteFirst();
            } else if (index == size - 1) {
                deleteLast();
            } else {
                Node<E> target = getNode(index);
                Node<E> preTarget = getNode(index - 1);
                Node<E> afterTarget = getNode(index + 1);
                preTarget.setNextElement(afterTarget);
                afterTarget.setPreviousElement(preTarget);
                target.setPreviousElement(null);
                target.setNextElement(null);
                target.setCurrentElement(null);
                size--;
            }
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        return true;
    }

    public E getFirst() {
        if (size > 0) {
            return get(0);
        } else {
            throw new NoSuchElementException("No elements in the list");
        }
    }

    public E getLast() {
        if (size > 0) {
            return get(size - 1);
        } else {
            throw new NoSuchElementException("No elements in the list");
        }
    }

    public E get(int index) {
        Node<E> target = firstNode.getNextElement();
        if (index >= 0 && index < size) {
            for (int i = 0; i < index; i++) {
                target = getNextElement(target);
            }
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        return target.getCurrentElement();
    }

    /**
     * Private methods
     */

    private Node<E> getNode(int index) {
        Node<E> target = firstNode.getNextElement();
        if (index >= 0 && index < size) {
            for (int i = 0; i < index; i++) {
                target = getNextElement(target);
            }
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
        return target;
    }

    private Node<E> getNextElement(Node<E> current) {
        return current.getNextElement();
    }

    /**
     * Iterators
     */

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    public Iterator<E> descIterator() {
        return new Iterator<E>() {
            int index = size - 1;

            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return get(index--);
            }
        };
    }

    /**
     * Node class
     */

    private static class Node<E> {
        private E currentElement;
        private Node<E> nextElement;
        private Node<E> previousElement;

        private Node(Node<E> previousElement, E currentElement, Node<E> nextElement) {
            this.currentElement = currentElement;
            this.nextElement = nextElement;
            this.previousElement = previousElement;
        }

        public E getCurrentElement() {
            return currentElement;
        }

        public void setCurrentElement(E currentElement) {
            this.currentElement = currentElement;
        }

        public Node<E> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<E> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<E> getPreviousElement() {
            return previousElement;
        }

        public void setPreviousElement(Node<E> previousElement) {
            this.previousElement = previousElement;
        }
    }

    /**
     * Unsupported list methods
     */

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Method not supported!");
    }

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

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    /**
     * toString
     */

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(CustomLinkedList.class.getSimpleName());
        stringBuilder.append("[");
        stringBuilder.append("values=[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(get(i));
            stringBuilder.append(", ");
        }
        stringBuilder.append("]]");
        return stringBuilder.toString();
    }
}
