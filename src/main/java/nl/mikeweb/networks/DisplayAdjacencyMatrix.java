package nl.mikeweb.networks;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.fx_viewer.FxViewPanel;
import org.graphstream.ui.fx_viewer.FxViewer;
import org.graphstream.ui.layout.springbox.implementations.SpringBox;

import java.util.Random;

public class DisplayAdjacencyMatrix extends Application {
    protected static String styleSheet =
            "graph {"+
                    "	padding: 60px;"+
                    "}";


    Parent parent;

    public static void main(String args[]) {
        Application.launch(DemoAllInFx.class, args);
    }

    public static void showGraph(Matrix a) {
        Platform.startup(() -> {
            System.out.println("starting javafx");
        });

        DisplayAdjacencyMatrix application = new DisplayAdjacencyMatrix();
        Parent p = createGraph(a);
        application.parent = p;

        Platform.runLater(()->{
            // create primary stage
            Stage stage = new Stage();
            stage.setOnCloseRequest((e)->{
                synchronized (application.parent) {
                    application.parent.notifyAll();
                }
            });
            try {
                application.init();
                application.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        try {
            synchronized (application.parent) {
                application.parent.wait();
                application.stop();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static Parent createGraph(Matrix a) {
        assert a.width() == a.height();
        assert a.equals(a.transpose());
        Graph graph = new MultiGraph("mg");

        for(int i=0; i<a.width(); i++) {
            graph.addNode(""+i);
        }

        for(int i=0; i<a.width(); i++) {
            for(int j=0; j<i; j++) {
                if(a.index(i, j)==1.0f) {
                    graph.addEdge(i+"-"+j, ""+i, ""+j);
                }
            }
        }

        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        SpringBox layout = new SpringBox(false,new Random(0));
        viewer.enableAutoLayout(layout);
        FxViewPanel v =  (FxViewPanel) viewer.addDefaultView( false ) ;
        
        return v;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*Graph graph  = new MultiGraph("mg");

        FxViewer viewer = new FxViewer(graph, FxViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CA", "C", "A");
        graph.setAttribute( "ui.antialias" );
        graph.setAttribute( "ui.quality" );
        graph.setAttribute( "ui.stylesheet", styleSheet );

        //graph.getNode("A").setAttribute("xyz", -1, 0, 0 );
        //graph.getNode("B").setAttribute("xyz",  1, 0, 0 );
        //graph.getNode("C").setAttribute("xyz",  0, 1, 0 );

        // On ins�re la vue principale du viewer dans la JFrame.

        SpringBox layout = new SpringBox(false,new Random(0));
        viewer.enableAutoLayout(layout);
        FxViewPanel v =  (FxViewPanel) viewer.addDefaultView( false ) ;
        Scene scene = new Scene(v, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();

         */

        // create primary stage
        Scene scene = new Scene(parent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
