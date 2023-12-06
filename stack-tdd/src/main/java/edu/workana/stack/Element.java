package edu.workana.stack;

class Element<E> {
  E value;
  Element<E> previous;

  Element(E value, Element<E> previous) {
    this.value = value;
    this.previous = previous;
  }

  public E pop() {
    return value;
  }

  public int size() {
    return previous.size() + 1;
  }

  public boolean isEmpty() {
    return false;
  }
}
