package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_21610_마법사상어와비바라기
 * 크기가 N×N인 격자에서 연습하려고 한다. 
 * 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 
 * 바구니에 저장할 수 있는 물의 양에는 제한이 없다. 
 * (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.
 * 격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 
 * 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 
 * 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.
 * 비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 
 * 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 
 * 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.
 * 1.모든 구름이 di 방향으로 si칸 이동한다.
 * 2.각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
 * 3.구름이 모두 사라진다.
 * 4.2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 
 * 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
 * - 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
 * - 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
 * 5.바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
 * 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
 * M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
 * 
 * 1번 행과 N번 행이 연결되어있고, 1번 열과 N번 열도 연결되어 있으므로 나머지 연산자를 이용하여 이동하자.
 * 1.2 번을 묶고, 3-4번을 묶어 시행한다.
 * 5에서 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 하므로 방문배열을 이용하여 구현하자.
 * 방문배열은 초기화해줘야 다음 명령을 올바르게 실행할 수 있다.
 * 모든 이동이 끝난 후 격자를 순회하며 물의 양의 합을 구하자.
 * 
 * @author semin.kim
 */

public class BOJ_21610_마법사상어와비바라기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int mapSize; // 격자의 크기
	static int moveCount; // 이동횟수
	
	//  ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 
	static final int[] DR = {0, -1, -1, -1, 0, 1, 1, 1};
	static final int[] DC = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static class Cloud {
		int row;
		int col;

		public Cloud(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Cloud [row=" + row + ", col=" + col + "]";
		}

	}
	
	static Queue<Cloud> cloudList;
	static int[][] map;
	static boolean[][] visited;
	
	// 구름 이동, 구름이 있는 칸의 물의 양 1 증가
	private static void moveCloud(int d, int s) {
		for(Cloud cloud : cloudList) {
			cloud.row = (mapSize + cloud.row + DR[d] * (s % mapSize)) % mapSize;
			cloud.col = (mapSize + cloud.col + DC[d] * (s % mapSize)) % mapSize;
			map[cloud.row][cloud.col]++; // 구름이 있는 칸의 바구니에 저장된 물의 양 1 증가
		}
	}
	
	// 구름이 사라지고, 물복사버그 마법 시전
	private static void magicCloud() {
		while(!cloudList.isEmpty()) {
			Cloud cloud = cloudList.poll();
			int count = 0; // 인접한 대각선 칸에 물이 있는 바구니의 수
			
			visited[cloud.row][cloud.col] = true; // 방문처리(이 칸은 구름이 생길 수 없음)
			
			for(int dir = 1; dir <= 7; dir+= 2) { // 대각선 방향 확인
				int nextR = cloud.row + DR[dir];
				int nextC = cloud.col + DC[dir];
				
				if(nextR >= 0 && nextR < mapSize && nextC >= 0 && nextC < mapSize) {
					// 물이 있는 경우...
					if(map[nextR][nextC] > 0) {
						count++;
					}
				}
			}
			// 물이 있는 바구니의 수만큼 바구니의 물의 양 증가
			map[cloud.row][cloud.col] += count;
		}
	}
	
	// 바구니에 저장된 물의 양이 2이상인 모든 칸에 구름이 생기고 물의 양이 2 감소
	private static void makeCloud() {
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				// 구름이 사라진 칸이 아니고 바구니에 저장된 물의 양이 2이상인 경우...
				if(!visited[row][col] && map[row][col] >= 2) {
					cloudList.add(new Cloud(row, col)); // 구름 생성
					map[row][col] -= 2; // 바구니에 저장된 물의 양이 2 감소
				}
			}
		}
		// 방문 배열 초기화
		visited = new boolean[mapSize][mapSize];
	}
	
	// 바구니에 담긴 물의 양의 합 구하기
	private static int calculateTotal() {
		int sum = 0;
		for(int row = 0; row < mapSize; row++) {
			for(int col = 0; col < mapSize; col++) {
				sum += map[row][col];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		mapSize = Integer.parseInt(st.nextToken()); // 격자의 크기 입력
		moveCount = Integer.parseInt(st.nextToken()); // 이동횟수 입력
		
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		cloudList = new LinkedList<>();
		// 격자 정보 입력
		for(int row = 0; row < mapSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int col = 0; col < mapSize; col++) {
				map[row][col] = Integer.parseInt(st.nextToken());
				// 초기 구름 위치 설정
				if(row == mapSize - 1 || row == mapSize - 2) {
					if(col == 0 || col == 1) {
						cloudList.add(new Cloud(row, col));
					}
				}
			}
		}
		
		for(int move = 1; move <= moveCount; move++) {
			st = new StringTokenizer(br.readLine().trim());
			int dir = Integer.parseInt(st.nextToken()) - 1; // 방향 입력
			int shift = Integer.parseInt(st.nextToken()); // 거리 입력
			moveCloud(dir, shift);
			magicCloud();
			makeCloud();
		}
		
		System.out.println(calculateTotal());
	}
}
