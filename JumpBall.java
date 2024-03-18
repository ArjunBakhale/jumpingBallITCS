
import java.awt.Color;

public class JumpBall extends Ball{
    private double xAcceleration;
    private double yAcceleration;

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(double xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(double yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public JumpBall(int x, int y, int diameter, Color color){
        super(x, y, diameter, color);
        this.xAcceleration = 0;
        this.yAcceleration = 0;
    }

    public void setInitialVelocity(int speed, int angle){
        double xVelocity = speed * Math.cos(Math.toRadians(angle));
        double yVelocity = speed * Math.sin(Math.toRadians(angle));
        setxSpeed(xVelocity);
        setySpeed(yVelocity);
    }

    public int findDistanceFrom(int x, int y){
        return (int) Math.sqrt(Math.pow(getX()-x, 2) + Math.pow(getY()-y, 2));
    }

    public boolean intersectsWith(Ball ball){
        if (findDistanceFrom((int)ball.getX(), (int)ball.getY()) <= getRadius() + ball.getRadius()){
            return true;
        }
        return false;

    }



    public void move(int rightEdge, int bottomEdge){
        int xPos = (int)(Math.random()*rightEdge);
        int yPos = (int)(Math.random()*bottomEdge);
        setX(xPos);
        setY(yPos);

    }





}
