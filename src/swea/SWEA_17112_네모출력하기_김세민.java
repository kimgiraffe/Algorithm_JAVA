package swea;

class SWEA_17112_네모출력하기_김세민 {
	public static void main(String[] args) throws Exception {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if(i != 0 && j != 0 && i != 9 && j != 9) {
					System.out.print(0 + " ");
				}
				else {
					System.out.print(1 + " ");
				}
			}
			System.out.println("");
		}
	}
}