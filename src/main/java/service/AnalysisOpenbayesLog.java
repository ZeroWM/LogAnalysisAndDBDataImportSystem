package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日志分析
 */
public class AnalysisOpenbayesLog {

  public static void main(String[] args) {
    // 1.读取本地文件
    File file = new File("/Users/wangmei/Downloads/log.txt");

    // 2.解析日志并打印log
    txt2String(file);
  }

  public static void txt2String(File file) {
    try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/openbayes_analysis", "mysql", "mysql");
        PreparedStatement stmt = conn.prepareStatement("insert into resource_log values(?,?,?,?,?,?,?)");
    ) {
      try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
          if (str.contains("c.o.d.r.JobCreatedEventListener.on")) {
            String[] s = str.split(" ");
            String dateStr = s[0] + " " + s[1];
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = sdf.parse(dateStr);
            Timestamp ts = new Timestamp(date.getTime());
            stmt.setTimestamp(1, ts);
            //status, user, job, free_resource, locked_count, total_count
            stmt.setString(2, s[5]);
            stmt.setString(3, s[7]);
            stmt.setString(4, s[9]);
            stmt.setString(5, s[11]);
            stmt.setString(6, s[14]);
            stmt.setString(7, s[16]);
            stmt.execute();
           }else if (str.contains("c.o.d.r.WorkspaceRestartedEventListener.on")){
            String[] s = str.split(" ");
            String dateStr = s[0] + " " + s[1];
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = sdf.parse(dateStr);
            Timestamp ts = new Timestamp(date.getTime());
            stmt.setTimestamp(1, ts);
            //status, user, job, free_resource, locked_count, total_count
            stmt.setString(2, s[5]);
            stmt.setString(3, s[7]);
            stmt.setString(4, s[9]);
            stmt.setString(5, s[11]);
            stmt.setString(6, s[14]);
            stmt.setString(7, s[16]);
            stmt.execute();
           }else if(str.contains("c.o.d.r.JobFinishedEventListener.on")){
            String[] s = str.split(" ");
            String dateStr = s[0] + " " + s[1];
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = sdf.parse(dateStr);
            Timestamp ts = new Timestamp(date.getTime());
            stmt.setTimestamp(1, ts);
            //status, user, job, free_resource, locked_count, total_count
            stmt.setString(2, s[5]);
            stmt.setString(3, s[7]);
            stmt.setString(4, s[8]);
            stmt.setString(5, s[11]);
            stmt.setString(6, s[16]);
            stmt.setString(7, s[18]);
            stmt.execute();
           }
          }
        bufferedReader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
