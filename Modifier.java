import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class Modifier {
  public static void main(String args[]) {  
    Period p = new Period(42, 0);
    Period[] ps = {p.normal(), p.inter(),
                   p.normal(), p.inter(),
		   p.normal(), p.inter(),
		   p.normal(), p.inter(),
		   p,
		   p.normal(), p.inter(),
		   p.normal(), p.inter(),
		   p.normal(), p.inter()};
    Schedule s = new Schedule(ps);
    Date today = new Date();
    Calendar now = new GregorianCalendar();
    now.setTime(today);
    Calendar end = new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH), 3, 13);    
    System.out.print(now.getTime() - end.getTime() + "\n");
    s.reshape(60 * 6);
    System.out.print(s.elasticity() + "\n");
    s.render();
  }
}
