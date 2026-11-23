class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();
        int sIdx = 0;
        int pIdx = 0;
        int starIdx = -1;
        int matchIdx = 0;

        while (sIdx < sLen) {
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
                sIdx++;
                pIdx++;
            } else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                // Record wildcard position; initially match 0 characters of `s`.
                starIdx = pIdx;
                matchIdx = sIdx;
                pIdx++;
            } else if (starIdx != -1) {
                // Mismatch: backtrack to right after the last '*' and consume one more char in `s`.
                pIdx = starIdx + 1;
                matchIdx++;
                sIdx = matchIdx;
            } else {
                return false;
            }
        }

        // Consume any trailing '*' characters in pattern `p`.
        while (pIdx < pLen && p.charAt(pIdx) == '*') {
            pIdx++;
        }

        return pIdx == pLen;
    }
}