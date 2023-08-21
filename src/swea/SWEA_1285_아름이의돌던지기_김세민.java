package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1285_아름이의돌던지기_D2(C++)
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.돌을 던지는 사람의 수 (1이상 1000이하)를 입력받는다.
 * 3.돌을 던진 위치를 입력받아 1차원 배열에 저장한다.
 * 4.0으로부터 던진 돌의 거리를 갱신한다.
 * 	4-1. 돌의 위치가 음수인 경우 양수로 변환한다. (거리는 항상 양수)
 * 	4-2. 최솟값과 동일한 경우 그렇게 돌을 던진 사람의 수를 증가한다.
 * @author semin.kim
 */

public class SWEA_1285_아름이의돌던지기_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int people_cnt; //돌을 던지는 사람의 수
	static int[] position; //던진 돌의 위치를 저장하는 1차원 배열
	static int min_distance; //0으로부터 던진 돌의 거리의 최솟값
	static int min_people_cnt; //가장 0에 가깝게 던진 사람 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		
		for(int test_case = 1; test_case <= T; ++test_case) {
			min_distance = 100001;
			people_cnt = Integer.parseInt(br.readLine().trim());
			position = new int[people_cnt];
			st = new StringTokenizer(br.readLine().trim());
			for(int pos_idx = 0; pos_idx < people_cnt; pos_idx++) {
				position[pos_idx] = Integer.parseInt(st.nextToken());
				if(position[pos_idx] < 0) { // 거리는 항상 0이상이므로 위치가 음수인 경우 절댓값으로 계산
					position[pos_idx] *= -1;
				}
				if(position[pos_idx] < min_distance) { // 0에 더 가까운 경우 갱신
					min_distance = position[pos_idx]; 
					min_people_cnt = 1; // 가장 가깝게 던진 사람의 수는 1로 초기화
				}
				else if(position[pos_idx] == min_distance) { // 최솟값과 같은 거리에 돌을 던진 경우
					min_people_cnt++; // 가장 가깝게 던진 사람의 수가 증가
				}
			}
			
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ").append(min_distance).append(" ").append(min_people_cnt);
			System.out.println(sb);
		}
	}
}
