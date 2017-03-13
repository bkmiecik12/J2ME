import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.rms.RecordComparator;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStoreException;

public class Lista extends List implements CommandListener {

	Displayable ekranPowrotny;
	private RecordEnumeration iterator;
	private Display ekran;
	private Vector pilkarze = new Vector();
	Form details;
	
	Command powrot;
	private Command wczytajNaz;
	private Command szczegoly;
	private Command wczytajNr;
	
	
	public Lista(Displayable ekranP) {
		super("Pi³karze", Choice.IMPLICIT);
		ekranPowrotny = ekranP;
		ekran = MMidlet.mojEkran();
		powrot = new Command("Powrót", Command.BACK,1);
		this.addCommand(powrot);
		wczytajNaz = new Command("Wczytaj wg nazwiska",Command.ITEM,1);
		this.addCommand(wczytajNaz);
		wczytajNr = new Command("Wczytaj wg numeru",Command.ITEM,1);
		this.addCommand(wczytajNr);
		szczegoly = new Command("Wyswietl szczegoly", Command.ITEM,2);
		this.addCommand(szczegoly);
		this.setCommandListener((CommandListener)this);
	}
	
	public void setIterator(RecordComparator x){
		try{
            iterator = MMidlet.magazyn.enumerateRecords(null,x,false);
        }
        catch(RecordStoreException e){
        	e.printStackTrace();
        }
	}
	
	public void wczytajPilkarzy(RecordComparator x) throws RecordStoreException
    {
		pilkarze.removeAllElements();
		deleteAll();
       
        setIterator(x);
       
        String imie = "";
        String nazwisko = "";
        String nr = "";
        int drybling = 0;
        long dataur = 0;
       
        byte[] rekord;
       
        while(iterator.hasNextElement()){
           
       
        rekord = iterator.nextRecord();
       
       
        ByteArrayInputStream strbi = new ByteArrayInputStream(rekord);
        DataInputStream strdi = new DataInputStream(strbi);
       
       
       
       
        try {
            imie = strdi.readUTF();
            nazwisko = strdi.readUTF();
            nr = strdi.readUTF();
            drybling = strdi.readInt();
            dataur = strdi.readLong();
           
           
            } catch (IOException e) {
                e.printStackTrace();
                }
        Pilkarz pilkarz = new Pilkarz(imie,nazwisko,nr,drybling,dataur);
        pilkarze.addElement(pilkarz);
       
        }
   
       
        for(int i=0; i<pilkarze.size(); i++)
        {
            Pilkarz p = (Pilkarz) pilkarze.elementAt(i);
            this.append(p.nr+". "+p.naz+" "+p.imie, null); 
        }
        
        
    }

	public void commandAction(Command c, Displayable d) {
		if(c==powrot){
			ekran.setCurrent(ekranPowrotny);
		}
		else if(c==wczytajNaz){
			
			try{
				wczytajPilkarzy(new Komparator());
            }
            catch(RecordStoreException e)
                {e.printStackTrace();}
		}
		else if(c==wczytajNr){
			try{
				wczytajPilkarzy(new KomparatorN());
            }
            catch(RecordStoreException e)
                {e.printStackTrace();}
		}
		else if(c==szczegoly)
        {
            int index = this.getSelectedIndex();
            details = new Szczegoly(this, (Pilkarz)pilkarze.elementAt(index));
            ekran.setCurrent(details);
        }
	}

}
