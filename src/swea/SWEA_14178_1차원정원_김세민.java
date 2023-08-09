package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_14178_1차원정원_D3
 * 1.테스트 케이스의 개수를 입력받는다.
 * 2.정원의 길이와 분무기의 범위를 입력받는다.
 * 3.1번 좌표부터 정원의 길이를 벗어나지 않을 때까지 분무기를 놓는다.
 * 4.필요한 분무기의 개수를 출력한다.
 * @author semin.kim
 */

public class SWEA_14178_1차원정원_김세민 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; //테스트 케이스의 개수
	static int garden_len; //정원의 길이
	static int range; //분무기의 범위
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine().trim()); //테스트 케이스의 개수 입력
		for(int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			
			garden_len = Integer.parseInt(st.nextToken()); //정원의 길이 입력
			range = Integer.parseInt(st.nextToken()); // 분무기의 범위 입력
			
			int start = 1; //분무기를 처음 놓을 좌표
			int cnt = 0; // 필요한 분무기의 수
			while(start <= garden_len) { // 분모기의 좌표가 정원의 길이를 벗어나지 않을동안 분무기를 놓는다.
				start += range * 2 + 1; // 다음 놓을 분무기의 좌표는 분무기의 범위 * 2 + 1
				cnt++; //필요한 분무기의 수 1 증가
			}
			
			sb = new StringBuilder();
			sb.append('#').append(test_case).append(' ').append(cnt);
			System.out.println(sb);
		}
	}
}
