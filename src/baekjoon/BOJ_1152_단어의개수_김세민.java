package baekjoon;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1152_단어의개수_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		sc.close();
		
		StringTokenizer st = new StringTokenizer(str, " ");
		
		System.out.println(st.countTokens());
	}
}