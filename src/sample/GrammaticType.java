package sample;

/**
 * Created by Alex on 04.10.2017.
 */
public enum GrammaticType {
    ZERO_TYPE,
    FIRST_TYPE,
    SECOND_TYPE,
    THIRD_LEFT_TYPE,
    THIRD_RIGHT_TYPE,
    NONE;

    @Override
    public String toString() {
        switch (this.ordinal()){
            case 0: return "Грамматика нулевого типа";
            case 1: return "Тип 1: Контекстно-зависимая грамматика";
            case 2: return "Тип 2: Контекстно-свободная грамматика";
            case 3: return "Тип 3: Регулярная грамматика, выровненная влево";
            case 4: return "Тип 4: Регулярная грамматика, выровненная вправо";
            case 5: return "Error!";
            default: return "Error1";
        }
    }
}
