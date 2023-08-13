package baekjoon;
import java.util.Scanner;

public class BOJ_10818 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int MAX = -1000000;
		int MIN = 1000000;
		
		int A[] = new int[N];
		
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			if(A[i] > MAX) {
				MAX = A[i];
			}
			if(A[i] < MIN) {
				MIN = A[i];
			}
		}
		System.out.println(MIN + " " + MAX);
		
		sc.close();

	}
}