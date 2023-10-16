package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_1202_보석도둑
 * 
 * [문제]
 * 상덕이가 털 보석점에는 보석이 총 N개 있다. 각 보석은 무게 Mi와 가격 Vi를 가지고 있다. 
 * 상덕이는 가방을 K개 가지고 있고, 각 가방에 담을 수 있는 최대 무게는 Ci이다. 
 * 가방에는 최대 한 개의 보석만 넣을 수 있다.
 * 상덕이가 훔칠 수 있는 보석의 최대 가격을 구하는 프로그램을 작성하시오.
 * 
 * [풀이]
 * 1. 보석과 가방에 담을 수 있는 무게를 무게 기준 오름차순 정렬
 * 2. 담을 수 있는 무게가 가장 작은 가방부터 탐색하여 넣을 수 있는 보석의 가격을 우선순위 큐에 삽입
 * 3. 넣을 수 있는 보석 중 가장 비싼 보석의 가격을 꺼내 더하기
 * 4. 훔칠 수 있는 보석 가격의 합의 최댓값 출력
 * 
 * @author semin.kim
 */

public class BOJ_1202_보석도둑_김세민 {

	static BufferedReader br;
	static StringTokenizer st;
	
	static int jewelryCount, bagCount; // 보석의 개수, 가방의 개수
	static Jewelry[] jewelries;
	static int[] maxWeight;
	static long sum; // 보석 가격이 합의 최댓값
	
	// 보석 가격 기준 비싼 보석부터 나오도록 하는 우선순위 큐
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	static class Jewelry implements Comparable<Jewelry>{
		int weight;
		int value;
		
		public Jewelry(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Jewelry [weight=" + weight + ", value=" + value + "]";
		}

		@Override
		public int compareTo(Jewelry o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	private static void steal() {
		int jewelryIdx = 0;
		
		// 담을 수 있는 무게가 가장 작은 가방부터 탐색
		for(int idx = 0; idx < bagCount; idx++) {
			while(jewelryIdx < jewelryCount && maxWeight[idx] >= jewelries[jewelryIdx].weight) {
				pq.offer(jewelries[jewelryIdx].value); // 가방에 넣을 수 있는 보석의 가격을 우선순위 큐에 삽입
				jewelryIdx++;
			}
			// 넣을 수 있는 보석 중 가장 비싼 보석을 꺼내 더하기
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		
		System.out.println(sum);
	}
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine().trim());
		
		jewelryCount = Integer.parseInt(st.nextToken());
		bagCount = Integer.parseInt(st.nextToken());
		
		jewelries = new Jewelry[jewelryCount];
		maxWeight = new int[bagCount];
		
		for(int idx = 0; idx < jewelryCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			jewelries[idx] = new Jewelry(weight, value);
		}
		
		for(int idx = 0; idx < bagCount; idx++) {
			maxWeight[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.sort(jewelries); // 보석을 무게 기준 오름차순 정렬
		Arrays.sort(maxWeight); // 가방을 무게 기준 오름차순 정렬
		
		steal();
	}
}
