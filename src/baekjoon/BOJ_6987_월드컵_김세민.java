package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_6987_월드컵
 * 1.
 * @author semin.kim
 */

public class BOJ_6987_월드컵_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int[][] result, status;
	static boolean isValid;
	
	private static void check(int curr, int teamA, int teamB) {
		if(curr == 15) {
			isValid = true;
			return;
		}
		
		if(isValid) return;
		
		int next_teamA = teamA;
		int next_teamB = 0;
		
		if(teamB < 5) {
			next_teamB = teamB + 1;
		} else {
			next_teamA = teamA + 1;
			next_teamB = next_teamA + 1;
		}
		
		result[teamA][0]++;
		result[teamB][2]++;
		if(result[teamA][0] <= status[teamA][0] && result[teamB][2] <= status[teamB][2]) {
			check(curr + 1, next_teamA, next_teamB);
		}
		result[teamA][0]--;
		result[teamB][2]--;
		
		result[teamA][1]++;
		result[teamB][1]++;
		if(result[teamA][1] <= status[teamA][1] && result[teamB][1] <= status[teamB][1]) {
			check(curr + 1, next_teamA, next_teamB);
		}
		result[teamA][1]--;
		result[teamB][1]--;
		
		result[teamB][0]++;
		result[teamA][2]++;
		if(result[teamA][2] <= status[teamA][2] && result[teamB][0] <= status[teamB][0]) {
			check(curr + 1, next_teamA, next_teamB);
		}
		result[teamB][0]--;
		result[teamA][2]--;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		status = new int[6][3];
		
		for(int test_case = 1; test_case <= 4; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			result = new int[6][3];
			isValid = false;
			int total_match_cnt = 0; // 총 경기 수
			for(int team = 0; team < 6; team++) {
				for(int resultType = 0; resultType < 3; resultType++) {
					status[team][resultType] = Integer.parseInt(st.nextToken());
					total_match_cnt += status[team][resultType];
				}
			}
			
			if(total_match_cnt <= 30) { // 총 경기 수가 30 초과일 경우 불가능
				check(0, 0, 1);
			}
			
			if(isValid) {
				sb.append(1).append(" ");
			} else {
				sb.append(0).append(" ");
			}
		}
		System.out.println(sb);
		
	}
}
