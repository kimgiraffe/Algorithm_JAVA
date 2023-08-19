package swea;

import java.util.Scanner;

/**
 * 2019_더블더블
 * @author semin.kim
 * 1. 정수 1개가 입력으로 주어진다.
 * 2. 반복문을 통해 주어진 정수까지 2를 곱한 값을 출력한다.
 */

public class SWEA_2019_더블더블_김세민 {

	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		for(int idx = 0; idx <= N; idx++) {
			System.out.print((1 << idx) + " ");
		}
		
		sc.close();
	}

}
