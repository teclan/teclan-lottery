package com.teclan.lottery.dlt;

import com.alibaba.fastjson.JSONObject;
import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.utils.HttpUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DltTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(DltTest.class);

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
    public void fetch() {

        Integer qishu = Integer.valueOf(DBFactory.getDb().firstCell("select max(issue) from dlt ").toString());


        String urlTemplate = "https://jc.zhcw.com/port/client_json.php?callback=jQuery1122021491894573143444_1695098726518&transactionType=10001002&lotteryId=281&issue=%d&tt=0.39554650401464464&_=1695098726520\n";

        while (true) {

            try {
                String url = String.format(urlTemplate, ++qishu);
                LOGGER.info("请求 {}",qishu);

                String response = HttpUtils.getString(url, true);
                String jsonString = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                JSONObject o = JSONObject.parseObject(jsonString);
                String issue = o.getString("issue");
                String openTime = o.getString("openTime");
                String frontWinningNum = o.getString("frontWinningNum"); // 已排序
                String seqFrontWinningNum = o.getString("seqFrontWinningNum"); // 未排序
                String blue = o.getString("backWinningNum");  // 已排序
                String blues = o.getString("seqBackWinningNum");  // 已排序

                if(seqFrontWinningNum==null){
                    throw new Exception("数据不存在");
                }

                String[] v = seqFrontWinningNum.split(" ");
                String[] vs = frontWinningNum.split(" ");
                String[] b = blue.split(" ");
                String[] bs = blues.split(" ");

                long count = DBFactory.getDb().count("dlt", "`issue`=?", issue);
                if (count > 0) {
                    continue;
                }

                DBFactory.getDb().exec("insert into dlt (`issue`,`openTime`,r1,r2,r3,r4,r5,b1,b2) values (?,?,?,?,?,?,?,?,?) ",
                        issue, openTime, v[0], v[1], v[2], v[3], v[4], b[0],b[1]
                );

                DBFactory.getDb().exec("insert into dlt_sort (`issue`,`openTime`,r1,r2,r3,r4,r5,b1,b2) values (?,?,?,?,?,?,?,?,?) ",
                        issue, openTime, vs[0], vs[1], vs[2], vs[3], vs[4], bs[0],bs[1]
                );
            }catch (Exception e){
                LOGGER.info("无法获取数据，期数{} ", qishu);
                LOGGER.error(e.getMessage(),e);
                break;
            }
        }
    }
}
