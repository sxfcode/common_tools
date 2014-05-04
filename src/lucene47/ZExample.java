package lucene47;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import algorithm.ReadFileNames;

/**
 * The Class ZExample.
 * 
 * @date 2014-5-4 12:02:04
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ZExample {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		String indexPath = "D:\\搜索引擎\\index";
//		String docPath = "D:\\搜索引擎\\test";
		String docPath = "D:\\思考者\\技术思考";
		//indexFiles(docPath, indexPath);
		searchFiles(indexPath, "java", "contents");
		

	}

	public static void indexFiles(String docPath,String indexPath) throws IOException {
		long start = System.currentTimeMillis();
		System.out.println("创建索引开始");
		// step 1.索引写入器
		Directory dir =FSDirectory.open(new File(indexPath));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_47, analyzer);
		iwc.setOpenMode(OpenMode.CREATE);
		IndexWriter iw = new IndexWriter(dir, iwc);
		// step 2.执行创建索引操作
		List<String> files = ReadFileNames.getFileNames(docPath, false);
		for (String fileName : files) {
			System.out.println("正在为"+fileName+"创建索引");
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(fileName);
			Document doc = new Document();
			Field pathField = new StringField("path",file.getAbsolutePath() , Field.Store.YES);
			Field dateField = new LongField("modified", file.lastModified(), Field.Store.YES);
			Field contentsField = new TextField("contents", new BufferedReader(new InputStreamReader(fis)));
			doc.add(pathField);
			doc.add(dateField);
			doc.add(contentsField);
			if(iw.getConfig().getOpenMode()==OpenMode.CREATE){
				iw.addDocument(doc);
			}else {
				iw.updateDocument(new Term("path",file.getAbsolutePath()), doc);
			}
			fis.close();
		}
		iw.close();
		System.out.println("创建索引结束");
		long end = System.currentTimeMillis();
		System.out.println("创建索引cost:"+(end-start));
	}
	public static void searchFiles(String indexPath,String key,String queryField) throws IOException, ParseException {
		// step 1.索引读取器
		IndexReader reader = DirectoryReader.open(FSDirectory.open(new File(indexPath)));
		IndexSearcher searcher= new IndexSearcher(reader);
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_47);
		QueryParser parser = new QueryParser(Version.LUCENE_47,queryField , analyzer);
		Query query = parser.parse(key);
		// step 2.普通搜索
		searcher.search(query, null,100);
		// step 3.分页搜索
		int hitsPerPage = 5;// 命中数据(每页)
		int numberTotalHist = 0;// 总命中数
		TopDocs results = searcher.search(query, 5*hitsPerPage);
		ScoreDoc[] hits  = results.scoreDocs;
		numberTotalHist= results.totalHits;
		
		if(hits==null||hits.length==0){
			System.out.println("无匹配数据");
		}
		System.out.println("发现匹配数据，正在输出：");
		for (int i = 0; i < numberTotalHist; i++) {
			Document doc = searcher.doc(hits[i].doc);
			System.out.println("path:"+doc.get("path"));
		}
		reader.close();
	}
}
