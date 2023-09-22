package com.teclan.lottery.dlt;

import com.teclan.easyexcel.Utils.FileUtils;
import com.teclan.easyexcel.handler.DefaultExcelHandler;
import com.teclan.easyexcel.handler.ExcelHandler;
import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.happy8.Happy8Test;
import com.teclan.lottery.model.Dlt;
import com.teclan.lottery.model.Happy8;
import com.teclan.lottery.model.Ssq;
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


public class WriteDltToExcelTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(WriteDltToExcelTest.class);
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
        write("dlt");
    }
    @Test
    public  void writehappy8Sort() throws IOException {
        write("dlt_sort");
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

        final List<Dlt> datas = new ArrayList<Dlt>();
        ExcelHandler excelHandler = new DefaultExcelHandler(path);

        DBFactory.getDb().find(String.format("select * from %s",table), new RowListener() {
            public boolean next(Map<String, Object> map) {
                Dlt dlt = new Dlt();
                dlt.setIssue(map.get("issue").toString());
                dlt.setOpenTime(map.get("openTime").toString());
                dlt.setR1((Integer) map.get("r1"));
                dlt.setR2((Integer) map.get("r2"));
                dlt.setR3((Integer) map.get("r3"));
                dlt.setR4((Integer) map.get("r4"));
                dlt.setB1((Integer) map.get("b1"));
                dlt.setB2((Integer) map.get("b2"));

                datas.add(dlt);
                return true;
            }
        });

        excelHandler.writeSingleSheet(path,"大乐透开奖明细",datas,Dlt.class);
    }
}
