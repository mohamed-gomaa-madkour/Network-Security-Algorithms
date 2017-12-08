package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

public class Controller {
    String cipher_text;
    String plain_text;
    String key;

    File file;
    public static long[]des_cipher;
    public static long[] des_plain;
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
    public JFXTextArea des_plain_text;

    @FXML
    private JFXButton des_plain_encrypt;

    @FXML
    private JFXPasswordField des_plain_key;

    @FXML
    public JFXTextArea des_cipher_text;

    @FXML
    private JFXButton des_cipher_decrypt;

    @FXML
    private JFXPasswordField des_cipher_key;


    @FXML
    void file_chooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        Stage primaryStage = null;
        file = fileChooser.showOpenDialog(primaryStage);
        des_plain_encrypt.setDisable(false);
        System.out.println(file);

    }

    @FXML
    void des_decryption(ActionEvent event) {

    }

    @FXML
    void des_encryption(ActionEvent event) {
        key=des_plain_key.getText();
        byte[] bytes;
        String s="";


        if(key != null && !key.isEmpty()) {
            DESInterface desInterface=new DESInterface(file,key);

            for (long block : des_cipher) {
                bytes = ByteBuffer.allocate(8).putLong(block).array();

                s+=new String(bytes);///


            }

            des_cipher_text.setText(s);
            s="";


            for (long block : des_plain) {
                bytes = ByteBuffer.allocate(8).putLong(block).array();

                s+=new String(bytes);///

            }

            des_plain_text.setText(s);
            s="";
        }
        else des_cipher_text.setText( "please chooce file and Secret Keyword ");






    }

    @FXML
    void play_decryption(ActionEvent event) {
        PlayFair Pf;
        cipher_text=play_cipher_text.getText();
        key=play_cipher_key.getText();
        if(key != null && !key.isEmpty()&&cipher_text!=null&&!cipher_text.isEmpty()) {
            Pf=new PlayFair(key);
            play_plain_text.setText(Pf.decode(Pf.parseString(cipher_text)));
        }
        else play_cipher_text.setText( "please enter cipher text and Secret Keyword ");

    }

    @FXML
    void play_encryption(ActionEvent event) {
        //String output = cipher(input);

        PlayFair Pf;
        plain_text=play_plain_text.getText();
        key=play_plain_key.getText();
        if(key != null && !key.isEmpty()&&plain_text!=null&&!plain_text.isEmpty()) {
            Pf=new PlayFair(key);
            play_cipher_text.setText(Pf.cipher(Pf.parseString(plain_text)));
        }
        else play_cipher_text.setText( "please enter plain text and Secret Keyword ");

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
    @FXML
    void hyperlink(ActionEvent event){

        try {
            openWebpage(new URI("https://github.com/mohamed-gomaa-madkour/Network-Security-Algorithms/tree/master"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


    public static void openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
