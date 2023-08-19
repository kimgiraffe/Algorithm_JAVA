package swea;

import java.util.Scanner;

/**
 * SWEA_1208_Flatten_D3
 * 1. 반복문을 통해 최고 높이와 최저 높이를 갱신한다.
 * 2. dump 횟수가 주어진 덤프 횟수를 도달하면
 * 3. 최종적으로 최고 높이와 최저 높이의 차를 반환한다.
 * @author semin.kim
 */

public class SWEA_1208_Flatten_김세민 {

	static Scanner sc;
	static final int T = 10;
	static final int NumOfBoxes = 100;
	static int limit;
	static int[] boxes;
	
	/**
	 * flattenBoxes method 구현
	 * 1. 반복문을 통해 입력으로 주어진 덤프 횟수만큼 반복하여
	 * 2. box 배열을 순회하여 최고 높이와 최저 높이를 갱신한다.
	 * 3. 최고 높이와 최저 높이가 1 이하이면 작업을 중단하고
	 * 4. 최종적으로 최고 높이와 최저 높이의 차를 반환한다.
	 * @return 최고 높이와 최저 높이의 차
	 */
	public static int flattenBoxes() {
		for(int dump = 0; dump < limit; dump++) {
			int maxIndex = 0, minIndex = 0;
			int maxHeight = 0, minHeight = 101;
			for(int box = 0; box < NumOfBoxes; box++) {
				if(boxes[box] > maxHeight) {
					maxHeight = boxes[box];
					maxIndex = box;
				}
				if(boxes[box] < minHeight) {
					minHeight = boxes[box];
					minIndex = box;
				}
			}
			
			// 최고 높이와 최저 높이의 차가 1 이하이면 덤프 작업 중단
			if(maxHeight - minHeight <= 1) break;
			
			// 최고 높이는 1 감소, 최저 높이는 1 증가
			boxes[maxIndex]--;
			boxes[minIndex]++;
		}
		
		// 최종적으로, 최고 높이와 최저 높이의 차 반환
		return max(boxes) - min(boxes);
	}
	
	/**
	 * max method 구현
	 * arr 배열을 순회하며 최댓값을 갱신한다.
	 * @param arr
	 * @return
	 */
	public static int max(int[] arr) {
		int max = Integer.MIN_VALUE;
		for(int value: arr) {
			if(value > max) max = value;
		}
		return max;
	}
	
	/**
	 * min method 구현
	 * arr 배열을 순회하며 최솟값을 갱신한다.
	 * @param arr
	 * @return
	 */
	public static int min(int[] arr) {
		int min = Integer.MAX_VALUE;
		
		for(int value: arr) {
			if(value < min) min = value;
		}
		
		return min;
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		
		for(int test_case = 1; test_case <= T; test_case++) {
			limit = sc.nextInt(); //덤프 횟수 입력
			boxes = new int[NumOfBoxes];
			for(int boxIdx = 0; boxIdx < NumOfBoxes; boxIdx++) {
				boxes[boxIdx] = sc.nextInt();
			}
			
			System.out.println("#" + test_case + " " + flattenBoxes());
		}
		
		sc.close();
	}

}
