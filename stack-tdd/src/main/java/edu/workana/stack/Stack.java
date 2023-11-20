package edu.workana.stack;

class Stack {
  private Element element;
  
  Stack() {
    element = new BaseElement(-1, null);
  }
  
  public boolean isEmpty() {
    return element.previous == null;
  }

  public void push(int i) {
    element = new Element(i, element);
  }

  public int pop() {
    var value = element.pop();
    element = element.previous;
    return value;
  }
}
