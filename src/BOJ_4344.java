import java.util.*;

public class BOJ_4344 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		for(int test_case = 1; test_case <= C; test_case++) {
			int N = sc.nextInt();
			int cnt = 0;
			double sum = 0.0;
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
				sum += arr[i];
			}
			sum = sum / N;
			for(int i = 0; i < N; i++) {
				if(arr[i] > sum) {
					cnt++;
				}
			}
			System.out.println(((double)cnt / N) * 100.0 + "%");
		}
		sc.close();
	}
}