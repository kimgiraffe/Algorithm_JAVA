package baekjoon;

import java.util.Scanner;

/**
 * 2와 5로 나누어떨어지지 않는 정수 n(1 <= n <= 10000)가 주어졌을 때, 각 자릿수가 1로만 이루어진 n의 배수 중 가장 작은 수의 자릿수
 * @author semin.kim
 * 1. n을 입력받는다.
 * 2. num 값을 계속 갱신하며 n으로 나누어떨어지는지 확인
 * 3. n의 배수 중 가장 작은 수의 자릿수 출력
 */

public class BOJ_4375 {

	static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextInt()) {
			int n = sc.nextInt();
			int num = 0;
			for(int i = 1; ; i++) {
				num = (num * 10) + 1;
				num = num % n;
				if(num == 0) {
					System.out.println(i);
					break;
				}
			}
		}
		
		sc.close();
	}

}
