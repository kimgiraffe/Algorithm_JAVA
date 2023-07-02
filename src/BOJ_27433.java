import java.util.Scanner;

class BOJ_27433 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		System.out.println(Factorial(N));
		
		sc.close();
	}
	
	public static long Factorial(int n) {
		if(n == 0) {
			return 1;
		}
		else {
			return n * Factorial(n - 1);
		}
	}
}