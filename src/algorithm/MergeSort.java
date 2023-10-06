package algorithm;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
	
	static StringBuilder sb;
	static final int NUMBER_COUNT = 100; // 배열의 크기는 100으로 고정
	static final int MAX_NUM = 10000; // 숫자의 최댓값은 10000으로 고정
	static int[] numberList = new int[NUMBER_COUNT]; // 숫자들을 저장할 1차원 배열
	
	/**
	 * 1 이상 10000이하의 난수 생성하여 배열에 저장
	 */
	private static void generateRandomNumbers() {
		for(int idx = 0; idx < NUMBER_COUNT; idx++) {
			numberList[idx] = (int)(Math.random()*MAX_NUM) + 1;
		}
	}

	private static void sort(int start, int end) {
		if(start == end) { // start와 end가 동일 -> 배열의 크기가 1이다. 더 이상 할 게 없음
			return;
		}
		
		// start와 end의 중간 지점
		int mid = (start + end) / 2;
		
		// start부터 mid까지 sort 재귀 호출
		sort(start, mid); 

		// mid + 1부터 end까지 sort 재귀 호출
		sort(mid + 1, end);
		
		// 정렬된 sub-array 병합
		merge(start, mid, end);
	}
	
	private static void merge(int start, int mid, int end) {
		int i = start; // 첫 번째 배열의 시작 인덱스
		int j = mid + 1;	// 두 번째 배열의 시작 인덱스
		
		// 정렬된 숫자들을 담을 list
		List<Integer> list = new ArrayList<>();
		
		// 두 sub-array 순회
		while(i <= mid && j <= end) { 
			if(numberList[i] <= numberList[j]) { // 첫 번째 sub-array의 i index 값이 두번째 sub-array의 j index 보다 작거나 같은 경우...
				list.add(numberList[i++]);
			}
			else { // // 첫 번째 sub-array의 i index 값이 두번째 sub-array의 j index 보다 큰 경우...
				list.add(numberList[j++]);
			}
		}
		
		// 첫번째 sub-array의 남은 값들을 list에 담기
		while(i <= mid) { 
			list.add(numberList[i++]);
		}
		
		// 두 번째 sub-array의 남은 값들을 list에 담기
		while(j <= end) {
			list.add(numberList[j++]);
		}
		
		// list에 정렬된 숫자들을 다시 numberList로 복사
		i = start; j = 0;
		while(i <= end) {
			numberList[i++] = list.get(j++);
		}
	}

	/**
	 * 숫자를 10개씩 출력하는 메서드
	 */
	private static void printNumberList() {
		for(int idx = 0; idx < NUMBER_COUNT; idx++) {
			sb.append(numberList[idx]).append(" ");
			if(idx % 10 == 9) {
				sb.append("\n");
			}
		}
	}

	public static void main(String[] args) {
		sb = new StringBuilder();
		generateRandomNumbers(); // 1이상 10000이하의 난수 100개 생성

		sb.append("THIS IS MERGE SORT!!!\n");

		sb.append("--------------------BEFORE SORT--------------------\n");
		printNumberList();

		sort(0, NUMBER_COUNT - 1);

		sb.append("--------------------AFTER SORT--------------------\n");
		printNumberList();

		System.out.print(sb);
	}
}
