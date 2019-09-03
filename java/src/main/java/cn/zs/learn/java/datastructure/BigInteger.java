package cn.zs.learn.java.datastructure;

public abstract class BigInteger {
    int signum;
    char[] mag;
    abstract BigInteger add(BigInteger bigInteger);
    abstract BigInteger subtract(BigInteger bigInteger);
    abstract BigInteger multiply(BigInteger bigInteger);
    abstract BigInteger divide(BigInteger bigInteger);

}
