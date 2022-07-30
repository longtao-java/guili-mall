package com.guigu.mall.product.mapper;

import java.util.List;
import com.guigu.mall.product.domain.SpuInfo;

/**
 * spu信息Mapper接口
 * 
 * @author longTao
 * @date 2022-07-30
 */
public interface SpuInfoMapper 
{
    /**
     * 查询spu信息
     * 
     * @param id spu信息主键
     * @return spu信息
     */
    public SpuInfo selectSpuInfoById(Long id);

    /**
     * 查询spu信息列表
     * 
     * @param spuInfo spu信息
     * @return spu信息集合
     */
    public List<SpuInfo> selectSpuInfoList(SpuInfo spuInfo);

    /**
     * 新增spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    public int insertSpuInfo(SpuInfo spuInfo);

    /**
     * 修改spu信息
     * 
     * @param spuInfo spu信息
     * @return 结果
     */
    public int updateSpuInfo(SpuInfo spuInfo);

    /**
     * 删除spu信息
     * 
     * @param id spu信息主键
     * @return 结果
     */
    public int deleteSpuInfoById(Long id);

    /**
     * 批量删除spu信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSpuInfoByIds(Long[] ids);
}
