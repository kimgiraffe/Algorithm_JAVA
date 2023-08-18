package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA_1953_탈주범검거
 * 맨홀 뚜껑 위치부터 시작하여 4방향 탐색을 한다.
 * 터널이 연결되어 있어 다음 위치로 이동할 수 있는 경우...다음 위치로 이동하고 해당 위치를 방문처리한다.
 * 범위를 벗어나거나 터널이 없는 경우 이동할 수 없다.
 * 이전 위치로부터 해당 위치까지의 거리를 1 증가한다.
 * 탈출 후 소요된 시간 이하의  거리에 있는 방문할 수 있는 위치의 수를 모두 더한다.
 * 현재 터널 구조물 타입 1 -> 다음 방문 가능한 터널 구조물 타입 상(1,2,5,6), 하(1,2,4,7), 좌(1,3,4,5), 우(1,3,6,7)
 * 현재 터널 구조물 타입 2 -> 다음 방문 가능한 터널 구조물 타입 상(1,2,5,6), 하(1,2,4,7), 좌(x), 우(x)
 * 현재 터널 구조물 타입 3 -> 다음 방문 가능한 터널 구조물 타입 상(x), 하(x), 좌(1,3,4,5), 우(1,3,6,7)
 * 현재 터널 구조물 타입 4 -> 다음 방문 가능한 터널 구조물 타입 상(1,2,5,6), 하(x), 좌(x), 우(1,3,6,7)
 * 현재 터널 구조물 타입 5 -> 다음 방문 가능한 터널 구조물 타입 상(x), 하(1,2,4,7), 좌(x), 우(1,3,6,7)
 * 현재 터널 구조물 타입 6 -> 다음 방문 가능한 터널 구조물 타입 상(x), 하(1,2,4,7), 좌(1,3,4,5), 우(x)
 * 현재 터널 구조물 타입 7 -> 다음 방문 가능한 터널 구조물 타입 상(1,2,5,6), 하(x), 좌(1,3,4,5), 우(x)
 * -------------------------------------------------------------------------------------------------
 * 위로 갈 경우 다음 방문 가능한 터널 구조물은 1,2,5,6 중 하나
 * 아래로 갈 경우 다음 방문 가능한 터널 구조물은 1,2,4,7 중 하나
 * 왼쪽으로 갈 경우 다음 방문 가능한 터널 구조물은 1,3,4,5 중 하나
 * 오른쪽으로 갈 경우 다음 방문 가능한 터널 구조물은 1,3,6,7 중 하나
 * @author semin.kim
 *
 */

public class SWEA_1953_탈주범검거_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; // 테스트 케이스의 개수
	static int tunnel_col_size, tunnel_row_size; // 터널의 세로, 가로 크기
	static int hole_col_idx, hole_row_idx; // 맨홀 뚜껑의 세로, 가로 위치
	static int elapesed_time; // 탈출 후 소요된 시간
	static int[][] tunnels, counted;
	static boolean[][] visited;
	static final int[] DC = {-1, 1, 0, 0}; // 상하 이동
	static final int[] DR = {0, 0, -1, 1}; // 좌우 이동
	static int possible_locations;

	static class pair {
		int col;
		int row;

		public pair(int col, int row) {
			this.col = col;
			this.row = row;
		}

		@Override
		public String toString() {
			return "pair [col=" + col + ", row=" + row + "]";
		}


	}

	// 지도의 범위를 벗어나는지 확인하는 메서드
	// 벗어나는 경우... true 반환
	public static boolean isOutOfRange(int col, int row) {
		if(col < 0 || col >= tunnel_col_size || row < 0 || row >= tunnel_row_size) {
			return true;
		}
		return false;
	}

	// 탈주범이 탈출 후 소요된 시간동안 위치할 수 있는 장소의 개수를 구하는 메서드
	public static void count() {
		for(int col_idx = 0; col_idx < tunnel_col_size; col_idx++) {
			for(int row_idx = 0; row_idx < tunnel_row_size; row_idx++) {
				if(counted[col_idx][row_idx] > 0 && counted[col_idx][row_idx] <= elapesed_time) {
					possible_locations++;
				}
			}
		}
	}

	public static void bfs(int col, int row) {

		Queue<pair> queue = new LinkedList<pair>();

		queue.add(new pair(col, row));

		while(!queue.isEmpty()) {
			pair curr = queue.poll();
			for(int dir = 0; dir < 4; dir++) {
				int next_col = curr.col + DC[dir];
				int next_row = curr.row + DR[dir];

				if(isOutOfRange(next_col, next_row)) continue;
				if(visited[next_col][next_row]) continue;


				if(tunnels[curr.col][curr.row] == 1) {
					if(dir == 0) { // 위
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 5 || tunnels[next_col][next_row] == 6) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 1) { // 아래
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 2) { // 왼쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 5) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 3) { // 오른쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 6 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
				else if(tunnels[curr.col][curr.row] == 2) {
					if(dir == 0) { // 위
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 5 || tunnels[next_col][next_row] == 6) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 1) { // 아래
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					
				}
				else if(tunnels[curr.col][curr.row] == 3) {
					if(dir == 2) { // 왼쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 5) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 3) { // 오른쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 6 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
				else if(tunnels[curr.col][curr.row] == 4) {
					if(dir == 0) { // 위
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 5 || tunnels[next_col][next_row] == 6) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 3) { // 오른쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 6 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
				else if(tunnels[curr.col][curr.row] == 5) {
					if(dir == 1) { // 아래
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					
					else if(dir == 3) { // 오른쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 6 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
				else if(tunnels[curr.col][curr.row] == 6) {
					if(dir == 1) { // 아래
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 7) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 2) { // 왼쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 5) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
				else if(tunnels[curr.col][curr.row] == 7) {
					if(dir == 0) { // 위
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 2 || tunnels[next_col][next_row] == 5 || tunnels[next_col][next_row] == 6) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
					else if(dir == 2) { // 왼쪽
						if(tunnels[next_col][next_row] == 1 || tunnels[next_col][next_row] == 3 || tunnels[next_col][next_row] == 4 || tunnels[next_col][next_row] == 5) {
							visited[next_col][next_row] = true;
							queue.add(new pair(next_col, next_row));
							counted[next_col][next_row] = counted[curr.col][curr.row] + 1;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			tunnel_col_size = Integer.parseInt(st.nextToken()); // 터널의 세로 크기 입력
			tunnel_row_size = Integer.parseInt(st.nextToken()); // 터널의 가로 크기 입력
			hole_col_idx = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 세로 위치 입력
			hole_row_idx = Integer.parseInt(st.nextToken()); // 맨홀 뚜껑의 가로 위치 입력
			elapesed_time = Integer.parseInt(st.nextToken()); // 탈출 후 소요된 시간 입력

			tunnels = new int[tunnel_col_size][tunnel_row_size];
			counted = new int[tunnel_col_size][tunnel_row_size];
			visited = new boolean[tunnel_col_size][tunnel_row_size];
			possible_locations = 0;
			visited[hole_col_idx][hole_row_idx] = true;
			counted[hole_col_idx][hole_row_idx] = 1;

			// 터널 지도 정보 입력
			for(int col_idx = 0; col_idx < tunnel_col_size; col_idx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int row_idx = 0; row_idx < tunnel_row_size; row_idx++) {
					tunnels[col_idx][row_idx] = Integer.parseInt(st.nextToken());
				}
			}

			bfs(hole_col_idx, hole_row_idx);
			count();

			sb.append('#').append(test_case).append(' ').append(possible_locations);
			System.out.println(sb.toString());
		}
	}
}
