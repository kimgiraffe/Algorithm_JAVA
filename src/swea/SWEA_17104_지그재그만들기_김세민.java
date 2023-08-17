package swea;
class SWEA_17104_지그재그만들기_김세민 {
	public static void main(String[] args) throws Exception {
		for(int i = 0; i <= 9; i++) {
			for(int j = 1; j <= 10; j++) {
				if(i % 2 == 0) {
					System.out.print(i * 10 + j + " ");
				}
				else {
					System.out.print(i * 10 + (10 - j + 1) + " ");
				}
			}
			System.out.println();
		}
	}
}