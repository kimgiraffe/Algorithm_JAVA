package baekjoon;
import java.util.Scanner;

class BOJ_5341 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = sc.nextInt();
			if(n == 0) break;
			System.out.println(n * (n + 1) / 2);
		}
		
		sc.close();
	}
}