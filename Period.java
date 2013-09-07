public class Period {
  public double length;
  public double elasticity;
  public double getLength() { return length; }
  public double getElasticity() { return elasticity; }
  public Period(double l, double e) {
    length = l;
    elasticity = e;
  }
  public Period normal() {
    return new Period(1, 1);
  }
}
