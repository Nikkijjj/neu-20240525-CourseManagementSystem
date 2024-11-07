# 系统基本功能

## 课程信息管理

### 课程添加
管理员可以通过输入课程的基本信息（如课程名称、课程封面、课程简介、课程排序、课程视频、作者等）来添加新课程。在点击“新增”按钮后，会打开创建课程弹窗，所有文本框默认值为空，课程封面默认为空，课程视频需要上传mp4格式。点击“确定”按钮完成课程创建，所有必填字段不能为空。创建成功后返回课程列表界面；创建失败则弹出提示失败原因，并停留在创建课程界面。

### 课程编辑
管理员可以对已有课程的信息进行修改。可编辑信息包括课程名称、课程封面、课程简介、课程排序、课程视频、作者等。点击课程列表中的“修改”按钮后，会打开课程修改页面，文本框默认值为当前课程信息。点击“确定”按钮完成课程信息修改，所有必填字段不能为空。修改成功后返回课程列表界面；修改失败则弹出提示失败原因，并停留在课程修改界面。

### 课程删除
管理员可以删除不再开设的课程。点击课程列表操作区的“删除”按钮后，会弹出确认删除提示框。选择“是”完成删除，选择“否”则返回至课程列表界面。删除失败会弹出提示失败原因，并停留在课程列表界面。

### 课程浏览
超级管理员和租户管理员可以浏览现有课程的列表，包括课程名称、课程简介等。

### 课程搜索
用户可以通过课程名称、课程排序等关键字进行模糊搜索。点击搜索按钮后，会在课程列表中显示搜索到的课程信息。

### 课程详情
用户点击课程标题后，可以查看课程的详细内容，包括课程视频、课程名称、作者等信息。

### 课程导出
点击操作区的导出按钮，可以导出课程信息到Excel文件，并且课程信息中包含每个课程的全部信息而不只是显示列表中的内容。

## 超级管理员审核管理

### 审核列表
超级管理员可以查看租户提交的课程发布审核列表。

### 审核操作
超级管理员可以进行通过审核和不通过审核操作。已通过审核的课程可展示在小程序端，未通过审核的课程会在租户端课程列表中展示审核状态。租户可以重新编辑后再次提交审核。

通过这些功能，课程管理模块能够有效地支持课程的全生命周期管理，从创建、编辑到删除，以及课程内容的审核和发布。这样不仅能保证课程信息的准确性和及时性，还能提高管理员的工作效率，提升用户体验。

# 模块架构

在课程管理模块中，前端使用了 **Vue3** + **Typescript** + **CSS** 作为主要技术，后端使用了 **Springboot** + **Mybatis** + **Java** + **MySQL** 数据库作为主要技术。

## 前端部分

在前端部分，我主要设计了几个重要的组件，包括：

- **CourseManage.vue**：负责课程列表页，包含课程模糊搜索、课程列表加载、添加课程、删除课程、更新课程、导出课程Excel表格、查看课程信息等功能的接口和实现。
- **addCourse.vue**：用于添加课程的组件。
- **updateCourse.vue**：用于更新课程的组件。
- **courseDetail.vue**：用于查看课程详情的组件。
- **courseCheck.vue**：用于超级管理员的课程审核功能，在这个组件中可以对课程进行审核并更新其审核状态。

另外，使用了 **Vue Router** 管理页面的导航，包括课程管理页和课程审核页。同时，各个组件之间的跳转也采用了路由配置。对于需要获取用户名进行相应操作的组件之间，还采用了路由传参进行实现。

对于前端与后端的交互，我采用了 **axios** 进行了实现，根据不同的功能采用 **GET** 和 **POST** 进行前后端链接，如获取课程列表、创建课程、更新课程信息、删除课程等，进而实现与数据库之间的连接。

## 后端部分

在后端部分，分别设置了三个主要层级：

- **Entity层**：定义课程实体类，定义课程相关属性，如课程名称、课程描述、作者等信息。
- **Controller层**：主要负责处理前端请求，包括获取课程列表、搜索课程、创建课程、更新课程、删除课程等接口。同时也能将后端处理得到的结果返回给前端。
- **Biz层**：主要实现课程管理的业务逻辑处理。
- **Mapper层**：主要定义了 Mybatis 的 Mapper 接口，定义 SQL 操作，如插入、更新、删除、查询课程信息等。

通过这样的模块架构设计，课程管理模块能够高效地实现课程的增删改查功能，并保证前后端的良好交互。

