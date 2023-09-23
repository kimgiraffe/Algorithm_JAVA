package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2096_내려가기
 * N줄에 0 이상 9 이하의 숫자가 세 개씩 적혀 있다.
 * 내려가기 게임을 하고 있는데, 이 게임은 첫 줄에서 시작해서 마지막 줄에서 끝나게 되는 놀이이다.
 * 먼저 처음에 적혀 있는 세 개의 숫자 중에서 하나를 골라서 시작하게 된다.
 * 그리고 다음 줄로 내려가는데, 다음 줄로 내려갈 때에는 다음과 같은 제약 조건이 있다.
 * 바로 아래의 수로 넘어가거나, 아니면 바로 아래의 수와 붙어 있는 수로만 이동할 수 있다는 것이다.
 * 
 * maxDP[idx][0] = Math.max(maxDP[idx-1][0], maxDP[idx-1][1]) + map[idx][0]
 * maxDP[idx][1] = Math.max(maxDP[idx-1][0], Math.max(maxDP[idx-1][1], maxDP[idx-1][2])) + map[idx][1]
 * maxDP[idx][2] = Math.max(maxDP[idx-1][1], maxDP[idx-1][2]) + map[idx][2]
 * minDP[idx][0] = Math.min(minDP[idx-1][0], minDP[idx-1][1]) + map[idx][0]
 * minDP[idx][1] = Math.min(minDP[idx-1][0], Math.min(minDP[idx-1][1], minDP[idx-1][2])) + map[idx][1]
 * minDP[idx][2] = Math.min(minDP[idx-1][1], minDP[idx-1][2]) + map[idx][2]
 * @author semin.kim
 */

public class BOJ_2096_내려가기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int size;
	static final int ROW_SIZE = 3;
	static int[][] map, maxDP, minDP;
	static int maxScore, minScore = Integer.MAX_VALUE;
	
	private static void calculate() {
		for(int idx = 1; idx <= size; idx++) {
			maxDP[idx][0] = Math.max(maxDP[idx-1][0], maxDP[idx-1][1]) + map[idx][0];
			maxDP[idx][1] = Math.max(maxDP[idx-1][0], Math.max(maxDP[idx-1][1], maxDP[idx-1][2])) + map[idx][1];
			maxDP[idx][2] = Math.max(maxDP[idx-1][1], maxDP[idx-1][2]) + map[idx][2];
			
			minDP[idx][0] = Math.min(minDP[idx-1][0], minDP[idx-1][1]) + map[idx][0];
			minDP[idx][1] = Math.min(minDP[idx-1][0], Math.min(minDP[idx-1][1], minDP[idx-1][2])) + map[idx][1];
			minDP[idx][2] = Math.min(minDP[idx-1][1], minDP[idx-1][2]) + map[idx][2];
		}
		
		maxScore = Math.max(maxDP[size][0], Math.max(maxDP[size][1], maxDP[size][2]));
		minScore = Math.min(minDP[size][0], Math.min(minDP[size][1], minDP[size][2]));
		
		sb.append(maxScore).append(" ").append(minScore);
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		size = Integer.parseInt(br.readLine().trim());
		
		map = new int[size + 1][ROW_SIZE];
		maxDP = new int[size + 1][ROW_SIZE];
		minDP = new int[size + 1][ROW_SIZE];
		
		for(int col = 1; col <= size; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < ROW_SIZE; row++) {
				map[col][row] = Integer.parseInt(st.nextToken());
			}
		}
		calculate();
		
	}
}
