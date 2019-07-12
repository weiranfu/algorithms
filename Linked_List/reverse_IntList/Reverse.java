/**
     * Returns the reverse of the given IntList.
     * This method is destructive. If given null
     * as an input, returns null.
     */
    public static IntList reverse(IntList A) {
        if (A == null) {
            return null;
        }
        IntList R = null;
        IntList tmp;
        // Construct a reversed list using while loop.
        while (A != null) {
            tmp = A.rest;
            A.rest = R;
            R = A;
            A = tmp;
        }
        return R;
    }