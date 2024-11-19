package hus.oop.polynomial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestPolynomial {
    public static void main(String[] args) {
        // Chạy demo các hàm test
        StringBuilder result = new StringBuilder();

        result.append("Test ArrayPolynomial Results:\n");
        testArrayPolynomial(result);

        result.append("\nTest ListPolynomial Results:\n");
        testListPolynomial(result);

        result.append("\nTest RootSolver Results:\n");
        testRootSolver(result);

        // Lưu kết quả vào file
        try (PrintWriter writer = new PrintWriter(new FileWriter("NguyenVanA_123456_MyPolynomial.txt"))) {
            writer.print(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testArrayPolynomial(StringBuilder result) {
        // Tạo đa thức với ArrayPolynomial
        ArrayPolynomial poly1 = new ArrayPolynomial(new double[]{2, 0, 3});
        ArrayPolynomial poly2 = new ArrayPolynomial(new double[]{1, -1, 2});

        // Thêm hệ số vào đa thức
        poly1.insertAtStart(1);
        poly1.insertAtEnd(-4);

        // Sửa hệ số tại vị trí index
        poly1.set(2, 5);

        result.append("poly1 coefficients: ");
        for (double coeff : poly1.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Cộng 2 đa thức
        ArrayPolynomial sum = poly1.plus(poly2);
        result.append("Sum of poly1 and poly2:\n");
        for (double coeff : sum.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Trừ 2 đa thức
        ArrayPolynomial diff = poly1.minus(poly2);
        result.append("Difference of poly1 and poly2:\n");
        for (double coeff : diff.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Nhân 2 đa thức
        ArrayPolynomial product = poly1.multiply(poly2);
        result.append("Product of poly1 and poly2:\n");
        for (double coeff : product.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Tính giá trị của đa thức tại x = 2
        double valueAtX = poly1.evaluate(2);
        result.append("poly1 evaluated at x = 2: ").append(valueAtX).append("\n");
    }

    public static void testListPolynomial(StringBuilder result) {
        // Tạo đa thức với ListPolynomial
        ListPolynomial poly1 = new ListPolynomial();
        poly1.insertAtStart(3);
        poly1.insertAtEnd(4);
        poly1.insertAtPosition(1, 2);

        // Sửa hệ số tại vị trí index
        poly1.set(1, 6);

        result.append("poly1 coefficients (List): ");
        for (double coeff : poly1.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Cộng 2 đa thức
        ListPolynomial sum = poly1.plus(poly1);
        result.append("Sum of poly1 and poly1:\n");
        for (double coeff : sum.coefficients()) {
            result.append(coeff).append(" ");
        }
        result.append("\n");

        // Tính giá trị của đa thức tại x = -1
        double valueAtX = poly1.evaluate(-1);
        result.append("poly1 evaluated at x = -1: ").append(valueAtX).append("\n");
    }

    public static void testRootSolver(StringBuilder result) {
        // Tạo đa thức có nghiệm trong khoảng [0, 2] (ví dụ x^2 - 2)
        Polynomial poly = new ArrayPolynomial(new double[]{-2, 0, 1});

        // Tạo đối tượng PolynomialRootFinding với phương pháp BisectionSolver
        RootSolver solver = new BisectionSolver(1e-6, 100);
        PolynomialRootFinding rootFinding = new PolynomialRootFinding(poly, solver);

        // Tìm nghiệm trong khoảng [0, 2]
        double root = rootFinding.solve(0, 2);
        result.append("Root found by BisectionSolver: ").append(root).append("\n");

        // Thử phương pháp Newton-Raphson
        solver = new NewtonRaphsonSolver(1e-6, 100);
        rootFinding.setRootSolver(solver);
        root = rootFinding.solve(0, 2);
        result.append("Root found by NewtonRaphsonSolver: ").append(root).append("\n");

        // Thử phương pháp Secant
        solver = new SecantSolver(1e-6, 100);
        rootFinding.setRootSolver(solver);
        root = rootFinding.solve(0, 2);
        result.append("Root found by SecantSolver: ").append(root).append("\n");
    }
}
