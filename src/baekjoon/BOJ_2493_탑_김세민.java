package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


/**
 * BOJ_2493_탑
 * @author semin.kim
 * 1. 탑의 수를 나타내는 정수를 입력받는다. (1이상 500,000이하)
 * 2. 탑들의 높이를 입력받는다. (1이상 100,000,000이하)
 * 3. 입력받는 높이가 스택의 top에 있는 탑의 높이보다 작을 때까지 스택을 검색한다.
 * 4. 수신 탑이 존재하는 경우 해당 탑의 번호를 출력하고
 * 5. 존재하지 않는 경우 0을 출력한다.
 */

public class BOJ_2493_탑_김세민 {

	static BufferedReader br; //입력
	static StringTokenizer st;
	static StringBuilder sb; //출력

	static int tower_cnt; //탑의 수
	static Stack<pair> towerStack = new Stack<pair>();
	
	static class pair {
		int height;
		int index;
		
		pair(int height, int index){
			this.height = height;
			this.index = index;
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		tower_cnt = Integer.parseInt(br.readLine().trim()); //탑들의 개수 입력

		st = new StringTokenizer(br.readLine().trim());
		sb = new StringBuilder();
		for(int towerIdx = 1; towerIdx <= tower_cnt; towerIdx++) {
			int height = Integer.parseInt(st.nextToken()); //탑의 높이 입력
			while(!towerStack.isEmpty()) {
				if(height < towerStack.peek().height) { //수신 탑이 존재하는 경우...
					sb.append(towerStack.peek().index).append(" "); //수신 탑의 번호를 출력
					break;
				}
				towerStack.pop(); //다음 탑으로 이동하여 수신 탑이 존재할 때까지 검색
			}
			// 수신 탑이 존재하지 않는 경우...
			if(towerStack.isEmpty()) {
				sb.append(0).append(" "); //0을 출력
			}
			//현재 높이와 번호를 스택에 push
			towerStack.push(new pair(height, towerIdx));
		}

		System.out.println(sb);
	}
}
