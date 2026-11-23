class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirst(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findLast(nums, target, first);
        return new int[]{first, last};
    }

    private int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int bound = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    bound = mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return bound;
    }

    private int findLast(int[] nums, int target, int startLow) {
        int low = startLow;
        int high = nums.length - 1;
        int bound = startLow;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    bound = mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return bound;
    }
}