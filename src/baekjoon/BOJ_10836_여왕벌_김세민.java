package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_10836_여왕벌
 * 크기가 M * M인 격자 형태의 벌집
 * 첫날 아침 모든 애벌레의 크기는 1, N일 동안 반복
 * 각 애벌레가 자라서 커지는 크기의 정도는 0,1,2 중 하나
 * 제일 왼쪽 열과 제일 위쪽 행의 애벌레들은 입력으로 주어진만큼 
 * 나머지 애벌레들은 자신의 왼쪽, 왼쪽 위, 위의 애벌레들이 모두 자란 후
 * 그 날 가장 많이 자란 애벌레가 자란만큼 자란다.
 * 마지막 날 저녁의 에벌레들의 크기를 출력
 * 
 * 제일 왼쪽 열과 제일 위쪽 행의 애벌레의 크기를 증가시키자.
 * (M - 1, 0) 부터 (0, 0), (0, 1)부터 (0, M - 1)에 있는 애벌레의 크기를 증가시킨다.
 * 주어진 0,1,2의 개수의 합은 2M - 1개
 * 
 * 나머지 애벌레 크기를 증가시키는데 O(n²)... n은 최대 700이지만 모든 날에 대하여 연산을 수행하는 경우...
 * 시간초과 -> 모든 날에 대하여 제일 왼쪽 열과 제일 위쪽 행의 애벌레 크기를 갱신하고, 마지막 한 번만 나머지 애벌레 크기 갱신)
 * 
 * @author semin.kim
 */

public class BOJ_10836_여왕벌_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int map_size, day_cnt; // 격자판의 가로(세로) 크기, 날짜 수
	static int[][] map; // 애벌레의 크기를 저장할 2차원 배열

	static int[] grow = new int[3]; // 0의 개수, 1의 개수, 2의 개수 저장

	public static void init_map() {
		// 초기 애벌레의 크기를 1로 초기화
		for(int row = 0; row < map_size; row++) {
			for(int col = 0; col < map_size; col++) {
				map[row][col] = 1;
			}
		}
	}

	public static void update_firsts() {
		int row = map_size - 1; // 가장 왼쪽 열을 갱신 시작할 행
		int col = 1; // 가장 위 행을 갱신 시작할 열
		int idx = 0; // 0,1,2

		while(row >= 0 && idx < 3) { // 가장 왼쪽 열 갱신
			if(grow[idx] > 0) { // 개수가 0이 아닌경우...
				map[row][0] += idx; // 가장 왼쪽 행의 애벌레 크기 갱신
				grow[idx]--; // 개수 1 감소
				row--; // 다음 애벌레(위)로 이동
			}
			else if(grow[idx] == 0)idx++; // 개수가 0인 경우 다음 크기로 이동(0->1->2)
		}

		while(col < map_size && idx < 3) { // 가장 위 행 갱신
			if(grow[idx] > 0) { // 개수가 0이 아닌 경우...
				map[0][col] += idx; // 가장 왼쪽 열의 애벌레 크기 갱신
				grow[idx]--; // 개수 1 감소
				col++; // 다음 애벌레(우)로 이동
			}
			else if(grow[idx] == 0) idx++; // 개수가 0인 경우 다음 크기로 이동(0->1->2)
		}
	}

	// 제일 왼쪽 열과 제일 위 행을 제외한 나머지 애벌레 크기 갱신

	public static void update_rest() {
		// 위, 왼쪽 위, 왼쪽에 있는 애벌레 크기 중 가장 큰 값으로 갱신
		for(int row = 1; row < map_size; row++) {
			for(int col = 1; col < map_size; col++) {
				map[row][col] = Math.max(map[row-1][col-1], Math.max(map[row-1][col], map[row][col-1]));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());

		map_size = Integer.parseInt(st.nextToken()); // 격자의 크기 입력
		day_cnt = Integer.parseInt(st.nextToken()); // 총 일 수 입력

		map = new int[map_size][map_size];
		init_map(); // 애벌레의 크기를 1로 전부 초기화

		for(int day = 1; day <= day_cnt; day++) {
			st = new StringTokenizer(br.readLine().trim());
			// 0의 개수, 1의 개수, 2의 개수 입력
			for(int idx = 0; idx < 3; idx++) {
				grow[idx] = Integer.parseInt(st.nextToken());
			}
			update_firsts(); // 제일 왼쪽 열과 제일 위 행의 애벌레 크기 갱신
		}
		update_rest(); // 나머지 벌레 크기 갱신

		sb = new StringBuilder();
		
		/// 애벌레 크기 출력
		for(int row = 0; row < map_size; row++) {
			for(int col = 0; col < map_size; col++) {
				sb.append(map[row][col]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
