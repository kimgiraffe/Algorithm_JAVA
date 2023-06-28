import java.util.Scanner;

public class BOJ_25304 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int X = sc.nextInt();
		int N = sc.nextInt();
		int tmp = 0;
		for(int i = 1; i <= N; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			tmp += a * b;
		}
		if(X == tmp) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
		sc.close();

	}
}