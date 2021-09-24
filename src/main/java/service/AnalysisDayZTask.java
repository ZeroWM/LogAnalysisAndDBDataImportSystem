package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/** DayZ服务器日志分析定时任务 作者：豆芽 Dora 版权：DayZ金三角服务器所有，仅可用于非盈利服务器 */
public class AnalysisDayZTask {

  /** 1. 读取文件, 设计实体 2. 数据代码入库 3. 页面展示数据，分页查询，并支持根据关键字搜索 */
  public static void main(String[] args) {
    // 1.读取本地文件
    File file = new File("/Users/wm/Downloads/ZhengShiFu_2021_09_24_055229001.ADM");

    // 2.读取本地处理后的文件
    //    File file = new File("/Users/wm/Downloads/loli-24.rtf");

    // 3.解析日志并打印log
    String result = txt2String(file);
    System.out.printf(result);
  }

  public static String txt2String(File file) {
    StringBuilder result = new StringBuilder();
    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
      String str = null;
      while ((str = bufferedReader.readLine()) != null) {

        if (str.contains("placed Wooden Crate")) {
          result.append(System.lineSeparator() + str.replace("placed Wooden Crate", "放置原木"));
        }

        if (str.contains("Tent")) {
          result.append(System.lineSeparator() + str.replace("Tent", "放置帐篷"));
        }

        if (str.contains("Dismantled Lower Frame from Fence with Hatchet")) {
          result.append(
              System.lineSeparator()
                  + str.replace("Dismantled Lower Frame from Fence with Hatchet", "通过小手斧拆下面墙"));
        }

        if (str.contains("Dismantled Upper Frame from Fence with Hatchet")) {
          result.append(
              System.lineSeparator()
                  + str.replace("Dismantled Upper Frame from Fence with Hatchet", "通过小手斧拆上面墙"));
        }
      }
      bufferedReader.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result.toString();
  }
}
