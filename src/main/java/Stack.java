/**
 * Implementación de una pila genérica utilizando un ArrayList
 * Sigue el principio LIFO (Last In, First Out
 * 
 * @param <T> tipo de datos almacenados en la pila
 */

import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> stack;

    public Stack() {
        this.stack = new ArrayList<>();
    }

    /**
     * Agrega un elemento en el tope de la pila
     * @param item elemento a insertar en la pila
     * 
     * @pre item no es nulo
     * @post el elemento queda en el tope
     *       size() aumenta en 1
     */
    public void push(T item) {
        stack.add(item);
    }

    /**
     * Elimina y devuelve el elemento en el tope de la pila
     * @return el elemento eliminado del tope de la pila
     * @throws RuntimeException si la pila está vacía
     * 
     * @pre la pila no está vacía
     * @post el elemento en el tope es eliminado y devuelto
     *      size() disminuye en 1
     */
    public T pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    /**
     * Devuelve el elemento en el tope de la pila sin eliminarlo
     * @return el elemento en el tope de la pila
     * @throws RuntimeException si la pila está vacía
     * 
     * @pre la pila no está vacía
     * @post se devuelve el elemento en el tope sin modificar la pila
     */
    public T peek() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void clear() {
        stack.clear();
    }

    public int size() {
        return stack.size();
    }

    public T get(int index){
        return stack.get(index);
    }
}