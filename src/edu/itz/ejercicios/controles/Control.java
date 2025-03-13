package edu.itz.ejercicios.controles;

import edu.itz.ejercicios.vistas.Ventana;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class Control {
    Ventana v;
    public Control(Ventana v) {
        this.v = v;
    }

    public void abrirArchivo() {
        limpiar();
        // v.getTxtContenido().setText("Hola Mundo !!!");
        String path = null;
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(v);
        if (returnValue == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
            v.getLblArchivo().setText(path);
        }
        if (path == null){
            JOptionPane.showMessageDialog(v, "No hay un archivo seleccionado");
            return; 
        } 
        leerArchivo(path);
    }
    
    public void limpiar() {
        v.getTxtContenido().setText("");
        v.getLblArchivo().setText(""); 
        v.getTxtSalida().setText("");
    }
    
    public void leerArchivo(String archivo){
        String texto=""; 
        try {BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine())!= null) {
                texto+=linea+"\n";
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        v.getTxtContenido().append(texto+"\n");
    }
    
    public void contar(){
        int letras = 0, numeros = 0, otros = 0; 
        String c = v.getTxtContenido().getText();
        v.getTxtSalida().setText(c.length()+ "\n");
        char a = c.charAt(0); 
        v.getTxtSalida().append(a+"\n");
        for (int i = 0; i < c.length(); i++) {
            a = c.charAt(i);
            if (Character.isLetter(a)){
                letras++; 
            } else if (Character.isDigit(a)){
                numeros++; 
            } else {
                otros++;
            }
        }
        v.getTxtSalida().append("Numero de letas es: "+"\n"+letras+"\n"+"Cantida"
                + "d de numeros:"+"\n"+numeros +"\n"+"Numeros de caracteres:"+""
                        + "\n"+otros+"\n");
        String palabra = v.getTxtContenido().getText();
        if(palabra.matches("\\b[a-zA-Z_][a-zA-Z-0-9_]\\b")){
            v.getTxtSalida().append("Si hace match");
        }else {
            v.getTxtSalida().append("No match");
        }
    }
        
    public void encuentraIdNum(){
            String texto = v.getTxtContenido().getText();
            v.getTxtSalida().setText("");
            Pattern patron = Pattern.compile("[a-zA-Z]\\w*|[1-9]\\d*|0");
            Matcher match = patron.matcher(texto); 
            while(match.find()){
                v.getTxtSalida().append(match.group()+"\n");
            }
        }
    public void validaRFC(){
            String rfc = v.getTxtContenido().getText();
            v.getTxtSalida().setText(""); 
            Pattern patron = Pattern.compile("[A-Z]{3,4}(9\\d|[01]\\d|2[0-5])"
                    + "(0[1-9]|1[0-2])(0[1-9]|[12][0-9]3[01])[A-Z0-9]{3}"); 
            Matcher match = patron.matcher(rfc); 
            while(match.find()){
                v.getTxtSalida().append(match.group()+"\n");
            }
    }
    public void validaHexadecimal(){
            String hexa = v.getTxtContenido().getText();
            v.getTxtSalida().setText(""); 
            Pattern patron = Pattern.compile("^(0x)[0-9A-F]+"); 
            Matcher match = patron.matcher(hexa); 
            while(match.find()){
                v.getTxtSalida().append(match.group()+"\n");
            }
    }
    public void validaCorreo(){
            String correo = v.getTxtContenido().getText();
            v.getTxtSalida().setText(""); 
            Pattern patron = Pattern.compile("^\\w[\\w\\.-]*@[\\w\\.-]{2,}"); 
            Matcher match = patron.matcher(correo); 
            while(match.find()){
                v.getTxtSalida().append(match.group()+"\n");
            }
    }
    public void idConAFD(){
        String texto = v.getTxtContenido().getText();
        v.getTxtSalida().setText("");
        for (int i = 0; i < texto.length(); i++) { 
        // System.out.println(texto.charAt(i));
        char c = texto.charAt(i); 
        String id = ""; 
            if (Character.isLetter(c)){
                id = "";
                while(Character.isLetterOrDigit(c)||c == '_'){
                    id += c ; 
                    i++;
                    if (i >= texto.length())break; 
                    c = texto.charAt(i);
                }
            }
        v.getTxtSalida().append(id + "\n");
            if (Character.isDigit(c)){
                id = "";
                if (c == '0'){
                    id += c;
                    c = texto.charAt(++i);
                    if (c == '.'){
                    id += c;
                    c = texto.charAt(++i);
                        while(Character.isDigit(c)){
                            id += c;  
                            i++; 
                            c = texto.charAt(i);
                        }   
                    }
                } else {
                    while (Character.isDigit(c)){
                        id += c; 
                        i++; 
                        c = texto.charAt(i);
                    }
                    if (c == '.'){
                    id += c;
                    c = texto.charAt(++i);
                        while(Character.isDigit(c)){
                            id += c;  
                            i++; 
                            c = texto.charAt(i);
                        }   
                        while(Character.isDigit(c)){
                            id += c;
                            i++; 
                            c = texto.charAt(i);
                        }
                    } 
                }
            }
            v.getTxtSalida().append(id + "\n");
        }
    }
}
 