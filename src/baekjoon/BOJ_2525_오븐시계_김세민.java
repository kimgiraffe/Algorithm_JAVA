package baekjoon;
import java.util.Scanner;

public class BOJ_2525_오븐시계_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		int tmp = a * 60 + b + c;
		
		if(tmp >= 1440) {
			tmp -= 1440;
		}
		System.out.print(tmp / 60 + " ");
		System.out.println(tmp % 60);
		
		sc.close();

	}
}