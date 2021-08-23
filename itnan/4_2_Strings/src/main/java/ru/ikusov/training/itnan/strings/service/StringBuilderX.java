package ru.ikusov.training.itnan.strings.service;

import java.util.EmptyStackException;
import java.util.Stack;

public class StringBuilderX {
    private StringBuilder debilder;
    private Stack<StringBuilder> history = new Stack<>();

    public StringBuilderX() {
        this.debilder = new StringBuilder(); }
    public StringBuilderX(int i) {
        this.debilder = new StringBuilder(i); }
    public StringBuilderX(String s) {
        this.debilder = new StringBuilder(s); }
    public StringBuilderX(CharSequence cs) {
        this.debilder = new StringBuilder(cs); }

    public StringBuilderX undo() {
        try {
        debilder = history.pop(); }
        catch (EmptyStackException e) {}
        return this;
    }

    public StringBuilder getStringBuilder() {
        return debilder;
    }

    public StringBuilderX append(int c) {
        toHistory();
        debilder.append(c);
        return this;
    }

    public StringBuilderX append(char c) {
        toHistory();
        debilder.append(c);
        return this;
    }

    public StringBuilderX append(float f) {
        toHistory();
        debilder.append(f);
        return this;
    }

    public StringBuilderX append(double d) {
        toHistory();
        debilder.append(d);
        return this;
    }

    public StringBuilderX append(long l) {
        toHistory();
        debilder.append(l);
        return this;
    }

    public StringBuilderX append(boolean b) {
        toHistory();
        debilder.append(b);
        return this;
    }

    public StringBuilderX append(char[] str) {
        toHistory();
        debilder.append(str);
        return this;
    }

    public StringBuilderX append(Object o) {
        toHistory();
        debilder.append(o);
        return this;
    }

    public StringBuilderX append(String s) {
        toHistory();
        debilder.append(s);
        return this;
    }

    public StringBuilderX append(CharSequence cs) {
        toHistory();
        debilder.append(cs);
        return this;
    }

    public StringBuilderX append(StringBuffer sb) {
        toHistory();
        debilder.append(sb);
        return this;
    }

    public StringBuilderX appendCodePoint(int cp) {
        toHistory();
        debilder.appendCodePoint(cp);
        return this;
    }

    public StringBuilderX delete(int start, int end) {
        toHistory();
        debilder.delete(start, end);
        return this;
    }

    public StringBuilderX deleteCharAt(int index) {
        toHistory();
        debilder.deleteCharAt(index);
        return this;
    }

    public StringBuilderX insert(int offset, int i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, char i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, long i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, float i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, double i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, boolean i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, char[] i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, Object i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, String i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int offset, CharSequence i) {
        toHistory();
        debilder.insert(offset, i);
        return this;
    }

    public StringBuilderX insert(int index, char[] str, int offset, int len) {
        toHistory();
        debilder.insert(index, str, offset, len);
        return this;
    }

    public StringBuilderX insert(int offset, CharSequence str, int start, int end) {
        toHistory();
        debilder.insert(offset, str, start, end);
        return this;
    }

    public StringBuilderX replace(int start, int end, String str) {
        toHistory();
        debilder.replace(start, end, str);
        return this;
    }

    public StringBuilderX reverse() {
        toHistory();
        debilder.reverse();
        return this;
    }

    public int compareTo(StringBuilderX another) {
        return debilder.compareTo(another.getStringBuilder());
    }

    public int indexOf(String str) {
        return debilder.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return debilder.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return debilder.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return debilder.lastIndexOf(str, fromIndex);
    }

    public String toString() {
        return debilder.toString();
    }

    public int length() {
        return debilder.length();
    }
    
    private void toHistory() {
        StringBuilder oldDebilder = new StringBuilder(debilder.toString());
        history.push(oldDebilder);
    }
}
