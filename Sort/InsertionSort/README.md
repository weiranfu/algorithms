# Insertion Sort

#### Attention

* On arrays with small number of inversions, insertion sort is extremely fast.
* For small array (N < 15), insertion sort is fastest.
  So in java merge sort, if N < 15, Collections.sort will switch to insertion sort.

#### Algorithm
For In-Place insertion sort:
1. Designate item i as travelling item
2. Compare item backwards with other items util traveler is at right place among all previously sorted items. 

Time complexity: O(n^2), the lower bound is O(n)   
Space complexity: O(1)

For Naive insertion sort:
1. Starting with an empty output sequence
2. Insert every item into output at right place.

Insertion item into an array needs move all items one step, 
so it will be useful for linked list rather than array.

Time complexity: O(n^2), if there's k inversions, O(n + k)  
Space complexity: O(n)
