<template>
    <div class="login">
        <el-card class="card-container-1" shadow="always">
            <el-row class="form-1" type="flex" justify="space-between">
                <el-col :span="12">
                    <el-form ref="ruleFormRef" style="max-width: 600px" :model="ruleForm" :rules="rules"
                        label-width="auto" :size="formSize" status-icon>
                        <el-form-item label="租户名称" prop="tenantName" class="Input">
                            <el-input v-model="ruleForm.tenantName"></el-input>
                        </el-form-item>
                        <el-form-item label="联系人姓名" prop="contactName" class="Input">
                            <el-input v-model="ruleForm.contactName"></el-input>
                        </el-form-item>
                        <el-form-item label="联系方式" prop="contactPhone" class="Input">
                            <el-input v-model="ruleForm.contactPhone"></el-input>
                        </el-form-item>
                    </el-form>
                </el-col>
                <el-col :span="8" class="upload-container">
                    <el-form :label-position="labelPosition">
                        <el-form-item label="租户图标" prop="uploadedImage">
                            <UploadImg @uploaded="handleImageUpload" />
                        </el-form-item>
                    </el-form>
                    <br>
                </el-col>
            </el-row>
        </el-card>
        <br>
        <el-card class="card-container-2" shadow="always">
            <el-form ref="ruleFormRef" style="max-width: 800px" :model="ruleForm" :rules="rules" label-width="auto"
                class="form-2" :size="formSize" status-icon :inline="true">
                <el-form-item label="管理员账号" prop="username" class="Input" style="width: 730px;">
                    <el-input v-model="ruleForm.username"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="账号密码" prop="password" class="Input" style="width: 730px;">
                    <el-input v-model="ruleForm.password" type="password" show-password
                        auto-complete="new-password"></el-input>
                </el-form-item>
                <br>
                <el-form-item label="用户昵称" prop="nickname" class="Input">
                    <el-input v-model="ruleForm.nickname" />
                </el-form-item>
                <el-form-item label="用户性别" class="Input">
                    <el-radio-group v-model="ruleForm.gender">
                        <el-radio value="男">男</el-radio>
                        <el-radio value="女">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="联系方式" prop="phone" class="Input">
                    <el-input v-model="ruleForm.phone" />
                </el-form-item>
                <el-form-item label="邮箱地址" prop="email" class="Input">
                    <el-input v-model="ruleForm.email" />
                </el-form-item>
                <el-form-item label="描述" style="width: 733px;">
                    <el-input v-model="ruleForm.description" type="textarea" />
                </el-form-item>
                <el-form-item label="验证码" prop="captcha">
                    <ImgKaptcha v-model="ruleForm.captcha" ref="captchaRef" />
                </el-form-item>
                <div style="display: flex; justify-content: flex-end;">
                    <el-button type="primary" @click.prevent="register(ruleFormRef)">注册</el-button>
                    <el-button @click.prevent="resetForm(ruleFormRef)">重置</el-button>
                </div>
            </el-form>
        </el-card>
    </div>
</template>

<script setup lang="ts" name="register">
import UploadImg from '../components/meeting/UploadImg_jhy.vue';
import ImgKaptcha from '../components/meeting/ImgKaptcha.vue';
import axios from 'axios';
import { ref, reactive } from 'vue';
import type { ComponentSize, FormInstance, FormRules } from 'element-plus';
import { ElMessage } from 'element-plus';
import { useRouter } from 'vue-router';
import type { FormProps } from 'element-plus';

const router = useRouter();

interface RuleForm {
    tenantId: string;
    tenantName: string;
    logo: string;
    contactName: string;
    contactPhone: string;
    username: string;
    password: string;
    role: string;
    nickname: string;
    gender: string;
    deptId: number;
    post: string;
    phone: string;
    email: string;
    state: string;
    createTime: string;
    description: string;
    captcha:string
}

const formSize = ref<ComponentSize>('default');
const ruleFormRef = ref<FormInstance>();
const captchaRef = ref<any>();

let ruleForm = reactive<RuleForm>({
    tenantId: '',
    tenantName: '11',
    logo: '',
    contactName: '11',
    contactPhone: '11',
    username: '11',
    password: '11',
    role: '租户管理员',
    nickname: '11',
    gender: '',
    deptId: 11,
    post: '11',
    phone: '11',
    email: '',
    state: '正常',
    createTime: '',
    description: '',
    captcha:''
});

