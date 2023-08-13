package swea;
class SWEA_17119 {
	public static void main(String[] args) throws Exception {
		for(int i = 1; i <= 100; i++) {
			System.out.print(i + " ");
			if(i % 10 == 0) {
				System.out.println("");
			}
		}
	}
}