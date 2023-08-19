package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_1204_최빈수구하기_D2
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.테스트 케이스의 번호와 학생 1000명의 점수를 입력받는다.
 * 3.학생들의 점수 빈도를 1차원 배열에 저장한다.
 * 4.빈도 수를 저장한 배열을 순회하면서 빈도수가 가장 높은 인덱스 값을 찾는다.
 * 5.빈도 수가 가장 높은 점수를 출력한다.
 * @author semin.kim
 */

public class SWEA_1204_최빈수구하기_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int[] scores_count; //점수의 빈도수를 저장할 배열
	static int[] students; //학생들의 점수를 저장할 배열
	
	public static void findMode() {
		int MAX = 0; // 최빈 횟수
		int idx, MAX_idx = 0; // 시작 인덱스와, 최빈수 인덱스 
		for(idx = 0; idx < 101; idx++) {
			if(scores_count[idx] >= MAX) { // 최빈 횟수 갱신
				MAX = scores_count[idx];
				MAX_idx = idx;
			}
		}
		sb.append(MAX_idx);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= T; ++test_case) {
			br.readLine();
			scores_count = new int[101];
			students = new int[1000];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < 1000; ++idx) {
				students[idx] = Integer.parseInt(st.nextToken());
				scores_count[students[idx]]++;
			}
			
			sb = new StringBuilder();
			sb.append("#").append(test_case).append(" ");
			findMode();
			System.out.println(sb);
		}
	}
}
