import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }

        // Sorting enables early pruning when a candidate exceeds the remaining target.
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
            int val = candidates[i];
            if (val > remaining) {
                // Since `candidates` is sorted, no later element will fit.
                break;
            }

            current.add(val);
            // Pass `i` (not `i + 1`) because the same number can be reused unlimited times.
            backtrack(candidates, remaining - val, i, current, result);
            current.remove(current.size() - 1);
        }
    }
}