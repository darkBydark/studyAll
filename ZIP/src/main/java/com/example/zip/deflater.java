package com.example.zip;

import org.springframework.util.StopWatch;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class deflater {
    public static byte[] compress(byte input[]) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Deflater compressor = new Deflater(1);
        try {
            compressor.setInput(input);
            compressor.finish();
            final byte[] buf = new byte[2048];
            while (!compressor.finished()) {
                int count = compressor.deflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            compressor.end();
        }
        return bos.toByteArray();
    }

    public static byte[] uncompress(byte[] input) throws DataFormatException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Inflater decompressor = new Inflater();
        try {
            decompressor.setInput(input);
            final byte[] buf = new byte[2048];
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            }
        } finally {
            decompressor.end();
        }
        return bos.toByteArray();

    }

    public static void main(String[] args) throws UnsupportedEncodingException, DataFormatException {
        String a = "{\"scm_id\":\"yf.commonplan.61.2hJoRnvclxHnkDOeg-5B3S3WRxKiiZgvDJrwvc0Q.0-41.I7rc9wqS-HshoeBqew9r8C16O8cEs\",\"buyer_group\":{},\"item_group\":{},\"algo\":{}}";
        StopWatch sw = new StopWatch();
        sw.start("压缩");
        byte[] compress1 = compress(a.getBytes());
        System.out.println(Arrays.toString(compress1));
        sw.stop();
        sw.start("解压");

        byte[] uncompress = uncompress(compress1);
        System.out.println("解压后： "+new String(uncompress,"UTF-8"));;


    }
}
