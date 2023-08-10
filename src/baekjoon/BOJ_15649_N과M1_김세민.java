package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_15649_N과M 1
 * 1. 자연수의 개수와 수열의 길이를 입력받는다.
 * 2. 입력받는 자연수를 오름차순으로 정렬한다.
 * 3. N개 중 M개를 뽑은 수열을 출력한다.
 *
 * @author semin.kim
 */

public class BOJ_15649_N과M1_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int number_cnt, selected_cnt; // 자연수의 개수, 수열의 길이
	static int[] selected;
	static boolean[] visited;
	
	public static void permutation(int currentIdx) {
		if(currentIdx == selected_cnt) {
			for(int idx = 0; idx < selected_cnt; idx++) {
				System.out.print(selected[idx] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int idx = 0; idx < number_cnt; idx++) {
			if(visited[idx]) continue;
			
			visited[idx] = true;
			selected[currentIdx] = idx + 1;
			permutation(currentIdx + 1);
			visited[idx] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		number_cnt = Integer.parseInt(st.nextToken());
		selected_cnt = Integer.parseInt(st.nextToken());
		selected = new int[number_cnt];
		visited = new boolean[number_cnt];
	
		permutation(0);
	}
}
