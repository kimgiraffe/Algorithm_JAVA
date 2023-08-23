package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * SWEA_1238_Contact
 * 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때, 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구하는 함수를 작성하시오.
 * 
 * 비상연락망 정보를 입력받아 인접행렬 형태로 저장한다.
 * 너비우선탐색을 통해 연락이 가능할 때까지 모든 사람을 찾는다.
 * 이 때, 연락 가능한 사람들의 정보를 번호와 연락하는데 걸린 시간을 리스트에 추가한다.
 * 너비 우선 탐색이 종료되면, 리스트를 번호, 시간에 대하여 오름차순으로 정렬한다.
 * 리스트의 마지막 인덱스의 번호를 출력한다.
 * 
 * @author semin.kim
 *
 */

public class SWEA_1238_Contact_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static final int TEST_CASE_CNT = 10; // 테스트 케이스의 개수는 10으로 고정
	static final int MAX_SIZE = 100; // 연락 인원은 최대 100명으로 고정
	static int dataLength, start; // 데이터의 길이, 시작점

	static int[][] adjMatrix; // 인접행렬
	static boolean[] visited;
	
	static ArrayList<Person> contactList; // 당번이 연락할 수 있는 사람들의 정보를 담는 리스트
	
	static class Person implements Comparable<Person>{
		int idx;
		int time;
		
		public Person(int idx, int time) {
			this.idx = idx;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Person [idx=" + idx + ", time=" + time + "]";
		}


		@Override
		public int compareTo(Person o) {
			if(this.time >= o.time) {
				if(this.time == o.time) { // 연락하는데 걸리는 시간이 동일한 경우...
					return this.idx - o.idx; // 번호 순으로 오름차순 정렬
				}
				return 1;
			}
			return -1;
		}
		
	}
	
	private static void bfs() {
		Queue<Person> queue = new LinkedList<>();
		queue.offer(new Person(start, 0)); // 당번과 초기 시간 0을 큐에 삽입
		visited[start] = true; // 방문처리
		
		while(!queue.isEmpty()) {
			Person current = queue.poll();
			
			contactList.add(current); // 연락 가능한 사람들과 이 때 걸린 시간을 리스트에 추가
			
			for(int idx = 1; idx <= MAX_SIZE; idx++) {
				if(adjMatrix[current.idx][idx] == 1 && !visited[idx]) { // 다음 연락할 사람이 있고, 연락한 적이 없는 경우...
					queue.offer(new Person(idx, current.time + 1)); // 큐에 연락 가능한 사람과 걸린 시간을 삽입
					visited[idx] = true; // 방문처리
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for(int test_case = 1; test_case <= TEST_CASE_CNT; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			sb = new StringBuilder();
			dataLength = Integer.parseInt(st.nextToken()); // 입력받는 데이터의 길이 입력
			start = Integer.parseInt(st.nextToken()); // 시작점 입력

			adjMatrix = new int[MAX_SIZE + 1][MAX_SIZE + 1]; // 사람들의 번호는 1번부터 시작
			visited = new boolean[MAX_SIZE + 1];
			contactList = new ArrayList<>(); // 연락처 리스트 초기화

			st = new StringTokenizer(br.readLine().trim());
			// 연락망 입력
			for(int data = 1; data <= dataLength / 2; data++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				adjMatrix[from][to] = 1;
			}

			bfs();
			
			// 연락가능한 사람들을 오름차순으로 정렬
			Collections.sort(contactList);
			
			sb.append('#').append(test_case).append(' ').append(contactList.get(contactList.size() - 1).idx);

			System.out.println(sb);
		}

	}
}
