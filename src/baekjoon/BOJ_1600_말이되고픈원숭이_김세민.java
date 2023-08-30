package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_1600_말이되고픈원숭이
 * 
 * 원숭이는 최대 K번 말처럼 움직일 수 있다.
 * (0, 0)에서 출발하여 오른쪽 아래 끝 위치까지 이동하는데 필요한 동작의 최솟값 출력
 * 이동할 수 없는 경우 -1 출력
 * @author semin.kim
 *
 */

public class BOJ_1600_말이되고픈원숭이_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int horseLikeMoveLimit;
	static int colSize, rowSize;
	static int[][] map, count;

	static final int[] HORSE_DELTA_COL = {-2, -1, 1, 2, 2, 1, -1, -2};
	static final int[] HORSE_DELTA_ROW = {1, 2, 2, 1, -1, -2, -2, -1};
	static final int[] DELTA_COL = {-1, 0, 1, 0};
	static final int[] DELTA_ROW = {0, 1, 0, -1};

	static boolean[][] visited;

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
		if(col < 0 || col >= colSize || row < 0 || row >= rowSize) {
			return false;
		}
		return true;
	}

	private static void horseLikeBFS(int col, int row) {
		Queue<Pos> queue = new LinkedList<>();

		queue.add(new Pos(col, row));

		visited[col][row] = true;

		while(!queue.isEmpty()) {
			Pos current = queue.poll();

			for(int dir = 0; dir < 8; dir++) {
				int nextCol = current.col + HORSE_DELTA_COL[dir];
				int nextRow = current.row + HORSE_DELTA_ROW[dir];

				if(!isValidRange(nextCol, nextRow)) continue;
				if(visited[nextCol][nextRow]) continue;

				if(map[nextCol][nextRow] == 1) continue;

				queue.offer(new Pos(nextCol, nextRow));
				visited[nextCol][nextRow] = true;
				count[nextCol][nextRow] = count[current.col][current.row] + 1;
			}
		}
	}

	private static void monkeyBFS(int col, int row) {
		Queue<Pos> monkeyQueue = new LinkedList<>();

		monkeyQueue.add(new Pos(col, row));

		visited[col][row] = true;

		while(!monkeyQueue.isEmpty()) {
			Pos current = monkeyQueue.poll();

			for(int dir = 0; dir < 4; dir++) {
				int nextCol = current.col + DELTA_COL[dir];
				int nextRow = current.row + DELTA_ROW[dir];

				if(!isValidRange(nextCol, nextRow)) continue;
				if(visited[nextCol][nextRow]) continue;

				if(map[nextCol][nextRow] == 1) continue;

				monkeyQueue.offer(new Pos(nextCol, nextRow));
				visited[nextCol][nextRow] = true;
				count[nextCol][nextRow] = count[current.col][current.row] + 1;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		horseLikeMoveLimit = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());

		map = new int[colSize][rowSize];
		count = new int[colSize][rowSize];
		visited = new boolean[colSize][rowSize];

		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
			}
		}

		horseLikeBFS(0, 0);
		
//		visited = new boolean[colSize][rowSize];

		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				if(count[colIdx][rowIdx] > horseLikeMoveLimit) {
					count[colIdx][rowIdx] = 0;
					visited[colIdx][rowIdx] = false;
				}
				if(count[colIdx][rowIdx] > 0) {
					monkeyBFS(colIdx, rowIdx);
				}
				
			}
		}
		
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				System.out.print(count[colIdx][rowIdx]+ " ");
			}
			System.out.println();
		}
		
		
	}
}
