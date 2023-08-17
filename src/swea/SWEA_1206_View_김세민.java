package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1206_View
 * 1.빌딩들의 높이를 순회하면서 양 옆으로 두 빌딩의 높이가 자신의 높이보다 낮은지 확인
 * 2.조건을 만족하는 경우 자신보다 낮은 양 옆 4개의 건물 중 가장 높은 건물의 높이를 자신의 높이에서 뺸 세대만큼 조망권 확보 가능
 * @author semin.kim
 */

public class SWEA_1206_View_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int T = 10; // 테스트 케이스의 개수는 10으로 고정
	static int building_cnt; // 건물의 개수
	static int[] buildings;
	static int count; // 조망권을 확보가능한 세대 수
	
	public static void View() {
		for(int idx = 2; idx < building_cnt - 2; idx++) {
			// 좌우로 2개(총 4개)의 건물보다 자신이 더 높은 경우...
			if(buildings[idx] > buildings[idx-1] && buildings[idx] > buildings[idx-2] && buildings[idx] > buildings[idx+1] && buildings[idx] > buildings[idx+2]) {
				count+= buildings[idx] - Math.max(Math.max(buildings[idx-1], buildings[idx-2]), Math.max(buildings[idx+1], buildings[idx+2]));
			}
		}
		sb.append(count);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int test_case = 1; test_case <= T; test_case++) {
			building_cnt = Integer.parseInt(br.readLine().trim()); // 빌딩의 개수 입력
			buildings = new int[building_cnt];
			sb = new StringBuilder();
			st = new StringTokenizer(br.readLine().trim());
			count = 0; // 조망권이 확보 가능한 세대 수 초기화
			// 빌딩들의 높이 입력
			for(int buildingIdx = 0; buildingIdx < building_cnt; buildingIdx++) {
				buildings[buildingIdx] = Integer.parseInt(st.nextToken());
			}
			sb.append('#').append(test_case).append(' ');
			View();
			System.out.println(sb);
			
		}
	}
}
