package baekjoon;
import java.util.Scanner;

class BOJ_10480_Oddities_김세민 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for(int i = 1; i <= n; i++) {
			int x = sc.nextInt();
			
			if(Math.abs(x) % 2 == 1) {
				System.out.println(x + " is odd");
			}
			else if(Math.abs(x) % 2 == 0) {
				System.out.println(x + " is even");
			}
		}
		
		sc.close();
	}
}