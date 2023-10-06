package algorithm;

public class QuickSort {
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
     * 중간 pivot 정렬 메소드
     * @param left 리스트의 왼쪽
     * @param right 리스트의 오른쪽
     */
    private static void sort(int left, int right) {
    	// left가 right보다 크거나 같으면 정렬 할 원소가
    	// 1개 이하이므로 정렬하지 않고 return
    	if(left>=right) {
    		return;
    	}
    	
    	int pivot = partition(left, right);
    	
    	sort(left, pivot);	// 왼쪽 부분 정렬
    	sort(pivot+1, right);	// 오른쪽 부분 정렬
    }
    
    /**
     * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
     * 
     * @param left 리스트 왼쪽
     * @param right 리스트 오른쪽
     * @return 최종적으로 위치한 pivot의 위치를 반환
     */
    private static int partition(int left, int right) {
    	int lIdx = left - 1;	// 리스트의 왼쪽에서 1 이전 위치에서 시작
    	int rIdx = right + 1;	// 리스트의 오른쪽에서 1 이후 위치에서 시작
    	int pivot = numberList[(left+right) / 2];	// 리스트의 중간 요소를 피봇으로 설정
    	
    	while(true) {
    		
    		// lIdx 요소가 pivot보다 큰 요소를 찾을 때 까지
    		do {
    			lIdx++;
    		}while(numberList[lIdx]<pivot);
    		
    		// rIdx 요소가 pivot보다 작은 요소를 찾을 때 까지
    		// rIdx가 lIdx보다 크거나 같은 위치일 때 까지
    		do {
    			rIdx--;
    		}while(numberList[rIdx] > pivot && lIdx<=rIdx);
    		
    		
    		// rIdx가 lIdx보다 크지 않다면(엇갈린다면) swap하지 않고 rIdx 리턴
    		if(lIdx>=rIdx) {
    			return rIdx;
    		}
    		
    		// 엇갈리지 않고 교환할 두 요소를 찾았으면 swap
    		swap(lIdx,rIdx);
    	}
    }
    
    /**
     * 리스트에 두 수를 교환하는 메소드
     * @param a 현재 리스트의 왼쪽 인덱스
     * @param b 현재 리스트의 오른쪽 인덱스
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
        
        sb.append("THIS IS QUICK SORT!!!\n");
        
        sb.append("--------------------BEFORE SORT--------------------\n");
        printNumberList();
        
        sort(0, numberList.length-1);
        
        sb.append("--------------------AFTER SORT--------------------\n");
        printNumberList();
        
        System.out.print(sb);
    }
}