package ${JAVABEAN_MODEL.packageName};

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * table ${JAVABEAN_MODEL.originTableName}
 * @author easy-dev-pojo
 * @since ${ALL_CURRENT_TIME?string("yyyy/MM/dd")}
 */
@Data
public class ${JAVABEAN_MODEL.className} {
<#list JAVABEAN_MODEL.fileds as filed>

  /**
    <#if filed.comment??>
   * ${filed.comment}
    </#if>
   * @default ${filed.defaultVal}
   */
  private ${filed.javaType} ${filed.javaName};

</#list>
}
