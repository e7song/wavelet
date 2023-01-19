import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Search implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> repository = new ArrayList<>();

    public String handleRequest(URI url) {
        //default
        if (url.getPath().equals("/")) {
            String instructions = 
                    "Search Engine\nAdd: /add?[word]\nPrint:/print\nSearch: /search?[word]";
            return instructions;
        }

        //add
        else if (url.getPath().equals("/add")) {
            String added = url.getQuery();
            repository.add(added);
            return "Added " + added;
        }

        //print

        else if (url.getPath().equals("/print")) {
            return repository.toString();
        }

        //search
        else if (url.getPath().equals("/search")) {
            String search = url.getQuery();
            //search contents
            ArrayList<String> searchContents = new ArrayList<>();

            //check through each word that is added into the repository
            for (int i = 0; i < repository.size(); i++) {
                String repo = repository.get(i);
                //iterate through repo to see if word is in it or is it
                if (repo.contains(search) == true) {
                    searchContents.add(repo);
                }
            }

            return searchContents.toString();
        }

        else {
            return "Function not Supported";
        }
        

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