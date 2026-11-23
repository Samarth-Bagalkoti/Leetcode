class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.isEmpty() || num2.isEmpty()) {
            return "0";
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int sum = d1 * d2 + pos[i + j + 1];

                pos[i + j + 1] = sum % 10;
                pos[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder(m + n);
        int idx = 0;
        // Skip leading zeros in `pos`.
        while (idx < pos.length && pos[idx] == 0) {
            idx++;
        }
        while (idx < pos.length) {
            sb.append(pos[idx++]);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }
}