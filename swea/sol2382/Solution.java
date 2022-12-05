package com.ssafy.recur.swea.sol2382;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	// 걸린시간 : 1시간 15분
	
	// 미생물 군집들은 한시간마다 다음 셀로 이동
	// 미생물들이 각 사이드에 도착하면 절반으로 줄고 이동방향이 반대로 바뀜
	// 만약 미생물이 홀수일 경우 2로 나누고 소수점 이하를 버린 값 -> 1마리일경우 살아남은 미생물 수가 0
	// 만약 이동 후에 같은 셀에 군집이 두개 이상 있다면 가장 많은 쪽의 방향으로 정해진다
	// M 시간 후 남아있는 미생물 수의 총합 구하기
	// 1 상, 2 하, 3 좌 , 4 우
	static class Microbe {
		int x, y, num, direc;

		public Microbe(int x, int y, int num, int direc) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.direc = direc;
		}
	}

	//static Microbe[][] map;
	static int N, M, K; // N : 한변의 셀의 개수 , M : 격리 시간, K : 미생물 군집의 개수
	static ArrayList<Microbe> micro;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			micro = new ArrayList<>();
			//map = new Microbe[N][N];
			result = 0;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				Microbe m = new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())-1);
				micro.add(m);
				//map[m.x][m.y] = m;
			}
			move(0);
			System.out.println("#" + tc + " " + result);
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void move(int time) {
		if (time >= M) {
			for (int i = 0; i < micro.size() ; i++) {
				result+=micro.get(i).num;
			}
			return;
		}
		int[][] map3 = new int[N][N];
		Microbe[][] map2 = new Microbe[N][N];
		for (int i = 0; i < micro.size(); i++) {
			//System.out.println(micro.get(i).x + " " + micro.get(i).y);
			micro.get(i).x += dx[micro.get(i).direc];
			micro.get(i).y += dy[micro.get(i).direc];
			// 움직인 곳이 가장자리일 경우 num을 반으로 깎고 direc을 +2%4연산 해줌
			//System.out.println(nx + " "+ ny);
			if (map2[micro.get(i).x][micro.get(i).y] != null) {
				if (map3[micro.get(i).x][micro.get(i).y]>micro.get(i).num) {
					map2[micro.get(i).x][micro.get(i).y].num += micro.get(i).num;
					micro.remove(micro.get(i));
					i--;
				}else {
					map2[micro.get(i).x][micro.get(i).y].num += micro.get(i).num;
					map2[micro.get(i).x][micro.get(i).y].direc=micro.get(i).direc;
					map3[micro.get(i).x][micro.get(i).y]=micro.get(i).num;
					micro.remove(micro.get(i));
					i--;
				}
			} else {
				map2[micro.get(i).x][micro.get(i).y] = micro.get(i);
				map3[micro.get(i).x][micro.get(i).y]= micro.get(i).num;
			}
		}
		for (int i =0 ; i<micro.size();i++) {
			if(isIn(micro.get(i).x,micro.get(i).y)) {
				micro.get(i).num/=2;
				if(micro.get(i).direc == 0 || micro.get(i).direc == 1) {
					micro.get(i).direc=micro.get(i).direc == 1 ? 0 : 1;
				}
				else {					
					micro.get(i).direc=micro.get(i).direc == 2 ? 3 : 2; 
				}
			}
		}
		move(time + 1);
	}

	private static boolean isIn(int x, int y) {
		if (x<= 0 || x>=N-1 || y<=0 || y>=N-1) return true;
		return false;
	}
}
