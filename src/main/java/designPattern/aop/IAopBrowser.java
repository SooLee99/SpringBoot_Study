package designPattern.aop;


import designPattern.proxy.Html;

public interface IAopBrowser {
    Html show() throws InterruptedException;
}