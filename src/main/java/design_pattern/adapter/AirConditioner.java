package design_pattern.adapter;

public class AirConditioner implements Electronic220V{
    @Override
    public void connect() {
        System.out.println("220v 에어컨 ON");
    }
}