package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_3055_탈출
 * 
 * @author semin.kim
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
 * 1. 맵의 정보를 입력받아 고슴도치 시작 위치를 저장하고 빈 칸으로 변경한다.
 * 2. 물이 차 있는 칸을 큐에 삽입
 * 3. 너비 우선 탐색을 통해 4방향 탐색하여 인접 칸으로 물이 퍼져나감
 * 4. 고슴도치 시작 위치를 큐에 삽입
 * 5. 너비 우선 탐색을 통해 물이 찰 예정이 아니고 방문할 수 있는 경우에 고슴도치가 다음 위치로 이동하여 탐색
 * 6. 비버의 굴까지 도달할 수 있는 경우 필요한 시간 출력, 도달할 수 없는 경우 -1 출력
 * 
 */

public class BOJ_3055_탈출_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

<<<<<<< HEAD
	static int colSize, rowSize; // 지도의 세로, 가로 크기
	static char[][] map; // 맵의 정보를 저장할 2차원 배열
	static int[][] hedgehogDistance, waterDistance; // 고슴도치, 물의 도달 시간을 저장할 2차원 배열
=======
	static int colSize, rowSize;
	static char[][] map;
	static int[][] hedgehogDistance, waterDistance;
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
	static Queue<Position> queue = new ArrayDeque<>();
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌우
	static int dCol, dRow; // 비버 굴의 세로, 가로 위치
<<<<<<< HEAD
	static int sCol, sRow; // 고슴도치의 시작 위치 (세로, 가로)
=======
	static int sCol, sRow; // 시작 위치
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git

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
<<<<<<< HEAD
=======
	}
	
	private static void spreadWater() {
		while (!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				if(!isValidRange(nextCol, nextRow)) continue;
				
				if(waterDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] != 'X' && map[nextCol][nextRow] != 'D') {
					waterDistance[nextCol][nextRow] = waterDistance[curr.col][curr.row] + 1;
					queue.offer(new Position(nextCol, nextRow));
				}
			}
		}
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
	}
