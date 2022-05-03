public class TrapezoidalMethod {
    private final Data data;

    public TrapezoidalMethod(Data data) {
        this.data = data;
    }

    public double getSquare(double lowerLimit, double upperLimit, int n) {
        double h = (upperLimit - lowerLimit) / n;
        double y0 = data.getFunction(lowerLimit);
        double yn = data.getFunction(upperLimit);
        double xi = lowerLimit + h;
        double square = 0;
        for (int i = 1; i < n; i++) {
            square += data.getFunction(xi);
            xi += h;
        }
        return h * ((y0 + yn) / 2 + square);
    }

    public double doMethod() {
        int n = 2;
        double previousAnswer = 0;
        double answer = Double.MAX_VALUE;
        if (data.findDiscontinuities() == null) {
             while (Math.abs(answer - previousAnswer) > data.getEpsilon()) {
                 previousAnswer = answer;
                 answer = getSquare(data.getLowerLimit(), data.getUpperLimit(), n);
                 n *= 2;
             }
        } else {
            System.out.println("В интервал интегрирования входит точка разрыва первого рода. Рассчитаем интеграл слева и справа от точки разрыва");
            while (Math.abs(answer - previousAnswer) > data.getEpsilon()) {
                previousAnswer = answer;
                double leftPart = getSquare(data.getLowerLimit(), data.findDiscontinuities() - 0.00001, n);
                double rightPart = getSquare(data.findDiscontinuities() + 0.00001, data.getUpperLimit(), n);
                answer = leftPart + rightPart;
                n *= 2;
            }
        }
        return answer;
    }
}
