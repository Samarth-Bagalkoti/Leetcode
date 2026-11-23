import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || s.isEmpty() || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int numWords = words.length;
        int totalLen = wordLen * numWords;

        if (s.length() < totalLen) {
            return result;
        }

        Map<String, Integer> target = new HashMap<>();
        for (String word : words) {
            target.put(word, target.getOrDefault(word, 0) + 1);
        }

        // Only `wordLen` starting offsets exist since every word has length `wordLen`.
        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = offset; right <= s.length() - wordLen; right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (target.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;

                    // Shrink from the left if `word` exceeds its allowed frequency.
                    while (seen.get(word) > target.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }

                    // Exact match of all words in `words`.
                    if (count == numWords) {
                        result.add(left);

                        // Advance `left` by one word to continue searching for overlapping matches.
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        count--;
                        left += wordLen;
                    }
                } else {
                    // Unrecognized word breaks the contiguous window; reset state.
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }
}