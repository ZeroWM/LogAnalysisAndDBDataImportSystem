package service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonArray;
import jdk.jfr.Experimental;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 对比 tags 筛选性能对比构建数据 1. 作为json查询
 *
 * <p>select * from projects where JSON_CONTAINS(tags, JSON_OBJECT('name', '文本摘要')) and
 * JSON_CONTAINS(tags, JSON_OBJECT('name', 'BUSINESS_CHANNEL_ML'));
 *
 * <p>2. 通过关联关系查询
 *
 * select * from projects P inner join entity_tags ET on P.id = ET.entity_id and ET.tag_id =
 * 87334 inner join entity_tags ET1 on P.id = ET1.entity_id and ET1.tag_id = 5;
 */
public class DataManufacturingMachineForDB2 {

  public static void main(String[] args) {
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/openbayes_analysis", "mysql", "mysql");
        PreparedStatement stmt = conn.prepareStatement("insert into projects values(?,?,?)");
        PreparedStatement stmt1 = conn.prepareStatement("insert into entity_tags(entity_id, tag_id) values(?,?)");
    ) {
      try {
        for (int i = 0; i < 30000; i++) {
          Random random = new SecureRandom();
          char[] alphabet = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
          int size = 12;
          String projectId = NanoIdUtils.randomNanoId(random, alphabet, 12);
          stmt.setString(1, projectId);
          stmt.setString(2, "ssss");

          List<Tag> tagList = new ArrayList<>();
          tagList.add(new Tag(87334, "BUSINESS_CHANNEL_ML"));

          if(i == 100 || i == 200 || i == 300 || i == 1000){
            tagList.add(new Tag(5, "文本摘要"));
          }
          stmt.setString(3, new Gson().toJson(tagList));

          stmt1.setString(1, projectId);
          stmt1.setInt(2, 87334);

          stmt.execute();
          stmt1.execute();
         }
      } catch (Exception e) {
        e.printStackTrace();
      }

    }catch (Exception e){
      e.printStackTrace();
    }
  }
}


class Tag{
  private long id;
  private String name;

  public Tag(long id, String name){
    this.id = id;
    this.name = name;
  }
}
