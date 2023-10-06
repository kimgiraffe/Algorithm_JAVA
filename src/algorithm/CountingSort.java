package algorithm;

public class CountingSort {

	static StringBuilder sb;
	static final int NUMBER_COUNT = 100; // 배열의 크기는 100으로 고정
	static final int MAX_NUM = 10000; // 숫자의 최댓값은 10000으로 고정
	static int[] numberList = new int[NUMBER_COUNT]; // 숫자들을 저장할 1차원 배열
	static int[] countList = new int[MAX_NUM + 1]; // 인덱스보다 작은 숫자를 저장할 1차원 배열
	static int[] sortedList = new int[NUMBER_COUNT]; // 정렬된 숫자들을 저장할 1차원 배열
	
	/**
	 * 1 이상 10000이하의 난수 생성하여 배열에 저장
	 */
	private static void generateRandomNumbers() {
		for(int idx = 0; idx < NUMBER_COUNT; idx++) {
			numberList[idx] = (int)(Math.random()*MAX_NUM) + 1;
		}
	}
	
	private static void sort() {
		// 각 숫자가 등장하는 횟수를 countList에 저장
		for(int idx = 0; idx < NUMBER_COUNT; idx++) {
			countList[numberList[idx]]++;
		}
		
		// idx보다 작은 숫자가 등장하는 횟수를 저장
		for(int idx = 1; idx <= MAX_NUM; idx++) {
			countList[idx] += countList[idx-1];
		}
		
		// 계수리스트 로부터 제공받은 위치에 따라 numberList의 숫자를 sortedList로 복사
		for(int idx = NUMBER_COUNT - 1; idx >= 0; --idx) {
			sortedList[countList[numberList[idx]] - 1] = numberList[idx];
			countList[numberList[idx]]--;
		}
	}
	
	/**
	 * 숫자를 10개씩 출력하는 메서드
	 */
	private static void printNumberList(int[] arr) {
		for(int idx = 0; idx < NUMBER_COUNT; idx++) {
			sb.append(arr[idx]).append(" ");
			if(idx % 10 == 9) {
				sb.append("\n");
			}
		}
	}
	
	public static void main(String[] args) {
		sb = new StringBuilder();
		generateRandomNumbers(); // 1이상 10000이하의 난수 100개 생성
		
		sb.append("THIS IS COUNTING SORT!!!\n");
		
		sb.append("--------------------BEFORE SORT--------------------\n");
		printNumberList(numberList);
		
		sort();
		
		sb.append("--------------------AFTER SORT--------------------\n");
		printNumberList(sortedList);
		
		System.out.print(sb);
	}
}
