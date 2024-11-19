package hus.oop.polynomial;

public abstract class AbstractPolynomial implements Polynomial {
    /**
     * Mô tả đa thức theo định dạng [a0 + a1x + a2x^2 + ... + anx^n]
     * @return String mô tả về đa thức.
     */
    @Override
    public String toString() {
        /* TODO */
        double[] coeffs = coefficients();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coeffs.length; i++) {
            if (coeffs[i] == 0) {
                continue; // Bỏ qua hệ số bằng 0
            }

            if (sb.length() > 0) {
                sb.append(" + "); // Thêm dấu "+" giữa các số hạng
            }

            if (i == 0) {
                sb.append(coeffs[i]); // Hệ số không có x
            } else if (i == 1) {
                sb.append(coeffs[i]).append("x"); // Hệ số có x^1
            } else {
                sb.append(coeffs[i]).append("x^").append(i); // Hệ số có x^n
            }
        }

        return "[" + sb.toString() + "]";
    }

    /**
     * Lấy đạo hàm đa thức.
     * @return mảng các phần tử là hệ số của đa thức đạo hàm.
     */
        public double[] differentiate() {
            /* TODO */
            double[] coeffs = coefficients();
            if (coeffs.length <= 1) {
                return new double[]{0}; // Đạo hàm của hằng số là 0
            }

            double[] derivativeCoeffs = new double[coeffs.length - 1];
            for (int i = 1; i < coeffs.length; i++) {
                derivativeCoeffs[i - 1] = i * coeffs[i];
            }

            return derivativeCoeffs;
        }

}
