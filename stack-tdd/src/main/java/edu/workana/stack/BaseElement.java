package edu.workana.stack;

public class BaseElement<E> extends Element<E> {

  BaseElement() {
    this(null, null);
  }
  
  BaseElement(E value, Element<E> previous) {
    super(value, previous);
  }

  @Override
  public E pop() {
    throw new StackEmpty();
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }
}
