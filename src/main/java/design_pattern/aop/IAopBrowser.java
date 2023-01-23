package design_pattern.aop;


import design_pattern.proxy.Html;

public interface IAopBrowser {
    Html show() throws InterruptedException;
}