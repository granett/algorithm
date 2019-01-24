package algorithm.Fibonacci;

public class Fibonacci {

	public static int Fib(int N) {
		if (N < 3)
			return 1;
		else
			return Fib(N - 1) + Fib(N - 2);
	}

	public static void main(String[] args) {
		int num = Fib(10);
		System.out.println(num);
	}

}
