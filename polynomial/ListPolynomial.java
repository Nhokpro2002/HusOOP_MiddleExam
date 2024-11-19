package hus.oop.polynomial;

import java.util.ArrayList;
import java.util.List;

public class ListPolynomial extends AbstractPolynomial {
    private List<Double> coefficients;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public ListPolynomial() {
        coefficients = new ArrayList<>();
    }

    /**
     * Khởi tạo đa thức với mảng hệ số đầu vào.
     */
    public ListPolynomial(double[] coeffs) {
        coefficients = new ArrayList<>();
        for (double coeff : coeffs) {
            coefficients.add(coeff);
        }
    }

    @Override
    public double coefficientAt(int index) {
        if (index < 0 || index >= coefficients.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return coefficients.get(index);
    }

    @Override
    public double[] coefficients() {
        double[] coeffArray = new double[coefficients.size()];
        for (int i = 0; i < coefficients.size(); i++) {
            coeffArray[i] = coefficients.get(i);
        }
        return coeffArray;
    }

    public void insertAtStart(double coefficient) {
        coefficients.add(0, coefficient);
    }

    public void insertAtEnd(double coefficient) {
        coefficients.add(coefficient);
    }

    public void insertAtPosition(int index, double coefficient) {
        if (index < 0 || index > coefficients.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        coefficients.add(index, coefficient);
    }

    public void set(int index, double coefficient) {
        if (index < 0 || index >= coefficients.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        coefficients.set(index, coefficient);
    }

    @Override
    public int degree() {
        return coefficients.size() - 1;
    }

    @Override
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.size(); i++) {
            result += coefficients.get(i) * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public Polynomial derivative() {
        if (coefficients.size() <= 1) {
            return new ListPolynomial(new double[]{0});
        }

        double[] derivativeCoeffs = new double[coefficients.size() - 1];
        for (int i = 1; i < coefficients.size(); i++) {
            derivativeCoeffs[i - 1] = i * coefficients.get(i);
        }
        return new ListPolynomial(derivativeCoeffs);
    }

    public ListPolynomial plus(ListPolynomial another) {
        int maxDegree = Math.max(this.degree(), another.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i < this.coefficients.size() ? this.coefficients.get(i) : 0;
            double b = i < another.coefficients.size() ? another.coefficients.get(i) : 0;
            resultCoeffs[i] = a + b;
        }
        return new ListPolynomial(resultCoeffs);
    }

    public ListPolynomial minus(ListPolynomial another) {
        int maxDegree = Math.max(this.degree(), another.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i < this.coefficients.size() ? this.coefficients.get(i) : 0;
            double b = i < another.coefficients.size() ? another.coefficients.get(i) : 0;
            /*
            Điều kiện (i < this.coefficients.size()): Kiểm tra xem chỉ số i có nhỏ hơn kích thước của danh sách coefficients hay không.
            Giá trị nếu đúng (this.coefficients.get(i)): Nếu chỉ số i hợp lệ (tức là nó nằm trong phạm vi của danh sách), giá trị tại vị trí i trong danh sách coefficients sẽ được lấy.
            Giá trị nếu sai (0): Nếu chỉ số i vượt quá phạm vi (tức là không có hệ số ở vị trí đó), giá trị 0 sẽ được sử dụng thay thế.
             */
            resultCoeffs[i] = a - b;
        }
        return new ListPolynomial(resultCoeffs);
    }

    public ListPolynomial multiply(ListPolynomial another) {
        int resultDegree = this.degree() + another.degree();
        double[] resultCoeffs = new double[resultDegree + 1];

        for (int i = 0; i < this.coefficients.size(); i++) {
            for (int j = 0; j < another.coefficients.size(); j++) {
                resultCoeffs[i + j] += this.coefficients.get(i) * another.coefficients.get(j);
            }
        }
        return new ListPolynomial(resultCoeffs);
    }
}
