public class Test extends Examen {
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
  public static void main(String[] args) {
    Test.run("testDefault", "Matches the default school schedule.");
  }
}

