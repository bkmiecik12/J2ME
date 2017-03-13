import java.util.Calendar;
import java.util.Date;

public class Pilkarz {
	public String imie;
	public String naz;
	public String nr;
	public long dataUr;
	public int drybling;
	public Pilkarz(String i,String n,String nu,int dr,long du){
		imie = i;
		naz = n;
		nr = nu;
		dataUr = du;
		drybling = dr;
	}
	public String getNr(int arg){
		return Integer.toString(arg);
	}
	public String getDate(){
		Date date = new Date(dataUr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String dateString = 
				String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"-"
				+String.valueOf(calendar.get(Calendar.MONTH)) +"-"
				+String.valueOf(calendar.get(Calendar.YEAR));
		return dateString;
	}
}