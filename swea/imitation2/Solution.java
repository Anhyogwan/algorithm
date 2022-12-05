package com.ssafy.recur.swea.imitation2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	// 순서는 상관이 없음 확인시켜주는것도 상관이 없음 확인할 필요도 없음?
	// 헌터는 1시간에 한칸씩 4방탐색
	// 몬스터가 있는곳으로 가야함
	// 몬스터 처리 확인을 위해 고객 집으로 가면 됨
	// 모든 몬스터를 없에고 고객들에게 확인시켜 작업을 완료하는데 가장 빠른 시간
	// 몬스터와 고객집의 거리값
	// 헌터와 몬스터의 최단거리값?
	// 우선 최단 경로탐색인것으로 보아 bfs 문제인거같음
	// 각 몬스터에서부터 고객의 집까지의 최단거리를 구해야할듯
	// 가장 심플한 방법 -> 몬스터의 숫자만큼 순열을 돌려서 각자 bfs로 최단경로를 찾은 다음 출력..인데..
	static class Hunter {
		int x, y, cnt;
		public Hunter(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static class Monster {
		int num, x, y;
		public Monster(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static class Customer {
		int num, x, y;
		public Customer(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	static int N;
	static int[][] map;
	static ArrayList<Monster> mon;
	static int[] mhdist;
	static Hunter hunter;
	static ArrayList<Customer> cus;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			hunter = new Hunter(0, 0, 0);
			mon = new ArrayList<>();
			cus = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num > 0) {
						mon.add(new Monster(num, i, j));
					}
					if (num < 0) {
						cus.add(new Customer(num * -1, i, j));
					}
					map[i][j] = num;
				}
			}
			Collections.sort(mon, (o1, o2) -> o1.num - o2.num);
			Collections.sort(cus, (o1, o2) -> o1.num - o2.num);
			min_value = Integer.MAX_VALUE;
			//int[] hunt = new int[mon.size()];
			for (int i = 0; i < mon.size(); i++) {
				if (mon.get(i).num < 0)
					continue;
				ArrayList<Monster> mon2 = new ArrayList<>();
				mon2.addAll(mon);
				mon2.add(new Monster(cus.get(i).num, cus.get(i).x, cus.get(i).y));
				perm(mon2, 1, 0 | 1 << i, Math.abs(0 - mon.get(i).x) + Math.abs(0 - mon.get(i).y), mon.get(i).x,
						mon.get(i).y);
			}
			System.out.println("#" + tc + " " + min_value);
		}
	}

	// 거리값(장애물이 없는경우) -> |x1-x2| + |y1-y2|
	// 몬스터 마다 시작 위치를 계산
	// 몬스터가 잡히면 가능한 경우의 수 하나 증가
	// 시작하는 몬스터
	//
	static int min_value;

	private static void perm(ArrayList<Monster> mon3, int idx, int flag, int total, int x, int y) {
		if (idx == mon3.size()) {
			min_value = Math.min(min_value, total);
			return;
		}
		for (int i = 0; i < mon3.size(); i++) {
			if ((flag & 1 << i) != 0)
				continue;
			ArrayList<Monster> mon2 = new ArrayList<>();
			mon2.addAll(mon3);
			if (i < mon.size()) {
				mon2.add(new Monster(cus.get(i).num, cus.get(i).x, cus.get(i).y));
			}
			perm(mon2, idx + 1, flag | 1 << i, total + (Math.abs(x - mon3.get(i).x) + Math.abs(y - mon3.get(i).y)),
					mon3.get(i).x, mon3.get(i).y);
		}
	}
}
