import java.lang.reflect.*;
public class Test {
  // form a list of blocks and breaks and form
  // a schedule of them to fit a given duration.
 public Boolean testDefault() {
    Block hr = new Block(5, false);
    Block n = new Block(42, true);
    Block l = new Block(77, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, a, s, l, s, n, s, n, a, s, n};
    Break[] breaks = {};
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, 438f);
    return sorted[2] > 44 && sorted[2] < 45;  
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
  public static void main(String[] args) {
    Test.run("testDefault", "Matches the default school schedule.");
  }
}

