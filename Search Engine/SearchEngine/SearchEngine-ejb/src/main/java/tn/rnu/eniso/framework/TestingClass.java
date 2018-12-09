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
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import tn.rnu.eniso.framework.IndexingCore.DocumentIndexer;
import tn.rnu.eniso.framework.IndexingCore.ProductDocumentBuilder;
import tn.rnu.eniso.framework.Services.SearchQueryService;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author Bacem
 */
public class TestingClass {

    public static void main(String[] args) throws IOException, ParseException {
   
       DocumentIndexer indexer = new DocumentIndexer("product");
        WhitespaceAnalyzer anal = new WhitespaceAnalyzer();
     

        MultiFieldQueryParser parser = new MultiFieldQueryParser(
                new String[]{"name", "description"},
                anal);

        TopDocs result = indexer.getIndexSearcher().search(parser.parse(" LED 4 GO Ram hp   "), 10);
        System.out.println("res "+  result.totalHits);
    }
}
