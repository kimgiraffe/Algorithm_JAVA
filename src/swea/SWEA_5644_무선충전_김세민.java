package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_5644_무선충전
 * 1.총 이동 시간, BC의 개수, 사용자의 이동 정보, BC의 정보를 입력받는다.
 * 2.사용자 A와 B가 접속가능한 모든 BC를 dfs를 통해 확인한다.
 * 3.모든 사용자의 충전량 합의 최댓값을 출력한다.
 * @author semin.kim
 *
 */

public class SWEA_5644_무선충전_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int MAP_SIZE = 10; // 지도의 가로, 세로 크기는 10으로 고정
	static final int USER_CNT = 2; // 사용자는 총 2명으로 고정
	
	static int T; // 테스트 케이스의 개수
	static int Moved_time; // 총 이동 시간
	static int BC_cnt; // BC의 개수
	
	static class BC {
		int x;
		int y;
		int coverage; // 충전 범위
		int performance; // 성능
		int used; // BC를 사용하고 있는 사용자 수
	}
	
	static class User {
		int x;
		int y;
	}
	
	static BC[] BCs;
	static User[] users = new User[USER_CNT];
	static int[][] move = new int[2][MAP_SIZE*MAP_SIZE+1];
	static int ret;
	
	static final int[] dx = {0, 0, 1, 0, -1};
	static final int[] dy = {0, -1, 0, 1, 0};
	
	/**
	 * 두 명의 사용자의 출발점 설정
	 * 각각 (1, 1), (10, 10)에서 출발	
	 */
	public static void init() {
		users[0] = new User();
		users[1] = new User();
		users[0].x = 1;
		users[0].y = 1;
		users[1].x = 10;
		users[1].y = 10;
		
		// 모든 BC에 대해 사용하고 있는 사용자 수는 0명으로 초기화
		for(int idx = 0; idx < BC_cnt; idx++) {
			BCs[idx].used = 0;
		}
	}
	
	// BC가 범위 내에 있는지 확인
	public static boolean checkCoverage(int userIdx, int bcIdx) {
		if(Math.abs(users[userIdx].x - BCs[bcIdx].x) + Math.abs(users[userIdx].y - BCs[bcIdx].y) <= BCs[bcIdx].coverage) {
			return true;
		}
		return false;
	}
	
	public static void dfs(int user, int sum) {
		// 기저 조건
		if(user == USER_CNT) {
			if(ret < sum) {
				ret = sum;
			}
			return;
		}
		
		// 모든 BC에 대하여 탐색
		for(int bcIdx = 0; bcIdx < BC_cnt; bcIdx++) {
			if((BCs[bcIdx].used == 0) && checkCoverage(user, bcIdx)) {
				BCs[bcIdx].used = 1;
				dfs(user + 1, sum + BCs[bcIdx].performance);
				BCs[bcIdx].used = 0;
			}
		}
		// 현재 사용자는 BC를 통해 충전하지 않고 다음 사용자로 넘어감
		dfs(user + 1, sum);
	}
	
	public static int count() {
		ret = 0;
		dfs(0, 0);
		return ret;
	}
	
	public static int solve() {
		int answer = 0;
		
		answer += count(); // 초기 위치에서 사용자 A와 B가 충전할 수 있는 양을 더함
		
		// 모든 BC에 대하여 탐색
		for(int time = 0; time < Moved_time; time++) {
			for(int userIdx = 0; userIdx < USER_CNT; userIdx++) {
				users[userIdx].x += dx[move[userIdx][time]];
				users[userIdx].y += dy[move[userIdx][time]];
			}
			
			answer += count();
		}
		
		return answer;
	}
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			
			Moved_time = Integer.parseInt(st.nextToken()); // 총 이동시간 입력
			BC_cnt = Integer.parseInt(st.nextToken()); // BC의 개수 입력
			
			BCs = new BC[BC_cnt];
			
			// 사용자들의 이동 정보 입력
			for(int userIdx = 0; userIdx < USER_CNT; userIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int time = 0; time < Moved_time; time++) {
					move[userIdx][time] = Integer.parseInt(st.nextToken());
				}
			}
			
			// BC의 정보 입력
			for(int idx = 0; idx < BC_cnt; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				BCs[idx] = new BC();
				BCs[idx].x = Integer.parseInt(st.nextToken());
				BCs[idx].y = Integer.parseInt(st.nextToken());
				BCs[idx].coverage = Integer.parseInt(st.nextToken());
				BCs[idx].performance = Integer.parseInt(st.nextToken());
			}
			
			init();
			dfs(0, 0);
			
			sb.append('#').append(test_case).append(' ').append(solve());
			System.out.println(sb);
		}
	}
}
