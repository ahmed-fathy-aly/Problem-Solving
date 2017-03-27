import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main
{

    /* IO */
    InputReader in;
    StringTokenizer tok;
    StringBuilder ans;

    /* FIELDS */

    public static void main(String[] args) throws IOException
    {
        Main sol = new Main();
        sol.begin();
    }

    private void begin() throws IOException
    {
        // io
        boolean file = true;
        if (file)
            in = new InputReader(new FileInputStream("input.txt"));
        else
            in = new InputReader(System.in);
        ans = new StringBuilder();

    }

}
class InputReader
{
    BufferedReader reader;
    StringTokenizer tok;

    public InputReader(InputStream stream)
    {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tok = new StringTokenizer("");
    }

    public String next()
    {
        while (!tok.hasMoreTokens())
            try
            {
                tok = new StringTokenizer(reader.readLine());
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        return tok.nextToken();
    }

    public int nextInt()
    {
        return Integer.parseInt(next());
    }
}