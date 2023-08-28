package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_10431_줄세우기
 * 아이들에게 키 순서대로 번호를 부여한다. 번호를 부여할 땐 키가 가장 작은 아이가 1번, 그 다음이 2번, ... , 가장 큰 아이가 20번이 된다. 
 * 강산이네 반 아이들은 항상 20명이며, 다행히도 같은 키를 가진 학생은 한 명도 없어서 
 * 시간이 조금 지나면 아이들은 자기들의 번호를 인지하고 한 줄로 세우면 제대로 된 위치에 잘 서게 된다.
 * 우선 아무나 한 명을 뽑아 줄의 맨 앞에 세운다. 그리고 그 다음부터는 학생이 한 명씩 줄의 맨 뒤에 서면서 다음 과정을 거친다.
 * 자기 앞에 자기보다 키가 큰 학생이 없다면 그냥 그 자리에 서고 차례가 끝난다.
 * 자기 앞에 자기보다 키가 큰 학생이 한 명 이상 있다면 그중 가장 앞에 있는 학생(A)의 바로 앞에 선다. 이때, A부터 그 뒤의 모든 학생들은 공간을 만들기 위해 한 발씩 뒤로 물러서게 된다.
 * 아이들의 키가 주어지고, 어떤 순서로 아이들이 줄서기를 할 지 주어진다. 위의 방법을 마지막 학생까지 시행하여 줄서기가 끝났을 때 학생들이 총 몇 번 뒤로 물러서게 될까?
 * 
 * 버블 정렬 알고리즘과 유사
 * 인접한 두 원소를 검사하며 정렬한다.
 * 오름차순으로 정렬되어 있지 않다면 두 원소를 교환한다.
 * 학생들이 밀려나는 횟수가 1 증가한다.
 * 1번 반복 후 키가 가장 큰 아이가 맨 뒤로 이동한다.
 * @author semin.kim
 */

public class BOJ_10431_줄세우기_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T; // 테스트 케이스의 개수
	static final int STUDENT_COUNT = 20; // 아이들의 수는 20으로 고정
	static int[] students; // 학생들의 키를 저장할 1차원 배열
	static int count; // 학생이 밀려나는 횟수
	
	private static void Sort() {
		// 앞에서부터 한 명을 뽑는다.
		for(int idx = 0; idx < STUDENT_COUNT; idx++) {
			// 그 다음 학생과 비교
			for(int compareIdx = idx + 1; compareIdx < STUDENT_COUNT; compareIdx++) {
				// 자기 앞에 자기보다 키가 큰 학생이 있는 경우...
				if(students[idx] > students[compareIdx]) {
					Swap(idx, compareIdx); // 위치를 바꾼다.
					count++; // 학생이 밀려나는 횟수 1 증가
				}
			}
		}
		sb.append(count);
	}
	
	private static void Swap(int first, int second) {
		int temp = students[first];
		students[first] = students[second];
		students[second] = temp;
				
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		
		students = new int[STUDENT_COUNT];
		
		for(int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			count = 0; // 밀려나는 횟수 초기화(모든 테스트케이스는 독립적)
			testCase = Integer.parseInt(st.nextToken());
			// 아이들의 키 입력
			for(int idx = 0; idx < STUDENT_COUNT; idx++) {
				students[idx] = Integer.parseInt(st.nextToken());
			}
			sb.append(testCase).append(" ");
			Sort();
			System.out.println(sb);
		}
	}
}
