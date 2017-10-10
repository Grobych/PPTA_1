package sample;

import java.util.*;

/**
 * Created by Alex on 04.10.2017.
 */
public class GrammaticalParser {

    public static String[] multisplit(String line, String targets[]){
        List<String> stringList = new ArrayList<>();
        Stack<String> stringStack = new Stack<>();
        stringStack.push(line);
        for (String s : targets) {
            while (!stringStack.empty()){
                String temp = stringStack.pop();
                stringList.addAll(Arrays.asList(temp.split(s)));
            }
            stringStack.addAll(stringList);
            stringList.clear();
        }
        String result[] = new String[stringStack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stringStack.pop();
            System.out.print(result[i]+" ");
        }
        return result;
    }

    public static void parseRools(String lines) throws StringParserException {
        setRoolsList(lines.split("\n"));
    }
    public static void setRoolsList(String[] rools) throws StringParserException {
        if ((rools.length==0)||(rools==null)){
            throw new StringParserException("Incorrect input!",0);
        }
        for (String line : rools) {
            String temp[] = line.split("->");
            if (temp.length == 1) throw new StringParserException("Incorrect input! INLine->OUTLine",0);
            GrammaticsController.roolsList.add(new Rool(temp[0],temp[1]));
        }
    }
    public static void parseLitSet(String line, boolean isTerminal) {
        List<String> lines = new LinkedList<>(Arrays.asList(multisplit(line, new String[]{" ", ", ", ",", ";"})));
        if (isTerminal) GrammaticsController.terminalList = lines;
        else GrammaticsController.unterminalList = lines;
    }

    public static void analyzeGrammatic(){
        GrammaticsController.roolsList.forEach(System.out::println);
        GrammaticsController.terminalList.forEach(System.out::println);
        GrammaticsController.unterminalList.forEach(System.out::println);

    }

    private static LitType getCharType(char c){
        if (GrammaticsController.terminalList.contains(String.valueOf(c))) return LitType.TERM;
        if (GrammaticsController.unterminalList.contains(String.valueOf(c))) return LitType.UNTERM;
        if (c=='*') return LitType.EPSILON;
        return LitType.NONE;
    }
    public static Direction getDirection(String line){
        char array[] = line.toCharArray();
        LitType first = getCharType(array[0]);
        LitType last;
        int i = 0;
        while ((i<array.length)&&(getCharType(array[i])==first)){
            i++;
        }
        if (i<array.length){
            last = getCharType(array[i]);
            while (i<array.length && getCharType(array[i])==last){
                i++;
            }
            if (i == array.length){
                switch (first){
                    case TERM: return Direction.RIGHT;
                    case UNTERM: return  Direction.LEFT;
                }
            }
        }
        return Direction.NONE;
    }
}
