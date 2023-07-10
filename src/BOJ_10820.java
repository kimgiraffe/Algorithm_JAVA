import java.io.*;

class BOJ_10820 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str = br.readLine();
		
		while(str != null) {
			int Upp = 0, Low = 0, Num = 0, Space = 0;
			for(int i = 0; i < str.length(); i++) {
				if(str.charAt(i) >= 'a' & str.charAt(i) <= 'z') {
					Low++;
				}
				else if(str.charAt(i) >= 'A' & str.charAt(i) <= 'Z') {
					Upp++;
				}
				else if(str.charAt(i) >= '0' & str.charAt(i) <= '9') {
					Num++;
				}
				else if(str.charAt(i) == ' ') {
					Space++;
				}
			}
			System.out.println(Low + " " + Upp + " " + Num + " " + Space);
			str = br.readLine();
		}
		
	}
}