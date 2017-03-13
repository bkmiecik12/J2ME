import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

public class MMidlet extends MIDlet implements CommandListener {

	private static Display ekran;
	private Form tb;
	private Command koniec;
	private Command wprowadz;
	private Command wyswietl;
	private Form f;
	public static Lista l;
	public static RecordStore magazyn;
	Image img;
	
	public MMidlet() throws IOException {
		
		ekran = Display.getDisplay(this);
		
		koniec = new Command("Koniec",Command.EXIT,1);
		wprowadz = new Command("Dodaj zawodnika",Command.SCREEN,2);
		wyswietl = new Command("Wyœwietl",Command.SCREEN,2);
		tb = new Form("FUTBOL MENAD¯ER 1488");
		img = Image.createImage("/img2.jpg");
		tb.addCommand(koniec);
		tb.addCommand(wprowadz);
		tb.addCommand(wyswietl);
		tb.append(new ImageItem(null,img,ImageItem.LAYOUT_DEFAULT,null));
		f = new Formularz("Dodaj zawodnika",tb);
		l = new Lista(tb);
	}

	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		try{
	        magazyn.closeRecordStore();
	    }
	    catch(RecordStoreException e){
	        e.printStackTrace();
	    }
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		// TODO Auto-generated method stub
		ekran.setCurrent(tb);
		tb.setCommandListener((CommandListener)this);
		try{
            magazyn = RecordStore.openRecordStore("Pilkarze", true, RecordStore.AUTHMODE_PRIVATE, false);
        }
        catch(RecordStoreException e){
            e.printStackTrace();
        }
	}
	public static Display mojEkran(){
		return ekran;
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if(c==koniec){
			try {
				destroyApp(false);
			} catch (MIDletStateChangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notifyDestroyed();
		}
		else if(c==wprowadz){
			ekran.setCurrent(f);
		}
		else if(c==wyswietl){
			ekran.setCurrent(l);
		}
	}

}