const rules = reactive<FormRules<RuleForm>>({
    tenantName: [{ required: true, message: '请输入租户名称', trigger: 'blur' }],
    contactName: [{ required: true, message: '请输入联系人姓名', trigger: 'blur' }],
    contactPhone: [{ required: true, message: '请输入联系人电话号码', trigger: 'blur' }],
    username: [{ required: true, message: '请输入管理员账号用户名', trigger: 'blur' }],
    password: [{ required: true, message: '请输入管理员账号密码', trigger: 'blur' }],
    nickname: [{ required: true, message: '请输入管理员账号昵称', trigger: 'blur' }],
    phone: [{ message: '请输入联系人电话号码', trigger: 'blur' }],
    email: [
        { message: '请输入邮箱地址', trigger: 'blur' },
        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ],
    captcha: [{ required: true, message: '请输入验证码', trigger: 'blur' }]

});

const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    formEl.resetFields();
};

const labelPosition = ref<FormProps['labelPosition']>('right');

const register = async (formEl: FormInstance | undefined) => {
    if (!formEl) return;
    await formEl.validate(async (valid) => {
        if (valid) {
            const captchaValid = await captchaRef.value.verifyCaptcha();
            if (captchaValid) {
                const currentDate = new Date();
                const year = currentDate.getFullYear();
                const month = String(currentDate.getMonth() + 1).padStart(2, '0'); // 月份从 0 开始，使用 padStart 补齐两位数
                const day = String(currentDate.getDate()).padStart(2, '0'); // 日份使用 padStart 补齐两位数
                ruleForm.createTime = `${year}-${month}-${day}`;
                let tenantFd = new FormData();
                tenantFd.append("tenantId", ruleForm.tenantId);
                tenantFd.append("tenantName", ruleForm.tenantName);
                tenantFd.append("logo", ruleForm.logo);
                tenantFd.append("contactName", ruleForm.contactName);
                tenantFd.append("contactPhone", ruleForm.contactPhone);
                tenantFd.append("description", ruleForm.description);
                let userFd = new FormData();
                userFd.append("username",ruleForm.username);
                userFd.append("password",ruleForm.password);
                userFd.append("role",ruleForm.role);
                userFd.append("nickname",ruleForm.nickname);
                userFd.append("gender",ruleForm.gender);
                userFd.append("deptId",ruleForm.deptId.toString());
                userFd.append("post",ruleForm.post);
                userFd.append("phone",ruleForm.phone);
                userFd.append("email",ruleForm.email);
                userFd.append("state",ruleForm.state);
                userFd.append("createTime", ruleForm.createTime);
                userFd.append("description", ruleForm.description);
                

                axios.post('/api/tenant/register', tenantFd)
                    .then((res) => {
                        if (res.data.isOk) {
                            ElMessage.success('企业租户注册成功！');
                            ruleForm.tenantId = res.data.tenantId;
                            console.log("前：" + res.data.tenantId);
                            userFd.append("tenantId", ruleForm.tenantId);
                            console.log("后：" + ruleForm.tenantId);
                            axios.post('/api/user/register', userFd)
                                .then((res) => {
                                    if (res.data.isOk) {
                                        ElMessage.success('租户管理员注册成功！');
                                        setTimeout(() => {
                                            router.push('/login/tenant');
                                        }, 1000);
                                    } else {
                                        ElMessage.error('租户管理员注册失败！');
                                    }
                                })
                                .catch(() => {
                                    ElMessage.error('租户管理员注册失败，请稍后重试');
                                });
                        } else {
                            ElMessage.error('企业租户注册失败！');
                        }
                    })
                    .catch(() => {
                        ElMessage.error('企业租户注册失败，请稍后重试');
                    });

                

                
            } else {
                ElMessage.error('验证码错误');
            }
        } else {
            ElMessage.error('无效信息，提交失败！');
        }
    });
};
const handleImageUpload = (imageUrl: string) => {
  ruleForm.logo = imageUrl;
};

</script>

<style scoped>
.login {
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    background-image: url('../assets/background.jpg');
    background-size: cover;
    background-position: center;
    background-color: rgba(128, 128, 128, 0.5);
}

.card-container-1 {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 30px;
    width: 1000px;
    height: 300px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.card-container-2 {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 30px;
    width: 1000px;
    height: 500px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}

.icon {
    margin-top: 0%;
    margin-left: 120px;
    margin-bottom: 30px;
}

.form-1 {
    width: 800px;
}

.form-2 {
    width: 800px;
}

.Input {
    width: 350px;
    margin-bottom: 30px;
}

.uploader {
    display: flex;
}

.card {
    background-color: #fbfdff;
    border: 1px dashed #c0ccda;
    border-radius: 6px;
    width: 160px;
    height: 160px;
    overflow: hidden;
}
</style>