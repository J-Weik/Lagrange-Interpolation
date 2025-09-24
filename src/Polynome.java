import java.util.ArrayList;
import java.util.List;

public class Polynome {
    private final List<Rational> coeffs;

    public Polynome(Rational c) {
        coeffs = new ArrayList<>();
        coeffs.add(c);
    }

    public Polynome(List<Rational> coeffs) {
        this.coeffs = new ArrayList<>(coeffs);
        trim();
    }

    public List<Rational> getCoeffs() {
        return coeffs;
    }

    public Polynome add(Polynome other) {
        int n = Math.max(coeffs.size(), other.coeffs.size());
        List<Rational> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Rational a = i < coeffs.size() ? coeffs.get(i) : new Rational(0);
            Rational b = i < other.coeffs.size() ? other.coeffs.get(i) : new Rational(0);
            res.add(Rational.add(a, b));
        }
        return new Polynome(res);
    }

    public Polynome multiply(Polynome other) {
        int n = coeffs.size() + other.coeffs.size() - 1;
        List<Rational> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(new Rational(0));
        for (int i = 0; i < coeffs.size(); i++) {
            for (int j = 0; j < other.coeffs.size(); j++) {
                Rational term = Rational.multiply(coeffs.get(i), other.coeffs.get(j));
                res.set(i+j, Rational.add(res.get(i+j), term));
            }
        }
        return new Polynome(res);
    }

    public Polynome scale(Rational k) {
        List<Rational> res = new ArrayList<>();
        for (Rational c : coeffs) {
            res.add(Rational.multiply(c, k));
        }
        return new Polynome(res);
    }

    private void trim() {
        int i = coeffs.size() - 1;
        while (i > 0 && coeffs.get(i).nominator == 0) {
            coeffs.remove(i);
            i--;
        }
    }

    public Rational eval(long x) {
        Rational result = new Rational(0);
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            result = Rational.add(Rational.multiply(result, new Rational(x)), coeffs.get(i));
        }
        return result;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            Rational c = coeffs.get(i);
            if (c.nominator == 0) continue;
            if (!sb.isEmpty()) sb.append(" + ");
            sb.append(c.toString());
            if (i > 0) {
                sb.append(" x");
                if (i > 1) sb.append("^").append(i);
            }
        }
        if (sb.isEmpty()) return "0";
        return sb.toString();
    }
}
