package cn.ifreehub.easy.excel.util;

import cn.ifreehub.easy.excel.core.ExcelReadUtil;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ExcelReadUtilTest {

  @Test
  public void toMap() {
    final List<Map<String, Object>> result = ExcelReadUtil
        .from("/Users/niuli/Downloads/需要开通堆糖商店会员的用户清单.xlsx")
        .toMap();
    final Set<Object> uids = result.stream().map(x -> x.get("uid")).filter(Objects::nonNull).collect(Collectors.toSet());
  }
}