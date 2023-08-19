package swea;

import java.util.Scanner;

/**
 * 2068_최대수구하기
 * @author semin.kim
 * 1. 테스트 케이스 수 T를 입력받는다.
 * 2. 각 테스트 케이스마다 10개의 수를 입력받아 배열에 저장한다.
 * 3. 배열을 순회하며 최댓값을 갱신한다.
 * 4. 최댓값을 출력한다.
 */

public class SWEA_2068_최대수구하기_김세민 {
	public static int T, MAX;
	public static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		T = sc.nextInt(); // 테스트 케이스의 개수 T 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			num = new int[10];
			MAX = 0; // 최댓값을 0으로 초기화
			
			for(int idx = 0; idx < 10; idx++) { // 10개의 수를 입력받아 배열에 저장
				num[idx] = sc.nextInt();
				if(num[idx] > MAX) { // 최댓값 갱신
					MAX = num[idx];
				}
			}
			
			System.out.println("#" + test_case + " " + MAX);
		}
		
		sc.close();
	}

}
