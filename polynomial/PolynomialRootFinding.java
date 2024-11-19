package hus.oop.polynomial;

public class PolynomialRootFinding {
    private Polynomial poly;
    private RootSolver rootSolver;

    /**
     * Khởi tạo đa thức.
     * @param polynomial
     */
    public PolynomialRootFinding(Polynomial polynomial) {
        this.poly = polynomial;
        this.rootSolver = null; // Mặc định không có phương pháp tìm nghiệm
    }

    /**
     * Khởi tạo đa thức và phương pháp tìm nghiệm.
     * @param polynomial
     * @param rootSolver
     */
    public PolynomialRootFinding(Polynomial polynomial, RootSolver rootSolver) {
        this.poly = polynomial;
        this.rootSolver = rootSolver;
    }

    /**
     * Thiết lập đa thức mới.
     * @param poly
     */
    public void setPoly(Polynomial poly) {
        this.poly = poly;
    }

    /**
     * Thiết lập phương pháp tìm nghiệm mới.
     * @param rootSolver
     */
    public void setRootSolver(RootSolver rootSolver) {
        this.rootSolver = rootSolver;
    }

    /**
     * Tìm nghiệm của đa thức theo phương pháp đã cho.
     * @param lower
     * @param upper
     * @return nghiệm của đa thức.
     */
    public double solve(double lower, double upper) {
        if (this.rootSolver == null) {
            throw new IllegalStateException("RootSolver is not set.");
        }
        return this.rootSolver.solve(this.poly, lower, upper);
    }
}
