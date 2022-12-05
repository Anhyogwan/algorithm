package com.ssafy.recur.swea.sol7465;

import java.util.Scanner;

public class Solution {
	static int[] p;
	static int N, M;

	public static void make() {
		p = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
	}

	public static int find(int i) {
		if (p[i] == i)
			return i;
		return p[i] = find(p[i]);
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b)
			return;
		p[b] = a;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			make();
			for (int i = 0; i < M; i++) {
				union(sc.nextInt(), sc.nextInt());
			}
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (p[i] == i)
					cnt++;
			}
			System.out.println("#" + tc + " " + cnt);
		}
	}
}
