package benjiecheng;

public class Clumsy {
    public int Clumsys(int N) {
		int operator = N-1;
		return Factorial (N,operator);
	}
	public int Factorial(int N , int operator){
		int x = operator % 4;
		int storage = N;
		int  result = 0;
		for(int i = N; i>0; i--){
			switch (x){
				case 1:
					storage = storage*(N-1);
					break;
				case 0:
					storage = (int)Math.floor(storage/(N-1));
					break;
				case 3:
					result = result + storage +N-1;
					storage =0;
					break;
				case 2:
					storage = -(N-1);
					break;
				default:
					break;  

			}
			x = (operator-1)% 4;
            N--;
		}
		result =result+storage;
		return result;
	}
}
