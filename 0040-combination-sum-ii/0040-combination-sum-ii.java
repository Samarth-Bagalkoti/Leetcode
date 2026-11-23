import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        // Sorting groups duplicates together and enables early pruning.
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(
            int[] candidates,
            int remaining,
            int startIndex,
            List<Integer> current,
            List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // Skip duplicate values at the same recursion depth to avoid duplicate combinations.
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            int val = candidates[i];
            if (val > remaining) {
                break;
            }

            current.add(val);
            // Advance to `i + 1` since each element can be used at most once.
            backtrack(candidates, remaining - val, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}