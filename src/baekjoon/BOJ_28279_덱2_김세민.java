package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_28279_덱2_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int queryCount; // 명령의 개수
	static int X;
	static Deque<Integer> deque = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		queryCount = Integer.parseInt(br.readLine().trim());
		
		
		for(int query = 0; query < queryCount; query++) {
			st = new StringTokenizer(br.readLine().trim());
			int Q = Integer.parseInt(st.nextToken());
			switch (Q) {
			case 1:
				X = Integer.parseInt(st.nextToken());
				deque.addFirst(X);
				break;
			case 2:
				X = Integer.parseInt(st.nextToken());
				deque.addLast(X);
				break;
			case 3:
				if(!deque.isEmpty()) {
					sb.append(deque.pollFirst()).append("\n");
				}
				else {
					sb.append(-1).append("\n");
				}
				break;
			case 4:
				if(!deque.isEmpty()) {
					sb.append(deque.pollLast()).append("\n");
				}
				else {
					sb.append(-1).append("\n");
				}
				break;
			case 5:
				sb.append(deque.size()).append("\n");
				break;
			case 6:
				if(deque.isEmpty()) {
					sb.append(1).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
				break;
			case 7:
				if(!deque.isEmpty()) {
					sb.append(deque.peekFirst()).append("\n");
				}
				else {
					sb.append(-1).append("\n");
				}
				break;
			case 8:
				if(!deque.isEmpty()) {
					sb.append(deque.peekLast()).append("\n");
				}
				else {
					sb.append(-1).append("\n");
				}
				break;
			default:
				break;
			}
		}
		
		System.out.print(sb);
	}
}
