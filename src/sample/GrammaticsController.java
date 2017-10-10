package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 05.10.2017.
 */
public class GrammaticsController {
    static List<Rool> roolsList = new ArrayList<Rool>();
    static List<String> terminalList = new ArrayList<>();
    static List<String> unterminalList = new ArrayList<>();
    static String S = "S";
    static GrammaticType type;

    static {
        unterminalList.add(S);
    }

    public static boolean checkLength(Rool rool){
        return (rool.getIn().length() <= rool.getOut().length()) ? true : false;
    }
    public static boolean isCrossover(){
        for (String s : terminalList) {
            if (unterminalList.contains(s)) return true;
        }
        return false;
    }

    public static boolean checkZeroType(){
        if (isCrossover()) return false;
        for (Rool rool : roolsList) {
            if (rool.getIn().equals(S)) {
                System.out.println("LOL");
                return true;
            }
        }
        return false;
    }
    public static boolean checkFirstType(){
        for (Rool rool : roolsList) {
            if (rool.getIn().contains("*")) return false;
            if (rool.getIn()==S) continue;
            if (!checkLength(rool)) return false;
        }
        return true;
    }
    public static boolean checkSecondType(){
        for (Rool rool : roolsList) {
            if (rool.getIn().equals(S)) continue;
            if (rool.getTypeIn()!=LitType.UNTERM) return false;
        }
        return true;
    }

    public static boolean checkThirdType(){
        boolean left = false;
        boolean right = false;
        boolean none = false;
        Direction direction;

        for (Rool rool : roolsList) {
            if (rool.getIn().equals(S)) continue;
            if (rool.getTypeIn()!=LitType.UNTERM) return false;
            if (rool.getTypeOut()==LitType.TERM) continue;
            if (rool.getTypeOut()==LitType.BOTH) {
                direction = GrammaticalParser.getDirection(rool.getOut());
                System.out.println(direction);
                switch (direction){
                    case LEFT: left = true; break;
                    case RIGHT: right = true; break;
                    case NONE:  none = true; break;
                }
            }
        }
        if (none) return false;
        if (left&&!right) type = GrammaticType.THIRD_LEFT_TYPE;
        if (!left&&right) type = GrammaticType.THIRD_RIGHT_TYPE;
        if ((!left&&!right)||(left&&right)) return false;
        return true;
    }

    public static void clean(){
        terminalList.clear();
        unterminalList.clear();
        roolsList.clear();
    }

    public static void analyze(){
        if (checkZeroType()) type = GrammaticType.ZERO_TYPE;
        else {
            type = GrammaticType.NONE;
            return;
        }
        if (checkFirstType()) type = GrammaticType.FIRST_TYPE;
        else return;

        if (checkSecondType()) type = GrammaticType.SECOND_TYPE;
        else return;

        if (!checkThirdType()) return;
    }

}
