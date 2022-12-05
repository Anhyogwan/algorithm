package com.ssafy.recur.BOJ.sol1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int to, weight;
		Node next;

		public Node(int to , int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
		}
		
	}
	
	static class Vertex {
		int no, weight;

		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}
	
	static int V, E, start;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine()) - 1;
		
		Node[] nodeList = new Node[V];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			
			nodeList[from] = new Node(to, weight, nodeList[from]);
		}
		
		// start -> end 로의 최단경로
		// 출발정점 start
		int end = V-1; // 도착정점
		
		int[] D = new int[V];  // 출발지에서 자신으로 오는데 소요되는 최소비용
		boolean[] visited = new boolean[V];  // 처리한 정점 여부
		
		Arrays.fill(D, Integer.MAX_VALUE);
		// 출발 정점 처리
		D[start] = 0;
		
		PriorityQueue<Vertex> qu = new PriorityQueue<>( (o1, o2) -> o1.weight - o2.weight);
		qu.offer(new Vertex(start, D[start]));
		
		while (!qu.isEmpty()) {
			
			Vertex v = qu.poll();
			visited[v.no] = true;
			
			for (Node temp = nodeList[v.no]; temp != null; temp = temp.next) {
				if(!visited[temp.to] && D[temp.to] > D[v.no] + temp.weight) {
					D[temp.to] = D[v.no] + temp.weight;
					qu.offer(new Vertex(temp.to, D[temp.to]));
 				}
			}
		}
		
		for(int d : D) {			
			System.out.println(d == Integer.MAX_VALUE ? "INF" : d);
		}
	}

}
