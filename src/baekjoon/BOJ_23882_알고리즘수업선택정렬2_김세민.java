package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_23882_알고리즘수업선택정렬2
 * 
 * N(5이상 10,000이하)개의 서로 다른 양의 정수를 선택 정렬로 오름차순 정렬할 경우 K번 교환이 발생한 직후
 * 배열을 출력
 * @author semin.kim
 */

public class BOJ_23882_알고리즘수업선택정렬2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int arraySize; // 배열의 크기
	static int swapCount; // 교환 횟수
	static int[] array;

	/**
	 * array[first]와 array[second]를 교환하는 메서드
	 * @param first
	 * @param second
	 */
	private static void swap(int first, int second) {
		int tmp = array[first];
		array[first] = array[second];
		array[second] = tmp;
	}

	/**
	 * arrayy[1...last] 중 최댓값의 인덱스를 반환하는 메서드
	 * @param last
	 * @return
	 */
	private static int findMaxIdx(int last) {
		int maxIdx = 0;
		int max = 0;
		for(int idx = 1; idx <= last; idx++) {
			if(array[idx] > max) {
				maxIdx = idx;
				max = array[idx];
			}
		}
		return maxIdx;
	}

	private static void sortArray() {
		int count = 0;
		for(int last = arraySize; last >= 2; last--) {
			int idx = findMaxIdx(last);

			if(last != idx) {
				swap(last, idx);

				if(++count == swapCount) {
					for(int arrayIdx = 1; arrayIdx <= arraySize; arrayIdx++) {
						sb.append(array[arrayIdx]).append(" ");
					}
					System.out.println(sb);
					return;
				}
			}
		}
		System.out.println(-1);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();

		arraySize = Integer.parseInt(st.nextToken());
		swapCount = Integer.parseInt(st.nextToken());

		array = new int[arraySize + 1];

		st = new StringTokenizer(br.readLine().trim());

		for(int idx = 1; idx <= arraySize; idx++) {
			array[idx] = Integer.parseInt(st.nextToken());
		}

		sortArray();
	}
}
