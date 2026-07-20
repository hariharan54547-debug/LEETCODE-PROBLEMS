class Solution {
    public int divide(int dividend, int divisor) {
        // Handle overflow: the only case that overflows 32-bit int range
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        // Determine sign of result
        boolean negative = (dividend < 0) != (divisor < 0);
        
        // Use long and absolute values to avoid overflow issues (especially with MIN_VALUE)
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        
        long result = 0;
        
        while (absDividend >= absDivisor) {
            long temp = absDivisor;
            long multiple = 1;
            // Double the divisor (via bit shift) as long as it still fits within dividend
            while (absDividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            absDividend -= temp;
            result += multiple;
        }
        
        return negative ? (int) -result : (int) result;
    }
}
