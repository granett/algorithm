package algorithm.Fibonacci;

public class FibArray {
	
	public static int[] array = new int[20];

	public static int Fib(int n) {
		if (n <= 1)
			return n;
		if (array[n] != 0)
			return array[n];
		else
			return array[n] = Fib(n - 1) + Fib(n - 2);

	}
	
	public static void main(String[] args) {
		int num = Fib(10);
		System.out.println(num);
	}
}
