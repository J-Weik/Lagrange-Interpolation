import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String s;
        Scanner sc = new Scanner(System.in);
        boolean mainLoop = true;
        LinkedList<Pair> points = new LinkedList<>();
        LinkedList<Integer> unknowns = new LinkedList<>();
        while(mainLoop) {
            int counter = 1;
            System.out.print("[F]olge oder [S]tützpunkte?: ");
            s = sc.nextLine().trim().toLowerCase();
            if (s.equals("f")) {
                System.out.println("Zahlen in der Reihenfolge eingeben, ? falls Zahl nicht bekannt und leer lassen um Eingabe zu beenden");

                while (true) {
                    System.out.print(counter+": " );
                    s = sc.nextLine().trim().toLowerCase();
                    if(s.isEmpty()) break;
                    if (s.equals("?")) unknowns.add(counter++);
                    else {
                        points.add(new Pair(counter, Long.parseLong(s)));
                        counter++;
                    }
                }
                System.out.println("Alle Punkte eingeben!");
            } else if (s.equals("s")) {
                System.out.println("Bitte Stützpunkte eingeben, leer Lassen um Eingabe zu beenden");
                while (true) {
                    System.out.print("Bitte x-Koordinate Nr." + counter + " eingeben: ");
                    s = sc.nextLine().trim().toLowerCase();
                    if (s.isEmpty()) break;
                    long x = Long.parseLong(s);
                    System.out.print("Bitte y Koordinate Nr." + counter + " eingeben: ");
                    long y = Long.parseLong(sc.nextLine().trim().toLowerCase());
                    points.add(new Pair(x, y));
                    counter++;
                }

            } else System.out.println("Keine korrekte Eingabe!");
            // Point Entry complete

            Polynome fun = Lagrange.interpolate(points);
            System.out.println("Mithilfe der beinahe banal simplen Funktion:");
            System.out.println("f(x) = " + fun);
            System.out.println("Test: ");
            for (Pair p : points) {
                System.out.println("f("+p.getA()+") = "+fun.eval(p.getA()));
            }
            System.out.println();
            System.out.println("Finden wir heraus, dass die einzig sinnige Antwort");
            for (int i : unknowns) {
                System.out.println("f("+i+") = "+fun.eval(i));
            }
            System.out.println();
            System.out.println("Sein kann.");
            mainLoop = false;
        }
    }
}
