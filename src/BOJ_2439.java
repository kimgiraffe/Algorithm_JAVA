import java.util.Scanner;

public class BOJ_2439 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i = 1; i <= N; i++) {
			for(int j = N; j > i; j--) {
				System.out.print(" ");
			}
			for(int k = 1; k <= i; k++) {
				System.out.print("*");
			}
			System.out.println("");
		}
		sc.close();

		
	}
}