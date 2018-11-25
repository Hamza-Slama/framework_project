/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

/**
 *
 * @author Bacem
 */
public class TestingClass {

    public static void main(String[] args) throws IOException {

        Reader reader = new StringReader("PC Portable SCHNEIDER SCL141CTP Quad-Core 2Go 32Go eMMC\n"
                + "Ecran 14.1\" IPS Full HD (1920 x 1080 px) - Processeur: Intel Atom x5-8350 Quad-Core (1,44 GHz up..");
        Analyzer analyzer = new WhitespaceAnalyzer();
        TokenStream ts = analyzer.tokenStream("myField", reader);
        OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);
        CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
        ts.reset();
        while (ts.incrementToken()) {
            String token = termAtt.toString();
            System.out.println("[" + token + "]");
            System.out.println("Token starting offset: " + offsetAtt.
                    startOffset());
            System.out.println(" Token ending offset: " + offsetAtt.
                    endOffset());
            System.out.println("");
        }
        ts.end();
        ts.close();
        analyzer.close();

    }
}
