package swea;

import java.util.Scanner;

/**
 * 2025_N줄덧셈
 * @author semin.kim
 * 1. 숫자 하나를 입력받는다.
 * 2. 1부터 N까지의 합을 N(N+1)/2 식을 이용하여 구하여 출력한다.
 */

public class SWEA_2025_N줄덧셈_김세민 {

	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		System.out.println(N * (N + 1) / 2);
		
		sc.close();
	}

}
