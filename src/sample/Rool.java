package sample;

/**
 * Created by Alex on 05.10.2017.
 */
public class Rool {
    private String in;
    private String out;
    private LitType typeIn;
    private LitType typeOut;

    public Rool(String inLine, String outLine){
        if ((inLine==null)||(outLine==null)) throw new NullPointerException("Rool line might not be empty");
        this.in = inLine;
        this.out = outLine;
        setTypeInOut();
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public LitType getTypeIn() {
        return typeIn;
    }

    public LitType getTypeOut() {
        return typeOut;
    }

    //    public void setIn(String in) {
//        this.in = in;
//    }
//
//    public void setOut(String out) {
//        this.out = out;
//    }

    @Override
    public String toString(){
        return new String("in: "+in+"  out: "+out+";  Type IN: "+getTypeIn()+";  Type OUT: "+getTypeOut());
    }

    private void setTypeInOut(){
        typeIn = getType(in);
        typeOut = getType(out);
    }

    private LitType getType(String line){
        boolean termBool = false;
        boolean untermBool = false;
        boolean epsiBool = false;
        char temp[] = line.toCharArray();
        char term[] = GrammaticsController.terminalList.toString().toCharArray();
        char unterm[] = GrammaticsController.unterminalList.toString().toCharArray();

        for (char c : temp) {
            if (contains(c, term)) termBool = true;
            if (contains(c, unterm)) untermBool = true;
            if (c=='*') epsiBool = true;
        }
        if (epsiBool) return  LitType.EPSILON;
        if (termBool&&!untermBool) return  LitType.TERM;
        if (!termBool&&untermBool) return LitType.UNTERM;
        if (termBool&&untermBool) return  LitType.BOTH;
        if (!termBool&&!untermBool) return  LitType.NONE;
        return LitType.NONE;
    }

    private boolean contains(char lit, char array[]){
        for (char c : array) {
            if (c==lit) return true;
        }
        return false;
    }

}
