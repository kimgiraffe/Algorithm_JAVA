package algorithm;

public class InsertionSort {
	
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
		// 0번째 idx는 이미 정렬된 상태
		for(int idx = 1; idx < NUMBER_COUNT; idx++) {
			// 삽입될 수를 key에 저장
			int key = numberList[idx];
			
			int compareIdx = idx - 1;

			// 역순으로 순회하며 비교하는 인덱스가 0 이상이고 key 값보다 정렬된 배열에 있는 값이 크다면...
			while(compareIdx >= 0 && numberList[compareIdx] > key) {
				numberList[compareIdx + 1] = numberList[compareIdx]; // 오른쪽으로 이동
				compareIdx = compareIdx - 1;
			}
			// key 값 갱신
			numberList[compareIdx + 1] = key;
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
		
		sb.append("THIS IS INSERTION SORT!!!\n");
		
		sb.append("--------------------BEFORE SORT--------------------\n");
		printNumberList();
		
		sort();
		
		sb.append("--------------------AFTER SORT--------------------\n");
		printNumberList();
		
		System.out.print(sb);
	}
}
