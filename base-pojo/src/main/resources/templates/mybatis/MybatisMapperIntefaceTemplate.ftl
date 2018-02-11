package ${MYBATIS_INTEFACE_MODEL.packageName};

import ${MYBATIS_INTEFACE_MODEL.javaModel.packageName};

import java.util.Date;
import java.util.List;
import java.util.Collection;
import java.util.Set;

public interface ${MYBATIS_INTEFACE_MODEL.className} {

  /**
   * 根据id查询
   * @param id 模型主键
   * @return 该实体 null则不存在
   */
  ${MYBATIS_INTEFACE_MODEL.javaModel.className} findById(Long id);

  /**
   * 根据ids查询
   * @param ids 模型主键集合
   * @return 该实体集合
   */
  List<${MYBATIS_INTEFACE_MODEL.javaModel.className}> queryByIds(Collection<Long> ids);

  /**
  * 创建实体.自动写回主键
  * @param model 该实体
  * @return 1成功 0失败
  */
  int create(${MYBATIS_INTEFACE_MODEL.javaModel.className} model);


  /**
  * 创建实体
  * @param model 该实体
  * @return 1成功 0失败
  */
  int update(${MYBATIS_INTEFACE_MODEL.javaModel.className} model);
}