package baekjoon;
import java.util.*;

public class BOJ_10807_개수세기_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int A[] = new int[N];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}
		
		int v = sc.nextInt();
		for(int i = 0; i < N; i++) {
			if(A[i] == v) {
				cnt++;
			}
		}
		System.out.println(cnt);
		sc.close();

	}
}