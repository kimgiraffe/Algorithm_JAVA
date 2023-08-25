package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_16778_반배치고사
 * 모 학교의 학생 N명을 대상으로 반 배치고사를 실시했다.
 * 반 배치고사 점수를 기반으로 A, B, C 3개의 분반으로 나누어 수업을 진행하려고한다.
 * 분반을 나누는 기준은 점수 S1, S2로 임의로 선정하여 3개의 분반을 다음과 같이 나누려고한다.
 * S2 이상	A 분반
 * S1 이상 S2 미만	B 분반
 * S1 미만	C 분반
 * 각 분반은 원활한 교육을 위해 인원이 너무 적거나, 너무 많으면 안되기 때문에 각 분반별로 주어진 최소 인원(N_min)과 최대 인원(N_max)을 만족해야한다.
 * 양질의 교육을 위해 임의의 점수 S1, S2를 통해 반을 배치했을때, 학생의 수가 가장 많은 반과 가장 적은 반의 차이의 최솟값을 구하여라.
 * 만약 각 반의 최소 인원, 최대 인원을 만족하는 점수 S1, S2가 없다면 -1을 출력하라.
 * 
 * S1, S2는 1이상 100이하
 * 학생 수는 최대 1000명
 * 
 * 임의의 점수에 대하여 각 분반별 학생 수를 구한다.
 * 모든 분반이 최소 인원, 최대 인원 조건을 만족하는 경우
 * 각 분반을 오름차순으로 정렬한다.
 * 학생의 수가 가장 많은 반과 가장 적은 반의 차이를 구한다.
 * 모든 점수에 대하여 학생 수의 차이의 최솟값을 갱신한다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_16778_반배치고사_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int T; // 테스트케이스의 개수
	static int studentCount, minStudent, maxStudent; // 학생 수, 각 반의 최소, 최대 인원
	static int[] score; // 학생들의 점수를 담을 1차원 배열
	static int[] Class; // 각 분반의 인원수를 담을 1차원 배열
	static int minDiff = Integer.MAX_VALUE; // 학생의 수가 가장 많은 분반과 가장 적은 분반의 차이

	private static boolean isValid() {
		for(int idx = 0; idx < 3; idx++) {
			if(Class[idx] > maxStudent || Class[idx] < minStudent) { // 최소, 최대 인원 수 조건을 만족하지 않은 경우...
				return false;
			}
		}
		return true;
	}

	private static void split() {
		for(int S1 = 1; S1 <= 100; S1++) {
			for(int S2 = 1; S2 <= 100; S2++) {
				Class = new int[3];
				for(int idx = 0; idx < studentCount; idx++) {
					if(score[idx] >= S2) {
						Class[0]++;
					} else if(score[idx] < S2 && score[idx] >= S1) {
						Class[1]++;
					} else {
						Class[2]++;
					}
				}

				if(isValid()) { // 최소, 최대 인원 수 조건을 만족하는경우...
					Arrays.sort(Class); // 분반별 인원 수 오름차순 정렬
					int diff = Class[2] - Class[0]; // 학생 수가 가장 많은 분반과 가장 작은 분반의 차이 구하기
					if(diff < minDiff) { // 최솟값 갱신
						minDiff = diff;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());

		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			studentCount = Integer.parseInt(st.nextToken());
			minStudent = Integer.parseInt(st.nextToken());
			maxStudent = Integer.parseInt(st.nextToken());

			score = new int[studentCount];
			minDiff = Integer.MAX_VALUE; 

			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < studentCount; idx++) {
				score[idx] = Integer.parseInt(st.nextToken());
			}

			split();
			if(minDiff == Integer.MAX_VALUE) { // 만족하는 점수가 존재하지 않는 경우...
				minDiff = -1;
			}
			sb.append('#').append(testCase).append(' ').append(minDiff);
			System.out.println(sb);

		}
	}
}
