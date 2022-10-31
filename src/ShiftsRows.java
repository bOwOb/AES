
public class ShiftsRows {
	int[][] ShiftsRows(int x[][], boolean inverse){
		int temp = 0;

		int[][] y = new int[x.length][x[0].length];
		y[0] = x[0];
		for(int i = 1; i<x.length; i++) {
			
			for(int j = 0; j<x[i].length;j++) {	
				
				temp= (j+x[i].length-i)%x[i].length;
				if(inverse) {
					y[i][j] = x[i][temp]; 
				}
				else {
					y[i][temp] = x[i][j];  
				}
				
			}
		}
		return y;
	}
}
