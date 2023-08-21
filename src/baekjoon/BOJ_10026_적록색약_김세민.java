package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1002-6_적록색약
 * N * N(1이상 100이하) 그리드
 * 4방향 인접 같은 색상은 같은 구역
 * 적록색약이 아닌 사람과 적록색약이 봤을 때 구역의 수
 * 
 * 우선, 적록색약이 아닌 사람에 대하여 깊이 우선 탐색을 통하여 구역의 개수를 구하자.
 * 방문배열과 구역의 개수를 초기화한다.
 * 적록색약인 사람에 맞게 그리드 정보를 수정하자.
 * 적록색약인 사람에 대하여 깊이 우선 탐색을 통하여 구역의 개수를 구하자.
 * @author semin.kim
 *
 */

public class BOJ_10026_적록색약_김세민 {

	static BufferedReader br;
	static StringBuilder sb;
	
	static int grid_size; // 그리드의 크기
	static int[][] grid; // 그리드의 정보를 저장할 2차원 배열
	static boolean[][] visited; // 방문 여부를 저장할 2차원 배열
	static int district_cnt; // 구역의 수
	
	static int[] DC = {-1, 1, 0, 0}; // 상하
	static int[] DR = {0, 0, -1, 1}; // 좌우
	
	static class pair {
		int col;
		int row;

		public pair(int col, int row) {
			this.col = col;
			this.row = row;
		}
		
	}
	
	public static void dfs(int c, int r) {
		visited[c][r] = true; // 방문 처리
		
		for(int dir = 0; dir < 4; dir++) {
			int next_c = c + DC[dir];
			int next_r = r + DR[dir];
			
			// 그리드 범위를 벗어나는 경우...
			if(next_c < 0 || next_c >= grid_size || next_r < 0 || next_r >= grid_size) continue;
			// 이미 방문한 경우...
			if(visited[next_c][next_r]) continue;
			
			// 같은 색인 경우... 계속 탐색
			if(grid[c][r] == grid[next_c][next_r]) {
				dfs(next_c, next_r);
			}
		}
	}
	
	/**
	 * 방문 배열과 구역의 개수 초기화
	 */
	public static void init() {
		// 방문 배열 초기화
		for(int col = 0; col < grid_size; col++) {
			for(int row = 0; row < grid_size; row++) {
				visited[col][row] = false;
			}
		}
		district_cnt = 0; // 구역의 개수 초기화
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		grid_size = Integer.parseInt(br.readLine().trim()); // 그리드의 크기 입력
		
		grid = new int[grid_size][grid_size];
		visited = new boolean[grid_size][grid_size];
		
		for(int col = 0; col < grid_size; col++) {
			String tmp = br.readLine().trim();
			for(int row = 0; row < grid_size; row++) {
				grid[col][row] = tmp.charAt(row);
			}
		}
		
		// 적록색약이 아닌 사람에 대하여 구역의 개수 구하기
		for(int col = 0; col < grid_size; col++) {
			for(int row = 0; row < grid_size; row++) {
				if(!visited[col][row]) {
					dfs(col, row);
					district_cnt++;
				}
			}
		}
		
		sb.append(district_cnt).append(" ");
		
		// 방문 배열과 구역의 개수 초기화
		init();
		
		// 적록색약은 빨간색과 초록색을 구분 못하므로 두 색을 같은 색으로 만들자
		for(int col = 0; col < grid_size; col++) {
			for(int row = 0; row < grid_size; row++) {
				if(grid[col][row] == 'R') {
					grid[col][row] = 'G'; 
				}
			}
		}
		
		// 적록색약인 사람에 대하여 구역의 개수 구하기
		for(int col = 0; col < grid_size; col++) {
			for(int row = 0; row < grid_size; row++) {
				if(!visited[col][row]) {
					dfs(col, row);
					district_cnt++;
				}
			}
		}

		sb.append(district_cnt);
		System.out.println(sb.toString());
	}
}
