/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senai.sc.util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Caio Pereira
 */
public class MaskFields {
    
    public MaskFormatter maskData(JFormattedTextField textfield) throws ParseException{
        MaskFormatter mask = new MaskFormatter("##/##/####");
        mask.setOverwriteMode(true);
        mask.setValidCharacters("0123456789");
        mask.setPlaceholderCharacter('_');
        mask.install(textfield);
        return mask;
    }
    
    public MaskFormatter maskCpf(JFormattedTextField textfield) throws ParseException{
        MaskFormatter mask = new MaskFormatter("###.###.###-##");
        mask.setOverwriteMode(true);
        mask.setValidCharacters("0123456789");
        mask.setPlaceholderCharacter('_');
        mask.install(textfield);
        return mask;
    }
    public MaskFormatter maskPlaca(JFormattedTextField textfield) throws ParseException{
        MaskFormatter mask = new MaskFormatter("***-####");
        mask.setOverwriteMode(true);
        mask.setValidCharacters("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        mask.setPlaceholderCharacter('_');
        mask.install(textfield);
        return mask;
    }
    
    
    
}
