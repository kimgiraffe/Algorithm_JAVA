package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_15651_N과M(3)
 * 1.자연수의 개수와 수열의 길이를 입력받는다.
 * 2.중복순열 출력
 * 
 * @author semin.kim
 */

public class BOJ_15651_N과M3_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int number_cnt, select_cnt;
	static int[] selected;

	public static void permutation_with_repetition(int currentIdx, StringBuilder sb) {
		if(currentIdx == select_cnt) {
			for(int idx = 0; idx < select_cnt; idx++) {
				sb.append(selected[idx]).append(' ');
			}
			sb.append('\n');
			return;
		}
		else {
			for(int idx = 0; idx < number_cnt; idx++) {
				selected[currentIdx] = idx + 1;
				permutation_with_repetition(currentIdx + 1, sb);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		number_cnt = Integer.parseInt(st.nextToken());
		select_cnt = Integer.parseInt(st.nextToken());

		selected = new int[select_cnt];
		permutation_with_repetition(0, sb);
		System.out.print(sb.toString());
	}
}
