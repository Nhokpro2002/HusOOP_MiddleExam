package hus.oop.polynomial;

public class NewtonRaphsonSolver implements RootSolver {
    private double tolerance;  // Độ chính xác yêu cầu
    private int maxIterations; // Số vòng lặp tối đa

    /**
     * Khởi tạo giá trị các tham số.
     * @param tolerance Độ chính xác yêu cầu khi tìm nghiệm
     * @param maxIterations Số vòng lặp tối đa
     */
    public NewtonRaphsonSolver(double tolerance, int maxIterations) {
        this.tolerance = tolerance;
        this.maxIterations = maxIterations;
    }

    /**
     * Tìm nghiệm của đa thức sử dụng phương pháp Newton-Raphson.
     * @param polynomial Đa thức cần tìm nghiệm
     * @param lower Giới hạn dưới của khoảng
     * @param upper Giới hạn trên của khoảng
     * @return Nghiệm của đa thức.
     */
    @Override
    public double solve(Polynomial polynomial, double lower, double upper) {
        // Chọn giá trị khởi đầu là trung bình của khoảng [lower, upper]
        double x0 = (lower + upper) / 2;

        for (int i = 0; i < maxIterations; i++) {
            double fValue = polynomial.evaluate(x0);  // Giá trị của đa thức tại x0
            double fPrimeValue = polynomial.derivative().evaluate(x0);  // Đạo hàm của đa thức tại x0

            // Kiểm tra nếu đạo hàm bằng 0, tránh chia cho 0
            if (Math.abs(fPrimeValue) < tolerance) {
                throw new ArithmeticException("Đạo hàm gần bằng 0, không thể tiếp tục phương pháp Newton-Raphson.");
            }

            // Tính giá trị mới theo công thức Newton-Raphson
            double x1 = x0 - fValue / fPrimeValue;

            // Kiểm tra xem độ chính xác đã đủ chưa
            if (Math.abs(x1 - x0) <= tolerance) {
                return x1;  // Trả về nghiệm tìm được
            }

            // Cập nhật x0 cho lần lặp tiếp theo
            x0 = x1;
        }

        // Nếu không tìm được nghiệm trong số vòng lặp tối đa, trả về giá trị x0
        return x0;
    }
}
