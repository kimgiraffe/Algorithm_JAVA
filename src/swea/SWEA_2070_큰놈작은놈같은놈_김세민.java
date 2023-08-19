package swea;

import java.util.Scanner;

/**
 * 2070_큰놈작은놈같은놈
 * @author semin.kim
 * 1. 2개의 수를 입력받는다.
 * 2. 크기를 비교하여 등호 또는 부등호를 출력한다.
 */

public class SWEA_2070_큰놈작은놈같은놈_김세민 {

	public static int T, num1, num2;
	public static char ch;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt(); // 테스트 케이스 수 T 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			num1 = sc.nextInt();
			num2 = sc.nextInt();
			ch = num1 > num2 ? '>' : num1 < num2 ? '<' : '='; // 크기 비교하여 부등호 지정
			System.out.println("#" + test_case + " " + ch);
		}
		
		sc.close();
	}

}
