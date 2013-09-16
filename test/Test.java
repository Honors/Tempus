public class Test extends Examen {
 public Boolean testDefault() {
    // form a list of blocks and breaks and form
    // a schedule of them to fit a given duration.
    Block hr = new Block(7, false);
    Block n = new Block(45, true);
    Block l = new Block(77, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, a, s, l, s, n, s, n, a, s, n};
    Break[] breaks = {};
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, 443f);
    return sorted[2] == 45;
  }  
 public Boolean testShortDay() {
    Block hr = new Block(7, false);
    Block n = new Block(45, true);
    Block l = new Block(77, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, a, s, l, s, n, s, n, a, s, n};
    Break[] breaks = {};
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, 355f);
    return inRange(sorted[2], 32, 35);
  }  
  public static void main(String[] args) {
    Examen.run(Test.class, "testDefault", "Matches the default school schedule.");
    Examen.run(Test.class, "testShortDay", "Matches the default short school schedule.");
  }
}
