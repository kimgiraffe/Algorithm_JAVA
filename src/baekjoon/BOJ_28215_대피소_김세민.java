package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * BOJ_28215_대피소
 * N(1이상 50이하)개의 집 중 K(1이상 3이하)개의 집에 대피소를 설치
 * 집에서 가장 가까운 대피소까지의 거리 중 가장 큰 값이 가장 작아지려면?
 * 
 * N개의 집 중 K개를 뽑는 모든 경우에 대하여 집에서 가장 가까운 대피소까지의 거리 계산
 *  * @author semin.kim
 */

public class BOJ_28215_대피소_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int house_cnt, shelter_cnt; // 집의 개수, 대피소의 개수
	static int MAX;
	
	static class position {
		int x;
		int y;
		
		public position() {
			
		}
		
		public position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
	}
	
	static position[] houses;
	static position[] shelters;
	
	static Vector<Integer> vector = new Vector<>();
	
	public static void combination(int currentIdx, int selectIdx) {
		if(selectIdx == shelter_cnt) { // 대피소 선택 완료
			int MAX = 0; // 가장 가까운 대피소와 집 사이의 거리 중 가장 큰 값
			for(int houseIdx = 0; houseIdx < house_cnt; houseIdx++) {
				int min_distance = Integer.MAX_VALUE; // 집에서 가장 가까운 대피소까지의 거리
				for(int shelterIdx = 0; shelterIdx < shelter_cnt; shelterIdx++) {
					int distance = Math.abs(houses[houseIdx].x - shelters[shelterIdx].x) + Math.abs(houses[houseIdx].y - shelters[shelterIdx].y); // 집과 대피소 사이의 거리
					if(distance < min_distance) {
						min_distance = distance;
					}
				}
				if(MAX < min_distance) {
					MAX = min_distance;
				}
				
				
			}
			
			vector.add(MAX); // 가장 가까운 대피소와 집 사이의 거리 중 가장 큰 값을 벡터에 추가
			
			return;
		}
		
		if(currentIdx == house_cnt) {
			return;
		}
		
		shelters[selectIdx] = houses[currentIdx];
		combination(currentIdx + 1, selectIdx + 1);
		
		combination(currentIdx + 1, selectIdx);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		
		house_cnt = Integer.parseInt(st.nextToken());
		shelter_cnt = Integer.parseInt(st.nextToken());
		houses = new position[house_cnt];
		shelters = new position[shelter_cnt];
		
		for(int idx = 0; idx < house_cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());

			houses[idx] = new position();
			houses[idx].x = Integer.parseInt(st.nextToken());
			houses[idx].y = Integer.parseInt(st.nextToken());
		}
		
		
		
		combination(0, 0);
		Collections.sort(vector); // 오름차순 정렬
		System.out.println(vector.get(0)); // 가장 작은 값 출력
	}
	
}
