package algorithm.Fibonacci;

public class FibForEach {
	public static int Fib(int n) {
		int first = 1;
		int second = 1;
		int ret = 0;
		for (int i = 3; i <= n; i++) {
			ret = first + second;
			first = second;
			second = ret;
		}
		return second;
	}
	
	public static void main(String[] args) {
		int num = Fib(10);
		System.out.println(num);
	}

}
