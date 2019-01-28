package algorithm.kmp;

public class Next {
	
	public static void main(String[] args) {
		String a = "ABACDABABC";
		for(int i:getNext("ABACDABABC")){
			System.out.print(i);
		}
	}
	
	public static int[] getNext(String ps) {
		char[] p = ps.toCharArray();
		int[] next = new int[p.length];
		next[0] = -1;
		int j = 0;
		int k = -1;
		while (j < p.length - 1) {
			
			if (k == -1 || p[j] == p[k]) {
				next[++j] = ++k;
			} else {
				k = next[k];
			}
			System.out.println(j+":"+next[j]);
			
		}
		return next;
	}
}
