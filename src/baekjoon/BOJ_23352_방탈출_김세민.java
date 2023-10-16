package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23352_방탈출_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize; // 지도의 세로, 가로 크기
	static int[][] map;
	static final int[] DELTA_ROW = {-1, 1, 0, 0}; // 상하
	static final int[] DELTA_COL = {0, 0, -1, 1}; // 좌우
	static int maxLength; // 가장 긴 경로의 길이
	static int maxSum; // 숫자의 합의 최댓값
	
	static class Position {
		int row;
		int col;
		int length;
		
		public Position(int row, int col, int length) {
			this.row = row;
			this.col = col;
			this.length = length;
		}

		@Override
		public String toString() {
			return "Position [row=" + row + ", col=" + col + ", length=" + length + "]";
		}
	}
	
	private static boolean isValidRange(int row, int col) {
		return row >= 0 && col >= 0 && row < rowSize && col < colSize;
	}
	
	private static void bfs(int row, int col) {
		Queue<Position> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[rowSize][colSize];
		
		queue.offer(new Position(row, col, 0));
		visited[row][col] = true; // 방문처리
		
		while(!queue.isEmpty()) {
			Position curr = queue.poll();
			
			if(curr.length > maxLength) {
				maxLength = curr.length;
				maxSum = map[curr.row][curr.col] + map[row][col];
			}
			else if(curr.length == maxLength) {
				if(maxSum < map[curr.row][curr.col] + map[row][col]) {
					maxSum = map[curr.row][curr.col] + map[row][col];
				}
			}
			
			// 4방향 탐색
			for(int dir = 0; dir < 4; dir++) {
				int nextRow = curr.row + DELTA_ROW[dir];
				int nextCol = curr.col + DELTA_COL[dir];
				
				// 지도의 범위를 벗어나는 경우...
				if(!isValidRange(nextRow, nextCol)) continue;
				// 방문한적이 있는 경우...
				if(visited[nextRow][nextCol]) continue;
				// 0이 적힌 방인 경우...
				if(map[nextRow][nextCol] == 0) continue;
				
				queue.offer(new Position(nextRow, nextCol, curr.length + 1)); // 다음 위치와 거리를 큐에 삽입
				visited[nextRow][nextCol] = true; // 방문처리
			}
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0이 적힌 방이 아닌 모든 방에 대하여 너비우선탐색
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(map[rowIdx][colIdx] != 0) {
					bfs(rowIdx, colIdx);
				}
			}
		}
		
		System.out.println(maxSum);
	}
}
