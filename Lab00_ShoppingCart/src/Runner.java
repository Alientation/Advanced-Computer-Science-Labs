import static java.lang.System.*;
import java.util.*;
import java.lang.reflect.Method;

public class Runner {
	
	public static void main	(String[] args) throws Exception {
	   	
	   	WelcomeBack	wb	= new	WelcomeBack();   
      	
      	//runMethod(Object callingObject, String nameOfMethod, Object[] parametersForMethod, String expectedOutput)
      	//runMethod will return whatever the invoked method returns, which can be printed to the screen. 
      	
      	//out.println(wb.getMiddle("abcde"));                                        //How you probably tested getMiddle 
      	out.println(runMethod(wb, "getMiddle", new Object[] {"abcde"}, "c") + "\n"); //How I test getMiddle
      	
      	out.println(runMethod(wb, "getMiddle", new Object[] {"wxyz"}, "xy") + "\n");                                  
      	out.println(runMethod(wb, "sumNumbers", new Object[] {12}, "[0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78]") + "\n");            
      	out.println(runMethod(wb, "sumDigits", new Object[] {234}, "9") + "\n");      
      	out.println(runMethod(wb, "keepSummingDigits", new Object[] {29}, "2") + "\n");
      	out.println(runMethod(wb, "keepSummingDigits", new Object[] {99984}, "3") + "\n");      
      	out.println(runMethod(wb, "getIntersection", new Object[] {new int[] {1,2,3,4}, new int[] {9,0,4,3,4,1}}, "134") + "\n");      
      	out.println(runMethod(wb, "mapLengths", new Object[] {new String[] {"a", "b", "hello", "hi", "yo", "I"}}, "{1=3, 2=2, 5=1}") + "\n");    
      	out.println(runMethod(wb, "sumDigitsRecur", new Object[] {987654321}, "45") + "\n");          
      	out.println(runMethod(wb, "sumWithoutCarry", new Object[] {863, 551}, "314") + "\n");          
      	out.println(runMethod(wb, "buySell1", new Object[] {new int[] {3, 4, 3, 2, 1, 5}}, "4") + "\n");
      	out.println(runMethod(wb, "buySell1", new Object[] {new int[] {5, 4, 3, 2, 1, 1}}, "0") + "\n");
      	out.println(runMethod(wb, "zeck", new Object[] {"C:\\Users\\mcpir\\eclipse-workspace\\AdvancedComputerScience3\\Lab00_ShoppingCart\\zeck.txt"}, "120 = 89 + 21 + 8 + 2\n34 = 34\n88 = 55 + 21 + 8 + 3 + 1\n90 = 89 + 1\n320 = 233 + 55 + 21 + 8 + 3\nEND EXPECTED OUTPUT") + "\n");         
      	
      	/*
      	out.println("**************** ADVANCED PROBLEMS *************");
      	out.println(runMethod(wb, "buySell2", new Object[] {new int[] {1, 2, 7, 4, 11}}, "13") + "\n");   
      	out.println(runMethod(wb, "buySell2", new Object[] {new int[] {2, 6, 8, 7, 8, 7, 9, 4, 1, 2, 4, 5, 8}}, "16") + "\n");        
      	out.println(runMethod(wb, "buySell3", new Object[] {new int[] {1, 4, 7, 2, 11}}, "15") + "\n");  
      	out.println(runMethod(wb, "buySell3", new Object[] {new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9}}, "17") + "\n");        
      	out.println(runMethod(wb, "buySell4", new Object[] {4, new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0}}, "15") + "\n");
      	out.println(runMethod(wb, "buySell4", new Object[] {2, new int[] {1, 2, 4, 2, 5, 7, 2, 4, 9, 0, 9}}, "17") + "\n");
		*/
      
   }
   
   public static Object runMethod(Object o, String name, Object[] args, String expOut) {
      //runMethod (object containing method, method name, array of method parameters, expected output)      
      //runMethod will return whatever the invoked method returns

      try { 
         Class[] argTypes = new Class[args.length];
         for (int i = 0; i < args.length; i++) {
            argTypes[i] = fixPrimitive(args[i].getClass());
            }

         Method m = o.getClass().getMethod(name, argTypes);                 
         Object ret = null;
         if (m != null) 
            out.println(">>CALLING " + name + "(" + getParam(args) + ")\nEXPECTED OUTPUT: \n" + expOut + "\nACTUAL OUTPUT: ");
            ret = m.invoke(o, args);
            if (ret == null) return "";
            if (ret.getClass().isArray()) return arrayAsString(ret);           
            return ret;
         
      } catch (Exception e) {
         out.println("Method not found - " + name); 
      }
      return "";          
   }
   
   public static Class<?> fixPrimitive(Class c) {
      if (c == Byte.class) return byte.class;
      if (c == Short.class) return short.class;
      if (c == Integer.class) return int.class;
      if (c == Long.class) return long.class;
      if (c == Float.class) return float.class;
      if (c == Double.class) return double.class;
      if (c == Boolean.class) return boolean.class;
      if (c == Character.class) return char.class;
      return c;         
   }
   
   public static String arrayAsString(Object o) {
      if (o instanceof byte[]) return Arrays.toString((byte[])o);
      if (o instanceof short[]) return Arrays.toString((short[])o);
      if (o instanceof int[]) return Arrays.toString((int[])o);
      if (o instanceof long[]) return Arrays.toString((long[])o);
      if (o instanceof float[]) return Arrays.toString((float[])o);
      if (o instanceof double[]) return Arrays.toString((double[])o);
      if (o instanceof boolean[]) return Arrays.toString((boolean[])o);
      if (o instanceof char[]) return Arrays.toString((char[])o);      
      return Arrays.toString((Object[])o);   
   }
   
   public static String getParam(Object[] params) {
      String ret = "";
      for (Object o : params) {
         if (ret.length() > 0) ret += ", ";
         if (o instanceof String) ret += "\"" + o + "\"";
         else if (o.getClass().isArray()) ret += arrayAsString(o);
         else ret += o;
      }
      return ret;         
   }
   
   
}