package cn.ifreehub.easy.template;

import cn.ifreehub.util.ConnFactory;
import cn.ifreehub.util.PropertiesUtil;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;

/**
 * @author Quding Ding
 * @since 2018/1/4
 */
public abstract class AbstractDbutilTemblate {
  /**
   * 获取连接工厂
   */
  private ConnFactory connFactory;
  /**
   * 执行器
   */
  protected QueryRunner queryRunner = new QueryRunner();

  public Connection init(String dataSourceProp) {
    if (null == connFactory) {
      this.connFactory = new ConnFactory(PropertiesUtil.readClasspath(dataSourceProp));
    }
    final Connection connect = this.connFactory.getConnect();
    return connect;
  }


  public Connection init(String url, String username, String password) {
    if (null == connFactory) {
      this.connFactory = new ConnFactory(url, username, password);
    }
    return this.connFactory.getConnect();
  }



}
