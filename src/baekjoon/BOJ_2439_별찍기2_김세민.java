package baekjoon;
import java.util.Scanner;

public class BOJ_2439_별찍기2_김세민 {
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