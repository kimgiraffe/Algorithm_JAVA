package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_14502_연구소
 * 
 * 크기가 N * M인 연구소는 빈 칸(0), 벽(1), 바이러스(2)가 존재
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다.
 * 얻을 수 있는 안전 영역의 크기의 최댓값을 구하자.
 * (3 <= N, M <= 8)
 * 
 * 조합을 이용하여 모든 빈 칸에 대하여 3개의 벽을 세우고 이 때의 안전 영역의 크기를 구한다.
 * 너비 우선 탐색을 통해 안전 영역의 크기를 구하고 최댓값을 갱신한다.
 * 
 * @author semin.kim
 *
 */

public class BOJ_14502_연구소_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int colSize, rowSize; // 연구소의 세로, 가로 크기 
	static int[][] map, temp;
	static final int[] DELTA_COL = {0, -1, 0, 1};
	static final int[] DELTA_ROW = {1, 0, -1, 0};
	static final int WALL_ADDED_COUNT = 3;
	static Pos[] selected;
	static ArrayList<Pos> blankList;
	static boolean[][] visited;
	static int MAX;
	
	static class Pos {
		int col;
		int row;
		
		public Pos(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "Pos [col=" + col + ", row=" + row + "]";
		}
	}
	
	private static boolean isValidRange(int col, int row) {
		if(col < 0 || row < 0 || col >= colSize || row >= rowSize) {
			return false;
		}
		return true;
	}
	
	private static void calculateSafeArea(int col, int row) {
		
		Queue<Pos> queue = new LinkedList<>();
		
		queue.offer(new Pos(col, row));
		
		while(!queue.isEmpty()) {
			Pos curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				// 연구소의 범위를 벗어나는 경우... 무시
				if(!isValidRange(nextCol, nextRow)) continue;
				
				// 이미 방문한 적이 있는 경우... 무시
				if(visited[nextCol][nextRow]) continue;

				// 벽인 경우... 무시
				if(map[nextCol][nextRow] == 1) continue;
				
				// 바이러스 퍼지기
				map[nextCol][nextRow] = 2;
				
				// 방문처리
				visited[nextCol][nextRow] = true;
				queue.offer(new Pos(nextCol, nextRow));
			}
		}
		
	}
	
	private static void Combination(int currentIdx, int selectedIdx) {
		if(selectedIdx == WALL_ADDED_COUNT) { // 3개의 벽을 세울 수 있는 조합 구성 완료
			// 연구소를 입력 받은 형태로 초기화
			initMap();
			// 3개의 벽을 세우기
			for(int idx = 0; idx < 3; idx++) {
				map[selected[idx].col][selected[idx].row] = 1;
			}
			
			// 모든 바이러스에 대해 퍼지기
			for(int col = 0; col < colSize; col++) {
				for(int row = 0; row < rowSize; row++) {
					if(map[col][row] == 2) {
						calculateSafeArea(col, row);
					}
				}
			}
			
			int safeAreaSize = 0; // 안전 영역의 크기
			for(int col = 0; col < colSize; col++) {
				for(int row = 0; row < rowSize; row++) {
					if(map[col][row] == 0) {
						safeAreaSize++;
					}
				}
			}
			// 안전 영역의 크기의 최댓값 갱신
			MAX = Math.max(MAX, safeAreaSize);
			return;
		}
		
		if(currentIdx == blankList.size()) {
			return;
		}
		
		selected[selectedIdx] = blankList.get(currentIdx);
		Combination(currentIdx + 1, selectedIdx + 1);
		
		Combination(currentIdx + 1, selectedIdx);
	}
	
	private static void initMap() {
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				map[col][row] = temp[col][row];
				visited[col][row] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		
		map = new int[colSize][rowSize];
		temp = new int[colSize][rowSize];
		selected = new Pos[WALL_ADDED_COUNT];
		blankList = new ArrayList<>();
		visited = new boolean[colSize][rowSize];
		
		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++) {
				map[col][row] = Integer.parseInt(st.nextToken());
				temp[col][row] = map[col][row];
				// 빈 칸인 경우...
				if(map[col][row] == 0) {
					blankList.add(new Pos(col, row)); // 빈 칸 리스트에 추가
				}
			}
		}
		Combination(0, 0);
		System.out.println(MAX);
	}
}
