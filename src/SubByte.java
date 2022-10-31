import java.util.Scanner;

public class SubByte {
	Scanner s = new Scanner(System.in);
	void printstep(int x[], int y[], int t[]){
		System.out.printf("%d, %d, %s\n",x[0],x[1],Integer.toBinaryString(x[2]));
		System.out.printf("%d, %d, %d\n",y[0],y[1],y[2]);
		System.out.printf("%d, %d, %d\n",t[0],t[1],t[2]);
		System.out.println();
	//	String.format("%05d", Integer.parseInt(Integer.toBinaryString(x[2]).toString())
	}
	boolean gfnumber(int gf) {
		if(gf<=255) {
			return true;
		}
		for(int i =2; i<gf/2; i++) {
			if (gf%i==0) {
				return true;
			}
		}
		return false;
	}
	int Gfinverse(int m){
		int t[] = new int[3];
		int x[] = new int[3];
		int y[] = new int[3];
		int Q = 0;
		boolean gfcheck = true;
		//int m =0 ;
		int gf = 283;
//		while(gfcheck) {
//			//m = Integer.parseInt((s.next()), 16);
//			gf = s.nextInt();
//			gfcheck=gfnumber(gf);
//		}
		if(m ==0 ) {
			return 0;
		}
		x[0] = 1;
		y[1] = 1;
		x[2] = gf;
		y[2] = m;
		int a=0,b=0;
		while(m!=1) {			//나머지가 1이 아닐때 까지 반복
//			printstep(x,y,t);
			t[0] = x[0];
			t[1] = x[1];
			
			while(gf>=m) {				//x[2]가 y[2]보다 클경우 반복하여 나눔
				a = 0;
				b = 0;
				while(true) {			//x[2]혹은 gf의 최고차항
					if((2<<a)<=gf) {	
						a++;
					}
					else {						
						break;
					}
				}
				
				while(true) {			//y[2]의 최고차항을 x[2]와 맞춤
					Q = m<<b;					
					if(Q>=(2<<a)) {		//
						b--;
						break;
					}
					else {
						b++;
					}
				}
				gf ^= m<<b;				//최고차항을 맞추고 비교
				t[0] ^=y[0]<<b;			//나머지 0과 1도 y[2]를 시프트 한 횟수 만큼 이동
				t[1] ^=y[1]<<b;			

			}			
				x[0] = y[0];
				x[1] = y[1];
				x[2] = y[2];
				y[0] = t[0];
				y[1] = t[1];
				y[2] = t[2]= gf;
				gf = x[2];
				m = y[2];
				
		}
//		printstep(x,y,t);
		return y[1];
	}
	int afmapping(int x, boolean inverse) {
		int c= 99;
		int b = 31;//s.nextInt();			//소수값 입력
		if(inverse) {
			b = 74;
			c = 5;
		}
		
		int matrix[] = makematrix(b);		//소수값으로 매트릭스 제작
		int manum = 1;
		int afnum = 0;

		for(int i = 0; i<8; i++,manum<<=1) {
			if(x - (x^manum)==manum) {			
				afnum ^= matrix[i];
//				System.out.printf("%08d\n", Integer.parseInt(Integer.toBinaryString(matrix[i])));
			}
			
		}
//		System.out.printf("%08d\n", Integer.parseInt(Integer.toBinaryString(c)));
		afnum ^= c;
		return afnum;
	}
	int[] makematrix(int b) {
		int[] matrix = new int[8];
		matrix[0] = b;

		for(int i =1; i<8; i++) {
			b <<= 1;		
			if(b>255) {		//8비트 최대값인 255값을 넘을경우
				b -= 255;	//2의 9제곱 값인 256을 빼고 1을 더함으로써 로테이트 효과를 냄
			}
			matrix[i] = b;

		}

		return matrix;
	}
	

}

