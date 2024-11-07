<template>
    <div class="content" style="width: 1200px;height: 660px;display: flex;flex-wrap: wrap;">
        <div style="margin: 5px 10px; background-color: #fff; width: 350px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px ; text-align: center; font-size: 20px"><b>租 户 注 册</b></div>
            <el-form :rules="rules" ref="userForm" style="margin-top: 40px;">
                <el-form-item prop="tenantname" label="企业名称">
                    <el-input size="medium" placeholder="请输入企业名称" v-model="input_1"
                              ></el-input>
                </el-form-item>
                <el-form-item prop="contactName" label="联系人">
                    <el-input size="medium" style="margin-left: 15px;" placeholder="请输入企业联系人" v-model="input_4"
                              ></el-input>
                </el-form-item>
                <el-form-item prop="contactPhone" label="联系方式">
                    <el-input size="medium"  placeholder="请输入企业联系方式" v-model="input_2"
                              ></el-input>
                </el-form-item>
                <el-form>
                    <el-form-item label="企业logo" prop="uploadedImage">
                        <UploadImg @uploaded="handleImageUpload"/>
                    </el-form-item>
                </el-form>
                <el-form-item prop="identifyCode">
                    <el-input v-model="verifycode" placeholder="请输入验证码" class="identifyinput"></el-input>
                </el-form-item>
            <el-form-item>
                <div class="identifybox">
                <div @click="refreshCode">
                    <s-identify :identifyCode="identifyCode"></s-identify>
                </div>
                <el-button @click="refreshCode" type='text' class="textbtn">看不清，换一张</el-button>
                </div>
            </el-form-item>
                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button class='button' type="primary" size="medium" autocomplete="off" @click="tenantRegister">注 册</el-button>
                </el-form-item>

            </el-form>
        </div>

        <div style="margin: 5px 5px; background-color: #fff; width: 350px; padding: 20px; border-radius: 10px">
            <div style="margin: 20px ; text-align: center; font-size: 20px"><b>管 理 员 注 册</b></div>
            <el-form :rules="rules" ref="userForm">
                <el-form-item prop="username" label="用户名">
                    <el-input size="medium" style="margin-right: 1px;margin-left: 13px" placeholder="请输入用户名" v-model="input"
                              ></el-input>
                </el-form-item>

                <el-form-item prop="nickname" label="用户昵称">
                    <el-input size="medium" placeholder="请输入用户名" v-model="input1"
                              ></el-input>
                </el-form-item>
            
                <el-form-item prop="phone" label="联系方式">
                    <el-input size="medium"  placeholder="请输入个人联系方式" v-model="input2"
                              ></el-input>
                </el-form-item>
                <el-form-item prop="password" label="密 码">
                    <el-input size="medium" style="margin-right: 1px;margin-left: 13px" show-password placeholder="请输入密码"
                    v-model="input3"
                             ></el-input>
                </el-form-item>
                <el-form-item prop="email" label="邮 箱">
                    <el-input size="medium" style="margin-right: 1px;margin-left: 23px"  placeholder="请输入邮箱" v-model="input4"
                              ></el-input>
                </el-form-item>
                <el-form-item prop="post" label="职 位">
                    <el-input size="medium" style="margin-right: 1px;margin-left: 23px"  placeholder="请输入您的职位" v-model="input5"
                              ></el-input>
                </el-form-item>
                <el-form-item prop="role" label="所属角色">
                    <el-input size="medium"  placeholder="请输入您的角色" v-model="input6"
                              ></el-input>
                </el-form-item>

                <el-form-item prop="gender" label="性 别">
                    <el-radio v-model="radio" value="男" style="margin-right: 50px;margin-left: 25px" >男</el-radio>
                    <el-radio v-model="radio" value="女" style="margin-right: 1px;margin-left: 13px" >女</el-radio>
                </el-form-item>

                <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button class='button' type="primary" size="medium" autocomplete="off" @click="userRegister">注 册</el-button>
                </el-form-item>

            </el-form>
        </div>
    </div>
        
    
</template>

<script lang="ts">
    import { onMounted, ref } from 'vue';
    import axios from 'axios';
    import SIdentify from './Sidentify.vue';
    import { useRouter } from 'vue-router';
    import UploadImg from '@/components/UploadImg_jhy.vue';
    import { ElMessage } from 'element-plus';
    export default {
    name:'register',
   }
</script>

