public class Test {
  public static void main(String args[]) {
    Period p = new Period(1, 0);
    Period[] ps = {p.normal(), p.normal(), p.normal(), p.normal(), p, p.normal(), p.normal(), p.normal()};
    Schedule s = new Schedule(ps);
    s.reshape(6);
    System.out.print(s.elasticity() + "\n");
    s.render();
  }
}
