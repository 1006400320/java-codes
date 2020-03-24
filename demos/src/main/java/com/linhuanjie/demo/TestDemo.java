package com.linhuanjie.demo;

/**
 * @author: linhuanjie
 * @description:
 * @createTime : 2018-11-29 10:07
 * @email: lhuanjie@qq.com
 */
public class TestDemo {
  /*  public static void main(String[] args) {
            // create
//            CuckooFilter<Integer> filter = new CuckooFilter.Builder<>(Funnels.integerFunnel(), 2000000).build();
//            // insert
//            if (filter.put(42)) {
//                System.out.println("Insert Success!");
//            }
//            // contains
//            if (filter.mightContain(42)) {
//                System.out.println("Found 42!");
//            }
//            // count
//            System.out.println("Filter has " + filter.getCount() + " items");
//
//            // count
//            System.out.println("42 has been inserted approximately " + filter.approximateCount(42) + " times");
//
//            // % loaded
//            System.out.println("Filter is " + String.format("%.0f%%", filter.getLoadFactor() * 100) + " loaded");
//
//            // delete
//            if (filter.delete(42)) {
//                System.out.println("Delete Success!");
//            }

    }*/


   /* interface Language {
        void print(String s);
    }

    static class Java implements Language{
        @Override
        public void print(String x) {
            System.out.println("System.out.print(\""+ x +"\")");
        }
    }

    static class Coder {
        private Language lang = new Java();

        public void helloWorld() {
            lang.print("hello world");
        }
    }

    public static void main(String[] args) {
        Coder coder = new Coder();
        coder.helloWorld();
    }
*/

    interface Language {
        void print(String s);
    }

    static class Java implements Language{
        @Override
        public void print(String x) {
            System.out.println("System.out.print(\""+ x +"\")");
        }
    }

    static class Coder {
        private Language lang;

        public void setLang(Language lang) {
            this.lang = lang;
        }

        public void helloWorld() {
            lang.print("hello world");
        }
    }

    public static void main(String[] args) {
        Coder coder = new Coder();
        Language java = new Java();
        coder.setLang(java);
        coder.helloWorld();
    }
}