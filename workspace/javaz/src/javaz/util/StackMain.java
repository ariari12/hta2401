package javaz.util;

import java.util.Stack;

// Stack<E>
// - 한 방향으로만 데이터를 넣고 꺼낼 수 있는 구조
// - Last In First Out
// - 웹브라우저의 뒤로/앞으로, undo/redo

public class StackMain {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		stack.add("red");	System.out.println(stack);
		stack.push("mon");	System.out.println(stack);
		stack.push("tue");	System.out.println(stack);
		stack.push("wed");	System.out.println(stack);
		System.out.println();
		
		System.out.println(stack.get(1) + " " + stack);
		System.out.println(stack.peek() + " " + stack);
		System.out.println(stack.pop() + " " + stack);
		System.out.println();
		
		System.out.println(stack.search("red"));
		System.out.println(stack.search("레드"));
		System.out.println(stack.empty());
	}

}
