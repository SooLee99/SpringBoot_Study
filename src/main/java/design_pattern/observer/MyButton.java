package design_pattern.observer;

public class MyButton {
    private String buttonName;
    private IButtonClickListener buttonClickListener;

    public MyButton(String buttonName){
        this.buttonName = buttonName;
    }

    public void click(String clickEvent){
        buttonClickListener.clickEvent(this.buttonName+", "+clickEvent);
    }

    public void addListener(IButtonClickListener buttonClickListener){
        this.buttonClickListener = buttonClickListener;
    }
}