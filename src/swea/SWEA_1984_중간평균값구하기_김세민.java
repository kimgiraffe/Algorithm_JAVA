package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_1984_중간평균값구하기_D2
 * 1. 10개의 수를 입력받아 배열에 저장한다.
 * 2. 오름차순으로 장렬한다.
 * 3. 배열을 순회하며 최솟값과 최댓값을 제외한 나머지 값을 더하여
 * 4. 평균을 구하고 소수 첫째 자리에서 반올림하여 출력
 * @author semin.kim
 */

public class SWEA_1984_중간평균값구하기_김세민 {
	static BufferedReader br;
	static StringTokenizer st;
	static int T; //테스트 케이스의 개수
	static int[] nums;
	static double sum; 
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			nums = new int[10];
			sum = 0.0;
			for(int idx = 0; idx < 10; idx++) {
				nums[idx] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(nums); //오름차순으로 정렬
			
			/*최솟값과 최댓값을 제외한 나머지 합*/
			for(int idx = 1; idx < 9; idx++) {
				sum += nums[idx];
			}
			
			System.out.printf("#%d %.0f\n", test_case, sum / 8.0);
		}
		
		br.close();
	}
}
