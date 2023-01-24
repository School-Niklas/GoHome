import java.util.Random;
import java.util.Scanner;

/**
 * Beschreiben Sie hier die Klasse Spiel.
 * 
 * @author Niklas Baranski 
 * @version v1.1
 */
public class Spiel
{
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen
    Figur f1;
    Figur f2;
    Figur f3;
    Figur f4;
    
    Figur aktuelleFigur1;
    Figur aktuelleFigur2;
    
    String farbe1;
    String farbe2;
    
    String aktuelleFarbe;
  
    
    
    /**
     * Konstruktor für Objekte der Klasse Spiel
     */
    public Spiel()
    {
        // Instanzvariable initialisieren
        farbe1 = "blau";
        farbe2 = "rot";
        
        f1 = new Figur(farbe1, -2,2);
        f2 = new Figur(farbe2, 2,-2);
        f3 = new Figur(farbe1, -2,-2);
        f4 = new Figur(farbe2, 2, 2);
    }
    public void spielen(){
        while(druckeGewinner() == "Noch kein Gewinner"){
            neuenZugVorbereiten();
            Scanner userInput = new Scanner(System.in);
            while(!userInput.hasNext()){
                String input = "";
                if (userInput.hasNext()) input = userInput.nextLine();
                    System.out.println("input is '" + input + "'");
                userInput.close();
            }
        }
    }
    private String druckeGewinner(){
        if(f1.gewonnen()){
            return aktuelleFarbe + " hat gewonnen";
        } else if(f2.gewonnen()){
            return aktuelleFarbe + " hat gewonnen";
        } else if(f3.gewonnen()){
            return aktuelleFarbe + " hat gewonnen";
        } else if(f4.gewonnen()){
            return aktuelleFarbe + " hat gewonnen";
        } else {
            return "Noch kein Gewinner";
        }
    }
    private void druckeAktuellenSpieler(){
        if(aktuelleFarbe != null){
            System.out.println("An der Reihe ist der Spieler mit der Farbe " + aktuelleFarbe);
            System.out.println("----------------------------");
        }
    }
    private void druckeAktuelleFiguren(){
        System.out.println("Die aktuellen Figuren sind:");
        aktuelleFigur1.druckePosition();
        aktuelleFigur2.druckePosition();
        System.out.println("----------------------------");
    }
    private void druckeSpielfeld()
    {
        StringBuilder[][] feld = new StringBuilder[5][5];
        for(int x = 0; x < 5; x++)
            for(int y = 0; y < 5; y++)
                feld[x][y] = new StringBuilder();

        if(f1 == aktuelleFigur1 || f1 == aktuelleFigur2)
            feld[f1.x+2][f1.y+2].append(farbe1.toUpperCase().charAt(0));
        else
            feld[f1.x+2][f1.y+2].append(farbe1.toLowerCase().charAt(0));
        if(f2 == aktuelleFigur1 || f2 == aktuelleFigur2)
            feld[f2.x+2][f2.y+2].append(farbe1.toUpperCase().charAt(0));
        else
            feld[f2.x+2][f2.y+2].append(farbe1.toLowerCase().charAt(0));
        if(f3 == aktuelleFigur1 || f3 == aktuelleFigur2)
            feld[f3.x+2][f3.y+2].append(farbe2.toUpperCase().charAt(0));
        else
            feld[f3.x+2][f3.y+2].append(farbe2.toLowerCase().charAt(0));
        if(f4 == aktuelleFigur1 || f4 == aktuelleFigur2)
            feld[f4.x+2][f4.y+2].append(farbe2.toUpperCase().charAt(0));
        else
            feld[f4.x+2][f4.y+2].append (farbe2.toLowerCase().charAt(0));
        if(feld[2][2].toString().equals(""))
            feld[2][2] = new StringBuilder("HOME");
        System.out.print("    ------------------------");
        for(int y = 2; y >= -2; y--)
        {
            System.out.printf("\n%2s |", y);
            for(int x = -2; x <= 2; x++)
            {
                System.out.printf("%-4s|", feld[x+2][y+2]);
            }
            System.out.print("\n    ------------------------");
        }
        System.out.println("\n    -2   -1    0    1    2");
    }
    private void aktuelleFigurenAuswuerfeln(){
        Random r = new Random();
        int zahl1 = r.nextInt(4);
        int zahl2 = r.nextInt(4);
        
        if(zahl1 == 1){
            aktuelleFigur1 = f1;
        } else if (zahl1 == 2){
            aktuelleFigur1 = f2;
        } else if (zahl1 == 3){
            aktuelleFigur1 = f3;
        } else {
            aktuelleFigur1 = f4;
        }
        
        if(zahl2 == 1){
            aktuelleFigur2 = f1;
        } else if (zahl2 == 2){
            aktuelleFigur2 = f2;
        } else if (zahl2 == 3){
            aktuelleFigur2 = f3;
        } else {
            aktuelleFigur2 = f4;
        }
    }
    public void nachObenBewegen(){
        aktuelleFigur1.gehe(0);
        aktuelleFigur2.gehe(0);
    }
    public void nachRechtsBewegen(){
        aktuelleFigur1.gehe(1);
        aktuelleFigur2.gehe(1);
    }
    public void nachUntenBewegen(){
        aktuelleFigur1.gehe(2);
        aktuelleFigur2.gehe(2);
    }
    public void nachLinksBewegen(){
        aktuelleFigur1.gehe(3);
        aktuelleFigur2.gehe(3);
    }
    private void setzeNeuenSpieler(){
        if (aktuelleFarbe == farbe1){
            aktuelleFarbe = farbe2;
        } else{
            aktuelleFarbe = farbe1;
        }
        if (aktuelleFarbe == null){
            Random r = new Random();
            if (r.nextInt(1) == 1){
                aktuelleFarbe = farbe1;
             } else{
                aktuelleFarbe = farbe2;
             }
        }
    }
    public void neuenZugVorbereiten(){
        if (druckeGewinner() == "Noch kein Gewinner"){
            setzeNeuenSpieler();
            druckeAktuellenSpieler();
            aktuelleFigurenAuswuerfeln();
            druckeAktuelleFiguren();
            druckeSpielfeld();
        } else{
            druckeGewinner();
        }
    }
}

