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
            case 0: return "���������� �������� ����";
            case 1: return "��� 1: ����������-��������� ����������";
            case 2: return "��� 2: ����������-��������� ����������";
            case 3: return "��� 3: ���������� ����������, ����������� �����";
            case 4: return "��� 4: ���������� ����������, ����������� ������";
            case 5: return "Error!";
            default: return "Error1";
        }
    }
}
