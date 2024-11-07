import axios from "axios";
import { ElMessage } from 'element-plus';

const GITEE_API = "https://gitee.com/api/v5/repos";
const ACCESS_TOKEN = "2bbc9bbf59846b29e686d83f32532f5a";  // 替换为你的 Gitee 个人访问令牌
const OWNER = "nikkijjj";  // 替换为你的 Gitee 用户名
const REPO = "cmh-images";  // 替换为你的仓库名称
const PATH = "/cmhvideo/";  // 替换为你的存储路径

const service = axios.create({
    baseURL: GITEE_API
});

service.interceptors.response.use(response => {
    const code = response.status || 200;
    if (code === 200 || code === 201) {
        return response.data;
    }

    let msg = response.data.message || "上传视频失败";
    ElMessage.error(msg);
    return Promise.reject('上传视频失败：' + msg);
});

/**
 * 上传图片
 * @param {File} file 图片文件
 * @param {Ref} progress 上传进度
 * @returns promise
 */
function uploadVideo(file, progress) {
    const timestamp = new Date().getTime();
    const fileName = `${timestamp}_${file.name}`;
    const url = `${OWNER}/${REPO}/contents${PATH}${fileName}`;
    console.log("上传时URL："+url);

    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            const base64Content = reader.result.split(',')[1]; // 去掉前缀的base64编码
            const data = {
                access_token: ACCESS_TOKEN,
                message: "添加视频",
                content: base64Content,
            };

            service({
                url: url,
                method: "POST",
                data: data,
                headers: {
                    'Content-Type': 'application/json'
                },
                onUploadProgress(event) {
                    let percentage = Math.round((event.loaded * 100) / event.total);
                    progress.value = percentage;
                }
            }).then(response => {
                console.log("上传响应:", response); // 添加调试信息
                ElMessage.success("视频上传成功！");
                resolve(response);
            }).catch(error => {
                if (error.response) {
                    console.error("上传失败:", error.response.data);
                    ElMessage.error(`上传失败: ${error.response.data.message}`);
                } else {
                    console.error("上传失败:", error.message);
                    ElMessage.error('上传失败：未知错误');
                }
                reject(error);
            });
        };
        reader.onerror = error => {
            console.error("文件读取错误：", error);
            ElMessage.error("文件读取失败");
            reject(error);
        };
    });
}

export { uploadVideo };
