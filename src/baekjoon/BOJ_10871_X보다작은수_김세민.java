package baekjoon;
import java.util.Scanner;

public class BOJ_10871_X보다작은수_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int X = sc.nextInt();
		
		int A[] = new int[N];
		
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		for(int i = 0; i < N; i++) {
			if(A[i] < X) {
				System.out.print(A[i] + " ");
			}
		}
		sc.close();

	}
}