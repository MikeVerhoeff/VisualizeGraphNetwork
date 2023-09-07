package nl.mikeweb.networks;

public class Matrix {
    float[][] data;

    // initialization

    public Matrix(int width, int height) {
        data = new float[height][width];
    }

    public Matrix(float[][] data) {
        this.data = data;
    }

    public static Matrix identity(int n) {
        Matrix m = new Matrix(n, n);
        for(int i=0; i<n; i++) {
            m.data[i][i] = 1;
        }
        return m;
    }

    public static Matrix Vector(int d) {
        Matrix m = new Matrix(1,d);
        return m;
    }

    public static Matrix Vector(float[] data) {
        Matrix m = Vector(data.length);
        for(int i = 0; i < data.length; i++) {
            m.data[i][0] = data[i];
        }
        return m;
    }

    // add and remove columns

    public int addColumn() {
        float[][] newdata = new float[data.length][data[0].length+1];
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                newdata[i][j] = data[i][j];
            }
        }
        data = newdata;
        return newdata[0].length-1;
    }

    public void removeColumn(int c) {
        float[][] newdata = new float[data.length][data[0].length-1];
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[0].length; j++) {
                newdata[i][j] = data[i][j>=c?j+1:j];
            }
        }
        data = newdata;
    }

    // graph edges

    public void addAdjacencyEdge(int a, int b) {
        data[a][b]=1.0f;
        data[b][a]=1.0f;
    }

    public void clearAdjacencyEdge(int a, int b) {
        data[a][b]=0.0f;
        data[b][a]=0.0f;
    }

    public void addIncidenceEdge(int from, int to) {
        int i = addColumn();
        data[from][i] = 1.0f;
        data[to][i] = -1.0f;
    }

    public void clearIncidenceEdge(int from, int to) {
        int column = -1;
        for(int i = 0; i < data[0].length; i++) {
            if(data[from][i]==1.0f && data[to][i]==-1.0f) {
                column = i;
            }
        }
        removeColumn(column);
    }

    // math

    public int width() {
        return data[0].length;
    }

    public int height() {
        return data.length;
    }

    public void set(int column, int row, float value) {
        data[row][column] = value;
    }

    public Matrix transpose() {
        Matrix m = new Matrix(data.length, data[0].length);
        for(int i=0; i<data.length; i++) {
            for(int j=0; j<data[0].length; j++) {
                m.data[j][i] = data[i][j];
            }
        }
        return m;
    }

    public Matrix add(Matrix that) {
        assert (this.width() == that.width()) && (that.height() == that.height());
        Matrix c = new Matrix(width(), height());
        for(int i=0; i<data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                c.data[i][j] = this.data[i][j]+that.data[i][j];
            }
        }
        return c;
    }

    public Matrix sub(Matrix that) {
        assert (this.width() == that.width()) && (that.height() == that.height());
        Matrix c = new Matrix(width(), height());
        for(int i=0; i<data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                c.data[i][j] = this.data[i][j]-that.data[i][j];
            }
        }
        return c;
    }

    public Matrix neg() {
        Matrix c = new Matrix(width(), height());
        for(int i=0; i<data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                c.data[i][j] = -this.data[i][j];
            }
        }
        return c;
    }

    public Matrix mult(Matrix that) {
        assert this.width() == that.height();
        Matrix c = new Matrix(that.width(), this.height());
        for(int i=0; i < c.data.length; i++) {
            for (int j = 0; j < c.data[0].length; j++) {
                float sum=0;
                for (int k = 0; k < this.width(); k++) {
                    sum += this.data[i][k] * that.data[k][j];
                }
                c.data[i][j] = sum;
            }
        }
        return c;
    }

    // to string

    @Override
    public String toString() {
        String result = "[\n";
        for(int i=0; i< data.length; i++) {
            result += "  [";
            for(int j=0; j< data[i].length; j++) {
                result += data[i][j]+", ";
            }
            result += "]\n";
        }
        return result+"]";
    }
}
