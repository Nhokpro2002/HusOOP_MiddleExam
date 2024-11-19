package hus.oop.polynomial;

import java.util.Arrays;

public class ArrayPolynomial extends AbstractPolynomial {
    private static final int DEFAULT_CAPACITY = 8;
    private double[] coefficients;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public ArrayPolynomial() {
        coefficients = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Khởi tạo với hệ số đầu vào.
     */
    public ArrayPolynomial(double[] coeffs) {
        /* TODO */
        this.size = coeffs.length;
        this.coefficients = Arrays.copyOf(coeffs, Math.max(size, DEFAULT_CAPACITY));
    }

    @Override
    public double coefficientAt(int index) {
        /* TODO */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return coefficients[index];
    }

    @Override
    public double[] coefficients() {
        /* TODO */
        return Arrays.copyOf(coefficients, size);
    }

    @Override
    public void insertAtStart(double coefficient) {
        /* TODO */
        if (size == coefficients.length) {
            allocateMore();
        }
        System.arraycopy(coefficients, 0, coefficients, 1, size);
        coefficients[0] = coefficient;
        size++;
    }

    @Override
    public void insertAtEnd(double coefficient) {
        /* TODO */
        if (size == coefficients.length) {
            allocateMore();
        }
        coefficients[size++] = coefficient;
    }

    @Override
    public void insertAtPosition(int index, double coefficient) {
        /* TODO */
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == coefficients.length) {
            allocateMore();
        }
        System.arraycopy(coefficients, index, coefficients, index + 1, size - index);
        coefficients[index] = coefficient;
        size++;
    }

    @Override
    public void set(int index, double coefficient) {
        /* TODO */
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        coefficients[index] = coefficient;
    }

    @Override
    public int degree() {
        /* TODO */
        return size - 1;
    }

    @Override
    public double evaluate(double x) {
        /* TODO */
        double result = 0;
        for (int i = 0; i < size; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public Polynomial derivative() {
        /* TODO */
        if (size <= 1) {
            return new ArrayPolynomial(new double[]{0});
        }
        double[] derivativeCoeffs = new double[size - 1];
        for (int i = 1; i < size; i++) {
            derivativeCoeffs[i - 1] = i * coefficients[i];
        }
        return new ArrayPolynomial(derivativeCoeffs);
    }

    public ArrayPolynomial plus(ArrayPolynomial another) {
        /* TODO */
        int maxDegree = Math.max(this.degree(), another.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i < this.size ? this.coefficients[i] : 0;
            double b = i < another.size ? another.coefficients[i] : 0;
            resultCoeffs[i] = a + b;
        }
        return new ArrayPolynomial(resultCoeffs);
    }

    public ArrayPolynomial minus(ArrayPolynomial another) {
        /* TODO */
        int maxDegree = Math.max(this.degree(), another.degree());
        double[] resultCoeffs = new double[maxDegree + 1];

        for (int i = 0; i <= maxDegree; i++) {
            double a = i < this.size ? this.coefficients[i] : 0;
            double b = i < another.size ? another.coefficients[i] : 0;
            resultCoeffs[i] = a - b;
        }
        return new ArrayPolynomial(resultCoeffs);
    }

    public ArrayPolynomial multiply(ArrayPolynomial another) {
        /* TODO */
        int resultDegree = this.degree() + another.degree();
        double[] resultCoeffs = new double[resultDegree + 1];

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < another.size; j++) {
                resultCoeffs[i + j] += this.coefficients[i] * another.coefficients[j];
            }
        }
        return new ArrayPolynomial(resultCoeffs);
    }

    private void allocateMore() {
        coefficients = Arrays.copyOf(coefficients, coefficients.length * 2);
    }
}
