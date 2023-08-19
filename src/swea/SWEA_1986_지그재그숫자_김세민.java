package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * SWEA_1986_지그재그숫자_D2
 * 1. 1이상 10이하의 정수(num)을 입력받는다.
 * 2. 합 공식을 이용하여
 * 3. 짝수이면 -num / 2 출력하고
 * 3. 홀수이면 (num + 1) / 2 출력
 * @author semin.kim
 */

public class SWEA_1986_지그재그숫자_김세민 {

	static BufferedReader br;
	static int T, num; // 테스트 케이스의 개수, 숫자(1이상 10이하의 정수)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			num = Integer.parseInt(br.readLine().trim());
			System.out.print("#" + test_case + " ");
			if((num & 1) == 1) { // 홀수이면
				System.out.println((num + 1) / 2);
			} else { // 짝수이면
				System.out.println(-num / 2);
			}
		}
		
		br.close();
	}

}
