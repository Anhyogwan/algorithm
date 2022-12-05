package com.ssafy.recur.BOJ.sol1197;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainKruskal {
	static class Edge{
		int to;
		int from;
		int weight;
		public Edge(int to,int from,int weight) {
			this.to=to;
			this.from =from;
			this.weight=weight;
		}
	}
	static int[] p;
	static int V,E;
	static Edge[] edgeList;
	public static void make() {
		p = new int[V];
		for (int i = 0; i <V ; i++) {
			p[i]=i;
		}
	}
	public static int find(int a) {
		if (p[a]==a) return a;
		return p[a]=find(p[a]);
	}
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		p[aRoot]=bRoot;
		return true;
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edgeList = new Edge[E];
		for (int i = 0; i <E ; i ++) {
			st= new StringTokenizer(br.readLine());
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
		}
		make();
		Arrays.sort(edgeList,(o1,o2) -> Integer.compare(o1.weight ,o2.weight));
		//정점수 -1개만큼 탐색을 한 후 break;
		int cnt =0;
		// 그떄의 가중치의 합계를 구하기 위한 변수
		int result=0;
		for (int i =0 ; i<E ; i++) {
			if (union(edgeList[i].to,edgeList[i].from)) {
				result+=edgeList[i].weight;
				if (++cnt == V-1) break;
			}
		}
		System.out.println(result);
	}
}
