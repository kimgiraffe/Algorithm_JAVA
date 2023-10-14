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
 * BOJ_18248_감시피하기
 * 
 * 1. 빈 칸 중에 장애물을 설치할 위치 3개를 골라 설치한다.
 * 2. 장애물 3개를 모두 설치한 후 선생님의 위치에서 4방향 탐색을 통해 선생님이 감시할 수 있는 위치를 방문처리한다.
 * 3. 모든 학생들이 선생님들이 방문한 위치에 있는지 확인한다.
 * 4. 모든 학생들이 감시를 피할수 있는 경우 YES, 없는 경우 NO 출력한다.
 * 
 * @author semin.kim
 */

public class BOJ_18428_감시피하기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int mapSize;
	static boolean[][] visited;
	static char[][] map;

	static final int[] DELTA_ROW = {-1, 1, 0, 0}; // 상, 하
	static final int[] DELTA_COL = {0, 0, -1, 1}; // 좌, 우
	static int possiblePlaceCount; // 장애물을 놓을 수 있는 칸의 수
	static List<Position> emptyList = new ArrayList<>();
	static List<Position> teacherList = new ArrayList<>();
	static List<Position> studentList = new ArrayList<>();
	static Queue<Position> queue = new ArrayDeque<>();
	
	static class Position {
		int row, col;

		public Position(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Position [row=" + row + ", col=" + col + "]";
		}

	}

	private static void place(int curr, int depth) {
		// 장애물 3개 설치 완료
		if(depth == 3) {
			bfs();
			return;
		}

		if(curr == possiblePlaceCount) {
			return;
		}

		int row = emptyList.get(curr).row;
		int col = emptyList.get(curr).col;

		map[row][col] = 'O';
		place(curr + 1, depth + 1);

		map[row][col] = 'X';
		place(curr + 1, depth);
	}

	private static boolean isValidRange(int row, int col) {
		return row >= 1 && col >= 1 && row <= mapSize && col <= mapSize;
	}

	private static void bfs() {

		queue = new ArrayDeque<>();
		char[][] copiedMap = new char[mapSize+1][mapSize+1];
		visited = new boolean[mapSize+1][mapSize+1];
		
		for(int row = 1; row <= mapSize; row++) {
			for(int col = 1; col <= mapSize; col++) {
				copiedMap[row][col] = map[row][col];
				if(copiedMap[row][col] == 'T') {
					queue.offer(new Position(row, col));
					visited[row][col] = true;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextRow = curr.row + DELTA_ROW[dir];
				int nextCol = curr.col + DELTA_COL[dir];
				
				while (isValidRange(nextRow, nextCol)) {
					if(copiedMap[nextRow][nextCol] != 'O') {
						visited[nextRow][nextCol] = true;
						nextRow += DELTA_ROW[dir];
						nextCol += DELTA_COL[dir];
					}
					else {
						break;
					}
				}
			}
		}
		
		if(check()) {
			System.out.println("YES");
			System.exit(0);
		}
		
	}
	
	private static boolean check() {
		for(Position student : studentList) {
			if(visited[student.row][student.col]) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		mapSize = Integer.parseInt(br.readLine().trim());

		map = new char[mapSize+1][mapSize+1];
		visited = new boolean[mapSize+1][mapSize+1];

		for(int row = 1; row <= mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 1; col <= mapSize; col++) {
				map[row][col] = st.nextToken().charAt(0);
				if(map[row][col] == 'X') {
					possiblePlaceCount++;
					emptyList.add(new Position(row, col));
				}
				else if(map[row][col] == 'S') {
					studentList.add(new Position(row, col));
				}
				else if(map[row][col] == 'T') {
					queue.offer(new Position(row, col));
					teacherList.add(new Position(row, col));
					visited[row][col] = true;
				}
			}
		}

		place(0, 0);

		System.out.println("NO");
	}
}
