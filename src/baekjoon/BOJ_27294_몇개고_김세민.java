package baekjoon;
import java.util.Scanner;

class BOJ_27294_몇개고_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int S = sc.nextInt();
		
		if(S == 1) {
			System.out.println(280);
		}
		else {
			if(T < 12 || T > 16) {
				System.out.println(280);
			}
			else {
				System.out.println(320);
			}
		}
		
		
		
		sc.close();
	}
}