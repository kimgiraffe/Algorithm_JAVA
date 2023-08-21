package swea;

import java.util.Scanner;

/**
 * 2072_홀수만 더하기
 * @author semin.kim
 * 1. 10개의 수를 입력 받아 배열에 담는다.
 * 2. 각 테스트 케이스마다 합을 0으로 초기화하고
 * 3. 배열을 순회하며 홀수이면 더한다.
 * 4. 총 합을 출력한다.
 */

public class SWEA_2072_홀수만더하기_김세민 {
	public static int T, N, sum;
	public static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			num = new int[10];
			sum = 0;	// 합을 0으로 초기화
			for(int idx = 0 ; idx < 10; idx++) {
				num[idx] = sc.nextInt(); //10개의 수를 배열에 저장
				if(num[idx] % 2 == 1) {	// 홀수이면 더함
					sum += num[idx];
				}
			}
			System.out.println("#" + test_case + " " + sum);
		}
		
		sc.close();
	}

}
