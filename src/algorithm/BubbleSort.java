package algorithm;

public class BubbleSort {

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
	
	private static void sort() {
		for(int idx = NUMBER_COUNT - 1; idx > 0; idx--) {
			for(int compareIdx = 0; compareIdx < idx; compareIdx++) {
				// 앞에 위치한 숫자가 바로 뒤 위치한 숫자보다 큰 경우...
				if(numberList[compareIdx] > numberList[compareIdx + 1]) {
					swap(compareIdx, compareIdx+1); // 두 숫자 위치 swap
				}
			}
		}
	}
	
	/**
	 * numberList[a]와 numberList[b]를 swap하는 메서드
	 * @param a
	 * @param b
	 */
	private static void swap(int a, int b) {
		int temp = numberList[a];
		numberList[a] = numberList[b];
		numberList[b] = temp;
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
		
		sb.append("THIS IS BUBBLE SORT!!!\n");
		
		sb.append("--------------------BEFORE SORT--------------------\n");
		printNumberList();
		
		sort();
		
		sb.append("--------------------AFTER SORT--------------------\n");
		printNumberList();
		
		System.out.print(sb);
	}
}
