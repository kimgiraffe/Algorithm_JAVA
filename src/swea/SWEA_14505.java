package swea;
import java.util.*;

class SWEA_14505 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			int total = 0;
			
			for(int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					
					total += arr[i] % arr[j];
					
				}
			}
			
			System.out.println("#" + test_case + " " + total);
		}
		
		sc.close();
	}
}