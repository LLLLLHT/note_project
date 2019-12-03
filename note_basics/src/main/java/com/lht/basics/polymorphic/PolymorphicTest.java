package com.lht.basics.polymorphic;

public class PolymorphicTest {
    class A {
        public String run(D obj) {
            return ("A & D");
        }
        public String run(A obj) {
            return ("A & A");
        }
    }
    class B extends A {
        public String run(B obj) {
            return ("B & B");
        }
        @Override
        public String run(A obj) {
            return ("B & A");
        }
    }
    class C extends B {
    }
    class D extends B {
    }
    public static void main(String[] args) {
        PolymorphicTest polymorphicTest = new PolymorphicTest();
        A aa = polymorphicTest.new A();
        A ab = polymorphicTest.new B();
        B b = polymorphicTest.new B();
        C c = polymorphicTest.new C();
        D d = polymorphicTest.new D();
        System.out.println("1--" + aa.run(b));//AA
        System.out.println("2--" + aa.run(c));//AA
        System.out.println("3--" + aa.run(d));//AD
        System.out.println("4--" + ab.run(b));//AA
        System.out.println("5--" + ab.run(c));//AA
        System.out.println("6--" + ab.run(d));//AD
        System.out.println("7--" + b.run(b));//BB
        System.out.println("8--" + b.run(c));//BB
        System.out.println("9--" + b.run(d));//BB
    }
}
