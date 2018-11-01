public class Tuple{

    private int[] tupArray;

    public Tuple (int k){
        if (k < 0){
            throw new IllegalArgumentException();
        }
        this.tupArray = new int[k];
        for (int i = 0; i < k; i++){
            this.tupArray[i] = 0;
        }
    }

    public Tuple (int[] data){
        this.tupArray = data;
    }

    public static Tuple makeTupleFromData (int[] data){
        Tuple newTuple = new Tuple(data);
        return newTuple;
    }

    public Tuple add (Tuple t){
        // Returns a new tuple whose elements are the sums of the respective elements of this tuple and tuple t
        if (this.tupArray.length != t.length()){
            throw new IllegalArgumentException();
        }
        if (tupArray.length == 0){
            Tuple zeroTuple = new Tuple(0);
            return zeroTuple;
        }
        int[] result = new int[this.tupArray.length];
        for (int i = 0; i < this.tupArray.length; i++){
            result[i] = this.tupArray[i] + t.getElement(i);
        }
        Tuple answerTuple = new Tuple(result);
        return answerTuple;
    }

    public Tuple clone(){
        Tuple cloneTuple = new Tuple(this.tupArray);
        return cloneTuple;
    }

    public boolean equals (Object obj){
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Tuple insertedTuple = (Tuple)obj;
        return toString().equals(insertedTuple.toString());
    }

    public int getElement (int i){
        if (i < 0){
            throw new IllegalArgumentException();
        }
        return this.tupArray[i];
    }

    public void setElement (int i, int value){
        if (i < 0){
            throw new IllegalArgumentException();
        } else if (i > this.tupArray.length -1){
            throw new IllegalArgumentException();
        }
        this.tupArray[i] = value;
    }

    public int length(){
        return this.tupArray.length;
    }

    public int sum(){
        // Returns the sum of the elements in this tuple.
        if (tupArray.length == 0){
            return 0;
        }
        int tupleSum = 0;
        for (int i = 0; i < this.tupArray.length; i ++){
            tupleSum += this.tupArray[i];
        }
        return tupleSum;
    }

    public String toString(){
        if (tupArray.length == 0){
            return "[]";
        }
        String tupleString = "[";
        for (int i = 0; i < this.tupArray.length; i++){
            tupleString += this.tupArray[i] + ",";
        }
        tupleString = tupleString.substring(0,tupleString.length() -1);
        tupleString += "]";
        return tupleString;
    }
}
