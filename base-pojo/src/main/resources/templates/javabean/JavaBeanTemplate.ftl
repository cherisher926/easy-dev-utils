package ${JAVABEAN_PACKAGE_NAME};

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * @author easy-dev-pojo
 * @since ${ALL_CURRENT_TIME?string("yyyy/MM/dd")}
 */
@Data
public class ${JAVABEAN_CLASS_NAME} {
<#list JAVABEAN_FILEDS as filed>

  /**
    <#if filed.comment??>
   * ${filed.comment}
    </#if>
   * @default ${filed.defaultVal}
   */
  private ${filed.type} ${filed.javaName};

</#list>
}
