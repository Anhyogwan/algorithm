package com.ssafy.recur.swea.sol3289;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289_서로소집합 {
	static int[] p;
	static int N, M;

	public static void make() {
		p = new int[N+1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	public static int find(int a) {
		if(p[a]==a) return a;
		return p[a]=find(p[a]);
	}

	public static void union(int a, int b) {
		a= find(a);
		b= find(b);
		if (a==b) return;
		p[a]=b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			make();
			System.out.print("#" + tc + " ");
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int con = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (con == 0) {
					union(a, b);
					continue;
				}
				if (con == 1) {
					if (find(a)==find(b)) System.out.print(1);
					else System.out.print(0);
				}
				
			}
			System.out.println();
		}

	}
}
