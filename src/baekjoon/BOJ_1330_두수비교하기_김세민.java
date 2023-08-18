package baekjoon;
import java.util.*;

public class BOJ_1330_두수비교하기_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		if(a < b) {
			System.out.println("<");
		}
		else if(a > b) {
			System.out.println(">");
		}
		else {
			System.out.println("==");
		}
		sc.close();


	}
}