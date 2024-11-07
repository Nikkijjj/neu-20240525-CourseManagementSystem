@echo off

rem 设置变量
set UPLOAD_URL=https://gitee.com/api/v5/repos/nikkijjj/cmh-images/cmhlogo
set ACCESS_TOKEN=d865cbe24cba3af8f21dd769ad9e8b27
set FILE_TO_UPLOAD="C:\Users\姜慧阳\Videos\软件测试课程视频2.mp4"

rem 使用curl命令上传文件到Gitee
curl -X POST -H "Authorization: token %ACCESS_TOKEN%" -F "file=@%FILE_TO_UPLOAD%" %UPLOAD_URL%

rem 可以根据需要添加其他命令或逻辑
