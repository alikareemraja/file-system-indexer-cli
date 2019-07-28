import Indexer.Indexer;
import Indexer.Util.AnalyzerEnum;
import org.apache.lucene.document.Document;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class CLITool {

    public static void main(String[] args){


        System.out.println("Welcome to the Indexer CLI Tool");

        System.out.println("Please enter a directory to index:");
        Scanner scanner = new Scanner(System.in);

        String directory = scanner.nextLine();

        System.out.println("Please enter a directory to store index:");
        String index_directory = scanner.nextLine();

        Indexer _indexer = new Indexer(new String[]{directory}, index_directory, AnalyzerEnum.STANDARD, 1, 1, TimeUnit.MINUTES);

        while(true){

            System.out.println("Please enter a query to search:");
            String query = scanner.nextLine();

            List<Document> hits = _indexer.searchIndex(query);

            if(hits.size() == 0 || hits == null){
                System.out.println("Your query did not match any files");
            }
            else{
                for (Document match: hits) {
                    System.out.println("Keyword was found in file:");
                    System.out.println(match.getField("path"));
                }
            }

            System.out.println("Would you like to make another query? Please Y:");
            String cont = scanner.nextLine();
            if(!cont.equalsIgnoreCase("Y")){
                _indexer.closeIndexer();
                return;
            }
        }


    }

}
