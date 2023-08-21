package swea;

import java.util.Scanner;

/**
 * 2071_평균값구하기
 * @author semin.kim
 * 1. 테스트 케이스의 개수 T를 입력받는다.
 * 2. 각 테스트 케이스마다 10개의 수를 입력 받아 배열에 저장한다.
 * 3. 배열을 순회하며 총 합을 구한다.
 * 4. 총 합을 배열의 크기인 10으로 나눈 평균 값을 소수점 첫째 자리에서 반올림하여 출력한다.
 */

public class SWEA_2071_평균값구하기_김세민 {

	public static int T;
	public static double sum;
	public static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt(); // 테스트 케이스의 개수 T 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			num = new int[10];
			sum = 0.0; //총 합을 0으로 초기화
			for(int idx = 0; idx < 10; idx++) { // 10개의 숫자를 입력받아 배열에 저장하고 총 합을 구한다.
				num[idx] = sc.nextInt();
				sum += num[idx];
			}
			
			System.out.println("#" + test_case + " " + Math.round(sum / 10.0));
		}
		
		sc.close();
	}

}
