package org.zjy.learn.code.google;

public class ClassicAlgorithm {
    // findMedianSortedArrays
    public double findMedianSortedArrays(int nums1[], int nums2[]) {
        int len = nums1.length + nums2.length;
        if (len % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, len / 2 + 1);
        }
        return (findKth(nums1, 0, nums2, 0, len / 2) + findKth(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
    }

    private static int findKth(int[] A, int A_start, int[] B, int B_start, int k) {
        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }
        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }
        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }
    // binary search 1
    public int binarySearch1(int[] a, int target) {
        int start = 0, end = a.length - 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(a[mid] == target) {
                return mid;
            } else if(a[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
    // binary search 2
    public int binarySearch2(int[] a, int target) {
        int start = 0, end = a.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(a[mid] == target) {
                end = mid; // 如果target有很多重复的，返回的是下界，start = mid，返回上界
            } else if(a[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (a[start] == target) {
            return start;
        } else if (a[end] == target) {
            return end;
        } else {
            return -1;
        }
    }
}
