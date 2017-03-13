import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class Szczegoly extends Form implements CommandListener {

	private Display ekran;
	private Displayable ekranP;
	private Command powrot;
	private Pilkarz pilkarz;
	
	private StringItem imie;
	private StringItem nazwisko;
	private StringItem nr;
	private StringItem dataur;
	private StringItem drybling;
		
	public Szczegoly(Displayable ekranPowrotny, Pilkarz p) {
		super(p.nr+" "+p.naz);
		ekran = MMidlet.mojEkran();
		ekranP= ekranPowrotny;
		pilkarz = p;
		
		powrot = new Command("Powrot", Command.BACK, 1);
		this.addCommand(powrot);
		
		setupFields();
		inflateView();
        
        this.setCommandListener((CommandListener) this);
	}
	
	private void setupFields() {
		imie = new StringItem("Imiê: ", null);
		nazwisko = new StringItem("Nazwisko: ", null);
		nr = new StringItem("Nr: ", null);
		dataur = new StringItem("Data ur. ", null);
		drybling = new StringItem("Drybling: ", null);
		this.append(imie);
		this.append(nazwisko);
		this.append(nr);
		this.append(dataur);
		this.append(drybling);
	}

	private void inflateView() {
		imie.setText(pilkarz.imie);
		nazwisko.setText(pilkarz.naz);
		nr.setText(pilkarz.nr);
		dataur.setText(pilkarz.getDate());
		drybling.setText(pilkarz.getNr(pilkarz.drybling)+"/10");
	}

	public void commandAction(Command c, Displayable d) {
		// TODO Auto-generated method stub
		if(c==powrot){
			ekran.setCurrent(ekranP);
		}		
	}
}
