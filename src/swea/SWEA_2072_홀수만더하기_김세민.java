package swea;
import java.util.Scanner;

class SWEA_2072_홀수만더하기_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] arr = new int[10];
			int sum = 0;
			for(int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
				if(arr[i] % 2 == 1) {
					sum += arr[i];
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
		sc.close();
	}
}