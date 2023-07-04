import java.util.Scanner;

class BOJ_5300 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		for(int i = 1; i <= N; i++) {
			System.out.print(i + " ");
			if(i % 6 == 0) {
				System.out.print("Go! ");
			}
			else if(i == N) {
				System.out.print("Go! ");
			}
		}
		
		sc.close();
	}
}