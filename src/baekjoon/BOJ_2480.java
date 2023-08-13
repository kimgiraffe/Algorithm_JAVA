package baekjoon;
import java.util.Scanner;

public class BOJ_2480 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		if(a == b && b == c) {
			System.out.println(10000 + a * 1000);
		}
		else if(a == b && a != c && b != c) {
			System.out.println(1000 + a * 100);
		}
		else if(a == c && a != b && c != b) {
			System.out.println(1000 + c * 100);
		}
		else if(b == c && a != b && c != a) {
			System.out.println(1000 + b * 100);
		}
		else {
			if(a > b) {
				if(b > c) { // a > b > c
					System.out.println(a * 100);
				}
				else {
					if(a > c) {
						System.out.println(a * 100);
					}
					else { // c > a > b
						System.out.println(c * 100);
					}
				}
			}
			else {	// a < b
				if(b > c) {
					System.out.println(b * 100);
				}
				else { // b < c
					if(a > c) {
						System.out.println(b * 100);
					}
					else {
						System.out.println(c * 100);
					}
				}
			}
			
		}
		sc.close();

	}
}