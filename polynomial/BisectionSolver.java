package hus.oop.polynomial;

public class BisectionSolver implements RootSolver {
    private double tolerance;  // Độ chính xác yêu cầu
    private int maxIterations; // Số vòng lặp tối đa

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance Độ chính xác yêu cầu khi tìm nghiệm
     * @param maxIterations Số vòng lặp tối đa
     */
    public BisectionSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    /**
     * Tìm nghiệm của đa thức theo phương pháp chia đôi (Bisection)
     * @param polynomial Đa thức cần tìm nghiệm
     * @param lower Giới hạn dưới của khoảng
     * @param upper Giới hạn trên của khoảng
     * @return Nghiệm gần đúng của đa thức
     */
    @Override
    public double solve(Polynomial polynomial, double lower, double upper) {
        // Kiểm tra giá trị của đa thức tại các đầu của đoạn
        if (polynomial.evaluate(lower) == 0) {
            return lower;
        }
        if (polynomial.evaluate(upper) == 0) {
            return upper;
        }

        // Lặp lại quá trình chia đôi cho đến khi đạt được độ chính xác yêu cầu hoặc số vòng lặp tối đa
        for (int i = 0; i < maxIterations; i++) {
            double middle = (lower + upper) / 2;
            double middleValue = polynomial.evaluate(middle);

            // Kiểm tra nếu nghiệm đã đạt độ chính xác yêu cầu
            if (Math.abs(middleValue) <= tolerance) {
                return middle;
            }

            // Chia đôi khoảng tìm nghiệm
            if (polynomial.evaluate(lower) * middleValue < 0) {
                upper = middle;  // Nghiệm nằm trong khoảng [lower, middle]
            } else {
                lower = middle;  // Nghiệm nằm trong khoảng [middle, upper]
            }
        }

        // Nếu chưa tìm được nghiệm trong số vòng lặp tối đa, trả về giá trị trung bình của khoảng
        return (lower + upper) / 2;
    }
}
