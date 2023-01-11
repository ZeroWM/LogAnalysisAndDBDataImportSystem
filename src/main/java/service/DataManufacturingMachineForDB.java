package service;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 给数据库造数据工具
 */
public class DataManufacturingMachineForDB {

  public static void main(String[] args) {
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/openbayes_analysis", "mysql", "mysql");
        PreparedStatement stmt = conn.prepareStatement("insert into projects values(?,?)");
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
