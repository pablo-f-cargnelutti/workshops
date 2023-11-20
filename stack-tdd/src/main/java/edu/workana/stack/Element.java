package edu.workana.stack;

class Element {
  int value;
  Element previous;

  Element(int value, Element previous) {
    this.value = value;
    this.previous = previous;
  }

  public int pop() {
    return value;
  }

}
