import java.util.List;

public class Lagrange {
    public static Polynome interpolate(List<Pair> points) {
        Polynome result = new Polynome(new Rational(0)); // Start mit 0

        for (int i = 0; i < points.size(); i++) {
            long xi = points.get(i).getA();
            long yi = points.get(i).getB();

            // Basis-Polynom ℓ_i(x)
            Polynome li = new Polynome(new Rational(1)); // Start = 1
            Rational denom = new Rational(1);

            for (int j = 0; j < points.size(); j++) {
                if (j == i) continue;
                long xj = points.get(j).getA();

                // (x - xj)
                Polynome factor = new Polynome(List.of(new Rational(-xj), new Rational(1)));
                li = li.multiply(factor);

                denom = Rational.multiply(denom, new Rational(xi - xj));
            }

            // Skalar = yi / denom
            Rational scalar = Rational.divide(new Rational(yi), denom);
            li = li.scale(scalar);

            // Zum Ergebnis addieren
            result = result.add(li);
        }

        return result;
    }
}
