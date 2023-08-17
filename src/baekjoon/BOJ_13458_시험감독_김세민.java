package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_13458_시험감독
 * 1.각 시험장마다 총감독관 한 명을 배치하고 응시자 수에서 총감독관이 감시할 수 있는 응시자 수를 뺀다.
 * 2.1에서 구한 응시자 수를 부감독관이 감시할 수 있는 응시자 수만큼 나눈다.
 * 3.나누어 떨어지는 경우...필요한 부감독관 수는 몫과 같다.
 * 4.나누어 떨어지지 않는 경우... 필요한 부감독관 수는 (몫+1)과 같다.
 * @author semin.kim
 *
 */

public class BOJ_13458_시험감독_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int test_room_cnt; //시험장의 개수
	static int[] test_rooms;
	static int master_observer, sub_observer;
	static long needed_observers;
	
	public static void calculateObserver() {
		
		for(int idx = 0; idx < test_room_cnt; idx++) {
			test_rooms[idx] -= master_observer; // 총감독관이 감시할 수 있는 응시자 수만큼 빼기
			needed_observers++; // 총감독관은 반드시 한 명 존재
			if(test_rooms[idx] > 0) { // 아직 응시자가 남아있는경우...
				// 부감독관을 필요한만큼 추가
				needed_observers += test_rooms[idx] / sub_observer;
				if(test_rooms[idx] % sub_observer > 0) { // 나누어 떨어지지 않는 경우...
					needed_observers++; // 부감독관이 한 명 더 필요
				}
			}
		}
		System.out.println(needed_observers);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		test_room_cnt = Integer.parseInt(br.readLine().trim()); // 시험장의 개수 입력
		test_rooms = new int[test_room_cnt];
		st = new StringTokenizer(br.readLine().trim());
		// 각 시험장의 응시자 수 입력
		for(int idx = 0; idx < test_room_cnt; idx++) {
			test_rooms[idx] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine().trim());
		master_observer = Integer.parseInt(st.nextToken()); // 총감독관이 감시할 수 있는 응시자 수 입력
		sub_observer = Integer.parseInt(st.nextToken()); // 부감독관이 감시할 수 있는 응시자 수 입력
		
		calculateObserver();
	}
}
