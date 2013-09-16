import java.lang.reflect.*;
public class Examen {
  public Boolean inRange(float x, int low, int high) {
    return x >= low && x <= high;
  }
  public static void run(String meth, String desc) {
    Test tests = new Test();
    Method method = null;
    Boolean resp = null;
    try {
      method = tests.getClass().getMethod(meth);
    } catch (SecurityException e) {
    } catch (NoSuchMethodException e) {
    }    
    try {
      resp = (Boolean)method.invoke(tests);
    } catch (IllegalArgumentException e) {
    } catch (IllegalAccessException e) {
    } catch (InvocationTargetException e) {
    }

    if( resp ) {
      System.out.print("PASSED: "+ desc +"\n");
    } else {
      System.out.print("FAILED: "+ desc +"\n");
    }
  }
}
