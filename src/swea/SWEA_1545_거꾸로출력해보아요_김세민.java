package swea;

import java.util.Scanner;

/**
 * 1545_거꾸로출력해보아요
 * @author semin.kim
 * 1. 정수 1개를 입력받는다.
 * 2. 반복문을 이용하여 주어진 숫자부터 0까지 출력한다.
 */

public class SWEA_1545_거꾸로출력해보아요_김세민 {

	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int idx = N; idx >= 0; idx--) {
			System.out.print(idx + " ");
		}
		
		sc.close();
	}
}
