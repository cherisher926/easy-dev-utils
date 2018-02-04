package ${packageName};

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * @author easy-dev-pojo
 * @since ${currentTime?string("yyyy/MM/dd")}
 */
@Data
public class ${className} {
<#list fileds as filed>

  /**
    <#if filed.comment??>
   * ${filed.comment}
    </#if>
   * @default ${filed.defaultVal}
   */
  private ${filed.type} ${filed.name};

</#list>
}
