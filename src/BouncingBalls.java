import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;


/******************************************************************************
 *  Compilation:  javac BouncingBalls.java
 *  Execution:    java BouncingBalls n
 *  Dependencies: Ball.java StdDraw.java
 *
 *  Creates an array of n bouncing balls and animates them.
 *
 *  % java BouncingBalls 10
 *
 ******************************************************************************/

public class BouncingBalls {

    public static void main(String[] args) {

        // number of bouncing balls
        //int n = Integer.parseInt(args[0]);
        int n = 10;
        // set boundary to box with coordinates between -1 and +1
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);

        // create an array of n random balls
        Ball[] balls = new Ball[n];
        for (int i = 0; i < n; i++)
            balls[i] = new Ball();

        // do the animation loop
        StdDraw.enableDoubleBuffering();
        while (true) {

            // move the n balls
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < n; j++)
                    balls[i].checkCollision(balls[j]);
                balls[i].move();
            }

            // draw the n balls
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            for (int i = 0; i < n; i++) {
                balls[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}

/******************************************************************************
 *  Compilation:  javac Ball.java
 *  Execution:    java Ball
 *  Dependencies: StdDraw.java
 *
 *  Implementation of a 2-d Ball moving in square with coordinates
 *  between -1 and 1. Bounces off the walls upon collision.
 *
 *
 ******************************************************************************/

class Ball {

    private final double radius;  // radius
    // instance variables
    private double rx, ry;        // position
    private double vx, vy;        // velocity

    // constructor
    public Ball() {
        rx = 0.0;
        ry = 0.0;
        vx = StdRandom.uniform(-0.015, 0.015);
        vy = StdRandom.uniform(-0.015, 0.015);
        radius = StdRandom.uniform(0.025, 0.075);
    }

    // test client
    public static void main(String[] args) {

        // create and initialize two balls
        Ball b1 = new Ball();
        Ball b2 = new Ball();

        // animate them
        StdDraw.setXscale(-1.0, +1.0);
        StdDraw.setYscale(-1.0, +1.0);
        StdDraw.enableDoubleBuffering();

        while (true) {
            StdDraw.clear(StdDraw.GRAY);
            StdDraw.setPenColor(StdDraw.BLACK);
            b1.move();
            b2.move();
            b1.draw();
            b2.draw();
            StdDraw.show();
            StdDraw.pause(20);
        }
    }

    // bounce of vertical wall by reflecting x-velocity
    private void bounceOffVerticalWall() {
        vx = -vx;
    }

    // bounce of horizontal wall by reflecting y-velocity
    private void bounceOffHorizontalWall() {
        vy = -vy;
    }

    private void wallsCollision() {
        if (Math.abs(rx + vx) + radius > 1.0) bounceOffVerticalWall();
        if (Math.abs(ry + vy) + radius > 1.0) bounceOffHorizontalWall();
    }

    public void checkCollision(Ball ball) {
        double ballSize = Math.abs(ball.rx + ball.vx) + ball.radius;
        if (Math.abs(rx + vx) + radius > ballSize) bounceOffHorizontalWall();
    }
    public void changeDirection(){

    }

    // move the ball one step
    public void move() {
        wallsCollision();
        rx = rx + vx;
        ry = ry + vy;
    }

    // draw the ball
    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

}