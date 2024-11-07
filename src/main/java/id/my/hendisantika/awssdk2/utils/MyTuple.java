package id.my.hendisantika.awssdk2.utils;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aws-sdk2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 07/11/24
 * Time: 11.49
 * To change this template use File | Settings | File Templates.
 */
public class MyTuple {
    public static class MyTuple2<T1, T2> {
        public final T1 t1;
        public final T2 t2;

        public MyTuple2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }
    }

    public static class MyTuple3<T1, T2, T3> {
        public final T1 t1;
        public final T2 t2;
        public final T3 t3;

        public MyTuple3(T1 t1, T2 t2, T3 t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }
    }

    public static class MyTuple4<T1, T2, T3, T4> {
        public final T1 t1;
        public final T2 t2;
        public final T3 t3;
        public final T4 t4;

        public MyTuple4(T1 t1, T2 t2, T3 t3, T4 t4) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
            this.t4 = t4;
        }
    }
}
