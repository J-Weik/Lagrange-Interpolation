public class Rational {
    long nominator, denominator;
    double decimal;
    ///   a
    /// ------
    ///   b
    public Rational(long a, long b) {
        nominator = a;
        denominator = b;
        decimal = (double) a / b;
    }
    public Rational(long a) {
        nominator = a;
        denominator = 1;
    }

    public String toString() {
        if (denominator == 1) {
            return String.valueOf(nominator);
        } else return nominator + "/" + denominator;
    }
    public static Rational add(Rational n, Rational m) {
        return shorten(new Rational(n.nominator*m.denominator+n.denominator*m.nominator, n.denominator*m.denominator));
    }
    public static Rational subtract(Rational n, Rational m) {
        return shorten(new Rational(n.nominator*m.denominator-n.denominator*m.nominator, n.denominator*m.denominator));
    }
    public static Rational multiply(Rational n, Rational m) {
        return shorten(new Rational(n.nominator* m.nominator, n.denominator*m.denominator));
    }
    public static Rational divide(Rational n, Rational m) {
        return shorten(multiply(n, kehrwert(m)));
    }
    public static Rational kehrwert(Rational n) {
        return new Rational(n.denominator, n.nominator);
    }
    public static Rational negative(Rational n) {
        return new Rational(-n.denominator, n.nominator);
    }
    public static Rational abs(Rational n) {
        return new Rational(Math.abs(n.denominator), Math.abs(n.nominator));
    }
    public static Rational shorten(Rational n) {
        long gcd = gcd(n.denominator, n.nominator);
        return new Rational(n.nominator/gcd, n.denominator/gcd);
    }
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    public static char sign(Rational n) {
        if (n.nominator == 0) return ' ';
        else if (Math.signum(n.denominator)==1 && Math.signum(n.nominator) == 1 ) return '+';
        else if(Math.signum(n.nominator)==-1 && Math.signum(n.denominator) == -1) return '+';
        else return '-';
    }
}
