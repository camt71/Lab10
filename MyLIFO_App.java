package lab10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> stack = new Stack<>();
		for (int i = 0; i < array.length; i++) {
			stack.push(array[i]);
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = stack.pop();
		}
		System.out.println(Arrays.toString(array));
	}

	// This method checks the correctness of the given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==> false
	public static boolean isCorrect(String input) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			switch (ch) {
			case ']': {
				if (stack.isEmpty() || stack.pop() != '[')
					return false;
				else
					break;
			}
			case '}': {
				if (stack.isEmpty() || stack.pop() != '{')
					return false;
				else
					break;
			}
			case ')': {
				if (stack.isEmpty() || stack.pop() != '(')
					return false;
				else
					break;
			}
			default:
				stack.push(ch);
			}
		}
		return stack.isEmpty();
	}

	// This method evaluates the value of an expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static int evaluateExpression(String exp) {
        Stack<Integer> operandStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        String num = "";
        for (int i = 0; i < exp.length(); i++) {
            char currentChar = exp.charAt(i);
            if (!isOpearator(currentChar)) {
                num += currentChar;
                if (i == exp.length() - 1) operandStack.push(Integer.parseInt(num));
            } else {
                if (!num.equals("")) {
                    operandStack.push(Integer.parseInt(num));
                }
                switch (currentChar) {
                    case ')': {
                        while (operatorStack.peek() != '(') {
                            operandStack.push(calc(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
                        }
                        operatorStack.pop();
                        break;
                    }
                    case '+', '-': {
                        if (!operatorStack.isEmpty() && operandStack.size() > 1 && operatorStack.peek() != '(') {
                            int test = calc(operandStack.pop(), operatorStack.pop(), operandStack.pop());
                            operandStack.push(test);
                        }
                        operatorStack.push(currentChar);
                        break;
                    }
                    case '*', '/': {
                        if (!operatorStack.isEmpty())
                            if (operatorStack.peek() == '*' || operatorStack.peek() == '/') {
                                operandStack.push(calc(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
                            }
                        operatorStack.push(currentChar);
                        break;
                    }
                    default: operatorStack.push(currentChar);
                }
                num = "";
            }
        }
        while (!operatorStack.isEmpty()) {
            operandStack.push(calc(operandStack.pop(), operatorStack.pop(), operandStack.pop()));
        }
        return operandStack.pop();
    }

	public static boolean isOpearator(char input) {
        return input == '+' || input == '-' || input == '*' || input == '/' || input == '(' || input == ')';
    }

    public static int calc(int a, char exp, int b) {
        switch (exp) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0;
    }

	public static void main(String[] args) {
		Integer[] array = { 1, 2, 3, 4, 5, 6 };
//		reserve(array);
		String text = "){[]}()";
		System.out.println(isCorrect(text));
	}
}
