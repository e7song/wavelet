import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

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

        else if (url.getPath().equals("sunnybunny")) {
            /*File file = new File("wavelet/compliments.txt");
            try {
                Scanner compliment = new Scanner(file);
                int line = (int) (Math.random() * 234);
                int count = 0;
                while (compliment.hasNext()) {
                    String compli = compliment.nextLine();
                    count++;
                    if (count == line) {
                        compliment.close();
                        return compli;
                    }
                    
                }
                compliment.close();
                return "";
            } catch (IOException E) {
                return "File Error";
            }*/

            return ">3<!!";
            
        }

        /*else if (url.getRawPath().equals("/searchbar")) {
            Server.output("Search for the object: ");


        }*/

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