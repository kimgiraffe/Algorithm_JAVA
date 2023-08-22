package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_2528_사다리
 * 모든 막대기들은 단위시간당 길이 1만큼 이동
 * 철수는 가장 아래층막대기 위체 시작.
 * 현재 위치하고 있는 막대기 위에서는 0시간에 이동 가능
 * 철수가 현재 위치하고 있는 막대기의 임의의 위치에서 수직으로 이동했을 때, 바로 위 층의 막대기 구간 안에 있으면(구간의 양쪽 끝 포함) 0시간에 바로 위 층의 막대기로 수직으로 올라갈 수 있다.
 * (즉, 이 조건을 만족해서 하나 위층으로 올라 갈 수 있다면, 올라가는 움직임은 시간이 걸리지 않는다.)
 * 시간 0의 초기 상태에서 출발해서 철수가 가장 아래층의 막대기에서 가장 위층의 막대기로 올라가는데 걸리는 최소 시간을 구하
 * 층의 수 (1이상 3000이하), 층의 길이(1이상 3000이하 짝수)
 *  
 * 최소 시간으로 가장 위층의 막대기까지 이동하려면...
 * 위 층의 막대가 구간 안에 있는 경우 최대한 위로 이동
 * 더이상 위 층으로 이동할 수 없다면... 막대들을 이동시킴
 *  
 * 인접합 층의 이동 방향이 서로 반대인 경우... 1초마다 두 막대의 거리는 2씩 줄어든다.
 * @author semin.kim
 */

public class BOJ_2528_사다리_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static int floor_cnt, floor_len; // 층의 수, 층의 길이 

	static class stick {
		int start_loc; // 막대의 왼쪽 끝 위치
		int end_loc; // 막대의 오른쪽 끝 위치
		int dir; // 방향

	}

	static stick[] sticks;

	static int curr_floor = 1; // 현재 위치
	static int time;


	public static int go_up(int floor) {
		int next_floor = floor + 1; // 위 층

		// 위 층의 막대기의 왼쪽 끝이 현재 막대기의 왼쪽 끝 이하이고, 위 층 막대기의 오른쪽 끝이 현재 막대기의 오른쪽 끝 이상인 경우...
		//    -----
		//     --
		if(sticks[next_floor].start_loc <= sticks[floor].start_loc && sticks[next_floor].end_loc >= sticks[floor].end_loc) {
			return next_floor;
		}
		// 위 층의 막대기의 왼쪽 끝이 현재 막대기의 왼쪽 끝 이상이고, 위 층 막대기의 오른쪽 끝이 현재 막대기의 오른쪽 끝 이하인 경우...
		//          --
		//       -------
		else if(sticks[next_floor].start_loc >= sticks[floor].start_loc && sticks[next_floor].end_loc <= sticks[floor].end_loc) {
			return next_floor;
		}
		// 위 층의 막대기의 왼쪽 끝이 현재 막대기의 왼쪽 끝 이하이고, 위 층 막대기의 오른쪽 끝이 현재 막대기의 왼쪽 끝 이상인 경우...
		//    ---
		//       --
		else if(sticks[next_floor].start_loc <= sticks[floor].start_loc && sticks[next_floor].end_loc >= sticks[floor].start_loc) {
			return next_floor;
		}
		// 위 층의 막대기의 왼쪽 끝이 현재 막대기의 오른쪽 끝 이하이고, 위 층 막대기의 오른쪽 끝이 현재 막대기의 오른쪽 끝 이상인 경우...
		//      --
		//   ---
		else if(sticks[next_floor].start_loc <= sticks[floor].end_loc && sticks[next_floor].end_loc >= sticks[floor].end_loc) {
			return next_floor;
		}

		return floor;
	}

	public static void move_sticks(int floor) {
		// 이미 지나온 층은 관심대상 아님
		for(int idx = floor; idx <= floor_cnt; idx++) {
			if(sticks[idx].dir == 0) { //왼쪾에서 오른쪽으로 이동하는 막대기인 경우...
				sticks[idx].start_loc++;
				sticks[idx].end_loc++;
				
				if(sticks[idx].end_loc == floor_len) { // 오른쪽 끝에 도달한 경우...
					sticks[idx].dir = 1; // 방향 전환
				}
			}
			else {
				sticks[idx].start_loc--;
				sticks[idx].end_loc--;
				
				if(sticks[idx].start_loc == 0) { // 왼쪽 끝에 도달한 경우...
					sticks[idx].dir = 0; // 방향 전환
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		floor_cnt = Integer.parseInt(st.nextToken()); // 층의 수 입력
		floor_len = Integer.parseInt(st.nextToken()); // 층의 길이 입력

		sticks = new stick[floor_cnt+1]; // 1층부터 사용

		for(int idx = 1; idx <= floor_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int length = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());

			sticks[idx] = new stick();
			sticks[idx].dir = direction;
			if(direction == 0) { // 왼쪽 벽에 붙어 있는 막대기
				sticks[idx].start_loc = 0; // 왼쪽 끝은 0
				sticks[idx].end_loc = length; // 오른쪽 끝은 막대의 길이
			}
			else if(direction == 1) { // 오른쪽 벽에 붙어 있는 막대기
				sticks[idx].start_loc = floor_len - length; // 왼쪽 끝은 층의 길이 - 막대의 길이
				sticks[idx].end_loc = floor_len; // 오른쪽 끝은 층의 길이
			}
		}

		while(true) {
			if(curr_floor == floor_cnt) { // 가장 위 층에 도달한 경우...
				break;
			}

			if(go_up(curr_floor) == curr_floor) { // 위로 이동하지 못한 경우...
				move_sticks(curr_floor); // 막대기 이동
				time++; // 1초 경과
			}
			else { // 위로 이동할 수 있는 경우...
				curr_floor = go_up(curr_floor); // 현재 층을 이동한 층으로 갱신
			}
		}

		System.out.println(time);
	}
}
