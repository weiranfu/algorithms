## Radix Sort


#### Algorithm

LSD: Least Significant Digit First
MSD: Most Significant Digit First

1. Counting Sort the array base on least significant digit.
2. Counting Sort the previous sorted array base on second least significant digit.
3. Counting Sort .... base on third least significant digit.
4. ...
5. Counting Sort the previous sorted array base on most significant digit.


Time Complexity: O(kn)     k is max length of string

Space Complexity: O(n)



Original:           bda, cfd, qwe, yui, abc, rrr, uue   
Sort on 3th char:   bda, abc, cfd, qwe, uue, yui, rrr   
Sort on 2th char:   abc, bda, cfd, rrr, uue, yui, qwe    
Sort on 1th char:   abc, bda, cfd, qwe, rrr, uue, yui    


