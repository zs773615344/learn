package cn.zs.learn.bigdate.learnhadoopsource;

import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.io.compress.zlib.BuiltInZlibDeflater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Codec {
    public static void main(String[] args) throws IOException {

        int compressOutputBufferSize = 4096;
        int length = 50;

        File filein = new File("/home/shuai/idea/hadoop-2.6.5-src/README.txt");
        FileInputStream in = new FileInputStream(filein);
        int datalength = in.available();
        byte[] inbuf = new byte[datalength];
        in.read(inbuf,0,datalength);
        in.close();

        byte[] outbuf = new byte[compressOutputBufferSize];
        Compressor compressor = new BuiltInZlibDeflater();

        int step = 100;
        int inputPos = 0;
        int putcount = 0;
        int getcount = 0;
        int compressedlen = 0;

        while(inputPos < datalength) {
            int len = (datalength - inputPos >= step) ? step : datalength - inputPos;
            compressor.setInput(inbuf, inputPos, len);
            putcount ++;
            while (!compressor.needsInput()) {
                compressedlen = compressor.compress(outbuf,0, 1);
                if(compressedlen>0) {
                    getcount ++;
                }
            }
            inputPos += step;
        }

        compressor.finish();

        while (!compressor.finished()) {
            getcount ++;
            compressor.compress(outbuf, 0, compressOutputBufferSize);
        }

        System.out.println("compress " + compressor.getBytesRead()
                + " bytes into " + compressor.getBytesWritten());
        System.out.println("put "+putcount+" times and get " + getcount
                + " times");

        compressor.end();


    }
}
