package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_1600_말이되고픈원숭이 
 * 원숭이는 최대 K번 말처럼 움직일 수 있다.
 * (0, 0)에서 출발하여 오른쪽 아래 끝 위치까지 이동하는데 필요한 동작의 최솟값 출력
 * 이동할 수 없는 경우 -1 출력
 * 
 * 말처럼 움직였을 때 방문한 위치와 인접한 네 방향으로 움직였을 때 방문한 위치를 구분하기 위해 3차원 방문 배열 사용
 * 필요한 동작을 최소로 하려면...
 * 말처럼 움직일 수 있는 경우... 대각선 8방 탐색하여 이동하고 말처럼 움직일 수 있는 횟수 1감소, 동작 횟수 1 증가
 * 말처럼 움직일 수 없는 경우... 상하좌우 4방 탐색하여 이동하고 말처럼 움직일 수 있는 횟수는 유지, 동작 횟수 1 증가
 * 맨 오른쪽 아래에 도착한 경우... 필요한 동작 횟수가 갱신된 경우 갱신된 최솟값을, 그렇지 않은 경우 -1 출력
 * 
 * @author semin.kim
 */

public class BOJ_1600_말이되고픈원숭이_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int knightLikeMoveLimit;
	static int colSize, rowSize;
	static int[][] map;
	static int minMoveCount = Integer.MAX_VALUE; // 동작수의 최솟값

	static final int[] DELTA_COL = {-2, -1, 1, 2, 2, 1, -1, -2, -1, 0, 1, 0};
	static final int[] DELTA_ROW = {1, 2, 2, 1, -1, -2, -2, -1, 0, 1, 0, -1};

	static boolean[][][] visited;

	static class Pos {
		int col;
		int row;
		int moveCount; // 동작 횟수
		int knightLikeMoveCount; // 말처럼 움직일 수 있는 횟수

		public Pos(int col, int row, int moveCount, int knightLikeMoveCount) {
			super();
			this.col = col;
			this.row = row;
			this.moveCount = moveCount;
			this.knightLikeMoveCount = knightLikeMoveCount;
		}

		@Override
		public String toString() {
			return "Pos [col=" + col + ", row=" + row + ", moveCount=" + moveCount + ", knightLikeMoveCount="
					+ knightLikeMoveCount + "]";
		}
	}

	/*
	 * 범위를 벗어나는지 확인하는 메서드
	 * 범위를 벗어나는 경우 false 반환, 그렇지 않은 경우 true 반환
	 */
	private static boolean isValidRange(int col, int row) {
		if(col < 0 || col >= colSize || row < 0 || row >= rowSize) {
			return false;
		}
		return true;
	}

	private static int BFS(int col, int row) {
		Queue<Pos> queue = new LinkedList<>();

		queue.offer(new Pos(col, row, 0, knightLikeMoveLimit));
		visited[col][row][knightLikeMoveLimit] = true;

		while(!queue.isEmpty()) {
			Pos current = queue.poll();

			// 맨 오른쪽 아래에 도착한 경우...
			if(current.col == colSize - 1 && current.row == rowSize - 1) {
				return current.moveCount; // 동작 횟수 반환
			}

			// 말처럼 움직일 수 없는 경우...상하좌우 4방 탐색
			for(int dir = 8; dir < 12; dir++) {
				int nextCol = current.col + DELTA_COL[dir];
				int nextRow = current.row + DELTA_ROW[dir];
				if(isValidRange(nextCol, nextRow) && 
					!visited[nextCol][nextRow][current.knightLikeMoveCount] && 
					map[nextCol][nextRow] == 0) {
					visited[nextCol][nextRow][current.knightLikeMoveCount] = true; // 방문처리
					// 동작 횟수 1 증가
					queue.offer(new Pos(nextCol, nextRow, current.moveCount + 1, current.knightLikeMoveCount));
				}
			}

			// 말처럼 움직일 수 있는 경우... 8방 탐색
			if(current.knightLikeMoveCount > 0) {
				for(int dir = 0; dir < 8; dir++) {
					int nextCol = current.col + DELTA_COL[dir];
					int nextRow = current.row + DELTA_ROW[dir];
					if(isValidRange(nextCol, nextRow) && 
						!visited[nextCol][nextRow][current.knightLikeMoveCount - 1] && 
						map[nextCol][nextRow] == 0) {
						visited[nextCol][nextRow][current.knightLikeMoveCount - 1] = true; // 방문처리
						// 동작 횟수 1 증가, 말처럼 움직일 수 있는 횟수 1 감소
						queue.add(new Pos(nextCol, nextRow, current.moveCount + 1, current.knightLikeMoveCount - 1));
					}
				}
			}
		}
		return minMoveCount;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		knightLikeMoveLimit = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken()); // 격자판의 가로길이 입력
		colSize = Integer.parseInt(st.nextToken()); // 격자판의 세로길이 입력

		map = new int[colSize][rowSize];
		visited = new boolean[colSize][rowSize][knightLikeMoveLimit + 1];

		// 격자판의 정보 입력
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = Integer.parseInt(st.nextToken());
			}
		}
		// 최소 동작 횟수 구하기
		minMoveCount = BFS(0, 0);
		// 최소 동작 횟수 출력(도착점까지 갈 수 없는 경우 -1 출력)
		System.out.println(minMoveCount != Integer.MAX_VALUE ? minMoveCount : -1);

	}
}
