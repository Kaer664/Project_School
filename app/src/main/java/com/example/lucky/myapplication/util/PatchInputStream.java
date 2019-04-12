package com.example.lucky.myapplication.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lucky on 2019/4/10.
 */

public class PatchInputStream extends FilterInputStream {

    public PatchInputStream(InputStream in) {
        super(in);
    }

    public long skip(long n)throws IOException {
        long m=0l;
        while(m<n){
            long _m=in.skip(n-m);
            if(_m==0l){
                break;
            }
            m+=_m;
        }
        return m;
    }

}