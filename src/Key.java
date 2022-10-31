
public class Key {
	

	int[][] Addround(int m[][], int key[][]){
		for(int i =0; i<m.length; i++) {
			for(int j=0; j<m[0].length; j++) {
				m[i][j] ^= key[i][j];
			}
		}
		
		return m;
	}
	int[][] schedule(int key[][],int r) {
		int[][] k = new int[4][4];
		k = key;
		int[] rcon = {1,0,0,0};
		rcon[0] = makercon(r);
		SubByte sb = new SubByte();
		int[] t = new int[4];
//		System.out.println(rcon[0]);
		for(int i = 0; i<t.length; i++) {
			t[i] = k[(i+1)%4][k.length-1]; 		//로테이트
			t[i] = sb.Gfinverse(t[i]);
			t[i] = sb.afmapping(t[i],false);
		}
		for(int i = 0; i<t.length; i++) {
			k[i][0] = k[i][0] ^ t[i] ^ rcon[i];	

			for(int j = 1; j<t.length; j++) {				
				k[i][j] = k[i][j-1] ^ k[i][j];
			}
			//System.out.println();
			
		}
	
		return k;
	}
	int[][] invschedule(int key[][],int r) {
		int[] rcon = {1,0,0,0};
		rcon[0] = makercon(r);
		SubByte sb = new SubByte();
		int[] t = new int[4];
//		t = key[3];
		//		System.out.println(rcon[0]);
		for(int i = t.length-1; i>=0; i--) {
 
//			key[i][0] = key[i][0] ^ t[i] ^ rcon[i];	

			for(int j = t.length-1; j>0; j--) {
				
				key[i][j] = key[i][j-1] ^ key[i][j];
			}
			//System.out.println();
			
		}
		for(int i = 0; i<t.length; i++) {

			t[i] = key[(i+1)%4][key.length-1]; 		//로테이트

			t[i] = sb.Gfinverse(t[i]);

			key[i][0] ^= sb.afmapping(t[i],false) ^ rcon[i];


		}
		
		
		return key;
	}
	int [][] copymat(int key[][]){
		int t[][] = new int[4][4];
		for(int i =0; i<t.length;i++) {
			for(int j = 0; j<t[i].length;j++) {
				t[i][j] = key[i][j];
			}
		}
		return t;
	}
	int makercon(int r) {
		int x = 1;
		for(int i =0; i<r; i++) {
			x<<=1;
			if(x>255) {
				x^=283;
			}
		}
		return x;
	}
}
