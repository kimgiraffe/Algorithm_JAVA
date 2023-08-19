package swea;

import java.util.Scanner;

/**
 * 2058_자릿수더하기
 * @author semin.kim
 * 1.하나의 자연수를 입력받는다.
 * 2.자연수가 0보다 클 때까지 자연수를 10으로 나누고
 * 3.10으로 나눈 나머지를 자릿수 합에 더한다.
 * 4.자릿수를 모두 더한 값을 출력한다.
 */

public class SWEA_2058_자릿수더하기_김세민 {
	public static int N, sum;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		while(N > 0) { // N이 0이 되면 중단
			sum += N % 10; // 자릿수를 더함
			N /= 10; // 다음 자리로 이동
		}
		
		System.out.println(sum); // 자릿수 합 출력
		
		sc.close();
	}

}
