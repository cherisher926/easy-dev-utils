package com.itoolshub.easy.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Quding Ding
 * @since 2017/12/1
 */
public class MapUtils {

  private static ObjectMapper objectMapper = new ObjectMapper();

  private final static TypeReference<Map<String, Object>> BEAN_TO_MAP =
      new TypeReference<Map<String, Object>>() {};

  /**
   * bean转Map工具
   * @param beans bean集合
   * @return map集合
   */
  public static <T> List<Map<String,Object>> toMaps(List<T> beans) {
    if (null == beans || beans.isEmpty()) {
      return Collections.emptyList();
    }
    return beans.parallelStream()
        .map(x -> objectMapper.<Map<String,Object>>convertValue(x, BEAN_TO_MAP))
        .collect(Collectors.toList());

  }
}
