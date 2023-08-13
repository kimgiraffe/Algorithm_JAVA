package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_15650_N과M(2)
 * 1.자연수의 개수와 고를 자연수의 개수를 입력받는다.
 * 2.사전 순으로 중복없이 수열을 출력한다.
 * @author semin.kim
 */

public class BOJ_15650_N과M2_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int number_cnt, select_cnt;
	static int[] selected;
	
	public static void combination(int cnt, int start) { //cnt: 현재까지 뽑은 원소의 개수, start: 조합 시도할 원소의 시작 인덱스
		if(cnt == select_cnt) { // 조합 생성 완료
			for(int idx = 0; idx < select_cnt; idx++) {
				System.out.print(selected[idx] + " ");
			}
			System.out.println();
			return;
		}
		for(int idx = start; idx < number_cnt; idx++) {
			selected[cnt] = idx + 1;
			combination(cnt + 1, idx + 1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		number_cnt = Integer.parseInt(st.nextToken()); // 자연수의 개수 입력
		select_cnt = Integer.parseInt(st.nextToken()); // 고를 자연수의 개수 입력	
		
		selected = new int[select_cnt];
		combination(0, 0);
	}
}
