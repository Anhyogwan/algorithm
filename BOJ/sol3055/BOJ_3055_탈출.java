package com.ssafy.recur.BOJ.sol3055;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Animal {
	int x, y;

	public Animal(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Water {
	int x, y;

	public Water(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_3055_탈출 {
	// 물도 매 분마다 사방으로 확장
	// 물,고슴도치 둘다 돌 통과 불가
	// 고슴도치 물,돌 통과 불가
	// 물 돌,비버소굴 통과 불가
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static boolean[][] visited2;
	static Animal ani;
	static ArrayList<Water> wa ;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		wa = new ArrayList<>();
		visited = new boolean[R][C];
		visited2 = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String temp = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = temp.charAt(j);
				if (map[i][j] == 'S') {
					ani = new Animal(i, j);
				}
				if (map[i][j] == '*') {
					wa.add(new Water(i, j));
				}
			}
		}

		bfs(ani.x, ani.y);
		System.out.println(result != 0 ? result : "KAKTUS");
	}
	static int result = 0;
	static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> water = new LinkedList<>();
		for (int i = 0; i<wa.size(); i++) {
			water.offer(new int[] { wa.get(i).x, wa.get(i).y });
			visited2[wa.get(i).x][wa.get(i).y] = true;
		}
		queue.offer(new int[] { x, y });
		visited[x][y] = true;
		
		int time =-1;
		outer :while (!queue.isEmpty()) {
			time++;
			water(water);
			int size=queue.size();
			for (int i = 0 ; i < size; i++) {
				int[] temp = queue.poll();
				x = temp[0];
				y = temp[1];
				if(map[x][y]== 'D') {
					result = time;
					break outer;
				}
				for (int j = 0; j < 4; j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == '*'
							|| map[nx][ny] == 'X')
						continue;
					visited[nx][ny]=true;
					queue.offer(new int[] { nx, ny });
				}
			}
		}
	}

	private static void water(Queue<int[]> water) {
		int size =water.size();
		for (int j = 0; j <size ; j++) {
			int[] temp = water.poll();
			int x = temp[0];
			int y = temp[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >=R || ny <0 || ny >=C || map[nx][ny]== 'X' || map[nx][ny]=='D' || visited2[nx][ny]) continue;
				map[nx][ny]='*';
				visited2[nx][ny]=true;
				water.offer(new int[] {nx,ny});
			}
		}
		

	}
}
