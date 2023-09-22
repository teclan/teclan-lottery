package com.teclan.lottery.ssq;

import com.teclan.easyexcel.Utils.FileUtils;
import com.teclan.easyexcel.handler.DefaultExcelHandler;
import com.teclan.easyexcel.handler.ExcelHandler;
import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.happy8.WriteHappy8ToExcelTest;
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


public class WriteSsqToExcelTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(WriteSsqToExcelTest.class);
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
        write("ssq");
    }
    @Test
    public  void writehappy8Sort() throws IOException {
        write("ssq_sort");
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

        final List<Ssq> datas = new ArrayList<Ssq>();
        ExcelHandler excelHandler = new DefaultExcelHandler(path);

        DBFactory.getDb().find(String.format("select * from %s",table), new RowListener() {
            public boolean next(Map<String, Object> map) {
                Ssq ssq = new Ssq();
                ssq.setIssue(map.get("issue").toString());
                ssq.setOpenTime(map.get("openTime").toString());
                ssq.setR1((Integer) map.get("r1"));
                ssq.setR2((Integer) map.get("r2"));
                ssq.setR3((Integer) map.get("r3"));
                ssq.setR4((Integer) map.get("r4"));
                ssq.setR5((Integer) map.get("r5"));
                ssq.setBlue((Integer) map.get("blue"));

                datas.add(ssq);
                return true;
            }
        });

        excelHandler.writeSingleSheet(path,"双色球开奖明细",datas,Ssq.class);
    }
}
