package swea;
class SWEA_17119_2차원배열출력하기_김세민 {
	public static void main(String[] args) throws Exception {
		for(int i = 1; i <= 100; i++) {
			System.out.print(i + " ");
			if(i % 10 == 0) {
				System.out.println("");
			}
		}
	}
}