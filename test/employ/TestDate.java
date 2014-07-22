package employ;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	Date date = newDate();
	
	public String getTime(){
		this.date = newDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(date);
	}
	
	Date newDate(){
		return new Date();
	}
}
