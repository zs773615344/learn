package cn.zs.learn.bigdate.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUDF extends UDF {

    public String evaluate(String num){
        Date d=new Date(Long.decode(num));
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
        return sdf.format(d) ;
    }

}
