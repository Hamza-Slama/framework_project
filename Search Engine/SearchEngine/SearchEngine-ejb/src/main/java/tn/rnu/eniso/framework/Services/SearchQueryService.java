/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.rnu.eniso.framework.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.SynchronizationType;
import jdk.nashorn.internal.runtime.Context;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import tn.rnu.eniso.framework.IndexingCore.DocumentIndexer;
import tn.rnu.eniso.framework.models.Product;

/**
 *
 * @author martin
 */
@Stateless
public class SearchQueryService {

    @EJB
    private ProductService productService;

    public void indexQuery(String a) throws IOException {

        DocumentIndexer indexer = new DocumentIndexer("searhQueryWord");
        Document doc = new Document();
        doc.add(new TextField("searchQuery", a, Field.Store.YES));
        Term term = new Term("searhQueryWord");
        indexer.getIndexWriter().updateDocument(term, doc);
        indexer.getIndexWriter().commit();
        indexer.close();
    }

    public List<String> allQueries(String word) throws IOException, ParseException {
        ArrayList<String> re = new ArrayList<>();
        DocumentIndexer indexer = new DocumentIndexer("searhQueryWord");
        WhitespaceAnalyzer anal = new WhitespaceAnalyzer();
        QueryParser parser = new QueryParser("searchQuery", anal);
        TopDocs result = indexer.getIndexSearcher().search(parser.parse(word), 10);
        for (ScoreDoc scoredoc : result.scoreDocs) {
            re.add(indexer.getIndexSearcher().doc(scoredoc.doc).getField("searchQuery").stringValue());
        }

        indexer.close();

        return re;
    }

    public List<Product> searchForProduct(String word) throws IOException, ParseException {
        ArrayList<Long> re = new ArrayList<>();
        DocumentIndexer indexer = new DocumentIndexer("product");
        WhitespaceAnalyzer anal = new WhitespaceAnalyzer();

        MultiFieldQueryParser parser = new MultiFieldQueryParser(
                new String[]{"name", "description"},
                anal);

        TopDocs result = indexer.getIndexSearcher().search(parser.parse(word), 10);
            Logger.getLogger(SearchQueryService.class.getName()).log(Level.SEVERE, null, result.totalHits);
        for (ScoreDoc scoredoc : result.scoreDocs) {
        
            re.add(indexer.getIndexSearcher().doc(scoredoc.doc).getField("id")
                    .numericValue().longValue()
            );
        }

        indexer.close();

        return  this.productService.findProuductsByIdList(re);
    }
}
