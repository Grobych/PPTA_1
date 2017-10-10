package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    TextArea inputArea, terminalInputArea, unterminalInputArea;
    @FXML
    Button runButton;
    @FXML
    Label resultLabel;

    @FXML
    public void runButtonClick(){
        try{
            resultLabel.setText("");
            inputArea.setStyle("-fx-border-color: none;");
            terminalInputArea.setStyle("-fx-border-color: none;");
            unterminalInputArea.setStyle("-fx-border-color: none;");

            if (inputArea.getText().length()==0) throw new StringParserException("Поле ввода пусто!",0);
            GrammaticsController.clean();
            GrammaticalParser.parseLitSet(terminalInputArea.getText(), true);
            GrammaticalParser.parseLitSet(unterminalInputArea.getText(), false);
            GrammaticalParser.parseRools(inputArea.getText());

            GrammaticsController.analyze();
            resultLabel.setText(GrammaticsController.type.toString());

        }catch (StringParserException e){
            resultLabel.setText(resultLabel.getText().concat("\n"+e.getMessage()));
            switch (e.getErrorOffset()){
                case 0:{
                    inputArea.setStyle("-fx-border-color: red;");
                    break;
                }
                case 1:{
                    terminalInputArea.setStyle("-fx-border-color: red;");
                    break;
                }
                case 2:{
                    unterminalInputArea.setStyle("-fx-border-color: red;");
                    break;
                }
            }

        }

    }
}
