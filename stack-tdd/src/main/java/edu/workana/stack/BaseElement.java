package edu.workana.stack;

public class BaseElement extends Element {

  BaseElement(int value, Element previous) {
    super(value, previous);
  }

  @Override
  public int pop() {
    throw new StackEmpty();
  }
}
