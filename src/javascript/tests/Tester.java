package javascript.tests;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * Represents that a specific class is a tester class
 * @author Josh
 * @version 10th October, 2020
 */
class Tester 
{
	/**
	 * Tests this class
	 */
	public void test()
	{
		Class<?> clazz = this.getClass();
		
		this.log("== " + clazz.getSimpleName() + " ==");
		
		Method[] methods = clazz.getMethods();
		
		int len = methods.length;
		
		Method[] methodsToInvoke = new Method[len];
		String[] testNames = new String[len];
		
		int methodCount = 0;
		
		for (int index = 0; index < len; index++)
		{
			Method method = methods[index];
			
			if (method.isAnnotationPresent(Test.class))
			{
				methodsToInvoke[methodCount] = method;
				testNames[methodCount] = method.getAnnotation(Test.class).testName();
				methodCount++;
			}
		}
		
		methodsToInvoke = Arrays.copyOf(methodsToInvoke, methodCount);
		testNames = Arrays.copyOf(testNames, methodCount);
		
		long totalStart = this.getTimestamp();
		
		int testsPassed = 0;
		
		for (int index = 0; index < methodCount; index++)
		{
			Method method = methodsToInvoke[index];
			String testName = testNames[index];
			
			long testStart = this.getTimestamp();
			
			String failMessage = null;
			
			try 
			{
				method.invoke(this);
			} 
			catch (IllegalAccessException | IllegalArgumentException e) 
			{
				failMessage = "An error occurred whilst attempting to start the test";
			}
			catch (InvocationTargetException e)
			{
				Throwable thrownException = e.getTargetException();
				thrownException.printStackTrace();
				
				if (thrownException instanceof AssertionError)
					failMessage = thrownException.getMessage();
				else
					failMessage = "An error occurred whilst running the test";
			}
			catch (AssertionError e)
			{
				failMessage = e.getMessage();
			}
			finally
			{
				long testEnd = this.getTimestamp();
				
				if (failMessage == null) testsPassed++;
				
				String message = testName;
				
				if (failMessage == null)
				{
					message += " passed in " + (testEnd - testStart) + "ms";
				}
				else
				{
					message += " failed in " + (testEnd - testStart) + "ms [" + failMessage + "]";
				}
				
				this.log(message);
			}
		}
		
		long totalEnd = this.getTimestamp();
		
		this.log("\nAll tests finished in " + (totalEnd - totalStart) + "ms (" +
				testsPassed + "/" + methodCount + " passed)\n");
	}
	
	public void assertEquals(String expected, Object result)
	{
		if (!(result instanceof String))
			throw new AssertionError(this.getErrorMessage(expected, result));
		else
			this.assertEquals(expected, (String)result);
	}
	
	public void assertEquals(String expected, String result)
	{
		if (!expected.contentEquals(result))
			throw new AssertionError(this.getErrorMessage(expected, result));
	}
	
	public void assertEquals(int expected, Object result)
	{
		Class<?> objClass = result.getClass();
		if ((objClass != int.class && objClass != Integer.class))
			throw new AssertionError(this.getErrorMessage(expected, result));
		else
			this.assertEquals(expected, (int)result);
	}
	
	public void assertEquals(int expected, int result)
	{
		if (expected != result)
			throw new AssertionError(this.getErrorMessage(expected, result));
	}
	
	public void assertEquals(boolean expected, Object result)
	{
		Class<?> objClass = result.getClass();
		if ((objClass != boolean.class && objClass != Boolean.class))
			throw new AssertionError(this.getErrorMessage(expected, result));
		else
			this.assertEquals(expected, (boolean)result);
	}
	
	public void assertEquals(boolean expected, boolean result)
	{
		if (expected != result)
			throw new AssertionError(this.getErrorMessage(expected, result));
	}
	
	public void assertEquals(double expected, Object result, double epsilon)
	{
		Class<?> objClass = result.getClass();
		if (objClass != double.class && objClass != Double.class)
			throw new AssertionError(this.getErrorMessage(expected, result));
		else
			this.assertEquals(expected, (double)result, epsilon);
	}
	
	public void assertEquals(double expected, double result, double epsilon)
	{
		if (Math.abs(expected - (Double)result) > epsilon)
			throw new AssertionError(this.getErrorMessage(expected, result));
	}
	
	public void assertEquals(Object expected, Object result)
	{
		if (expected == null) this.assertEquals(true, result == null);
		else if (expected instanceof String) this.assertEquals((String)expected, result);
		else
		{
			Class<?> c = expected.getClass();
			if (c == double.class) this.assertEquals((double)expected, result, 0.0001F);
			else if (c == boolean.class) this.assertEquals((boolean)expected, result);
			else if (c == int.class) this.assertEquals((int)expected, result);
		}
		
	}
	
	public String getErrorMessage(Object expected, Object result)
	{
		return "Got '" + (result == null ? "null" : String.valueOf(expected))
				+ "', expected '" + (expected == null ? "null" : String.valueOf(result))
				+ "'";
	}
	
	public void log(String message)
	{
		System.out.println(message);
	}
	
	public long getTimestamp()
	{
		return new Date().getTime();
	}
}