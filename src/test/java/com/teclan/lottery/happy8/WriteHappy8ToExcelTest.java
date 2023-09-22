package com.teclan.lottery.happy8;

import com.teclan.easyexcel.Utils.FileUtils;
import com.teclan.easyexcel.handler.DefaultExcelHandler;
import com.teclan.easyexcel.handler.ExcelHandler;
import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.dlt.WriteDltToExcelTest;
import com.teclan.lottery.model.Happy8;
import com.teclan.lottery.utils.HttpUtils;
import org.javalite.activejdbc.RowListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class WriteHappy8ToExcelTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(WriteHappy8ToExcelTest.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    @Before
    public void setUp(){
        DBFactory.open();
    }

    @After
    public void setDown(){
        DBFactory.close();
    }

    @Test
    public  void writehappy8() throws IOException {
        write("happy8");
    }
    @Test
    public  void writehappy8Sort() throws IOException {
        write("happy8_sort");
    }

    public  void write(String ...tables)  {
        try {
            for (String table : tables) {
                write(table);
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }

    public  void write(String table) throws IOException {
        String path = String.format("excel/%s-%s.xlsx",table,sdf.format(new Date()));
        FileUtils.deleteFile(path);

        final List<Happy8> datas = new ArrayList<Happy8>();
        ExcelHandler excelHandler = new DefaultExcelHandler(path);

        DBFactory.getDb().find(String.format("select * from %s",table), new RowListener() {
            public boolean next(Map<String, Object> map) {
                Happy8 happy8 = new Happy8();
                happy8.setIssue(map.get("issue").toString());
                happy8.setOpenTime(map.get("openTime").toString());
                happy8.setV1((Integer) map.get("v1"));
                happy8.setV2((Integer) map.get("v2"));
                happy8.setV3((Integer) map.get("v3"));
                happy8.setV4((Integer) map.get("v4"));
                happy8.setV5((Integer) map.get("v5"));
                happy8.setV6((Integer) map.get("v6"));
                happy8.setV7((Integer) map.get("v7"));
                happy8.setV8((Integer) map.get("v8"));
                happy8.setV9((Integer) map.get("v9"));
                happy8.setV10((Integer) map.get("v10"));
                happy8.setV11((Integer) map.get("v11"));
                happy8.setV12((Integer) map.get("v12"));
                happy8.setV13((Integer) map.get("v13"));
                happy8.setV14((Integer) map.get("v14"));
                happy8.setV15((Integer) map.get("v15"));
                happy8.setV16((Integer) map.get("v16"));
                happy8.setV17((Integer) map.get("v17"));
                happy8.setV18((Integer) map.get("v18"));
                happy8.setV19((Integer) map.get("v19"));
                happy8.setV20((Integer) map.get("v20"));

                datas.add(happy8);
                return true;
            }
        });

        excelHandler.writeSingleSheet(path,"快乐8开奖明细",datas,Happy8.class);
    }
}
