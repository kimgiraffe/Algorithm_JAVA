package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_15652_N과M(4)
 * 1.자연수의 개수와 수열의 길이를 입력받는다.
 * 2.중복조합 출력
 *
 * @author semin.kim
 */

public class BOJ_15652_N과M4_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int number_cnt, select_cnt;
	static int[] selected;
	
	public static void combination_with_repetition(int cnt, int start, StringBuilder sb) {
		if(cnt == select_cnt) {
			for(int idx = 0; idx < select_cnt; idx++) {
				sb.append(selected[idx]).append(' ');
			}
			sb.append('\n');
			return;
		}
		for(int idx = start; idx < number_cnt; idx++) {
			selected[cnt] = idx + 1;
			combination_with_repetition(cnt + 1, idx, sb);
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		
		number_cnt = Integer.parseInt(st.nextToken());
		select_cnt = Integer.parseInt(st.nextToken());
		
		selected = new int[select_cnt];
		combination_with_repetition(0, 0, sb);
		System.out.print(sb);
	}
}
