package com.teclan.lottery.happy8;

import com.alibaba.fastjson.JSONObject;
import com.teclan.lottery.db.DBFactory;
import com.teclan.lottery.utils.HttpUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class Happy8Test {
    private final static Logger LOGGER = LoggerFactory.getLogger(Happy8Test.class);
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");


    @Test
    public void fetch() {

        Integer qishu = Integer.valueOf(DBFactory.getDb().firstCell("select max(issue) from happy8 ").toString());

        String urlTemplate = "https://jc.zhcw.com/port/client_json.php?callback=jQuery112208078377006841968_1695090902601&transactionType=10001002&lotteryId=6&issue=%d&tt=0.5182121305675247&_=1695090902603";

       while (true){
           try {
               Thread.sleep(100);

                String url = String.format(urlTemplate, ++qishu);
                LOGGER.info("请求 {}",qishu);

                String response = HttpUtils.getString(url, true);
                String jsonString = response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1);
                JSONObject o = JSONObject.parseObject(jsonString);
                String issue = o.getString("issue");
                String openTime = o.getString("openTime");
                String frontWinningNum = o.getString("frontWinningNum");

                if(frontWinningNum==null){
                    throw new Exception("数据不存在");
                }

                String[] v = frontWinningNum.split(" ");
                long count = DBFactory.getDb().count("happy8", "`issue`=?", issue);
                if (count > 0) {
                    continue;
                }
                DBFactory.getDb().exec("insert into happy8 (`issue`,`openTime`,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
                        issue, openTime, v[0], v[1], v[2], v[3], v[4], v[5], v[6], v[7], v[8], v[9], v[10], v[11], v[12], v[13], v[14], v[15], v[16], v[17], v[18], v[19]
                );

                long  count_s = DBFactory.getDb().count("happy8_sort", "`issue`=?", issue);
                if (count_s > 0) {
                    continue;
                }

                Thread.sleep(100);

                sort(v);
                DBFactory.getDb().exec("insert into happy8_sort (`issue`,`openTime`,v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18,v19,v20) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ",
                        issue, openTime, v[0], v[1], v[2], v[3], v[4], v[5], v[6], v[7], v[8], v[9], v[10], v[11], v[12], v[13], v[14], v[15], v[16], v[17], v[18], v[19]
                );




                LOGGER.info("新增：期数{},日期{}，数据：{}", issue, openTime, frontWinningNum);
            }catch (Exception e){
                LOGGER.info("无法获取数据，期数{} ", qishu);
                LOGGER.error(e.getMessage(),e);
                break;
            }

        }
    }

    private void sort(String[] v){
        for(int i=0;i<v.length;i++){
            for(int j=i+1;j<v.length;j++){
                if(Integer.valueOf(v[i])>Integer.valueOf(v[j])){
                    String temp = v[i];
                    v[i] = v[j];
                    v[j] = temp;
                }
            }
        }
    }

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



}
