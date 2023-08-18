package baekjoon;
import java.util.Scanner;

public class BOJ_27866_문자와문자열_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String[] str = sc.next().split("");
		int i = sc.nextInt();
		
		System.out.println(str[i-1]);
		sc.close();

	}
}