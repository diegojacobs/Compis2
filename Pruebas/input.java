 class program {
     
     int test(int numTest, int numTest2){
         return numTest%numTest2;
     }
   
    void bubbleSort(int array[]){
        int largo;
        int i;
        int j;
        int temp;
        i = 0;
        j = i+1;
        while (i<largo){
            while (j<largo-i){
                if (array[j-1]>array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
                j = j +1;
            }
            i = i+1;
        }
        
        
    }
    
    int fibonacci(int n)   // Find n-th Fibonacci number.
    {
        if ((n == 1) || (n == 2)){
          return 1;
        }  
        else {
          return fibonacci(n-1) + fibonacci(n-2);
        }
    }
    
    int gcd(int a, int b) //maximo comun divisor
    { 
        int m; 
        m = b % a;
        if (m == 0){
          return a;
        }else{
          return gcd(m, a);
        }
    }
        
    void main(){

        int num1;
        int num2;
        int res;
        int resultTest;
        int numerosDesordenados[10];
        num1 = 25;
    	
        num2 = 25;
        res = num1 + num2;
    	
        
        resultTest = test(2,4);
        bubbleSort(numerosDesordenados);

    }
  
}
