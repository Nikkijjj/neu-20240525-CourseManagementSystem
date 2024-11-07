# neu-20240525-RestaurantSystem
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
