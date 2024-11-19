package hus.oop.polynomial;

public class SecantSolver implements RootSolver {
    private double tolerance;  // Độ chính xác yêu cầu
    private int maxIterations; // Số vòng lặp tối đa

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance Độ chính xác yêu cầu khi tìm nghiệm
     * @param maxIterations Số vòng lặp tối đa
     */
    public SecantSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    /**
     * Tìm nghiệm của đa thức theo phương pháp Secant.
     * @param polynomial Đa thức cần tìm nghiệm
     * @param lower Giới hạn dưới của khoảng
     * @param upper Giới hạn trên của khoảng
     * @return Nghiệm của đa thức.
     */
    @Override
    public double solve(Polynomial polynomial, double lower, double upper) {
        // Khởi tạo các giá trị ban đầu x0 và x1
        double x0 = lower;
        double x1 = upper;

        // Đánh giá giá trị của đa thức tại x0 và x1
        double f0 = polynomial.evaluate(x0);
        double f1 = polynomial.evaluate(x1);

        // Lặp qua số vòng lặp tối đa
        for (int i = 0; i < maxIterations; i++) {
            // Nếu giá trị f1 - f0 gần bằng 0, dừng lại (tránh chia cho 0)
            if (Math.abs(f1 - f0) < tolerance) {
                throw new ArithmeticException("Chênh lệch giữa f1 và f0 quá nhỏ, không thể tiếp tục.");
            }

            // Tính giá trị mới của x2 theo công thức Secant
            double x2 = x1 - f1 * (x1 - x0) / (f1 - f0);

            // Nếu sự thay đổi giữa x2 và x1 nhỏ hơn độ chính xác, trả về nghiệm
            if (Math.abs(x2 - x1) <= tolerance) {
                return x2;
            }

            // Cập nhật các giá trị cho vòng lặp tiếp theo
            x0 = x1;
            f0 = f1;
            x1 = x2;
            f1 = polynomial.evaluate(x1);
        }

        // Nếu không tìm được nghiệm trong số vòng lặp tối đa, trả về x1
        return x1;
    }
}
