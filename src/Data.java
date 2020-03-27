import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Data {
    File dir = new File("resources/stl nonplaner test2.stl");
    Reader readerFile;
    public double[][][] doubleNormal;
    public double[][][] doubleVertex;

    public Data() throws IOException {
        readerFile = new FileReader(dir);
        BufferedReader reader = new BufferedReader(readerFile);
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        readerFile = new FileReader(dir);
        reader = new BufferedReader(readerFile);

        String facetNormal;
        String[] vertex = new String[3];
        String[] stringNormal;
        String[][] stringVertex = new String[3][3];

        int numTriangles = (lines-2)/7;
        doubleNormal = new double[numTriangles][4][1];
        doubleVertex = new double[numTriangles][4][3];

        int triangle = 0;
        boolean read = true;        
        
        reader.readLine(); //skip model name
        while (read) {
            facetNormal = reader.readLine();
            reader.readLine(); //read outer loop
            vertex[0] = reader.readLine();
            vertex[1] = reader.readLine();
            vertex[2] = reader.readLine();
            reader.readLine(); //read end loop
            reader.readLine(); //read end facet
            if (vertex[2] != null) {
                stringNormal = facetNormal.substring(16).split(" ");

                for (int i=0; i<stringNormal.length; i++) {
                    doubleNormal[triangle][i][0] = Double.parseDouble(stringNormal[i]);
                }
                doubleNormal[triangle][3][0] = 1;
                for (int i=0; i<vertex.length; i++) {
                    stringVertex[i] = vertex[i].substring(16).split(" ");
                    for (int j=0; j<3; j++) {
                        doubleVertex[triangle][j][i] = Double.parseDouble(stringVertex[i][j]);
                        doubleVertex[triangle][3][i] = 1;
                    }
                }
                triangle++;
            }
            else {
                read = false;
            }
        }
        readerFile.close();
    }

    public double[][][] getNormal() {
        return doubleNormal;
    }
    public double[][][] getVertex() {
        return doubleVertex;
    }
}