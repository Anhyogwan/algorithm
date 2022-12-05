package com.ssafy.recur.BOJ.sol19236;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 한 칸에는 물고기 한마리가 존재
	// 번호(1~16)와 방향(8방)을 가지고있음
	// 상어는 0,0에 있는 물고기를 먹고 0,0에 들어감
	// 상어의 방향은 물고기가 보고있던 방향과 같음
	// 물고기는 턴마다 한번 움직임(작은 물고기 부터) -> 움직이려는 칸이 상어가 있거나 범위 밖이면 불가능
	// 이동할 수 있을떄까지 45도 반시계 회전, 없으면 이동안함
	// 다른 물고기가 있는 칸으로 갈때는 해당 물고기와 자리 변경
	// 상어는 여러개의 칸 이동 가능 , 방향에 있는 칸으로 이동 가능, 물고기를 먹으면 그 물고기의 방향을 가짐
	// 물고기가 없는 칸으로는 이동 불가, 이동하는 중에는 물고기를 먹지 않음,이동할 수 있는 칸이 없으면 탈출
	// 순서 -> 1.물고기 이동 2. 상어 이동
	static class Shark {
		int direc;int x;int y;
		public Shark(int direc, int x, int y) {
			this.direc = direc;
			this.x = x;
			this.y = y;
		}
	}
	static class Fish {
		int direc;int num;int x;int y;
		public Fish(int num,int direc, int x, int y) {
			this.num=num;
			this.direc = direc;
			this.x = x;
			this.y = y;
		}
	}
	// 방향 순서 1부터 상에서 반시계방향으로 즉, -1 해서 받아야함
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static Fish[][] map2 ;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map2= new Fish[4][4];
		PriorityQueue<Fish> pq = new PriorityQueue<>((o1,o2) -> o1.num - o2.num);
		for (int i = 0; i <4 ;i ++) {
			st= new StringTokenizer(br.readLine());
			for (int j =0 ; j<4 ; j++) {
				Fish f= new Fish(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,i,j);
				map2[i][j]= f;

			}
		}
		int total=map2[0][0].num;
		map2[0][0].num=99;
		Shark shark = new Shark(map2[0][0].direc,0,0);
		Fish[][] map= new Fish[4][4];
		for (int i = 0 ; i < 4 ;i ++) {
			for (int j = 0; j < 4 ; j++) {
				Fish f2=new Fish(map2[i][j].num,map2[i][j].direc,map2[i][j].x,map2[i][j].y);
				map[i][j]=f2;
				if(i==0 && j == 0) continue;
				pq.offer(f2);
			}
		}
		moveShark(pq,map,shark,total);
		System.out.println(result);
	}
	
	private static void moveFish(PriorityQueue<Fish> pq,Fish[][] map) {
		while(!pq.isEmpty()) {
			Fish f = pq.poll();
			int cnt=0;
			while(cnt!=7) {
				int nx = f.x + dx[f.direc];
				int ny = f.y + dy[f.direc];
				if ( nx < 0 || nx >=4 || ny < 0 || ny >=4 || map[nx][ny].num ==99) {
					f.direc= (f.direc + 1)%8;
					cnt++;
					continue;
				}
				int temp3=f.x;
				int temp4=f.y;
				map[temp3][temp4].x=nx;
				map[temp3][temp4].y=ny;
				map[nx][ny].x=temp3;
				map[nx][ny].y=temp4;
				Fish temp = map[nx][ny];
				map[nx][ny]= map[temp3][temp4];
				map[temp3][temp4]= temp;
				break;
			}
		}
	}
	static int result=0;
	private static void moveShark(PriorityQueue<Fish> pq,Fish[][] map,Shark shark,int total) {
		//System.out.println(total);
		result= Math.max(total,result);
		moveFish(pq,map);
		map[shark.x][shark.y].num=0;
//		for (int i = 0 ; i< 4 ; i++) {
//			for (int j = 0; j <4  ; j++) {
//				System.out.print(map[i][j].num + "/" + map[i][j].direc + " " );
//			}
//			System.out.println();
//		}
		int cnt=1;
		while(true) {
			int nx = shark.x+(dx[shark.direc] *cnt);
			int ny = shark.y+(dy[shark.direc] *cnt);
			//System.out.println(shark.x + " " + shark.y);
			if (nx <0 || ny < 0 || nx >=4 || ny >= 4) break;
			if (map[nx][ny].num == 0) {
				cnt++;
				continue;
			}
			Fish[][] map2= new Fish[4][4];
			for (int i = 0 ; i < 4 ;i ++) {
				for (int j = 0; j < 4 ; j++) {
					map2[i][j]=new Fish(map[i][j].num,map[i][j].direc,map[i][j].x,map[i][j].y);
				}
			}
			map2[nx][ny].num=99;
			Shark shark2=new Shark(map2[nx][ny].direc,nx,ny);
			PriorityQueue<Fish> pq2 = new PriorityQueue<>((o1,o2) -> o1.num-o2.num);
			for (int i = 0; i <4 ;i ++) {
				for (int j =0 ; j<4 ; j++) {
					if (map2[i][j].num>0  && map2[i][j].num<17)
					pq2.offer(map2[i][j]);
				}
			}
			moveShark(pq2,map2,shark2,total+map[nx][ny].num);
			cnt++;
		}
	}
}
