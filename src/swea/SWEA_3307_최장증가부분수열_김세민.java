package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA_3307_최장증가부분수열
 * 
 * LIS[i] : array[1] ~ array[i]에서 최장 증가 부분 수열의 길이
 * 1. 최장 부분수열의 길이를 1로 초기화
 * 2. 모든 LIS[]를 1로 초기화
 * 3. 증가 수열 관계인 것 중 최댓값 찾기
 * 4. 최댓값을 찾아 1 증가
 * 5. 모든 LIS 중 최댓값 찾아 출력
 * @author semin.kim
 *
 */

public class SWEA_3307_최장증가부분수열_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testcaseCount;
	static int arrayLength;
	static int[] array;
	static int[] LIS; // LIS[i] : array[1] ~ array[i]에서 최장 증가 부분 수열의 길이
	
	private static int LongestIncreasingSubsequence() {
		int maxLength = 1; // 최장 길이 초기화
		for(int idx = 0; idx < arrayLength; idx++) {
			LIS[idx] = 1; // 각 LIS[] 1로 초기화
			for(int prevIdx = 0; prevIdx < idx; prevIdx++) {
				// 증가 수열 관계이면서 기존 길이보다 더 긴 증가 부분 수열 길이인 경우...
				if(array[prevIdx] < array[idx] && LIS[idx] < LIS[prevIdx] + 1) {
					// 최댓값을 찾아 1 증가
					LIS[idx] = LIS[prevIdx] + 1;
				}
			}
			// LIS 중에서 최댓값 찾기
			maxLength = Math.max(maxLength, LIS[idx]);
		}
		return maxLength;
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		testcaseCount = Integer.parseInt(br.readLine().trim()); //테스트케이스 개수 입력
		for(int testcase = 1; testcase <= testcaseCount; ++testcase) {
			sb = new StringBuilder();
			arrayLength = Integer.parseInt(br.readLine().trim());
			
			array = new int[arrayLength];
			LIS = new int[arrayLength];
			
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < arrayLength; idx++) {
				array[idx] = Integer.parseInt(st.nextToken());
			}
			
			sb.append('#').append(testcase).append(' ').append(LongestIncreasingSubsequence());
			System.out.println(sb);
		}
	}
}
