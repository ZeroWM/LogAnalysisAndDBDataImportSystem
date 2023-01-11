package domain;

/**
 * 玩家信息和行为
 *
 * 作者：豆芽 Dora
 * 版权：Dora
 */
public class Player {
  //姓名
  private String name;
  //steamId
  private String steamId;
  //x坐标
  private double xPos;
  //y坐标
  private double yPos;
  //行为
  private String action;
  //健康
  private double health;
  //伤害
  private Double damage;

  //是否存活
  public boolean isDead() {
    return action.contains("died");
  }

  //具体方位
  public String getDetailPosition() {
    if (xPos > 100 && yPos > 100) {
      return "";
    }
    return "";
  }

  //整理行为
  public String getDetailAction(){
    return "";
  }
}
