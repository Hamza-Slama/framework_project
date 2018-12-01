/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import tn.rnu.eniso.framework.IndexingCore.ProductDocumentBuilder;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
public class TestingClass {

    public static void main(String[] args) throws IOException {
      //indexing
      FSDirectory directory = FSDirectory.open(Paths.get("C:\\Users\\Bacem\\Documents\\NetBeansProjects\\LuceneTutorial\\indexes"));
   Analyzer analyzer = new WhitespaceAnalyzer();
IndexWriterConfig config = new IndexWriterConfig( analyzer);
config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
config.setRAMBufferSizeMB(64);
config.setMaxBufferedDocs(4000);
IndexWriter indexWriter = new IndexWriter(directory, config);
        Document document = new Document();
document.add(new StringField("telephone_number", "04735264927",
Field.Store.YES));
document.add(new StringField("area_code", "0484",
Field.Store.NO));
Document document2 = new Document();
String text = "Lucene is an Information Retrieval library"
+"written in Java.";
document2.add(new TextField("text", text, Field.Store.YES));
        ProductDocumentBuilder a = new ProductDocumentBuilder();
Product p = new Product() ;
p.setName("pc hp");
p.setCategory("PC") ;
p.setDescription("pc hp 2go ram nvicia");
p.setId(2L);
p.setPrice(1230);
a.setEntitySource(p);
indexWriter.addDocument(document);
indexWriter.addDocument(document2);
indexWriter.addDocument(a.buildDocument()); 
indexWriter.commit();

//reading 
IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get("C:\\Users\\Bacem\\Documents\\NetBeansProjects\\LuceneTutorial\\indexes")));
document = reader.document(0);
System.out.println("doc 0: " + document.getField("telephone_number"));
document = reader.document(1);
System.out.println("doc 1: " + document.toString());
document = reader.document(2);
System.out.println("doc 2: " + document.toString());


IndexSearcher indexSearcher =  new IndexSearcher(reader);
IndexReader re = indexSearcher.getIndexReader();
   
        
        
      

    }
}
