package edu.workana.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 1. You are not allowed to write any production code unless it is to make a failing unit test pass.
 
 * 2. You are not allowed to write any more of a unit test than is sufficient to fail; and compilation failures are failures.
 
 * 3. You are not allowed to write any more production code than is sufficient to pass the one failing unit test.
 */
class StackTest {

  private Stack<Integer> stack;

  @BeforeEach
  void setUp() {
    // Arrange, Given
    stack = new Stack<>();
  }

  @Test
  void whenCreateStackIsEmpty() {
    assertThat(stack.isEmpty(), is(true));
  }

  @Test
  void canPushToStack() {
    // Act, When
    stack.push(10);

    // Assert, Then
    assertThat(stack.isEmpty(), is(false));    
  }

  @Test
  void canPopFromStack() {
    // Arrange, Given
    stack.push(10);
    
    // Act, When
    var popped = stack.pop();
    
    // Assert
    assertThat(stack.isEmpty(), is(true));
    assertThat(popped, is(10));
  }

  @Test
  void cannotPopFromEmpty() {
    // When, then
    assertThrows(StackEmpty.class, stack::pop);
  }

  @Test
  void canPushAndPopAsStack() {
    stack.push(10);
    stack.push(20);

    var popped = stack.pop();

    assertThat(stack.isEmpty(), is(false));
    assertThat(popped, is(20));
  }

  @Test
  void canPushAndPopAllValuesAsStack() {
    stack.push(10);
    stack.push(20);

    var popped = stack.pop();
    var popped2 = stack.pop();

    assertThat(stack.isEmpty(), is(true));
    assertThat(popped, is(20));
    assertThat(popped2, is(10));
  }

  @Test
  void canCreateAStackOfString() {
    var stack = new Stack<String>();
    stack.push("20");

    var popped = stack.pop();

    assertThat(stack.isEmpty(), is(true));
    assertThat(popped, is("20"));
  }

  @Test
  void canGetStackSize() {
    stack.push(10);    
    stack.push(20);
    stack.push(30);

    assertThat(stack.size(), is(3));
    stack.pop();
    assertThat(stack.size(), is(2));
  }
  
}
