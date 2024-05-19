<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型名称" prop="typeName">
        <el-input
          v-model="queryParams.typeName"
          placeholder="请输入类型名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="榜单英文名" label-width="120" prop="typeNameEn">
        <el-input
          v-model="queryParams.typeNameEn"
          placeholder="请输入榜单英文名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['resource:list_type:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['resource:list_type:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['resource:list_type:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['resource:list_type:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          icon="el-icon-refresh"
          size="mini"
          @click="reloadData"
          v-hasPermi="['resource:home:export']"
        >更新数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="list_typeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="类型名称" align="center" prop="typeName" />
      <el-table-column label="榜单英文名" align="center" prop="typeNameEn" />
      <el-table-column label="综合营销价值" align="center" prop="compositeMarketValue" />
      <el-table-column label="商业适应度指数" align="center" prop="businessAdaptationExponent" />
      <el-table-column label="传播指数" align="center" prop="spreadExponent" />
      <el-table-column label="活跃度指数" align="center" prop="activityExponent" />
      <el-table-column label="成长指数" align="center" prop="growthExponent" />
      <el-table-column label="健康指数" align="center" prop="healthExponent" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['resource:list_type:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['resource:list_type:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改榜单类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="类型名称" prop="typeName">
          <el-input v-model="form.typeName" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="榜单英文名" prop="typeNameEn">
          <el-input v-model="form.typeNameEn" placeholder="请输入榜单英文名" />
        </el-form-item>
        <el-form-item label="综合营销价值" prop="compositeMarketValue">
          <el-input v-model="form.compositeMarketValue" placeholder="请输入综合营销价值" />
        </el-form-item>
        <el-form-item label="商业适应度指数" prop="businessAdaptationExponent">
          <el-input v-model="form.businessAdaptationExponent" placeholder="请输入商业适应度指数" />
        </el-form-item>
        <el-form-item label="传播指数" prop="spreadExponent">
          <el-input v-model="form.spreadExponent" placeholder="请输入传播指数" />
        </el-form-item>
        <el-form-item label="活跃度指数" prop="activityExponent">
          <el-input v-model="form.activityExponent" placeholder="请输入活跃度指数" />
        </el-form-item>
        <el-form-item label="成长指数" prop="growthExponent">
          <el-input v-model="form.growthExponent" placeholder="请输入成长指数" />
        </el-form-item>
        <el-form-item label="健康指数" prop="healthExponent">
          <el-input v-model="form.healthExponent" placeholder="请输入健康指数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listList_type,
  getList_type,
  delList_type,
  addList_type,
  updateList_type,
  reloadListType
} from '@/api/resource/list_type'

export default {
  name: "List_type",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 榜单类型表格数据
      list_typeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        typeName: null,
        typeNameEn: null,
        compositeMarketValue: null,
        businessAdaptationExponent: null,
        spreadExponent: null,
        activityExponent: null,
        growthExponent: null,
        healthExponent: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询榜单类型列表 */
    getList() {
      this.loading = true;
      listList_type(this.queryParams).then(response => {
        this.list_typeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        typeName: null,
        typeNameEn: null,
        compositeMarketValue: null,
        businessAdaptationExponent: null,
        spreadExponent: null,
        activityExponent: null,
        growthExponent: null,
        healthExponent: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加榜单类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getList_type(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改榜单类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateList_type(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addList_type(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除榜单类型编号为"' + ids + '"的数据项？').then(function() {
        return delList_type(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('resource/list_type/export', {
        ...this.queryParams
      }, `list_type_${new Date().getTime()}.xlsx`)
    },
    async reloadData() {
      await reloadListType();
      this.$message.success('刷新成功')
    }
  }
};
</script>
