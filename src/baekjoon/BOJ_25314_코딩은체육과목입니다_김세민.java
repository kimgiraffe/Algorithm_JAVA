package baekjoon;
import java.util.Scanner;

public class BOJ_25314_코딩은체육과목입니다_김세민 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i = 1; i <= N / 4; i++) {
			System.out.print("long ");
		}
		System.out.println("int");
		sc.close();

	}
}