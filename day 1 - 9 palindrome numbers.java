class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers are never palindromes
        // Numbers ending in 0 (but not 0 itself) are never palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        
        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }
        
        // For even digit count: x == reversedHalf
        // For odd digit count: middle digit is dropped by reversedHalf/10
        return x == reversedHalf || x == reversedHalf / 10;
    }
}
