package algorithm;

public class SelectionSort {
    static StringBuilder sb;
    static final int NUMBER_COUNT = 100;
    static final int MAX_NUM = 10000;
    static int[] numberList = new int[NUMBER_COUNT];
    
    /**
     * 랜덤 숫자 만들어서 리스트에 저장하는 메소드 
     */
    private static void generateRandomNumbers() {
        for(int idx = 0; idx < NUMBER_COUNT; idx++) {
            numberList[idx] = (int)(Math.random() * MAX_NUM) + 1;
        }
    }
    
    /**
     * 정렬 메소드
     */
    private static void sort() {
        for (int idx1 = 0; idx1 < numberList.length-1; idx1++) {
        	int minNumIdx = idx1;	// 시작 인덱스를 최소 인덱스로 일단 설정하고
        	
			for (int idx2 = idx1+1; idx2 < numberList.length; idx2++) {
				if(numberList[idx2] < numberList[minNumIdx]) {	// 리스트를 순회하면서 최솟값의 인덱스를 찾는다
					minNumIdx = idx2;
				}
			}
			
			swap(idx1, minNumIdx);	// 시작 인덱스와 최솟값 인덱스를 swap
		}
    }
    
    /**
     * 리스트에 두 수를 교환하는 메소드
     * @param leftIdx 현재 리스트의 왼쪽 인덱스
     * @param rightIdx 현재 리스트의 오른쪽 인덱스
     */
    private static void swap(int leftIdx, int rightIdx) {
        int temp = numberList[leftIdx];
        numberList[leftIdx] = numberList[rightIdx];
        numberList[rightIdx] = temp;
    }
    
    /**
     * 리스트 출력 메소드
     * 한 줄에 10개씩 출력 
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
        generateRandomNumbers();
        
        sb.append("THIS IS SELECTION SORT!!!\n");
        
        sb.append("--------------------BEFORE SORT--------------------\n");
        printNumberList();
        
        sort();
        
        sb.append("--------------------AFTER SORT--------------------\n");
        printNumberList();
        
        System.out.print(sb);
    }
}