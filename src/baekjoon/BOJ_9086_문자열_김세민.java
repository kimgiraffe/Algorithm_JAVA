package baekjoon;
import java.util.Scanner;

public class BOJ_9086_문자열_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int i = 1; i <= T; i++) {
			String[] str = sc.next().split("");
			System.out.print(str[0]);
			System.out.println(str[str.length - 1]);
		}
		sc.close();

	}
}