package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_1194_달이차오른다가자
 * 
 * @author semin.kim
 * 
 * [문제]
 * 민식이는 지금 미로 속에 있다. 미로는 직사각형 모양이고, 여행길을 떠나기 위해 미로를 탈출하려고 한다. 미로는 다음과 같이 구성되어져있다.
 * 빈 칸: 언제나 이동할 수 있다. ('.')
 * 벽: 절대 이동할 수 없다. ('#')
 * 열쇠: 언제나 이동할 수 있다. 이 곳에 처음 들어가면 열쇠를 집는다. ('a', 'b', 'c', 'd', 'e', 'f')
 * 문: 대응하는 열쇠가 있을 때만 이동할 수 있다. ('A', 'B', 'C', 'D', 'E', 'F')
 * 민식이의 현재 위치: 빈 곳이고, 민식이가 현재 서 있는 곳이다. ('0')
 * 출구: 달이 차오르기 때문에, 민식이가 가야하는 곳이다. 이 곳에 오면 미로를 탈출한다. ('1')
 * 달이 차오르는 기회를 놓치지 않기 위해서, 미로를 탈출하려고 한다. 한 번의 움직임은 현재 위치에서 수평이나 수직으로 한 칸 이동하는 것이다.
 * 민식이가 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하시오.
 * 
 * [입력]
 * 첫째 줄에 미로의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 50) 
 * 둘째 줄부터 N개의 줄에 미로의 모양이 주어진다.
 * 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 마찬가지이다. 
 * 그리고, 문에 대응하는 열쇠가 없을 수도 있다. '0'은 한 개, '1'은 적어도 한 개 있다. 열쇠는 여러 번 사용할 수 있다.
 * 
 * [출력]
 * 첫째 줄에 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값을 출력한다. 
 * 만약 민식이가 미로를 탈출 할 수 없으면, -1을 출력한다.
 * 
 * [풀이]
 * 열쇠와 문의 종류는 총 6개 -> 가질 수 있는 열쇠의 경우의 수 -> 2⁶ = 64개
 * 부분집합을 비트를 이용하여 표현 가능
 * 현재 가지고 있는 열쇠와 새로 얻는 열쇠를 OR(|)연산을 통해 나타낼 수 있음
 * 문과 가지고 있는 열쇠의 AND(&) 연산 결과가 0보다 큰 경우 해당 문을 열 수 있음
 * 각 열쇠정보에 대해 방문배열을 달리하기 위해 3차원 방문배열을 사용
 * 
 * 1. 미로 정보를 입력 받아 시작 위치 설정
 * 2. 큐에 시작 위치를 삽입
 * 3. 4방향 탐색을 통해 미로 탈출 시도
 * 	3-1. 열쇠, 문이 아닌 경우... 방문 처리 후 다음 위치 큐에 삽입
 * 	3-2. 열쇠를 만난 경우... 새로운 키 정보를 기존의 키 정보에 추가하고 방문 처리 및 다음 위치 큐에 삽입
 *  3-3. 문을 만난 경우... 갖고 있는 키를 이용해 열 수 있다면 방문 처리 및 다음 위치 큐에 삽입
 * 4. 출구를 만날 때까지 3-1~3-3 반복
 * 	4-1. 출구를 만난 경우... 이동 횟수 반환
 * 	4-2. 탈출할 수 없는 경우... -1 반환
 * 
 */

public class BOJ_1194_달이차오른다가자_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int colSize, rowSize; // 미로의 세로, 가로 크기
	static char[][] maze;
	static boolean[][][] visited;
	static final int[] DELTA_COL = {-1, 1, 0, 0}; // 상하
	static final int[] DELTA_ROW = {0, 0, -1, 1}; // 좌우
	static Position startPosition; // 시작 위치
	
	static class Position {
		int col;
		int row;
		int moveCount; // 이동 횟수
		int key; // 가지고 있는 키 정보

		public Position(int col, int row, int moveCount, int key) {
			this.col = col;
			this.row = row;
			this.moveCount = moveCount;
			this.key = key;
		}
		
		@Override
		public String toString() {
			return "Position [col=" + col + ", row=" + row + ", moveCount=" + moveCount + ", key=" + key + "]";
		}
	}
	
	/**
	 * 미로의 범위를 벗어났는지 확인하는 메서드
	 * @param col
	 * @param row
	 * @return true if isValidRange, false if not.
	 */
	private static boolean isValidRange(int col, int row) {
		if(col < 0 || row < 0 || col >= colSize || row >= rowSize) {
			return false;
		}
		
		return true;
	}
	
	private static int BFS() {
		Queue<Position> queue = new LinkedList<>();
		// 큐에 시작 위치 삽입
		queue.offer(startPosition);
		
		while(!queue.isEmpty()) {
			Position currPosition = queue.poll();
			
			// 출구에 도달한 경우...
			if(maze[currPosition.col][currPosition.row] == '1') {
				return currPosition.moveCount; // 이동한 횟수 반환
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nextCol = currPosition.col + DELTA_COL[dir];
				int nextRow = currPosition.row + DELTA_ROW[dir];
				
				// 미로의 범위를 벗어나는 경우... 무시
				if(!isValidRange(nextCol, nextRow)) continue;
				
				// 벽을 만나는 경우... 무시
				if(maze[nextCol][nextRow] == '#') continue;
				
				// 방문한 적이 있는 경우... 무시
				if(visited[currPosition.key][nextCol][nextRow]) continue;
				
				// 빈 칸, 시작 위치, 출구
				if(maze[nextCol][nextRow] == '.' || maze[nextCol][nextRow] == '0' || maze[nextCol][nextRow] == '1') {
					visited[currPosition.key][nextCol][nextRow] = true; // 방문 처리
					queue.offer(new Position(nextCol, nextRow, currPosition.moveCount + 1, currPosition.key));
				} else if(maze[nextCol][nextRow] >= 'a' && maze[nextCol][nextRow] <= 'f') { // 열쇠를 만난 경우...
					int newKey = 1 << (maze[nextCol][nextRow] - 'a');
					newKey = newKey | currPosition.key; // 새로운 키 정보를 기존의 키 정보에 추가
					if(!visited[newKey][nextCol][nextRow]) { // 방문한 적이 없는 경우...
						visited[currPosition.key][nextCol][nextRow] = true; // 현재 키에 대한 방문처리
						visited[newKey][nextCol][nextRow] = true; // 새로운 키에 대한 정보 방문처리
						queue.offer(new Position(nextCol, nextRow, currPosition.moveCount + 1, newKey));
					}
				} else if(maze[nextCol][nextRow] >= 'A' && maze[nextCol][nextRow] <= 'F') { // 문을 만난 경우...
					int door = 1 << (maze[nextCol][nextRow] - 'A');
					if((currPosition.key & door) > 0) { // 문을 열 수 있는 경우...
						visited[currPosition.key][nextCol][nextRow] = true; // 방문 처리
						queue.offer(new Position(nextCol, nextRow, currPosition.moveCount + 1, currPosition.key));
					}
				}
			}
		}
		// 미로를 탈출 할 수 없는 경우...
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		
		maze = new char[colSize][rowSize];
		visited = new boolean[(1<<6)][colSize][rowSize];
		
		// 미로 정보 입력
		for(int col = 0; col < colSize; col++) {
			String input = br.readLine().trim();
			for(int row = 0; row < rowSize; row++) {
				maze[col][row] = input.charAt(row);
				if(maze[col][row] == '0') {
					startPosition = new Position(col, row, 0, 0);
				}
			}
		}
		
		System.out.println(BFS());
	}
}
