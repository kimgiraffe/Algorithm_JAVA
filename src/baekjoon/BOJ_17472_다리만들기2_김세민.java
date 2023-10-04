package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_17472_다리만들기2
 * 
 * @author semin.kim
 *
 * 1. 깊이 우선 탐색을 통해 땅을 뮦어 섬들을 그룹화한다.
 * 2. 각 섬을 하나의 노드로 보고 다른 섬까지의 길이를 구한다.
 * 3. 섬들간의 최소 신장 트리를 구한다.
 *
 */

public class BOJ_17472_다리만들기2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int colSize, rowSize; // 지도의 세로, 가로 크기
	static int[][] map;
	static int[][] adjMatrix;
	static int minBridgeLength = Integer.MAX_VALUE;
	static final int[] DELTA_COL = {-1, 1, 0, 0};
	static final int[] DELTA_ROW = {0, 0, -1, 1};
	static boolean[][] visited;
	static boolean[] visitedVertex;
	static int[] minEdge;
	static ArrayList<Position>[] islands;
	static int islandIdx;

	static class Position {
		int col;
		int row;

		public Position(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "Position [col=" + col + ", row=" + row + "]";
		}
	}

	private static boolean isValidRange(int col, int row) {
		return col >= 0 && row >= 0 && col < colSize && row < rowSize;
	}

	private static void island(int col, int row) {

		if(map[col][row] == 0) {
			return;
		}

		if(!visited[col][row]) {
			islands[islandIdx].add(new Position(col, row));
			visited[col][row] = true;
		}

		for(int dir = 0; dir < 4; dir++) {
			int nextCol = col + DELTA_COL[dir];
			int nextRow = row + DELTA_ROW[dir];

			if(isValidRange(nextCol, nextRow) && map[nextCol][nextRow] == 1 && !visited[nextCol][nextRow]) {
				islands[islandIdx].add(new Position(nextCol, nextRow));
				visited[nextCol][nextRow] = true;
				island(nextCol, nextRow);
			}
		}
	}

	private static void calculateWeight() {
		for(int idx = 0; idx < islandIdx; idx++) {
			for(int compare = 0; compare < islandIdx; compare++) {
				if(compare == idx) {
					adjMatrix[idx][compare] = 0;
				}
				else {
					adjMatrix[idx][compare] = Integer.MAX_VALUE;
					visited = new boolean[colSize][rowSize];

					for(Position position : islands[idx]) {
						for(Position position2 : islands[compare]) {
							if(position.col == position2.col) {
								//if(Math.abs(position.row - position2.row)-1 >= 2) {
									adjMatrix[idx][compare] = Math.min(adjMatrix[idx][compare], Math.abs(position.row - position2.row)-1);
								//}
							}
							else if(position.row == position2.row) {
								//if(Math.abs(position.col - position2.col)-1 >= 2) {
									adjMatrix[idx][compare] = Math.min(adjMatrix[idx][compare], Math.abs(position.col - position2.col)-1);
								//}
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		map = new int[colSize][rowSize];
		visited = new boolean[colSize][rowSize];
		islands = new ArrayList[6];
		for(int idx = 0; idx < 6; idx++) {
			islands[idx] = new ArrayList<>();
		}

		for(int col = 0; col < colSize; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < rowSize; row++) {
				map[col][row] = Integer.parseInt(st.nextToken());
			}
		}

		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				if(map[col][row] == 1 && !visited[col][row]) {
					island(col, row);
					islandIdx++;
				}
			}
		}

		//		for(int idx = 0; idx < islandIdx; idx++) {
		//			System.out.println(islands[idx].toString());
		//		}

		adjMatrix = new int[islandIdx][islandIdx];
		visitedVertex = new boolean[islandIdx];
		minEdge = new int[islandIdx];

		Arrays.fill(minEdge, Integer.MAX_VALUE);

		calculateWeight();

		for(int i = 0; i < islandIdx; i++) {
			for(int j = 0; j < islandIdx; j++) {
				if(adjMatrix[i][j] == Integer.MAX_VALUE || adjMatrix[i][j] < 2) {
					adjMatrix[i][j] = -1; 
				}
				System.out.print(adjMatrix[i][j]+ " ");
			}
			System.out.println();
		}
		
		int minVertex = 0, min = 0;

		for(int c = 0; c < islandIdx; c++) {
			minVertex = 0;
			min = Integer.MAX_VALUE;

			for(int idx = 0; idx < islandIdx; idx++) {
				if(!visitedVertex[idx] && min > minEdge[idx]) {
					minVertex = idx;
					min = minEdge[idx];
				}
			}

			visitedVertex[minVertex] = true;
			minBridgeLength += min;

			for(int idx = 0; idx < islandIdx; idx++) {
				if(!visitedVertex[idx] && adjMatrix[minVertex][idx] != -1 && minEdge[idx] > adjMatrix[minVertex][idx]) {
					minEdge[idx] = adjMatrix[minVertex][idx];
				}
			}
		}

		minBridgeLength = minBridgeLength == Integer.MAX_VALUE ? -1 : minBridgeLength;
		System.out.println(minBridgeLength);
	}
}
