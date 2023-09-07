package nl.mikeweb.networks;

public class Main {



    public static void main(String[] args) {
        System.setProperty("org.graphstream.ui", "javafx");
        System.out.println("Start");

        //Matrix m = new Matrix( new float[][] {{1.0f, 2.0f}, {3.0f, 4.0f}});
        //System.out.println(m);
        //System.out.println(Matrix.Vector(new float[]{1.0f, 2.0f, 3.0f}));

        Matrix m1 = Matrix.Vector(new float[]{1, 2, 0});
        Matrix m2 = Matrix.Vector(new float[]{0, 1, 2});
        System.out.println(m1.mult(m2.transpose()));


        //DemoAllInFx.main(args);

        Matrix A = new Matrix(10, 10);
        A.addAdjacencyEdge(0,1);
        A.addAdjacencyEdge(0,2);
        A.addAdjacencyEdge(0,3);
        A.addAdjacencyEdge(0,4);

        //A.addAdjacencyEdge(4, 9);
        //A.addAdjacencyEdge(9,2);
        //A.addAdjacencyEdge(4,2);


        DisplayAdjacencyMatrix.showGraph(A);

        System.out.println("Done");
        System.exit(0);
    }


}
