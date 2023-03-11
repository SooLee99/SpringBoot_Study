package jUnit.component;
// 계산기 인터페이터
public interface ICalculator {

    int sum(int x, int y);

    int minus(int x, int y);

    int multiply(int x, int y);

    int division(int x, int y);

    void init();

}