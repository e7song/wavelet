import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Search implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> list = new ArrayList<>();

    public String handleRequest(URI url) {

        //add
        if (url.getPath().equals("/add")) {
            try {
                String[] query = url.getQuery().split("=");
                String word = query[1];
                list.add(word);
                return "Successfully Added!";
            } catch(Exception E) {
                return "Unsuccessful. Syntax: /add?s=[word]";
            }

        }
        //search
        if (url.getPath().equals("/search")) {
            ArrayList<String> findings = new ArrayList<>();
            try {
                String[] query = url.getQuery().split("=");
                String word = query[1];
                
                for (int i = 0; i < list.size(); i++) {
                    String term = list.get(i);
                    //found word
                    if (word.equals(term)) {
                        System.out.println("found word");
                        findings.add(term);
                    }
                    //skip if searched word is too big
                    if (word.length() > term.length()) {
                        continue;
                    } else {
                        //find
                        //pineapple
                        //app
                        for (int j = 0; j < term.length()-word.length(); j++) {
                            if (word.equals(term.substring(i, i + word.length()))) {
                                System.out.println(term);
                                findings.add(term);
                            }
                        }
                    }

                    if (findings.size() == 0) {
                        return "Nothing Found.";
                    } else {
                        return findings.toString();
                    }
                }
            } catch (Exception E) {
                return "ERROR";
            }
        }

        if (url.getPath().equals("/")) {
            return "Search Engine";
        }

        return "";

    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Search());
    }
}