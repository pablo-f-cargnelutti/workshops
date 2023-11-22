package edu.workana.stack;

class Stack<E> {
  private Element<E> element;
  
  Stack() {
    element = new BaseElement<>();
  }
  
  public boolean isEmpty() {
    return element.isEmpty();
  }

  public void push(E i) {
    element = new Element<>(i, element);
  }

  public E pop() {
    var value = element.pop();
    element = element.previous;
    return value;
  }

  public int size() {
    return element.size();
  }
}
