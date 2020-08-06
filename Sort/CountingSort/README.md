# Counting Sort

#### Attention

In order to keep the original order of items in array (make it stable sort) 

we use count[] to store total num of items ahead of item i.

#### Algorithm

1. Create an array to store the frequency of each item,
   the array length will be max - min.
2. Iterate the array to get the sorted items.

Time Complexity: O(N + M),  M is max - min.

Space Complexity: O(N)