<<<<<<< HEAD
	
	private static void spreadWater() {
		while (!queue.isEmpty()) {
=======

	private static boolean isValidRange(int col, int row) {
		if(col < 0 || row < 0 || col >= colSize || row >= rowSize) {
			return false;
		}
		return true;
	}

	private static void BFS() {
		hedgehogDistance[sCol][sRow] = 0;
		queue.offer(new Position(sCol, sRow));
		
		while(!queue.isEmpty()) {
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
<<<<<<< HEAD
				// 지도의 범위를 벗어나는 경우... 무시
=======
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
				if(!isValidRange(nextCol, nextRow)) continue;
				
<<<<<<< HEAD
				// 물이 퍼져나간 적이 없고 돌과 비버의 소굴이 아닌 경우... 
				if(waterDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] == '.') {
					waterDistance[nextCol][nextRow] = waterDistance[curr.col][curr.row] + 1; // 물이 퍼져나감
					queue.offer(new Position(nextCol, nextRow)); // 큐에 다음 위치 삽입
				}
			}
		}
	}

	/**
	 * 지도의 범위를 벗어나는지 확인하는 메서드
	 * @param col
	 * @param row
	 * @return 지도의 범위를 벗어나지 않는 경우... true 반환
	 * 			지도의 범위를 벗어나는 경우... false 반환
	 */
	private static boolean isValidRange(int col, int row) {
		return col >= 0 && row >= 0 && col < colSize && row < rowSize;
	}

	private static void BFS() {
		hedgehogDistance[sCol][sRow] = 0; // 고슴도치 시작 위치에서 도달 시간은 0
		queue.offer(new Position(sCol, sRow)); // 고슴도치 시작 위치를 큐에 추가
		
		while(!queue.isEmpty()) {
			Position curr = queue.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = curr.col + DELTA_COL[dir];
				int nextRow = curr.row + DELTA_ROW[dir];
				
				// 지도의 범위를 벗어나는 경우... 무시
				if(!isValidRange(nextCol, nextRow)) continue;
				
				// 고슴도치가 방문한 적이 없고 돌이 아닌 경우...
				if(hedgehogDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] != 'X') {
					// 물이 찰 예정이지 않거나 물이 찬 적이 없는 경우...
					if(waterDistance[nextCol][nextRow] > hedgehogDistance[curr.col][curr.row] + 1 || waterDistance[nextCol][nextRow] == -1) {
						// 인접한 칸까지 도달 시간은 현재 위치 도달 시간 + 1
						hedgehogDistance[nextCol][nextRow] = hedgehogDistance[curr.col][curr.row] + 1;
						// 큐에 다음 위치 삽입
=======
				if(hedgehogDistance[nextCol][nextRow] == -1 && map[nextCol][nextRow] != 'X') {
					if(waterDistance[nextCol][nextRow] > hedgehogDistance[curr.col][curr.row] + 1 || waterDistance[nextCol][nextRow] == -1) {
						hedgehogDistance[nextCol][nextRow] = hedgehogDistance[curr.col][curr.row] + 1;
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
						queue.offer(new Position(nextCol, nextRow));
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

		map = new char[colSize][rowSize];
		hedgehogDistance = new int[colSize][rowSize];
		waterDistance = new int[colSize][rowSize];
		
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			String input = br.readLine().trim();
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				map[colIdx][rowIdx] = input.charAt(rowIdx);
<<<<<<< HEAD
				// 고슴도치와 물이 방문 여부 및 시간을 저장할 2차원 배열 -1로 초기화
				hedgehogDistance[colIdx][rowIdx] = -1;
				waterDistance[colIdx][rowIdx] = -1;
				
				// 고슴도치 시작 위치 저장, 빈 칸으로 변경
				if(map[colIdx][rowIdx] == 'S') {
					sCol = colIdx; sRow = rowIdx;
					map[colIdx][rowIdx] = '.';
				}
				
				// 비버의 굴 위치 저장
=======
				hedgehogDistance[colIdx][rowIdx] = -1;
				waterDistance[colIdx][rowIdx] = -1;
				
				if(map[colIdx][rowIdx] == 'S') {
					sCol = colIdx; sRow = rowIdx;
					map[colIdx][rowIdx] = '.';
				}
				
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
				if(map[colIdx][rowIdx] == 'D') {
					dCol = colIdx; dRow = rowIdx;
				}
				
<<<<<<< HEAD
				// 물이 차 있는 곳의 위치의 퍼져 나가는 시간을 0으로 설정
				if(map[colIdx][rowIdx] == '*') {
					waterDistance[colIdx][rowIdx] = 0;
					// 큐에 물이 차 있는 곳의 위치를 추가
					queue.offer(new Position(colIdx, rowIdx));
				}
				
				// 빈 칸인 경우..
				if(map[colIdx][rowIdx] == '.') {
					boolean flag = false;
					
					// 인접한 칸 4방향 탐색
					for(int dir = 0; dir < 4; dir++) {
						int nextCol = colIdx + DELTA_COL[dir];
						int nextRow = rowIdx + DELTA_ROW[dir];
						
						// 지도의 범위를 벗어나는 경우... 무시
						if(!isValidRange(nextCol, nextRow)) continue;
						
						// 인접한 칸에 물이 찰 예정인 경우...
						if(map[nextCol][nextRow] == '*') {
							flag = true;
						}
					}
					
					if(flag) {
						// 물이 찰 예정인 칸을 큐에 추가
						queue.offer(new Position(colIdx, rowIdx));
						// 물이 해당 위치 도달 시간은 1
=======
				if(map[colIdx][rowIdx] == '*') {
					waterDistance[colIdx][rowIdx] = 0;
				}
				
				if(map[colIdx][rowIdx] == '.') {
					boolean flag = false;
					for(int dir = 0; dir < 4; dir++) {
						int nextCol = colIdx + DELTA_COL[dir];
						int nextRow = rowIdx + DELTA_ROW[dir];
						
						if(!isValidRange(nextCol, nextRow)) continue;
						
						if(map[nextCol][nextRow] == '*') {
							flag = true;
						}
					}
					if(flag) {
						queue.offer(new Position(colIdx, rowIdx));
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
						waterDistance[colIdx][rowIdx] = 1;
					}
				}
			}
		}
<<<<<<< HEAD
=======
		
		spreadWater();
		BFS();
		
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				System.out.print(waterDistance[col][row] + " ");
			}
			System.out.println();
		}
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
		
<<<<<<< HEAD
		spreadWater();
		BFS();
		
		// 고슴도치가 비버의 굴로 이동할 수 있는 경우...
		if(hedgehogDistance[dCol][dRow] != -1) {
			System.out.println(hedgehogDistance[dCol][dRow]);
		}
		// 이동할 수 없는 경우...
		else {
=======
		for(int col = 0; col < colSize; col++) {
			for(int row = 0; row < rowSize; row++) {
				System.out.print(hedgehogDistance[col][row] + " ");
			}
			System.out.println();
		}
		
		if(hedgehogDistance[dCol][dRow] != -1) {
			System.out.println(hedgehogDistance[dCol][dRow]);
		} else {
>>>>>>> branch 'master' of https://github.com/kimgiraffe/Baekjoon_JAVA.git
			System.out.println("KAKTUS");
		}
	}
}