package it.unibo.mvc.view;


import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutput implements DrawNumberView{

    @Override
    public void setController(final DrawNumberController observer) {
    }

    @Override
    public void start() {
        System.out.println("New game starts");
    }

    @Override
    public void result(final DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
            }
            case YOU_WON -> {
                System.out.println(res.getDescription());
                this.start();
            }
            case YOU_LOST -> {
                System.out.println(res.getDescription());
                this.start();
            }
            default -> throw new IllegalStateException("Unknown game state");
        }
    }
    
}
