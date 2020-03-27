import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class Start extends JFrame {
    private static final long serialVersionUID = 1L;

    public Start() throws IOException {
        GamePanel game = new GamePanel();
        this.setBounds(200, 200, 0, 0);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(game);
        // this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public class GamePanel extends JPanel implements ActionListener {
        private static final long serialVersionUID = 1L;
        private Color[] colorN;
        private InputManager im;
        Timer timer = new Timer(40, this);
        private Data triangles;
        private double[][][] triangleNormal;
        private double[][][] triangleVertex;
        private double[] scaleUp = new double[] {100,100,100};
        private double[] scaleDown = new double[] {0.01,0.01,0.01};
        private double[] translateOut = new double[] {400,250,0};
        private double[] translateIn = new double[] {-400,-250,0};
        
        public GamePanel() throws IOException {
            triangles = new Data();
            triangleNormal = triangles.getNormal();
            triangleVertex = triangles.getVertex();

            for (int i=0; i<triangleVertex.length; i++) {
                triangleVertex[i] = MathFNC.rotateX(triangleVertex[i],180.1);
                triangleVertex[i] = MathFNC.rotateY(triangleVertex[i],180);
                triangleNormal[i] = MathFNC.rotateX(triangleNormal[i],180.1);
                triangleNormal[i] = MathFNC.rotateY(triangleNormal[i],180);
                triangleVertex[i] = MathFNC.matrixScale(triangleVertex[i], scaleUp);
                triangleVertex[i] = MathFNC.matrixTranslate(triangleVertex[i], translateOut);
            }     

            im = new InputManager();
            this.addKeyListener(im);
            this.setFocusable(true);
            colorN = new Color[triangleNormal.length];
            for (int i=0; i<triangleNormal.length; i++) {
                colorN[i] = new Color((float)(triangleNormal[i][0][0]+1)/2,(float)(triangleNormal[i][1][0]+1)/2,(float)(triangleNormal[i][2][0]+1)/2);
            }
            timer.start();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            super.paintComponent(g2d);
            
            for (int i=0; i<triangleVertex.length; i++) {
                g2d.setColor(colorN[i]);
                g2d.setStroke(new BasicStroke(3));
                if (triangleNormal[i][2][0] > 0) {
                    g2d.drawLine((int)triangleVertex[i][0][0], (int)triangleVertex[i][1][0], (int)triangleVertex[i][0][1], (int)triangleVertex[i][1][1]);
                    g2d.drawLine((int)triangleVertex[i][0][1], (int)triangleVertex[i][1][1], (int)triangleVertex[i][0][2], (int)triangleVertex[i][1][2]);
                    g2d.drawLine((int)triangleVertex[i][0][2], (int)triangleVertex[i][1][2], (int)triangleVertex[i][0][0], (int)triangleVertex[i][1][0]);
                } 
            }  
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == timer) {
                int[] keyVector = {(im.isKeyDown(KeyEvent.VK_LEFT) ? 1 : 0)  + (im.isKeyDown(KeyEvent.VK_RIGHT) ? -1 : 0),
                                   (im.isKeyDown(KeyEvent.VK_UP) ? -1 : 0)   + (im.isKeyDown(KeyEvent.VK_DOWN)  ? 1 : 0)};
                if (keyVector[0] != 0 || keyVector[1] != 0) {
                    for (int i=0; i<triangleVertex.length; i++) {
                        triangleVertex[i] = MathFNC.matrixTranslate(triangleVertex[i], translateIn);
                        triangleVertex[i] = MathFNC.rotateX(triangleVertex[i],(double)keyVector[1]*4);
                        triangleVertex[i] = MathFNC.rotateY(triangleVertex[i],(double)keyVector[0]*4);
                        triangleVertex[i] = MathFNC.matrixTranslate(triangleVertex[i], translateOut);
                        triangleNormal[i] = MathFNC.rotateX(triangleNormal[i],(double)keyVector[1]*4);
                        triangleNormal[i] = MathFNC.rotateY(triangleNormal[i],(double)keyVector[0]*4);
                    }
                    repaint();
                }
                im.update();
            }  
        }
    }
    public static void main(String[] args) throws IOException {
        new Start();
    }
}