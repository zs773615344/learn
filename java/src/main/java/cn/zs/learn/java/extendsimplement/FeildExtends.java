package cn.zs.learn.java.extendsimplement;

import java.util.Map;
import java.util.Set;

public class FeildExtends {
    public static void main(String[] args) {
        /*Son son = new Son();
        son.setArgs("hehe");
        System.out.println(son.getArgs());
        System.out.println(((Parent)son).getArgs());
        son.arg="haha";
        System.out.println(son.arg);
        System.out.println(((Parent)son).arg);

        System.out.println("neibulei");*/

//        FeildExtends feildExtends1 = new FeildExtends();
//        FeildExtends feildExtends2 = new FeildExtends();
//        System.out.println(feildExtends2.new Parent().getClass() == (feildExtends1.new Parent()).getClass());
//        System.out.println(Parent.class.getName());
        new FeildExtends.Son();
    }
     static class Parent {
        private String args="haha";
        public String arg;
        public Parent(){
            System.out.println(args);
            System.out.println(arg);
            System.out.println(getArgs());
            System.out.println("#################");
            System.out.println(get());
            System.out.println(arg);
            this.arg = "xixi";
            System.out.println(get());
            System.out.println(arg);
        }
        public String getArgs() {
            return args;
        }

        public void setArgs(String args) {
            this.args = args;
        }
         public String get(){
             return arg;
         }
     }
     static class Son extends Parent{
        public String arg = "arg";
        public Son(){
            System.out.println(get());
        }
        public String get(){
            return arg;
        }

    }
}
