import java.lang.reflect.Method;
import java.util.Random;


/*
 * author Huber Flores
 */


public class ReflectionTest {


	public int[] localbubbleSort(int randValue, int sizeArray){
		int[] num = new int[sizeArray];
        Random r = new Random();
        for (int i = 0; i < num.length; i++) {
                num[i] = r.nextInt(randValue);
        } 
        int j;
        boolean flag = true; 
        int temp; 

        while (flag) {
                flag = false; 
                for (j = 0; j < num.length - 1; j++) {
                        if (num[j] < num[j + 1]) 
                        {
                                temp = num[j]; 
                                num[j] = num[j + 1];
                                num[j + 1] = temp;
                                flag = true; 
                        }
                }
        }

       return num;
	}


	public int [] bubbleSort(int randValue, int sizeArray) 
	{		
	Method toExecute; 
	Class<?>[] paramTypes = {int.class, int.class};
	Object[] paramValues = {randValue, sizeArray}; 
	int [] result = null; 

	try{

		String className = this.getClass().getName();
		Class cls = Class.forName(className);

		toExecute = cls.getDeclaredMethod("localbubbleSort", paramTypes);


		result = (int[]) toExecute.invoke(this, paramValues);


       	if(result != null){
       		System.out.println("Method was executed");

		}else{		
			System.out.println("Method was not executed");
			result = localbubbleSort(randValue, sizeArray);


		}


	}  catch (SecurityException se){
	} catch (NoSuchMethodException ns){
	}catch (Throwable th){
	}


	System.out.println(result);

	return result;
	}


	public static void main(String[] args) {
		ReflectionTest test = new ReflectionTest();
		test.bubbleSort(1000000, 8989);
	}
}