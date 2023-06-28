import java.util.Scanner;

public class BOJ_10988 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		String[] str = sc.next().split("");
		boolean flag = true;
		
		for(int i = 0; i < str.length / 2; i++) {
			if(str[i].charAt(0) != str[str.length - i - 1].charAt(0)) {
				flag = false;
				break;
			}
			
		}
		if(flag) {
			System.out.println(1);
		}
		else {
			System.out.println(0);
		}
		sc.close();

	}
}