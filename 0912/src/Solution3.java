
/**
 * @author YonghShan
 * @date 3/13/21 - 23:06
 */
public class Solution3 {
    // Quick Sort: O(nlogn) in best case (pivot is the median value of the list)
    //                 At the end, we actually construct a balanced binary search tree (BST) out of the list.
    //                 One can infer that the height of the tree would be logn,
    //                 and at each level of the tree the input list would be scanned once with the complexity O(N) due to the partitioning process.
    //             O(n^2) in worst case (pivot is the extreme value of the list, i.e. either the smallest or biggest)
    //                 At each partition we end up with only one single sublist (i.e. the other sublist is empty).
    //                 The reduction of the problem still works, but at a rather slow pace, i.e. one element at a time.
    //                 The partitioning would then occur n times, and each time the partitioning scans at most n elements.
    // Quick sort in worst case ends up to be exactly as Insertion Sort.
    /* Runtime: 4ms
       Memory: 46.9MB
     */
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }
    private void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = partition(nums, l, r);
        quickSort(nums, l, mid-1);   // 如果用的是partition2的写法，必须要用quickSort(nums, l, mid-1)，不然还是到mid的话，会StackOverFlow；但partition1到mid或者mid-1都可
        quickSort(nums, mid + 1, r);
    }
    private int partition(int[] nums, int l, int r) {
        // 类似于Hoare Partition scheme
        /*  algorithm partition(A, lo, hi) is
                pivot := A[ floor((hi + lo) / 2) ]
                i := lo - 1
                j := hi + 1
                loop forever
                    do
                        i := i + 1
                    while A[i] < pivot
                    do
                        j := j - 1
                    while A[j] > pivot
                    if i ≥ j then
                        return j
                    swap A[i] with A[j]
         */
    /*
      Picks the first element l as a pivot
      and returns the index of pivot value in the sorted array */
        int pivot = nums[l];
        while (l < r) {                                                               // 假设当前待排序数组为5 3 2 8 7 6 4， pivot为队首元素5
            while (l < r && nums[r] >= pivot) r--;  // 从右开始，定位小于pivot的元素       // 定位到从右开始第一个小于pivot的元素是末尾的4，此时l指向5，r指向末尾的4
            nums[l] = nums[r];   // 将小于pivot的元素移至前面                             // 对换后：4 3 2 8 7 6 4
            while (l < r && nums[l] <= pivot) l++;  // 从左开始，定位大于pivot的元素       // 定位到从左开始第一个大于pivot的元素是8，此时，l指向8， r指向末尾的4
            nums[r] = nums[l];   // 将大于pivot的元素移至后面                             // 对换后： 4 3 2 8 7 6 8  此时l指向第一个8
        }
        nums[l] = pivot;                                                               // 将l指向的8换成pivot：4 3 2 5 7 6 8
        return l;  // return pivot的index：l=3                                                                     p
    }

    private int partition2(int [] nums, int l, int r) {  // 另外一种partition的写法：会慢很多，而且两种partition方法对于同一nums返回的结果会有些许不同
        // Lomuto Partition scheme
        /*  algorithm partition(A, lo, hi) is
                pivot := A[hi]    // 也可以是随机选的pivot_index，Lomuto的关键是第一步将pivot移至末尾
                i := lo
                for j := lo to hi do
                    if A[j] < pivot then
                        swap A[i] with A[j]   // 下面的代码都是使用三行来完成swap
                        i := i + 1
                swap A[i] with A[hi]
                return i
         */
    /*
      Picks the last element r as a pivot
      and returns the index of pivot value in the sorted array */
        int pivot = nums[r];
        int i = l;                           // 假设当前待排序数组为1 5 3 2 8 7 6 4，pivot为队尾元素4
        for (int j = l; j < r; ++j) {        // 初始时，i=0，j=0
            if (nums[j] < pivot) {           // 从左开始，找第一个小于pivot的数，即队首的1
                int tmp = nums[i];           // swap nums[0] and nums[0]，i变为1，j也变为1，因为nums[1]=5>pivot,所以j继续+，变为2，此时nums[2]=3<pivot，swap nums[1] and nums[2]
                nums[i] = nums[j];           // 得到：1 3 5 2 8 7 6 4，同样继续将5和2交换：1 3 2 5 8 7 6 4，此时i=3，j=3
                nums[j] = tmp;               // j继续向后，一直到r结束循环，所以该for循环的作用为将数列中小于pivot的元素移至前面，大于pivot的移至后面
                i++;
            }
        }
        int tmp = nums[i];                   // 最后，只需要将大于pivot的部分的首元素：5和队尾的pivot互换，即可得到以pivot为界的数组
        nums[i] = nums[r];                   // 交换nums[i]=nums[3]和nums[r]=4，得到：1 3 2 4 8 7 6 5
        nums[r] = tmp;                       //                                          p
        return i;
    }
}
