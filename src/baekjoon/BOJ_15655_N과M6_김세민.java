package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_15655_N과M 6
 * 1. 자연수의 개수와 수열의 길이를 입력받는다.
 * 2. 입력받는 자연수를 오름차순으로 정렬한다.
 * 3. N개 중 M개를 뽀븐 수열을 출력한다.
 *
 * @author semin.kim
 */

public class BOJ_15655_N과M6_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int number_cnt, selected_cnt; // 자연수의 개수, 수열의 길이
	static int[] numbers;
	static int[] selected;
	
	public static void combination(int currentIdx, int selectIdx) {
		// 모든 원소를 선택했을 때
		if(selectIdx == selected_cnt) {
			for(int idx = 0; idx < selected_cnt; idx++) {
				System.out.print(selected[idx] + " ");
			}
			System.out.println();
			return;
		}
		
		// 모든 원소를 탐색했을 때
		if(currentIdx == number_cnt) {
			return;
		}
		
		// 원소를 선택하고 넘어가기
		selected[selectIdx] = numbers[currentIdx];
		combination(currentIdx + 1, selectIdx + 1);
		
		// 원소를 선택하지 않고 넘어가기
		selected[selectIdx] = 0;
		combination(currentIdx + 1, selectIdx);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		number_cnt = Integer.parseInt(st.nextToken());
		selected_cnt = Integer.parseInt(st.nextToken());
		numbers = new int[number_cnt];
		selected = new int[selected_cnt];
		
	
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < number_cnt; idx++) {
			numbers[idx] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);

		combination(0, 0);
	}
}
