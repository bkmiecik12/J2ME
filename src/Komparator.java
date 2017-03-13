import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import javax.microedition.rms.RecordComparator;

public class Komparator implements RecordComparator {
	

	public int compare(byte[] rec1, byte[] rec2) {
		Pilkarz p1 = new Pilkarz("","","",0,0);
		Pilkarz p2 = new Pilkarz("","","",0,0);
		
		String imie1 = "";
        String nazwisko1 = "";
        String nr1 = "";
        int drybling1 = 0;
        long dataur1 = 0;
		
        String imie2 = "";
        String nazwisko2 = "";
        String nr2 = "";
        int drybling2 = 0;
        long dataur2 = 0;
        
        ByteArrayInputStream strbi = new ByteArrayInputStream(rec1);
        DataInputStream strdi = new DataInputStream(strbi);

        try {
            imie1 = strdi.readUTF();
            nazwisko1 = strdi.readUTF();
            nr1 = strdi.readUTF();
            drybling1 = strdi.readInt();
            dataur1 = strdi.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        strbi = new ByteArrayInputStream(rec2);
        strdi = new DataInputStream(strbi);

        try {
            imie2 = strdi.readUTF();
            nazwisko2 = strdi.readUTF();
            nr2 = strdi.readUTF();
            drybling2 = strdi.readInt();
            dataur2 = strdi.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		int rel = nazwisko1.compareTo(nazwisko2);
		if(rel>0)return 1;
		else if(rel<0)return -1;
		else return 0;
	}

}
