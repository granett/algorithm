package algorithm.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Suffix {

	public static void main(String[] args) {
		computer("9+(3-1)*3+10/2");
	}

	public static void computer(String input) {
		List<String> cutList = cutInput(input);
		List<String> afterList = getAfterList(cutList);
		System.out.println(afterList);
	}

	/**
	 * 获取两个数的计算结果
	 */
	private static int cal(int a, int b, char flag) {
		int result = 0;

		switch (flag) {
		case '+': {
			result = a + b;
			break;
		}
		case '-': {
			result = a - b;
			break;
		}
		case '*': {
			result = a * b;
			break;
		}
		case '/': {
			result = a / b;
			break;
		}
		default: {
			break;
		}
		}

		return result;
	}

	/**
	 * 生成后缀表达式
	 */
	private static List<String> getAfterList(List<String> cutList) {
		List<String> output = new ArrayList<>();
		Stack<Character> stack = new Stack<>();

		for (String ele : cutList) {
			char flag = ele.charAt(0);
			if (isFlag(ele.charAt(0)) || (flag == '(') || (flag == ')')) {
				// 计算符入栈
				if (stack.isEmpty()) {
					stack.push(flag);
				} else {
					// 如果待入栈计算符大于栈顶计算符，则直接入栈；否则出栈直到栈为空或者待入栈计算符小于栈顶计算符
					if (flag == '(') {
						stack.push(flag);
					} else if (flag == ')') {
						while (stack.peek() != '(') {
							output.add(String.valueOf(stack.pop()));
						}
						stack.pop();
					} else if (isFlagSmaller(stack.peek(), flag)) {
						stack.push(flag);
					} else if (stack.peek() == '(') {
						stack.push(flag);
					} else {
						do {
							if (stack.peek() == '(') {
								break;
							}
							output.add(String.valueOf(stack.pop()));
						} while (!stack.isEmpty() && !isFlagSmaller(stack.peek(), flag));
						stack.push(flag);
					}
				}
			} else {
				// 数字直接添加到输出中
				output.add(ele);
			}
		}

		while (!stack.isEmpty()) {
			if ((stack.peek() != '(') || (stack.peek() != ')')) {
				output.add(String.valueOf(stack.pop()));
			}
		}

		return output;
	}

	/**
	 * 将字符串以操作符为分隔符切片
	 */
	private static List<String> cutInput(String input) {
		List<String> cutList = new ArrayList<>();
		boolean running = true;

		while ((input.length() > 0) && running) {
			char c = input.charAt(0);
			if (isFlag(c) || (c == '(') || (c == ')')) {
				cutList.add(String.valueOf(c));
				input = input.substring(1);
			} else {
				for (int i = 0; i < input.length(); i++) {
					char tmpC = input.charAt(i);
					if (isFlag(tmpC) || (tmpC == '(') || (tmpC == ')')) {
						cutList.add(input.substring(0, i));
						cutList.add(String.valueOf(tmpC));

						input = input.substring(i + 1);
						break;
					}

					if (i == input.length() - 1) {
						cutList.add(input);
						running = false;
					}
				}
			}
		}

		return cutList;
	}

	/**
	 * 判断一个字符是否是操作符
	 */
	private static boolean isFlag(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/');
	}

	/**
	 * 第一个操作符优先级是否小于第二个
	 */
	private static boolean isFlagSmaller(char a, char b) {
		boolean flag = true;

		switch (a) {
		case '+':
		case '-': {
			if ((b == '+') || (b == '-')) {
				flag = false;
			}
			break;
		}

		case '*':
		case '/': {
			flag = false;
		}
		case '(': {
			flag = false;
		}
		default: {
			break;
		}
		}

		return flag;
	}
}
