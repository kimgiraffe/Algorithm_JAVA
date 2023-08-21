package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1940_가랏RC카_D2
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.command의 개수에 따라 command(0, 1, 2), 가속도를 입력받는다.
 * 3.각 command에 따라 속도, 거리를 갱신한다.
 * 	3-1.command가 1인 경우, 속도는 가속도만큼 증가하고, 거리는 속도만큼 증가한다.
 * 	3-2.command가 2인 경우, 
 * 		3-2-1.가속도가 속도보다 크다면 속도는 0이되고,
 * 		3-2-2.가속도가 속도보다 작다면 속도는 가속도만큼 감소하고
 * 		거리는 속도만큼 증가한다.
 * 	3-3.command가 0인 경우, 거리는 속도만큼 증가한다.
 * 4.이동한 거리를 출력한다.
 * @author semin.kim
 */

public class SWEA_1940_가랏RC카_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; //테스트 케이스의 개수
	static int command_cnt; //command의 개수
	static int command, acceleration, velocity, distance; //command, 가속도, 속도, 거리

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력

		for(int test_case = 1; test_case <= T; ++test_case) {
			velocity = 0; //초기 속도는 0으로 set
			distance = 0; //움직인 거리는 0으로 set
			command_cnt = Integer.parseInt(br.readLine().trim()); //command의 개수 입력
			for(int command_idx = 0; command_idx < command_cnt; ++command_idx) {
				st = new StringTokenizer(br.readLine().trim());
				command = Integer.parseInt(st.nextToken()); //command 입력
				switch (command) {
				case 1: //command가 1(가속)인 경우...
					acceleration = Integer.parseInt(st.nextToken());
					velocity += acceleration; // 속도는 가속도만큼 증가한다
					distance += velocity; // 1초동안 이동한 거리는 속도와 같다.
					break;
				case 2: //command가 2(감속)인 경우...
					acceleration = Integer.parseInt(st.nextToken());
					if(acceleration > velocity) { // 가속도가 현재속도보다 큰 경우...
						velocity = 0; // 속도는 0
					} else { // 가속도가 현재속도 이하인 경우...
						velocity -= acceleration; // 속도는 가속도만큼 감소한다
					}
					distance += velocity; // 1초동안 이동한 거리는 속도와 같다.
					break;
				default: //command가 0(현재 속도 유지)인 경우...
					distance += velocity; // 1초동안 이동한 거리는 속도와 같다.
					break;
				}
			}
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(distance);
			System.out.println(sb);
		}
	}
}
