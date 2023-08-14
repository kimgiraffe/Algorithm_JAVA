package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16236
 * BOJ_16236_아기상어
 * 1.공간의 크기와 공간의 상태를 입력받는다.
 * 2.너비 우선 탐색으로 아기 상어가 먹을 수 있는 물고기의 거리를 구한다.
 * 3.거리가 가까운 물고기가 여러 마리인 경우... 가장 위쪽에 있는 물고기를 먹는다.
 * 4.3에 해당하는 물고기가 여러 마리인 경우... 가장 왼쪽에 있는 물고기를 먹는다.
 * 5.아기 상어와 물고기 사이의 거리를 초기화한다.
 * 6.아기 상어가 더 이상 물고기를 먹을 수 없을 때까지 2~5를 반복한다.
 * @author semin.kim
 */

public class BOJ_16236_아기상어_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int space_size; // 공간의 크기
	static int[][]space; // 공간의 정보를 담는 2차원 배열
	static int[][] check; // 아기 상어와 물고기의 거리를 저장할 2차원 배열
	static int baby_shark_size = 2; // 아기 상어의 크기
	static int to_size_up; // 아기 상어가 커지기 위해 먹어야 하는 물고기의 수
	static int baby_shark_col, baby_shark_row; // 아기 상어의 세로, 가로 위치
	static int MIN_DISTANCE, MIN_colIdx, MIN_rowIdx; // 물고기가 지나가야하는 최소거리, 이 때 세로, 가로 위치
	static final int[] dr = {-1, 1, 0, 0};
	static final int[] dc = {0, 0, 1, -1};
	static final int MAX_INT = 21; // 공간의 가로, 세로 최대 크기는 20
	static int time; // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아 먹을 수 있는 시간
	
	static class pair {
		int col;
		int row;
		
		public pair(int col, int row) {
			this.col = col;
			this.row = row;
		}
	}
	
	public static void init() {
		MIN_DISTANCE = 401;
		MIN_colIdx = MAX_INT;
		MIN_rowIdx = MAX_INT;
		
		for(int colIdx = 0; colIdx < space_size; colIdx++) {
			for(int rowIdx = 0; rowIdx < space_size; rowIdx++) {
				check[colIdx][rowIdx] = -1;
			}
		}
	}
	
	public static void bfs(int c, int r) {
		Queue<pair> queue = new LinkedList <>();
		check[c][r] = 0;
		queue.add(new pair(c, r));
		
		while(!queue.isEmpty()) {
			pair curr = queue.poll();
			
			for(int idx = 0; idx < 4; idx++) {
				int next_c = curr.col + dc[idx];
				int next_r = curr.row + dr[idx];
				// 범위를 벗어나는 경우...
				if(next_c < 0 || next_c >= space_size || next_r < 0 || next_r >= space_size) continue;
				// 아기 상어보다 더 큰 물고기를 만나는 경우...
				if(check[next_c][next_r] != -1 || space[next_c][next_r] > baby_shark_size) continue;
				
				// 거리 갱신
				check[next_c][next_r] = check[curr.col][curr.row] + 1;
				
				if(space[next_c][next_r] != 0 && space[next_c][next_r] < baby_shark_size) {
					if(MIN_DISTANCE > check[next_c][next_r]) {
						MIN_colIdx = next_c;
						MIN_rowIdx = next_r;
						MIN_DISTANCE = check[next_c][next_r];
					}
					else if(MIN_DISTANCE == check[next_c][next_r]) { // 거리가 가까운 물고기가 여러 마리인 경우...
						if(MIN_colIdx == next_c) { // 거리도 같고 세로 위치도 같은 물고기가 여러 마리인 경우...
							if(MIN_rowIdx > next_r) { // 가장 왼쪽에 있는 물고기
								MIN_colIdx = next_c;
								MIN_rowIdx = next_r;
							}
						} else if(MIN_colIdx > next_c) { // 가장 위에 있는 물고기
							MIN_colIdx = next_c;
							MIN_rowIdx = next_r;
						}
					}
					
				}
				queue.add(new pair(next_c, next_r));
				
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		space_size = Integer.parseInt(br.readLine().trim()); // 공간의 크기 입력
		
		space = new int[space_size][space_size];
		check = new int[space_size][space_size];
		
		// 공간의 상태 입력
		for(int colIdx = 0; colIdx < space_size; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < space_size; rowIdx++) {
				space[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
				if(space[colIdx][rowIdx] == 9) { // 아기 상어의 위치 저장
					baby_shark_col = colIdx;
					baby_shark_row = rowIdx;
					space[colIdx][rowIdx] = 0;
				}
			}
		}
		
		while(true) {
			init(); // 아기 상어와 물고기의 거리 초기화
			
			bfs(baby_shark_col, baby_shark_row); // 너비 우선 탐색
			
			if(MIN_colIdx != MAX_INT && MIN_rowIdx != MAX_INT) {
				time += check[MIN_colIdx][MIN_rowIdx]; // 아기 상어는 1초에 1칸 이동
				
				to_size_up++; // 아기 상어가 크기가 증가하기 위해 먹은 물고기 수가 1 증가
				
				if(to_size_up == baby_shark_size) { // 아기 상어 크기가 커질 수 있는 경우...
					baby_shark_size++; // 아기 상어의 크기 1 증가
					to_size_up = 0; // 아기 상어가 먹은 물고기 수 초기화
				}
				
				space[MIN_colIdx][MIN_rowIdx] = 0; // 물고기를 먹은 위치는 0으로 갱신
				
				// 아기 상어의 위치 갱신
				baby_shark_col = MIN_colIdx;
				baby_shark_row = MIN_rowIdx;
			}
			else { // 아기 상어가 더 이상 물고기를 먹을 수 없는 경우...
				break;
			}
		}
		System.out.println(time);
	}
}
