package ru.aryukov.arrayList;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by oaryukov on 20.04.2017.
 * @param <T> generic type.
 */
public class MyArrayList<T> {

    /**
     * Container for data.
     */
    private Object[] conteiner;
    /**
     * default container for data capacity is 0.
     */
    private Object[] defaultConteiner = {};

    /**
     * Count of elements.
     */
    private int size;

    /**
     * Constructor with initial capacity.
     * @param size of capacity.
     */
    public MyArrayList(int size) {
        conteiner = new Object[size];
        size = 0;
    }

    /**
     * Constructor with 0 capacity.
     */
    public MyArrayList() {
        conteiner = defaultConteiner;
        size = 0;
    }

    /**
     * Method for adding element in the end of container.
     * @param element for adding
     */
    public void add(T element) {
        if (checkCapacity()) {
            conteiner[size++] = element;
        } else {
            encriesCapacity();
            conteiner[size++] = element;
        }
    }

    /**
     * Method for adding element in the position of container.
     * @param element for adding
     * @param position where add element
     */
    public void add(T element, int position) {
        if (position < conteiner.length) {
            if (checkCapacity()) {
                System.arraycopy(conteiner, position, conteiner, position + 1, size - position);
                conteiner[position] = element;
            } else {
                encriesCapacity();
                System.arraycopy(conteiner, position, conteiner, position + 1, size - position);
                conteiner[position] = element;
            }
            conteiner[position] = element;
        }
        size++;
    }

    /**
     * Method for adding new Collection
     *
     * @param c collection containing elements to be added to this list.
     * @return true if this Object c add in result.
     */
    public boolean addAll(Collection<? extends T> c) {
        Object[] a = c.toArray();
        int numOfAddCells = a.length;
        encriesCapacity(size + numOfAddCells);
        System.arraycopy(a, 0, conteiner, size, numOfAddCells);
        size += numOfAddCells;
        return numOfAddCells != 0;
    }

    /**
     * Method for removing element from container.
     * @param element witch we wont remove
     */
    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            if (conteiner[i].equals(element)) {
                int position = i;
                int numOfElements = size - position - 1;
                System.arraycopy(conteiner, position + 1, conteiner, position, numOfElements);
                conteiner[--size] = null;
            }
        }
    }

    /**
     * Method for getting element by position.
     * @param position where element is.
     * @return T element
     */
    public T get(int position) {
        Object element = null;
        try {
            element = conteiner[position];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Element is null, because out of bound");
        }
        return (T) element;
    }


    /**
     * Method for checking space in container.
     * @return false if it has not
     */
    private boolean checkCapacity() {
        boolean flag = false;
        if ((size + 1) < conteiner.length) {
            flag = true;
        }
        return flag;
    }

    /**
     * Method for encriese container.
     */
    private void encriesCapacity() {
        conteiner = Arrays.copyOf(conteiner, (conteiner.length + 1) * 2);
    }

    /**
     * Method for encriese container.
     */
    private void encriesCapacity(int newSize) {
        conteiner = Arrays.copyOf(conteiner, newSize);
    }

    /**
     * Method for getting count of element in container.
     * @return int of elements
     */
    public int size() {
        return size;
    }

}
