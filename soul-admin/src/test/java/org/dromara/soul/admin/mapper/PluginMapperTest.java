/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.admin.mapper;

import org.dromara.soul.admin.AbstractSpringIntegrationTest;
import org.dromara.soul.admin.dto.PluginDTO;
import org.dromara.soul.admin.entity.PluginDO;
import org.dromara.soul.admin.query.PluginQuery;
import org.h2.engine.Role;
import org.junit.Test;
import javax.annotation.Resource;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Test cases for PluginMapperTest.
 *
 * @author HJ43
 */
public final class PluginMapperTest extends AbstractSpringIntegrationTest {

    @Resource
    private PluginMapper pluginMapper;

    @Test
    public void selectById() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final PluginDO resultPluginDO = pluginMapper.selectById(pluginDO.getId());
        assertThat(pluginDO, equalTo(resultPluginDO));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void selectByName() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final PluginDO resultPluginDO = pluginMapper.selectByName(pluginDO.getName());
        assertThat(pluginDO, equalTo(resultPluginDO));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void selectByQuery() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final PluginQuery pluginQuery = new PluginQuery();
        pluginQuery.setName(pluginDO.getName());
        final List<PluginDO> pluginDOList = pluginMapper.selectByQuery(pluginQuery);
        assertThat(pluginDOList.size(), equalTo(1));
        assertThat(pluginDO, equalTo(pluginDOList.get(0)));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void selectAll() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final List<PluginDO> pluginDOList = pluginMapper.selectAll();
        assertThat(pluginDOList.size(), equalTo(1));
        assertThat(pluginDO, equalTo(pluginDOList.get(0)));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void countByQuery() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final PluginQuery pluginQuery = new PluginQuery();
        pluginQuery.setName(pluginDO.getName());
        final Integer countResult = pluginMapper.countByQuery(pluginQuery);
        assertThat(countResult, equalTo(1));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void insert() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void insertSelective() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insertSelective(pluginDO);
        assertThat(insertResult, equalTo(1));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void update() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int result = pluginMapper.insertSelective(pluginDO);
        assertThat(result, equalTo(1));

        pluginDO.setName("test-update");
        final int updateResult = pluginMapper.update(pluginDO);
        assertThat(updateResult, equalTo(1));

        final PluginDO resultPluginDO = pluginMapper.selectById(pluginDO.getId());
        assertEquals("test-update", resultPluginDO.getName());

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void updateEnable() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        boolean hasEnable = !pluginDO.getEnabled();
        pluginDO.setEnabled(hasEnable);
        int count = pluginMapper.updateEnable(pluginDO);
        assertThat(count, equalTo(1));

        final PluginDO resultPluginDO = pluginMapper.selectById(pluginDO.getId());
        assertEquals(hasEnable, resultPluginDO.getEnabled());

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void updateSelective() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        pluginDO.setName("test-update");
        int updateResult = pluginMapper.updateSelective(pluginDO);
        assertThat(updateResult, equalTo(1));

        final PluginDO resultPluginDO = pluginMapper.selectById(pluginDO.getId());
        assertEquals("test-update", resultPluginDO.getName());

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    @Test
    public void delete() {
        final PluginDTO pluginDTO = buildPluginDTO();
        final PluginDO pluginDO = PluginDO.buildPluginDO(pluginDTO);
        final int insertResult = pluginMapper.insert(pluginDO);
        assertThat(insertResult, equalTo(1));

        final int deleteResult = pluginMapper.delete(pluginDO.getId());
        assertThat(deleteResult, equalTo(1));
    }

    private PluginDTO buildPluginDTO() {
        final PluginDTO pluginDTO = new PluginDTO();
        pluginDTO.setEnabled(true);
        pluginDTO.setConfig("test-config");
        pluginDTO.setRole(Role.USER);
        pluginDTO.setName("test-name");
        return pluginDTO;
    }
}
