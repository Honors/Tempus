import java.lang.reflect.*;
public class Schedule {
  public Period[] periods;
  public double sumOver(String meth) {
    double sum = 0;
    Method method = null;
    for( Period p : periods ) {
      try {
        method = p.getClass().getMethod(meth);
      } catch (SecurityException e) {
      } catch (NoSuchMethodException e) {
      }    
      try {
        sum += (Double)method.invoke(p);
      } catch (IllegalArgumentException e) {
      } catch (IllegalAccessException e) {
      } catch (InvocationTargetException e) {
      }
    }
    return sum;
  }
  public Schedule(Period[] ps) {
    periods = ps;
  }
  public double size() {
    return sumOver("getLength");
  }
  public double elasticity() {
    return sumOver("getElasticity");
  }
  public Schedule reshape(double size) {
    double parts = size();
    double es = elasticity();
    for( Period p : periods ) {
      double delta = p.elasticity / es * (size - parts);
      p.length += delta;
    }
    return this;
  }
  public void render() {
    for( Period p : periods ) {
      System.out.print(String.format("%.4f | ", p.length));
    }
    System.out.print("\n");
  }
}
