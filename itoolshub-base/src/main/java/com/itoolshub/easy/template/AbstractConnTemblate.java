package com.itoolshub.easy.template;

import com.itoolshub.easy.util.ConnFactory;
import com.itoolshub.easy.util.PropertiesUtil;

import java.sql.Connection;

/**
 * @author Quding Ding
 * @since 2018/1/4
 */
public abstract class AbstractConnTemblate {

  private ConnFactory connFactory;


  public Connection init(String dataSourceProp) {
    if (null == connFactory) {
      this.connFactory = new ConnFactory(PropertiesUtil.readClasspath(dataSourceProp));
    }
    return this.connFactory.getConnect();
  }


  public Connection init(String url, String username, String password) {
    if (null == connFactory) {
      this.connFactory = new ConnFactory(url, username, password);
    }
    return this.connFactory.getConnect();
  }



}
