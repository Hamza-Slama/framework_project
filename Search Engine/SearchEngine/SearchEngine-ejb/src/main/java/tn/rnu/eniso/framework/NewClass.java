/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.DocValues;
import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.LeafReader;
import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.index.NoDeletionPolicy;
import org.apache.lucene.index.SnapshotDeletionPolicy;
import org.apache.lucene.index.SortedDocValues;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;

/**
 *
 * @author Bacem
 */
public class NewClass {
    public static void main(String[] args) {
        try {
            Analyzer analyzer = new StandardAnalyzer();
            Directory directory = new RAMDirectory();
            IndexWriterConfig config = new
        IndexWriterConfig(analyzer);
            SnapshotDeletionPolicy policy = new
        SnapshotDeletionPolicy(NoDeletionPolicy.INSTANCE);
            config.setIndexDeletionPolicy(policy);
            IndexWriter indexWriter = new IndexWriter(directory, config);
            IndexCommit lastSnapshot;
            Document document = new Document();
            indexWriter.addDocument(document);
            indexWriter.commit();
            lastSnapshot = policy.snapshot();
            document = new Document();
            indexWriter.addDocument(document);
            indexWriter.commit();
            lastSnapshot = policy.snapshot();
            document = new Document();
            indexWriter.addDocument(document);
            indexWriter.rollback();
            indexWriter.close();
            List<IndexCommit> commits =
                    DirectoryReader.listCommits(directory);
            System.out.println("Commits count: " + commits.size());
for (IndexCommit commit : commits) {
IndexReader reader = DirectoryReader.open(commit);
System.out.println("Commit " + commit.getSegmentCount());
System.out.println("Number of docs: " + reader.numDocs());
  
}
System.out.println("\nSnapshots count: " +
policy.getSnapshotCount());
List<IndexCommit> snapshots = policy.getSnapshots();
for (IndexCommit snapshot : snapshots) {
IndexReader reader = DirectoryReader.open(snapshot);
System.out.println("Snapshot " +
snapshot.getSegmentCount());
System.out.println("Number of docs: " + reader.numDocs());
}
policy.release(lastSnapshot);
System.out.println("\nSnapshots count: " +
policy.getSnapshotCount());
        } catch (IOException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
