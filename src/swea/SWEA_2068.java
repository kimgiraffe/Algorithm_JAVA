package swea;
import java.util.Scanner;

class SWEA_2068 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++) {
			int[] arr = new int[10];
			int MAX = 0;
			for(int i = 0; i < 10; i++) {
				arr[i] = sc.nextInt();
				if(MAX < arr[i]) {
					MAX = arr[i];
				}
			}
			System.out.println("#" + test_case + " " + MAX);
		}
		sc.close();
	}
}