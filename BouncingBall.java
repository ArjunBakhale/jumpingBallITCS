import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class BouncingBall extends JPanel {

    //TODO: set the initial width and height of your image
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    //required global variables
    private BufferedImage image;
    private int numtouch = 0;
    private Graphics g;
    private Timer timer;
    //TODO: change this to whatever object(s) you are animating
    //make 14 more private balls from 1-15
    private Ball balls[] = new Ball[5];
    private JumpBall jumpBalls[] = new JumpBall[100];




    //Constructor required by BufferedImage
    public BouncingBall() {
        //set up Buffered Image and Graphics objects
        image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();

        //array of 15 balls with different sizes and colors and speeds
        //make an array of 15 uniqute balls
        Color colors[] = {Color.red, Color.blue, Color.green, Color.yellow, Color.orange, Color.pink, Color.magenta, Color.cyan};
        for (int i = 0; i < balls.length; i++) {
            int randIndex = (int)(Math.random()*colors.length);
            int randomDiameter = (int)(Math.random()*50+50);
            int colorIndex =(int)(Math.random()*colors.length);
            balls[i] = new Ball(100, 100, randomDiameter, colors[colorIndex]);

            balls[i].setRandomSpeed(10);
        }

        //set up the JumpBall

        for (int i=0; i<jumpBalls.length; i++){
            int randomDiameter = (int)(Math.random()*25+25);
            jumpBalls[i] = new JumpBall((int)(Math.random()*WIDTH), (int)(Math.random()*HEIGHT), randomDiameter, colors[(int)(Math.random()*colors.length)]);
            jumpBalls[i].setInitialVelocity(5, (int)(Math.random()*360));
        }








        //set up and start the Timer
        timer = new Timer(10, new TimerListener());
        timer.start();

    }

    //TimerListener class that is called repeatedly by the timer
    private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /* TODO: Move the objects that need to be animated
             * 		 Draw your ENTIRE scene
             * 		 Don't forget to call repaint!
             */
            //change backround to a lighter sha

            g.setColor(new Color(0, 191, 255));

            g.fillRect(0, 0, WIDTH, HEIGHT);
            //draw the cloud
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);

            g.setColor(Color.white);



            for (int i = 0; i < balls.length; i++) {
                balls[i].move(WIDTH, HEIGHT);
                balls[i].draw(g);

                for (int c = 0; c < jumpBalls.length; c++) {
                    jumpBalls[c].draw(g);
                }

                //check jumpball collision
                for (int j = 0; j < jumpBalls.length; j++) {
                    if (jumpBalls[j].intersectsWith(balls[i])) {
                        jumpBalls[j].move(WIDTH, HEIGHT);


numtouch++;



                    }
                }


            }
            g.setColor(Color.WHITE);
            String output = "Number of times the ball was touched: " + numtouch;
            g.drawString(output, WIDTH/10, HEIGHT/20);
            repaint(); //leave this alone, it MUST  be the last thing in this method
        }

    }

    //do not modify this
    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    //main method with standard graphics code
    public static void main(String[] args) {
        JFrame frame = new JFrame("Animation Shell");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(0, 0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BouncingBall()); //TODO: Change this to the name of your class!
        frame.setVisible(true);
    }

}