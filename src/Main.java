import java.io.*;
import java.util.StringTokenizer;

public class Main {

    /* IO */
    InputReader in;
    BufferedWriter out;
    StringTokenizer tok;
    StringBuilder ans;

    /* FIELDS */

    public static void main(String[] args) throws IOException {
        Main sol = new Main();
        sol.begin();
    }

    private void begin() throws IOException {
        // io
        boolean file = false;
        if (file)
            in = new InputReader(new FileInputStream("input.txt"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();


        // output to file
        out = new BufferedWriter(new FileWriter("output.txt"));
        out.write(ans.toString());
        out.flush();
        out.close();
    }

}

class InputReader {
    BufferedReader reader;
    StringTokenizer tok;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tok = new StringTokenizer("");
    }

    public String next() {
        while (!tok.hasMoreTokens())
            try {
                tok = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return tok.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}