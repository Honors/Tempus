public class Test extends Examen {
  public Boolean testDuration(Float duration, float blockSize) {
    return testDuration(duration, blockSize, blockSize);
  }
  public void renderList(Float[] sorted) {
    for( Float sort : sorted )
      System.out.print(sort + " | ");
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
  public Boolean testMass() {
    Block hr = new Block(7, false);
    Block n = new Block(32, true);
    Block l = new Block(74, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, s, l, n, s, n, a, s, n};
    Break[] breaks = {new Break(160f, 101f)}; 
    // - 101 is an arbitrary hack, to illustrate what is needed to pass
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, 443f);
    return sorted[2] == 32;
  }
  public Boolean morningAssembly() {
    Block hr = new Block(7, false);
    Block n = new Block(32, true);
    Block l = new Block(80, false);
    Block s = new Block(5, false);
    Block a = new Block(2, false);
    Block[] segments = {hr, s, n, s, n, s, n, s, n, a, s, l, n, s, n, a, s, n};
    Break[] breaks = {new Break(44f, 81f)};
    // - a 76 min assembly plus 5 minutes between 1st and assembly
    Schedule sched = new Schedule();
    Float[] sorted = sched.reshape(segments, breaks, 443f);
    return sorted[2] == 32 && sorted[5] == 34;
  }

  public static void main(String[] args) {
    Examen.run(Test.class, "testDefault", "Matches the default school schedule.");
    Examen.run(Test.class, "testShortDay", "Matches the default short school schedule.");
    Examen.run(Test.class, "testMass", "Matches the mass schedule.");
    Examen.run(Test.class, "morningAssembly", "Matches the morning assembly schedule.");
  }
}
