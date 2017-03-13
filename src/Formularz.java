import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;
import javax.microedition.rms.RecordStoreException;

public class Formularz extends Form implements CommandListener {
	
	Command powrot;
	Command zapisz;
	Command wyswietl;
	
	Displayable ekranPowrotny;
	Pilkarz nowyP;
	Lista l;
	
	TextField imie;
	TextField nazwisko;
	TextField nr;
	DateField dataur;
	Gauge drybling;
	
	private static Display ekran;
	
	public Formularz(String title, Displayable ekranP) {
		super(title);
		ekran = MMidlet.mojEkran();
		ekranPowrotny = ekranP;
		l = new Lista(this);
		
		powrot = new Command("Powrot", Command.BACK, 1);
        zapisz = new Command("Zapisz", Command.ITEM, 1);
        wyswietl = new Command("Wyœwietl", Command.ITEM, 1);
        
        imie = new TextField("Imie", "",16,TextField.ANY);
        nazwisko = new TextField("Nazwisko", "",16,TextField.ANY);
        nr = new TextField("Nr", "",2,TextField.NUMERIC);
        dataur = new DateField("Data ur.", DateField.DATE);
        drybling = new Gauge("Drybling",true, 10,5);
		
        this.append(imie);
		this.append(nazwisko);
		this.append(nr);
		this.append(dataur);
		this.append(drybling);
		
		wyczysc();
		
		this.addCommand(powrot);
		this.addCommand(zapisz);
		this.addCommand(wyswietl);
		
		this.setCommandListener(this);
	}
	public void wyczysc(){
		imie.setString("");
		nazwisko.setString("");
		nr.setString("");
		Date example = new Date(System.currentTimeMillis());
		dataur.setDate(example);
		drybling.setValue(5);
		
	}

	public void commandAction(Command c, Displayable d) {
		if(c==powrot){
			ekran.setCurrent(ekranPowrotny);
		}
		else if(c==zapisz){
			nowyP = new Pilkarz(imie.getString(),nazwisko.getString(),nr.getString(),drybling.getValue(),dataur.getInputMode());
			
			System.out.println(nowyP.imie+" "+nowyP.naz+" "+nowyP.drybling+" "+nowyP.nr+" ");
			
			ByteArrayOutputStream strb = new ByteArrayOutputStream();
            DataOutputStream strw = new DataOutputStream(strb);
            
			try {
				strw.writeUTF(nowyP.imie);
				strw.writeUTF(nowyP.naz);
				strw.writeUTF(nowyP.nr);
				strw.writeInt(nowyP.drybling);
				strw.writeLong(nowyP.dataUr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
			byte[] rekord = strb.toByteArray();
			if(rekord.length>0)
            {
                try{
                    MMidlet.magazyn.addRecord(rekord,0,rekord.length);
                    Alert alert = new Alert("Zapisano");
                    ekran.setCurrent(alert, this);
                    wyczysc();
                }
                catch(RecordStoreException e){
                    e.printStackTrace();
                }
            }
            else{
            	Alert alert = new Alert("Nie zapisano");
            	ekran.setCurrent(alert, this);
            }
		}
		else if(c==wyswietl){
			ekran.setCurrent(l);
		}
		
	}

}
