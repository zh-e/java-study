package com.zhe.javabase;
/**
 Copyright (c) 2022 BodyPark Co. 版权所有 请勿泄题
 后续面试会问相关问题，所以请勿借助他人

 The input is a text stream of multiple lines. If you
 are not familiar with the concept of i/o stream you may
 search it on the web.
 Your task is to find the longest continous substring
 consists of only "valid" characters for each line.
 Here, a character is called "valid" if it is an
 English letter (upper or lower case) or a space.
 The output should also be a text stream of multiple
 lines and each line should be the longest continous
 substring of its corresponding input line. Please
 note that the output lines should be in the exact
 same order as their input lines and the number of
 input lines and output lines should be the same.

 Here is an example of three lines of input text:
 :LSu9f*&;23lk45
 0ue u987*6OIIe
 765^*^%$*^&(*354
 And the ouput stream should also be three lines:
 LSu
 ue u


 InStream and OutStream are your interfaces for input
 and output respectively. You should use them for I/O
 and they have only the methods defined below. Don't
 waste your time reading their implementations, which
 are not relevant to this interview.

 TWO TASKs:
 1. Add enough (according to you) lines to the value of UnittestCases in ShowMebug.testLongestValidSubStrings (near the bottom of this file) as test cases.
 2. Implement LongestValidSubstrings() of Processor class

 ADDITIONAL REQUIREMENTS:
 Do not use regular expression.
 Use as little memory as you can.

 You may search on the web freely when you code.

 If any questions please contact me.
 */

import java.io.*;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.Test;

/**
 * The interface for input stream
 *
 * Only sequential access allowed.
 */
interface InStream {
    /*
     * Read a character from input stream
     */
    public Character readChar() throws IOException;

    /*
     * Read a line from input stream
     */
    public String readLine() throws IOException;

    /*
     * Read a block of text from input stream
     * Parameters:
     *   size: the length of text block to read
     */
    public String readBlock(int size) throws IOException;
}

/**
 * The interface for output stream
 */
interface OutStream {
    /*
     * Write a character to output stream
     */
    public void writeChar(char ch);

    /*
     * Write a line to output stream
     */
    public void writeLine(String line);

    /*
     * Write a text block to output stream
     */
    public void writeBlock(String block);
}

class Processor {
    public Processor(InStream instream, OutStream outstream) {
        this.instream = instream;
        this.outstream = outstream;
    }

    public void LongestValidSubStrings() throws IOException {
        // TASK 2: PLEASE IMPLEMENT THIS.
        // Note: You may define other functions and/or classes as you wish

        for(;;) {
            String str = instream.readLine();
            if (str == null || str.length() <= 0) {
                break;
            }

            int i = 0, j = 0;
            StringBuilder sb = new StringBuilder();
            while (i < str.length()) {
                char c = str.charAt(i);
                if (c == ' ') {
                    i++;
                    sb.append(str, j, i);
                    j = i;
                    continue;
                }

                if ((c - 'A' >= 0 && c - 'Z' <= 0) || (c - 'a' >= 0 && c - 'z' <= 0)) {
                    i ++;
                    continue;
                }

                sb.append(str, j, i);

                j = i;


//                65 90
//                97 122



            }




            outstream.writeLine(str);
        }

    }

    private InStream instream = null;
    private OutStream outstream = null;
};

/**
 * The mock implementation of InStream. Please ignore.
 */
class MockInStream implements InStream {
    public Character readChar() throws IOException {
        int code = implReader.read();
        if (code == -1)
            return null;

        return Character.valueOf((char)code);
    }

    public String readLine() throws IOException {
        return implReader.readLine();
    }

    public String readBlock(int size) throws IOException {
        var buffer = new char[size];
        var count = implReader.read(buffer, 0, size);
        if (count == -1)
            return null;
        else
            return new String(buffer);
    }

    public MockInStream(String testCases) {
        implStream = new ByteArrayInputStream(testCases.getBytes());
        implReader = new BufferedReader(new InputStreamReader(implStream));
    }

    private ByteArrayInputStream implStream = null;
    private BufferedReader implReader = null;
}

/**
 * The mock implementation of OutStream. Please ignore.
 */
class MockOutStream implements OutStream {
    public void writeChar(char ch) {
        System.out.print(ch);
    }

    public void writeLine(String line) {
        System.out.println(line);
    }

    public void writeBlock(String block) {
        System.out.print(block);
    }
}

public class ShowMeBug {
    @Test
    public void testProcessor() throws IOException {
        // TASK 1: PLEASE ADD INPUT LINES HERE AS TESTS.
        String UnittestCases = ":LSu9f*&;23lk45\n" +
                " 0ue u987*6OIIe\n" +
                " 765^*^%$*^&(*354";

        InStream instream = new MockInStream(UnittestCases);
        OutStream outstream = new MockOutStream();
        var processor = new Processor(instream, outstream);

        processor.LongestValidSubStrings();
    }

    public static void main(String[] args) {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(ShowMeBug.class);
    }
}