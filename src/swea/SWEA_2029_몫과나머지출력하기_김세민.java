package swea;

import java.util.Scanner;

/**
 * 2029_몫과나머지출력하기
 * @author semin.kim
 * 1. 테스트케이스의 개수 T를 입력받는다.
 * 2. 각 테스트케이스마다 2개의 수를 입력받는다.
 * 3. 첫번쨰 수를 두번째 수로 나눈 몫과 나머지를 출력한다.
 */

public class SWEA_2029_몫과나머지출력하기_김세민 {
	public static int T, num1, num2;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			num1 = sc.nextInt();
			num2 = sc.nextInt();
			System.out.println("#" + test_case + " " + num1 / num2 + " " + num1 % num2);
		}
		
		sc.close();
	}

}
