import java.lang.reflect.*;
public class Examen {
  public Boolean inRange(float x, int low, int high) {
    return x >= low && x <= high;
  }
  public Method getMethod(Examen obj, String meth) {
    Method method = null;
    try {
      method = obj.getClass().getMethod(meth);
    } catch (Exception e) {}
    return method;
  }
  public Boolean invokeTest(Method method, Examen obj)  {
    Boolean resp = null;
    try {
      resp = (Boolean)method.invoke(obj);
    } catch (Exception e) {}
    return resp;
  }
  public static void run(Class<? extends Examen> runner, String meth, String desc) {
    // instantiate instance of the testing class
    Examen tests = null;
    try {
      tests = runner.newInstance();
    } catch(Exception e){}

    // execute and render the test method
    Method method = tests.getMethod(tests, meth);    
    if( tests.invokeTest(method, tests) ) {
      System.out.print("PASSED: "+ desc +"\n");
    } else {
      System.out.print("FAILED: "+ desc +"\n");
    }
  }
}