<script lang="ts" setup>
    const input = ref('');//用户名
    const input1 = ref('');//用户昵称
    const input2 = ref('');//联系方式
    const input3 = ref('');//密码
    const input5 = ref('');//职位
    const input4 = ref('');//邮箱
    let image1 = ref('');//企业logo
    
    const input6 = ref('');//所属角色
    const radio = ref('男');//性别

    const input_1 = ref('');//企业名称
    const input_2 = ref('');//联系方式
    const input_4 = ref('');//联系人
    const verifycode = ref('')

     // 生成6位随机数字作为tenantId
    const tenantId = Math.floor(100000 + Math.random() * 900000);
    const deptId = Math.floor(10 + Math.random() * 90)


    let sidentifyMode = ref('')
    let identifyCode = ref('')
    const router = useRouter();
    const message = ElMessage;

    let identifyCodes = ref('1234567890abcdefjhijklinopqrsduvwxyz')//验证码出现的数字和字母

    function getCurrentDateTime() {
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0');
        const day = String(now.getDate()).padStart(2, '0');
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');

        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    }

    // 使用函数获取当前时间
    const currentTime = getCurrentDateTime();

    // 自定义验证码规则
    const validateVerifycode = (rule: any, value: string, callback: (error?: Error) => void) => {
        if (value === '') {
            callback(new Error('请输入验证码'));
        } else if (value !== identifyCode.value) {
            callback(new Error('验证码不正确!'));
        } else {
            callback();
        }
    };

    const rules = {
        name: [
            {required: true, message: '请输入用户名', trigger:'blur'},
            {min:3, max: 10, message: '长度在3到10个字符', trigger: 'blur'}
        ],
        password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        verifycode: [{
          required: true,
          trigger: 'blur',
          validator: validateVerifycode,
        }]
    };

     


    onMounted(()=>{
        identifyCode.value = ''
        makeCode(identifyCodes.value, 4)
    })

    const randomNum = (min:number, max: number) => {
        max = max + 1
        return Math.floor(Math.random() * (max - min) + min)
    }


    const makeCode = (o: string,l: number) => {
        for(let i = 0; i < 1; i++){
            identifyCode.value += o[randomNum(0, o.length)]
        }
    }

    const refreshCode = () => {
        identifyCode.value = ''
        makeCode(identifyCodes.value, 4)
    }
    

    const handleImageUpload = (imageUrl: any) => {
        image1.value = imageUrl.download_url;
    };

    //注册
    async function tenantRegister(imageUrl: string){
        console.log(image1.value);
        try{
            const response = await axios.post('/api/user/addTenant',{
                tenantId: tenantId,
                tenantName: input_1.value,
                contactPhone: input_2.value,
                contactName: input_4.value,
                logo :image1.value
            });
            //请求成功处理
            console.log('租户注册成功!');
            message.success('租户注册成功！');
        } catch (error) {
            console.error('租户注册失败！');
            message.error('租户注册失败！');
        }
    }

    async function userRegister(){
        try{
            // 构造数据对象，包括 createTime
            const userData = {
                username: input.value,
                nickname: input1.value,
                phone: input2.value,
                password: input3.value,
                email: input4.value,
                deptId: 11,
                post: input5.value,
                role: input6.value,
                gender: radio.value,
                tenantId: 123456,
                state: 0, // 初始化state为0
                description: null,
                createTime: currentTime  // 将当前时间作为字符串格式传递给后端
            };

            // 发送 POST 请求
            const response = await axios.post('/api/user/addUser', userData);
            //请求成功处理
            console.log('用户注册成功!');
            message.success('用户注册成功！');
            router.push('/')
        } catch (error) {
            console.error('用户注册失败！');
            message.error('用户注册成功！');
        }
    }
    
</script>

<style scoped>
    .content{
        /* background-image: url('/src/images/backpic.jpg'); */
        background-color: aliceblue;
        background-size: cover;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 5px;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 40px;
    }
    .button {
        display: grid;
        place-items: center; /* 水平和垂直居中 */
        width: 40%; /* 设置按钮宽度为父元素宽度的50% */
        height: 35px; /* 设置按钮固定高度 */
        margin-left: 105px;
    }
    .upload-demo {
        display: inline-block;
        width: 30px;
        height: 30px;
        margin-bottom: 100px;
    }

    .upload-demo .button {
        width: 100px; /* 设置卡片宽度 */
        height: 100px; /* 设置卡片高度 */
        border: 1px solid #ccc; /* 设置边框 */
        border-radius: 8px; /* 设置边框圆角 */
    }
    

</style>