package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_14503_로봇청소기
 * 로봇 청소기가 있는 방은 N * M크기의 직사각형으로 나타낼 수 있으며, 1 * 1 크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다.
 * 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다.
 * 처음에 빈 칸은 전부 청소되지 않은 상태이다.
 * 로봇 청소기는 다음과 같이 작동한다.
 * 1.현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우...
 * 	2-1.바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 *  2-2.바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
 *  3-1.반시계 방향으로 90도 회전한다.
 *  3-2.바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
 *  3-3.1번으로 돌아간다.
 *  로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.
 *  
 * @author semin.kim
 */

public class BOJ_14503_로봇청소기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;

	static int col_size, row_size; // 방의 크기
	static int robot_col, robot_row, robot_dir; // 로봇의 시작 좌표, 방향

	static int[][] room;
	static int cleaned_room_cnt; // 청소한 칸의 개수

	static final int[] DC = {-1, 0, 1, 0}; // 북 남
	static final int[] DR = {0, 1, 0, -1}; // 동 서

	public static void DFS(int col, int row, int dir) {

		boolean isEmpty = false;
		// 현재 칸이 아직 청소되지 않은 빈 칸인 경우...
		if(room[col][row] == 0) {
			room[col][row] = -1; // 현재 칸을 청소
			cleaned_room_cnt++; // 청소한 칸의 개수 1 증가
		}
		
		// 현재 칸의 주변 4칸 중 빈 칸이 있는지 확인하자

		for(int idx = 0; idx < 4; idx++) {
			int next_c = col + DC[idx];
			int next_r = row + DR[idx];

			// 빈 칸이 있는 경우...
			if(room[next_c][next_r] == 0) {
				isEmpty = true;
			}
		}

		// 빈 칸이 없는 경우...
		if(!isEmpty) {
			// 후진을 위해 임시로 방향 변경(0->2, 1->3, 2->0, 3->1)
			int tmp_dir = (dir + 2) % 4;
			int next_c = col + DC[tmp_dir];
			int next_r = row + DR[tmp_dir];

			// 벽인 경우...
			if(room[next_c][next_r] == 1) {
				return; // 중단
			}

			// 방향을 유지한 채로 한 칸 후진
			col = next_c;
			row = next_r;
			
			DFS(col, row, dir);
		}
		// 빈 칸이 있는 경우...
		else {
			// 반시계 방향으로 90도 회전(0->3, 1->0, 2->1, 3->2)
			dir = (dir + 3) % 4;
			int next_c = col + DC[dir];
			int next_r = row + DR[dir];

			// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우...
			if(room[next_c][next_r] == 0) {
				// 한 칸 전진
				col = next_c;
				row = next_r;
			}

			DFS(col, row, dir);
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		col_size = Integer.parseInt(st.nextToken());
		row_size = Integer.parseInt(st.nextToken());

		room = new int[col_size][row_size]; // (0,0)부터 사용

		st = new StringTokenizer(br.readLine().trim());

		robot_col = Integer.parseInt(st.nextToken());
		robot_row = Integer.parseInt(st.nextToken());
		robot_dir = Integer.parseInt(st.nextToken());

		// 방의 정보 입력
		for(int col = 0; col < col_size; col++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int row = 0; row < row_size; row++) {
				room[col][row] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(robot_col, robot_row, robot_dir);

		System.out.println(cleaned_room_cnt);
	}
}
