class Solution {
    public boolean isNumber(String s) {
        int n = s.length();
        int i = 0;
        
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;
        
        // optional leading sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            i++;
        }
        
        while (i < n) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                seenDigit = true;
                i++;
            } else if (c == '.') {
                // only one dot allowed, and not after 'e'/'E'
                if (seenDot || seenExp) {
                    return false;
                }
                seenDot = true;
                i++;
            } else if (c == 'e' || c == 'E') {
                // only one exponent allowed, and must have seen at least one digit before it
                if (seenExp || !seenDigit) {
                    return false;
                }
                seenExp = true;
                seenDigit = false; // reset: need at least one digit after exponent
                i++;
                // optional sign right after e/E
                if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
                    i++;
                }
            } else {
                // any other character is invalid
                return false;
            }
        }
        
        return seenDigit;
    }
}
