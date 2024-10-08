package zec.basics.services;

/**
 * 计算服务接口，提供加法和减法的操作。
 */
public interface CalService {

    /**
     * 计算两个整数的和。
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个整数的和
     */
    int add(int a, int b);

    /**
     * 计算两个整数的差值。
     *
     * @param a 第一个整数
     * @param b 第二个整数
     * @return 两个整数的差值
     */
    int sub(int a, int b);
}