/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.IndexingCore;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import jdk.nashorn.internal.runtime.Context;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

/**
 *
 * @author Bacem
 */
public class DocumentIndexer <T> {
    private final T ob = (T) new Object();
  private   Path path ;
   private  IndexWriter indexWriter ;
 private    IndexSearcher indexSearcher ;

 
    public DocumentIndexer() throws IOException {
         this.path = Paths.get( "C:\\Users\\Bacem\\Desktop\\fwk\\"+ob.getClass().getSimpleName() );
         FSDirectory directory = FSDirectory.open(path);
         Analyzer analyzer = new WhitespaceAnalyzer();
IndexWriterConfig config = new
IndexWriterConfig( analyzer);
//config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
this.indexWriter = new IndexWriter(directory, config);
this.indexWriter.commit();
DirectoryReader directoryReader = DirectoryReader.open(directory);
 this.indexSearcher = new IndexSearcher(directoryReader);

    }



    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public IndexWriter getIndexWriter() {
        return indexWriter;
    }

    public void setIndexWriter(IndexWriter indexWriter) {
        this.indexWriter = indexWriter;
    }

    public IndexSearcher getIndexSearcher() {
        return indexSearcher;
    }

    public void setIndexSearcher(IndexSearcher indexSearcher) {
        this.indexSearcher = indexSearcher;
    }

    
}
