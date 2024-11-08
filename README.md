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

# 功能概述

## 1. 课程列表加载

在这个部分，用户通过登录即可进入到课程管理界面，此时，课程列表会被加载到表格中，以方便管理员直接对全部课程信息列表进行查看。

![示例图片](images/图片1.png)

## 2. 添加课程

![示例图片](images/图片2.png)

为了进行添加课程操作，管理员可以在加载出的课程管理界面中点击添加课程按钮，即可通过路由跳转到添加课程界面。

![示例图片](images/图片3.png)

在添加课程信息界面，用户可以填写表单进行课程信息的创建。其中包括课程名称、作者、学分、学时、类型、开课时间与结课时间、课程描述、课程封面以及课程视频进行设置。填写好表单后，点击提交按钮即可成功提交。提交后，会有添加用户成功的提示弹框。

接下来，需要着重解释一下课程封面上传以及视频上传部分。

### 课程封面上传
![示例图片](images/图片4.png)
![示例图片](images/图片5.png)

对于图片上传，点击上传组件即可弹出电脑文件查找框，选择合适的课程封面后，即可显示图片上传成功，并且可以在上传组件框中预览到图片的缩略图。

![示例图片](images/图片6.png)

### 视频上传
![示例图片](images/图片7.png)
![示例图片](images/图片8.png)

对于视频上传部分，可以点击上传课程视频按钮进行上传，并且只能提交 mp4 格式文件。首先尝试在上传视频接口上传图片，会提示错误信息“请上传视频文件”。选择上传视频文件后，会提示文件上传成功，并且能够看到上传的视频预览图，并且可以放大进行视频观看，同时也提供倍速调整、下载等功能。

## 3. 删除课程
![示例图片](images/图片9.png)
![示例图片](images/图片10.png)

对于删除课程功能，首先要选中需要被删除的课程，点击删除课程按钮。点击后会提示是否删除 1 个课程，点击确定即可将课程删除。
![示例图片](images/图片11.png)
![示例图片](images/图片12.png)

同时，课程删除也提供批量删除功能，可以同时勾选多个待删除课程信息，点击删除按钮即可将这些课程信息全部删除，同时也提示成功删除 n 门课程的信息。

## 4. 更新课程功能
![示例图片](images/图片13.png)
![示例图片](images/图片14.png)

对于更新课程功能，首先需要选中待更新课程，然后点击更新课程按钮，即可通过路由跳转到更新课程信息表单页面。在该页面可以直接看到表单各项填充了该课程相应信息，这样管理员可以在原信息基础上进行课程信息的修改。

提交修改信息后，即可跳转回课程管理界面，如图可以看到 22 号课程的课程信息被成功修改。

## 5. 导出课程功能
![示例图片](images/图片15.png)
![示例图片](images/图片16.png)
![示例图片](images/图片17.png)

对于导出课程列表部分，只需点击导出课程按钮，在弹框中点击确认，即可自动导出 xlsx 格式的表格文件。同时，表格中的内容为课程的详细信息，而不是课程管理界面展示出的课程列表中的粗略信息。

## 6. 查看课程详情功能
![示例图片](images/图片118.png)
![示例图片](images/图片19.png)

对于查看课程详情部分，首先选中待查看课程，然后点击查看详情按钮，即可跳转到查看课程详细信息页面，点击返回按钮即可返回课程管理主界面。

## 7. 课程审核
![示例图片](images/图片20.png)
![示例图片](images/图片21.png)
![示例图片](images/图片22.png)
![示例图片](images/图片23.png)
![示例图片](images/图片24.png)

课程审核部分只有超级管理员可以进行操作。

对于课程审核的查看课程审核情况部分，可以通过下拉框进行课程审核状态的筛选。

![示例图片](images/图片25.png)
![示例图片](images/图片26.png)

对于课程审核部分，超级管理员可以通过控制课程审核处的开关组件进行课程审核，课程列表中的课程状态也即时相应地进行修改。


