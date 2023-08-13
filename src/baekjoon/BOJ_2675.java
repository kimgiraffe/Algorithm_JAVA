package baekjoon;
import java.util.Scanner;

public class BOJ_2675 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int i = 1; i <= T; i++) {
			int R = sc.nextInt();
			String[] str = sc.next().split("");
			for(int j = 0; j < str.length; j++) {
				for(int k = 1; k <= R; k++) {
					System.out.print(str[j].charAt(0));
				}
			}
			System.out.println();

		}
		sc.close();

	}
}