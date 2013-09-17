public class Test extends Examen {
  public Boolean testDuration(Float duration, float blockSize) {
    return testDuration(duration, blockSize, blockSize);
  }
  public Boolean testDuration(Float duration, float low, float high) {
    // form a list of blocks and breaks and form
    // a schedule of them to fit a given duration.
    Block hr = new Block(7, false);
    Block n = new Block(45, true);
    Block l = new Block(duration<=355f ? 61 : 77, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, a, s, l, s, n, s, n, a, s, n};
    Break[] breaks = {};
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, duration);
    return inRange(sorted[2], (int)low, (int)high);
  }
  public Boolean testDefault() {
    return testDuration(443f, 45);
  }  
  public Boolean testShortDay() {  
    return testDuration(355f, 34, 35);
  }  
  public static void main(String[] args) {
    Examen.run(Test.class, "testDefault", "Matches the default school schedule.");
    Examen.run(Test.class, "testShortDay", "Matches the default short school schedule.");
  }
}
