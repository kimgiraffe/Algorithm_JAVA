import java.util.Scanner;

class BOJ_10822 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		String[] lines = sc.next().split(",");
		
		int total = 0;
		for(String Item : lines) {
			total += Integer.parseInt(Item);
		}
		System.out.println(total);
		
		sc.close();
	}
}