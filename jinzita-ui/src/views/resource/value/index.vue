<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-input
          v-model="queryParams.gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简介" prop="introduction">
        <el-input
          v-model="queryParams.introduction"
          placeholder="请输入简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主页链接" prop="homeLink">
        <el-input
          v-model="queryParams.homeLink"
          placeholder="请输入主页链接"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="综合营销价值"
        label-width="120"
        prop="compositeMarketValue"
      >
        <el-input
          v-model="queryParams.compositeMarketValue"
          placeholder="请输入综合营销价值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="商业适应度指数"
        label-width="120"
        prop="businessAdaptationExponent"
      >
        <el-input
          v-model="queryParams.businessAdaptationExponent"
          placeholder="请输入商业适应度指数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="传播指数" prop="spreadExponent">
        <el-input
          v-model="queryParams.spreadExponent"
          placeholder="请输入传播指数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item
        label="活跃度指数"
        label-width="120"
        prop="activityExponent"
      >
        <el-input
          v-model="queryParams.activityExponent"
          placeholder="请输入活跃度指数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="成长指数" prop="growthExponent">
        <el-input
          v-model="queryParams.growthExponent"
          placeholder="请输入成长指数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="健康指数" prop="healthExponent">
        <el-input
          v-model="queryParams.healthExponent"
          placeholder="请输入健康指数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起始时间" prop="startTime">
        <el-date-picker
          clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择起始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endTime">
        <el-date-picker
          clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['resource:value:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['resource:value:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['resource:value:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['resource:value:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="valueList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="作者" align="center" prop="author" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column
        label="简介"
        align="center"
        prop="introduction"
        width="200"
      />
      <el-table-column
        label="主页链接"
        align="center"
        prop="homeLink"
        width="300"
      >
        <template slot-scope="scope">
          <span>
            <el-button
              @click="routerToHomeLink(scope.row.homeLink)"
              type="text"
              >{{ scope.row.homeLink }}</el-button
            >
          </span>
        </template>
      </el-table-column>
      <el-table-column
        label="综合营销价值"
        align="center"
        prop="compositeMarketValue"
        width="100"
      />
      <el-table-column
        label="商业适应度指数"
        align="center"
        prop="businessAdaptationExponent"
        width="120"
      />
      <el-table-column label="传播指数" align="center" prop="spreadExponent" />
      <el-table-column
        label="活跃度指数"
        align="center"
        prop="activityExponent"
        width="100"
      />
      <el-table-column label="成长指数" align="center" prop="growthExponent" />
      <el-table-column label="健康指数" align="center" prop="healthExponent" />
      <el-table-column label="周期类型" align="center" prop="periodType" />
      <el-table-column label="榜单类型" align="center" prop="listType" />
      <el-table-column
        label="起始时间"
        align="center"
        prop="startTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="结束时间"
        align="center"
        prop="endTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        width="180"
        fixed="right"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['resource:value:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['resource:value:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改价值榜单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="简介" prop="introduction">
          <el-input v-model="form.introduction" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="主页链接" prop="homeLink">
          <el-input v-model="form.homeLink" placeholder="请输入主页链接" />
        </el-form-item>
        <el-form-item label="综合营销价值" prop="compositeMarketValue">
          <el-input
            v-model="form.compositeMarketValue"
            placeholder="请输入综合营销价值"
          />
        </el-form-item>
        <el-form-item label="商业适应度指数" prop="businessAdaptationExponent">
          <el-input
            v-model="form.businessAdaptationExponent"
            placeholder="请输入商业适应度指数"
          />
        </el-form-item>
        <el-form-item label="传播指数" prop="spreadExponent">
          <el-input
            v-model="form.spreadExponent"
            placeholder="请输入传播指数"
          />
        </el-form-item>
        <el-form-item label="活跃度指数" prop="activityExponent">
          <el-input
            v-model="form.activityExponent"
            placeholder="请输入活跃度指数"
          />
        </el-form-item>
        <el-form-item label="成长指数" prop="growthExponent">
          <el-input
            v-model="form.growthExponent"
            placeholder="请输入成长指数"
          />
        </el-form-item>
        <el-form-item label="健康指数" prop="healthExponent">
          <el-input
            v-model="form.healthExponent"
            placeholder="请输入健康指数"
          />
        </el-form-item>
        <el-form-item label="起始时间" prop="startTime">
          <el-date-picker
            clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择起始时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker
            clearable
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间"
          >
          </el-date-picker>
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
  listValue,
  getValue,
  delValue,
  addValue,
  updateValue,
} from "@/api/resource/value";

export default {
  name: "Value",
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
      // 价值榜单表格数据
      valueList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        author: null,
        gender: null,
        introduction: null,
        homeLink: null,
        compositeMarketValue: null,
        businessAdaptationExponent: null,
        spreadExponent: null,
        activityExponent: null,
        growthExponent: null,
        healthExponent: null,
        periodType: null,
        listType: null,
        startTime: null,
        endTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    routerToHomeLink(homeLink) {
      location.href = homeLink;
    },
    /** 查询价值榜单列表 */
    getList() {
      this.loading = true;
      listValue(this.queryParams).then((response) => {
        this.valueList = response.rows;
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
        author: null,
        gender: null,
        introduction: null,
        homeLink: null,
        compositeMarketValue: null,
        businessAdaptationExponent: null,
        spreadExponent: null,
        activityExponent: null,
        growthExponent: null,
        healthExponent: null,
        periodType: null,
        listType: null,
        startTime: null,
        endTime: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加价值榜单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getValue(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改价值榜单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateValue(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addValue(this.form).then((response) => {
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
      this.$modal
        .confirm('是否确认删除价值榜单编号为"' + ids + '"的数据项？')
        .then(function () {
          return delValue(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "resource/value/export",
        {
          ...this.queryParams,
        },
        `value_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
