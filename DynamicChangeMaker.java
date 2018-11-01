public class DynamicChangeMaker{

    public static Tuple IMPOSSIBLE = new Tuple(0);
    private Tuple[][] twoDTupleArray;
    private int wantedChange;
    private int[] denominations;

    public DynamicChangeMaker(int[] denominations, int wantedChange){
        if (wantedChange < 0){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < denominations.length; i++){
            if (denominations[i] < 1){
                throw new IllegalArgumentException();
            }
            for(int k= i+1; k < denominations.length; k++){
                if (k != i && denominations[i] == denominations[k]){
                    throw new IllegalArgumentException();
                }
            }
        }
        this.wantedChange = wantedChange;
        this.denominations = denominations;
        twoDTupleArray = new Tuple[wantedChange + 1][denominations.length];
        solveTable();
    }

    public static boolean isImpossible(Tuple tuple){
        return tuple.equals(IMPOSSIBLE);
    }

    public void solveTable(){
        for(int row = 0; row < this.denominations.length; row++){
            for(int column=0; column < this.wantedChange + 1; column++){
                this.twoDTupleArray[column][row] = new Tuple(denominations.length);

                if (column >= 1){
                    int removeCoin = column - this.denominations[row];
                    if (removeCoin < 0){
                        this.twoDTupleArray[column][row] = IMPOSSIBLE;
                    } else {
                        this.twoDTupleArray[column][row].setElement(row, 1);
                        if (!isImpossible(this.twoDTupleArray[removeCoin][row])){
                            this.twoDTupleArray[column][row] = this.twoDTupleArray[column][row].
                                    add(this.twoDTupleArray[removeCoin][row]);
                            } else {
                            this.twoDTupleArray[column][row] = IMPOSSIBLE;
                        }
                    }
                    if (row >= 1){
                        Tuple aboveTuple = this.twoDTupleArray[column][row-1].clone();
                        int currentTupleSum = this.twoDTupleArray[column][row].sum();
                        int aboveTupleSum = aboveTuple.sum();
                        if (isImpossible(this.twoDTupleArray[column][row]) && !isImpossible(aboveTuple)){
                            this.twoDTupleArray[column][row] = aboveTuple;
                        } else if (!isImpossible(this.twoDTupleArray[column][row]) && !isImpossible(aboveTuple)){
                            if (aboveTupleSum < currentTupleSum){
                                this.twoDTupleArray[column][row] = aboveTuple;
                            }
                        }
                    }
                }

                System.out.println("Row: +" + row + " col: " + column);
                System.out.println(twoDTupleArray[column][row]);

            }
        }
    }

    public Tuple getSolution(){
        return this.twoDTupleArray[this.wantedChange][this.denominations.length -1];
    }

    /*
    *returns the optimal solution in string format
    */
    public String solutionString(){
        String resultString = "";
        if (isImpossible(this.getSolution())){
            return "IMPOSSIBLE";
        } else {
            for (int i = 0; i < this.denominations.length; i++){
                resultString += "\n" + this.getSolution().getElement(i) + " " + this.denominations[i]+ "-cent coins";
            }
            resultString += "\n" + "Total coins: " + this.getSolution().sum() + "\n";
        }
        return resultString;
    }

    public static void main(String[] args){
        try {
            if (args.length < 2){
                System.out.println("Please input at least 2 arguments");
            } else {
                int wantedChange = Integer.parseInt(args[args.length -1]);
                int[] denominations = new int[args.length - 1];
                for (int i = 0; i < denominations.length; i ++){
                    denominations[i] = Integer.parseInt(args[i]);
                }
                DynamicChangeMaker testDynamicChangeMaker = new DynamicChangeMaker(denominations, wantedChange);
                        System.out.println(testDynamicChangeMaker.solutionString());
            }
        } catch (NumberFormatException nfe){
            System.out.println("Wrong Format! Please input integers only");
        } catch (IllegalArgumentException iae){
            System.out.println("Please input denominations greater than 0, no duplicate denominations, " +
                    "and a positive value for wanted change");
        }
    }
}
