package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_15683_감시
 * 사무실은 1×1크기의 정사각형으로 나누어져 있는 N×M 크기의 직사각형으로 나타낼 수 있다. 
 * 사무실에는 총 K개의 CCTV가 설치되어져 있는데, CCTV는 5가지 종류가 있다. 각 CCTV가 감시할 수 있는 방법은 다음과 같다.
 * 1번 CCTV는 한 쪽 방향만 감시할 수 있다. 2번과 3번은 두 방향을 감시할 수 있는데, 2번은 감시하는 방향이 서로 반대방향이어야 하고, 3번은 직각 방향이어야 한다. 4번은 세 방향, 5번은 네 방향을 감시할 수 있다.
 * CCTV는 감시할 수 있는 방향에 있는 칸 전체를 감시할 수 있다. 사무실에는 벽이 있는데, CCTV는 벽을 통과할 수 없다. CCTV가 감시할 수 없는 영역은 사각지대라고 한다.
 * CCTV는 회전시킬 수 있는데, 회전은 항상 90도 방향으로 해야 하며, 감시하려고 하는 방향이 가로 또는 세로 방향이어야 한다.
 * 지도에서 0은 빈 칸, 6은 벽, 1~5는 CCTV의 번호이다. 위의 예시에서 1번의 방향에 따라 감시할 수 있는 영역을 '#'로 나타내면 아래와 같다.
 * 사무실의 크기와 상태, 그리고 CCTV의 정보가 주어졌을 때, CCTV의 방향을 적절히 정해서, 사각 지대의 최소 크기를 구하는 프로그램을 작성하시오.
 * 
 * 1번부터 5번 CCTV가 가지는 방향에 대하여 순열을 구하고
 * 모든 경우에 대해 사각지대의 수를 구한다.
 * @author semin.kim
 *
 */

public class BOJ_15683_감시_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	static int col_size, row_size;
	static int[][] office;
	static boolean[][] visited;
	static int[][] copied_office; // 지도를 복사하여 사용하지 위한 2차원 배열
	static int min_blindSpot = Integer.MAX_VALUE; // 사각 지대의 최소 크기
	static int[] output;
	
	static final int[] DC = {-1, 0, 1, 0}; // 상하
	static final int[] DR = {0, 1, 0, -1}; // 우좌
	static ArrayList<CCTV> CCTVlist; // 지도에 존재하는 CCTV를 저장
	
	static class CCTV {
		int num;
		int col;
		int row;

		public CCTV(int num, int col, int row) {
			this.num = num;
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "CCTV [num=" + num + ", col=" + col + ", row=" + row + "]";
		}

	}
	
	public static void permutation(int currentIdx, int select_cnt) {
		if(currentIdx == select_cnt) {
			
			copied_office = new int[col_size][row_size];
			
			for(int col = 0; col < office.length; col++) {
				System.arraycopy(office[col], 0, copied_office[col], 0, office[col].length);
			}
			
			for(int idx = 0; idx < CCTVlist.size(); idx++) {
				direction(CCTVlist.get(idx), output[idx]);
			}
			
			calculateBlindSpot();
			
			return;
		}
		
		for(int idx = 0; idx < 4; idx++) {
			output[currentIdx] = idx;
			permutation(currentIdx + 1, select_cnt);
		}
		
	}
	
	public static void direction(CCTV cctv, int dir) {
		int cctv_num = cctv.num;
		
		if(cctv_num == 1) {
			if(dir == 0) bfs(cctv, 0); // 상
			else if(dir == 1) bfs(cctv, 1); // 우
			else if(dir == 2) bfs(cctv, 2); // 하
			else if(dir == 3) bfs(cctv, 3); // 좌
		} else if(cctv_num == 2) {
			if(dir == 0 || dir == 2) {
				bfs(cctv, 0); // 상
				bfs(cctv, 2); // 하
			} else if(dir == 1 || dir == 3) {
				bfs(cctv, 1); // 우
				bfs(cctv, 3); // 좌
			}
		} else if(cctv_num == 3) {
			if(dir == 0) {
				bfs(cctv, 0); // 상
				bfs(cctv, 1); // 우
			} else if(dir == 1) {
				bfs(cctv, 1); // 우
				bfs(cctv, 2); // 하
			} else if(dir == 2) {
				bfs(cctv, 2); // 하
				bfs(cctv, 3); // 좌
			} else if(dir == 3) {
				bfs(cctv, 3); // 좌
				bfs(cctv, 0); // 상
			}
		} else if(cctv_num == 4) {
			if(dir == 0) {
				bfs(cctv, 0); // 상
				bfs(cctv, 1); // 우
				bfs(cctv, 3); // 좌
			} else if(dir == 1) {
				bfs(cctv, 0); // 상
				bfs(cctv, 1); // 우
				bfs(cctv, 2); // 하
			} else if(dir == 2) {
				bfs(cctv, 1); // 우
				bfs(cctv, 2); // 하
				bfs(cctv, 3); // 좌
			} else if(dir == 3) {
				bfs(cctv, 2); // 하
				bfs(cctv, 3); // 좌
				bfs(cctv, 0); // 상
			}
		} else if(cctv_num == 5) {
			bfs(cctv, 0); // 상
			bfs(cctv, 1); // 우
			bfs(cctv, 2); // 하
			bfs(cctv, 3); // 좌
		}
	}
	
	public static void bfs(CCTV cctv, int dir) {
		Queue<CCTV> queue = new LinkedList<>();
		visited = new boolean[col_size][row_size];
		
		queue.add(cctv);
		visited[cctv.col][cctv.row] = true;
		
		while(!queue.isEmpty()) {
			
			int next_c = queue.peek().col + DC[dir];
			int next_r = queue.poll().row + DR[dir];
			
			// 범위를 벗어나는 경우...
			if(next_c < 0 || next_c >= col_size || next_r < 0 || next_r >= row_size) break;
			
			// 벽인 경우...
			if(copied_office[next_c][next_r] == 6) break;
		
			// 빈 칸인 경우...
			if(copied_office[next_c][next_r] == 0) {
				copied_office[next_c][next_r] = -1; //감시 가능
				queue.add(new CCTV(cctv.num, next_c, next_r));
			} else {
				queue.add(new CCTV(cctv.num, next_c, next_r));
			}
		}
	}
	
	// 사각 지대 계산하기
	public static void calculateBlindSpot() {
		int cnt = 0; // 사각지대 수
		
		for(int col = 0; col < col_size; col++) {
			for(int row = 0; row < row_size; row++) {
				if(copied_office[col][row] == 0) { // 아직 빈 칸인 경우...
					cnt++;
				}
			}
		}
		
		min_blindSpot = Math.min(min_blindSpot, cnt); // 사각 지대의 최소 개수 갱신
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		col_size = Integer.parseInt(st.nextToken());
		row_size = Integer.parseInt(st.nextToken());
		
		office = new int[col_size][row_size];
		visited = new boolean[col_size][row_size];
		CCTVlist = new ArrayList<>();
		
		for(int colIdx = 0; colIdx < col_size; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < row_size; rowIdx++) {
				office[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
				if(office[colIdx][rowIdx] != 0 && office[colIdx][rowIdx] != 6) {
					CCTVlist.add(new CCTV(office[colIdx][rowIdx], colIdx, rowIdx));
				}
			}
		}
		
		output = new int[CCTVlist.size()];
		permutation(0, CCTVlist.size());
		
		System.out.println(min_blindSpot);
	}
}
