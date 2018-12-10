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
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.runtime.Context;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import tn.rnu.eniso.framework.MyConstantes;

/**
 *
 * @author Bacem
 */
public class DocumentIndexer {
    
  private   Path path ;
   private  IndexWriter indexWriter ;
 private    IndexSearcher indexSearcher ;

 
    public DocumentIndexer( String path) throws IOException {
         this.path = Paths.get( MyConstantes.inderxerPathCste +path );
         FSDirectory directory = FSDirectory.open(this.path);
         Analyzer analyzer = new WhitespaceAnalyzer();
IndexWriterConfig config = new
IndexWriterConfig( analyzer);
config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
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

    public void close() {
      try {
          indexWriter.close();
          indexSearcher.getIndexReader().close();
          indexWriter.getDirectory().close();
      } catch (IOException ex) {
          Logger.getLogger(DocumentIndexer.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    
}
