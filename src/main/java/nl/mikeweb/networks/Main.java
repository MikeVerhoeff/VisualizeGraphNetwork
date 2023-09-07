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
        System.out.println("Done");
    }


}
