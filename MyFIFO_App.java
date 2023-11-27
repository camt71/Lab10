package lab10;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
	// method stutter that accepts a queue of integers as a parameter and replaces
	// every element of the queue with two copies of that element
	// front [1, 2, 3] back
	// becomes
	// front [1, 1, 2, 2, 3, 3] back
	public static <E> void stutter(Queue<E> input) {
		Queue<E> queue = new LinkedList<>();
		for (E element : input) {
			queue.add(element);
			queue.add(element);
		}
		System.out.println(queue.toString());
	}

	// Method mirror that accepts a queue of strings as a parameter and appends the
	// queue's contents to itself in reverse order
	// front [a, b, c] back
	// becomes
	// front [a, b, c, c, b, a] back
	public static <E> void mirror(Queue<E> input) {
		Stack<E> stack = new Stack<>();
		for (E element : input) {
			stack.push(element);
		}
		while(!stack.isEmpty()) {
			input.add(stack.pop());
		}

		System.out.println(input.toString());

	}

	public static void main(String[] args) {
		Queue<Integer> integers = new LinkedList<>();
		integers.add(1);
		integers.add(2);
		integers.add(3);
//		stutter(integers);
		mirror(integers);
	}
}
