package com.teclan.lottery.all;

import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.dlt.DltTest;
import com.teclan.lottery.dlt.WriteDltToExcelTest;
import com.teclan.lottery.happy8.Happy8Test;
import com.teclan.lottery.happy8.WriteHappy8ToExcelTest;
import com.teclan.lottery.ssq.SsqTest;
import com.teclan.lottery.ssq.WriteSsqToExcelTest;
import com.teclan.lottery.utils.HttpUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AllTest {

    @Before
    public void setUp(){

        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "gzip, deflate, br");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Connection", "keep-alive");
        header.put("Cookie", "_ga_9FDP3NWFMS=GS1.1.1695090902.2.0.1695090902.0.0.0; Hm_lvt_692bd5f9c07d3ebd0063062fb0d7622f=1695003081,1695090903; Hm_lpvt_692bd5f9c07d3ebd0063062fb0d7622f=1695090903; Hm_lvt_12e4883fd1649d006e3ae22a39f97330=1695003081,1695090903; Hm_lpvt_12e4883fd1649d006e3ae22a39f97330=1695090903; _ga=GA1.2.1171619639.1695003080; _gid=GA1.2.528668990.1695090903; _gat_UA-66069030-3=1; KLBRSID=13ce4968858adba085afff577d78760d|1695090901|1695090901; PHPSESSID=85i6gnr9krb6po597kgbq6ndi1");
        header.put("Host", "jc.zhcw.com");
        header.put("Referer", "https://www.zhcw.com/");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");

        HttpUtils.setHeaders(header);

        DBFactory.open();
    }

    @After
    public void setDown(){
        DBFactory.close();
    }

    @Test
    public void getAll(){
        new SsqTest().fetch();
        new Happy8Test().fetch();
        new DltTest().fetch();
    }

    @Test
    public void exportAll(){
        new WriteDltToExcelTest().write("dlt","dlt_sort");
        new WriteHappy8ToExcelTest().write("happy8","happy8_sort");
        new WriteSsqToExcelTest().write("ssq","ssq_sort");
    }
}
