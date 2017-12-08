package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {
    String cipher_text;
    String plain_text;
    String key;

    @FXML
    private JFXTextArea saeser_plain_text;

    @FXML
    private JFXButton saeser_plain_encrypt;

    @FXML
    private JFXTextArea saeser_cipher_text;

    @FXML
    private JFXPasswordField saeser_plain_key;

    @FXML
    private JFXButton saeser_cipher_decrypt;

    @FXML
    private JFXPasswordField saeser_cipher_key;

    @FXML
    private JFXTextArea play_plain_text;

    @FXML
    private JFXButton play_plain_encrypt;

    @FXML
    private JFXTextArea play_cipher_text;

    @FXML
    private JFXPasswordField play_plain_key;

    @FXML
    private JFXButton play_cipher_decrypt;

    @FXML
    private JFXPasswordField play_cipher_key;

    @FXML
    private JFXTextArea des_plain_text;

    @FXML
    private JFXButton des_plain_encrypt;

    @FXML
    private JFXPasswordField des_plain_key;

    @FXML
    private JFXTextArea des_cipher_text;

    @FXML
    private JFXButton des_cipher_decrypt;

    @FXML
    private JFXPasswordField des_cipher_key;

    @FXML
    void des_decryption(ActionEvent event) {

    }

    @FXML
    void des_encryption(ActionEvent event) {

    }

    @FXML
    void play_decryption(ActionEvent event) {

    }

    @FXML
    void play_encryption(ActionEvent event) {

    }

    @FXML
    void saeser_decryption(ActionEvent event) {
        cipher_text=saeser_cipher_text.getText().replaceAll(" ", "");
        key=saeser_cipher_key.getText();
        if(key != null && !key.isEmpty()&&cipher_text!=null&&!cipher_text.isEmpty()) {
            saeser_plain_text.setText( Caeser.decrypt(cipher_text,Integer.parseInt(key) ));
        }
        else saeser_plain_text.setText( "please enter cipher text and key ");


    }

    @FXML
    void saeser_encryption(ActionEvent event) {

         plain_text=saeser_plain_text.getText().replaceAll(" ", "");
         key=saeser_plain_key.getText();

        if(key != null && !key.isEmpty()&&plain_text!=null&&!plain_text.isEmpty()) {
            saeser_cipher_text.setText( Caeser.encrypt(plain_text,Integer.parseInt(key) ));
        }
        else saeser_cipher_text.setText( "please enter plain text and key ");




    }

}
