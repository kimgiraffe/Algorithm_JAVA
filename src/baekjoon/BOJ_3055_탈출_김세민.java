package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 * BOJ_3055_탈출
 * 
 * @author SSAFY
 *
 * [문제]
 * 티떱숲의 지도는 R행 C열로 이루어져 있다. 
 * 비어있는 곳은 '.'로 표시되어 있고, 물이 차있는 지역은 '*', 돌은 'X'로 표시되어 있다. 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져 있다.
 * 매 분마다 고슴도치는 현재 있는 칸과 인접한 네 칸 중 하나로 이동할 수 있다. (위, 아래, 오른쪽, 왼쪽) 물도 매 분마다 비어있는 칸으로 확장한다.
 * 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한 변을 공유)은 물이 차게 된다. 물과 고슴도치는 돌을 통과할 수 없다. 
 * 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성하시오.
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
 * 
 * [풀이]
 * 
 * 
 */

public class BOJ_3055_탈출_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int colSize, rowSize;
	static char[][] map, temp;
	static boolean[][] visited;
	static int[][] distance;
	static Position hedgehog;
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌우
	static List<Position> waterList = new ArrayList<Position>();
	static int dCol, dRow; // 비버 굴의 세로, 가로 위치

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
		if(col < 0 || row < 0 || col >= colSize || row >= rowSize) {
			return false;
		}
		return true;
	}

	private static void spreadWater(int time) {
		
	}

	private static void BFS() {
		Queue<Position> queue = new ArrayDeque<>();

		queue.offer(hedgehog);

		while(!queue.isEmpty()) {
			Position curr = queue.poll();

			if(curr.col == dCol && curr.row == dRow) {
				return;
			}

			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];

				// 맵의 범위를 벗어나는 경우... 무시
				if(!isValidRange(nextCol, nextRow)) continue;

				// 돌인 경우... 무시
				if(map[nextCol][nextRow] == 'X') continue;

				// 물이 찰 예정인 칸인 경우... 무시
				// spreadWater(distance[nextCol][nextRow]);
				if(temp[nextCol][nextRow] == '*') continue;

				visited[nextCol][nextRow] = true; // 방문처리
				distance[nextCol][nextRow] = distance[curr.col][curr.row] + 1;
				queue.offer(new Position(nextCol, nextRow));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());

		map = new char[colSize][rowSize];
		temp = new char[colSize][rowSize];
		visited = new boolean[colSize][rowSize];
		distance = new int[colSize][rowSize];

		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			String input = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = input.charAt(rowIdx);
				temp[colIdx][rowIdx] = map[colIdx][rowIdx];
				if(map[colIdx][rowIdx] == 'S') { // 고슴도치의 위치
					hedgehog = new Position(colIdx, rowIdx);
				} else if(map[colIdx][rowIdx] == '*') { // 물이 차 있는 칸
					waterList.add(new Position(colIdx, rowIdx));
				} else if(map[colIdx][rowIdx] == 'D') { // 비버의 굴
					dCol = colIdx; dRow = rowIdx;
				}
			}
		}


		BFS();

		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				System.out.print(temp[col][row]);
			}
			System.out.println();
		}
		
		if(distance[dCol][dRow] == 0) {
			System.out.println("KACTUS");
		} else {
			System.out.println(distance[dCol][dRow]);
		}
	}
}
