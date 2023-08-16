package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_1987_알파벳
 * 1. 세로와 가로 크기를 입력받는다.
 * 2. 대문자 알파벳을 입력받는다.
 * 3. 깊이 우선 탐색 + 백트래킹을 이용하여 말이 지날 수 있는 최대의 칸 수를 출력한다.
 * @author semin.kim
 */

public class BOJ_1987_알파벳_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int col_size, row_size;	// 세로, 가로 크기
	static char[][] alphabets; //보드를 저장할 2차원 배열
	static boolean[] visited; // 알파벳의 방문여부를 저장할 배열
	static final int[] dc = {-1, 1, 0, 0}; // 상하
	static final int[] dr = {0, 0, -1, 1}; // 좌우
	static int answer; // 말이 지날 수 있는 최대 칸 수
	
	public static void dfs(int c, int r, int count) {
		answer = Math.max(answer, count); // 말이 지날 수 있는 최대 칸 수 갱신
		
		for(int dir = 0; dir < 4; dir++) {
			int next_c = c + dc[dir];
			int next_r = r + dr[dir];
			
			// 범위를 벗어나는 경우...
			if(next_c < 0 || next_c >= col_size || next_r < 0 || next_r >= row_size) continue;
			if(!visited[alphabets[next_c][next_r]-'A']) { // 새로운 알파벳인 경우...
				visited[alphabets[next_c][next_r]-'A'] = true; // 알파벳 방문처리
				dfs(next_c, next_r, count + 1); // 다음 알파벳으로 이동
				visited[alphabets[next_c][next_r]-'A'] = false; // 재사용을 위해 미방문 처리
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		col_size = Integer.parseInt(st.nextToken()); // 세로 크기 입력
		row_size = Integer.parseInt(st.nextToken()); // 가로 크기 입력
		
		alphabets = new char[col_size][row_size];
		visited = new boolean[26];
		// 알파벳 입력
		for(int colIdx = 0; colIdx < col_size; colIdx++) {
			String tmpString = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < row_size; rowIdx++) {
				alphabets[colIdx][rowIdx] = tmpString.charAt(rowIdx);
			}
		}
		
		// (0,0) 알파벳 방문처리
		visited[alphabets[0][0] - 'A'] = true;
		dfs(0, 0, 1); // (0, 0) 알파벳 1개는 이미 지남
		System.out.println(answer);
		
		
	}
}
