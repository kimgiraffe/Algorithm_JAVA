package swea;

import java.util.Scanner;

/**
 * 1938_아주간단한계산기
 * @author semin.kim
 * 1. 두 개의 자연수를 입력받는다.
 * 2. 사칙연산(+, -, *, /) 순서로 연산한 결과를 출력한다.
 */

public class SWEA_1938_아주간단한계산기_김세민 {

	public static int num1, num2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		num1 = sc.nextInt();
		num2 = sc.nextInt();
		
		System.out.println(num1 + num2);
		System.out.println(num1 - num2);
		System.out.println(num1 * num2);
		System.out.println(num1 / num2);

		sc.close();
	}

}
