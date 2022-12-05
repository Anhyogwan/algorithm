package com.ssafy.recur.BOJ.sol16236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
	int x;
	int y;
	int size;
	int size_cnt = 0;

	public Shark(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
}

class Fish {
	int x;
	int y;
	int size;
	int cnt = 0;

	public Fish(int x, int y, int size, int cnt) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.cnt = cnt;
	}
}

public class BOJ_16236_아기상어 {
	// 물고기 M마리와 아기상어 1마리 존재
	// 한칸에는 최대 물고기 한 마리
	// 처음 아기상어 크기 2 -> 아기상어 사방탐색
	// 크기가 작으면 섭취,이동 가능 , 같으면 불가능하지만 이동 가능 , 크면 섭취,이동 불가능
	// 먹을 수 있는 물고기 1마리면 그 물고기 먹으러 감
	// 여러마리면 가장 가까운 물고기 먹으러 감
	// 거리가 같은 물고기가 만다면 가장위쪽 물고기 , 위쪽물고기도 많다면 가장 왼쪽 물고기
	// 물고기를 먹은 마릿수가 자신의 크기와 같아지면 크기 1 증가
	// 아기상어가 더 이상 맵에 먹을 수 있는 물고기(아기상어보다 크기가 작은 or 물고기가 한마리도 없는 경우)가 없으면 리턴
	// 탐색을 N,N 부터 역으로 탐색하면 가장 위쪽의 왼쪽에 있는 물고기 찾는게 가능할듯
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] map;
	static int N;
	static Shark s;
	static ArrayList<Fish> fish_list;
	static int alive_time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[N][N];
		fish_list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 9) {
					s = new Shark(i, j, 2);
				}
				if (num != 9 && num != 0) {
					fish_list.add(new Fish(i, j, num, 0));
				}
				map[i][j] = num;
			}
		}
		Collections.sort(fish_list, (o1, o2) -> o1.x == o2.x ? o2.y - o1.y : o2.x - o1.x);
		move();
		System.out.println(alive_time);
	}

	static void move() {
		Fish target_fish = find_fish();
		if (target_fish != null) {
			eat(target_fish);
		} else {
			return;
		}
		move();
	}

	// 상어와 물고기의 거리 구하는 방법
	// 중간에 크기가 큰 물고기가 가로막고 있을때를 고려해야함
	// 각 물고기에 대하여 bfs 돌리기
	static Fish find_fish() {
		Fish fish = null;
		int min_dist = Integer.MAX_VALUE-1;
		int distance = Integer.MAX_VALUE;
		for (int i = 0; i < fish_list.size(); i++) {
			if (fish_list.get(i).size < s.size) {
				fish_list.get(i).cnt=0;
				distance = bfs(fish_list.get(i));
				if (min_dist >= distance) {
					min_dist = distance;
					fish = fish_list.get(i);
					fish.cnt = distance;
				}
			}

		}
		return fish;//!=null ? fish : null;
	}

	// 물고기를 bfs를 돌려서 경로 탐색
	// 경로를 탐색 할 때 범위 밖이거나 자신보다 큰 물고기가 있을 경우엔 못가게
	static int bfs(Fish fish) {
		Queue<Fish> queue = new LinkedList<>();
		queue.offer(fish);
		boolean[][] visited = new boolean[N][N];
		int min_distance = Integer.MAX_VALUE;
		visited[fish.x][fish.y]=true;
		while (!queue.isEmpty()) {
			Fish f = queue.poll();
			if (map[f.x][f.y] == 9) {
				min_distance = Math.min(min_distance, f.cnt);
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = f.x + dx[i];
				int ny = f.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || (map[nx][ny]!=9 &&(s.size < map[nx][ny])))
					continue;
				visited[nx][ny] = true;
				queue.offer(new Fish(nx, ny, f.size, f.cnt + 1));
			}
		}
		return min_distance;
	}

	static void eat(Fish fish) {
		alive_time += fish.cnt;
		map[s.x][s.y]=0;
		s.x = fish.x;
		s.y = fish.y;
		map[fish.x][fish.y]=9;
		fish_list.remove(fish);
		s.size_cnt++;
		if (s.size == s.size_cnt) {
			s.size++;
			s.size_cnt = 0;
		}
	}
}