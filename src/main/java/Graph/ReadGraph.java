package Graph;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ReadGraph {

    private Scanner scanner;

    public ReadGraph(Graph graph,String filename){

        readFile(filename);

        try{
            int V = scanner.nextInt();
            if(V < 0){
                throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            }
            if(V != graph.V()){
                throw new IllegalArgumentException(String.format("%d != %d",V,graph.V()));
            }
            int E = scanner.nextInt();
            if(E < 0){
                throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            }

            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                if(!(v >= 0 && v < V)){
                    throw new IllegalArgumentException(String.format("v:%d need in [0,%d)",v,V));
                }
                if(!(w >= 0 && w < V)){
                    throw new IllegalArgumentException(String.format("w:%d need in [0,%d)",w,V));
                }
                graph.addEdge(v,w);
            }
        }catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException("attempts to read an 'int' value from input stream, but the next token is \"" + token + "\"");
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from input stream, but there are no more tokens available");
        }
    }

    public void readFile(String filename){
        if (filename == null) {
            throw new IllegalArgumentException("filename not empty");
        }
        try{
            File file = new File(filename);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fileInputStream),"UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }else {
                throw new IllegalArgumentException(filename + "doesn't exist.");
            }
        } catch (Exception e){
            throw new IllegalArgumentException("Could not open " + filename, e);
        }
    }

}
