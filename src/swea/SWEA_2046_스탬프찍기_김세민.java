package swea;

import java.util.Scanner;

/**
 * 2046_스탬프찍기
 * @author semin.kim
 * 1. 정수 하나를 입력받는다.
 * 2. 주어진 정수만큼 반복문을 통해 '#'을 출력한다.
 */

public class SWEA_2046_스탬프찍기_김세민 {
	public static int N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for(int idx = 1; idx <= N; idx++) {
			System.out.print("#");
		}
		
		sc.close();
	}

}
