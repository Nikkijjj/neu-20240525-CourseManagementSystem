//创建一个路由器，并暴露出去

//第一步，引入createRouter
import { createRouter, createWebHistory } from "vue-router";
//引入一个个可能要呈现的组件
import superManagerLogin from "@/components/superManagerLogin.vue";
import tenantManagerLogin from "@/components/tenantManagerLogin.vue";
import register from "@/components/register.vue";
import Layout from "@/components/Layout.vue";
import Login from "@/components/firstPage.vue";
import Test from "@/components/Test.vue";
import test from "@/components/Test.vue"
import Test1 from "@/components/Test1.vue";
import Test2 from "@/components/Test2.vue";
import addCourse from "@/components/addCourse.vue"
import updateCourse from "@/components/updateCourse.vue"
import courseDetail from "@/components/coueseDetail.vue"
import Layout_2 from "@/components/Layout_2.vue"
import checkUserInfo from '@/components/checkUserInfo.vue'
import updateUserInfo from '@/components/updateUserInfo.vue'

//第二步，创建路由器
const router = createRouter({
    history:createWebHistory(),//路由器的工作模式
    routes:[
        {
            path:'/',
            component: Login,
        },
        {
            path:'/superManagerLogin',
            component: superManagerLogin,
        },
        {
            path:'/tenantManagerLogin',
            component: tenantManagerLogin,
        },
        {
            path:'/register',
            component: register,
        },
        {
            path:'/home',
            component: Layout,
            redirect:'/test1',
            children:[
                {
                    path:'/test1',
                    component: Test1, 
                }, 
                {
                    path:'/test2',
                    component: Test2, 
                },
                {
                    path:'/test1/addCourse',
                    name:'addCourse',
                    component: addCourse,
                },
                {
                    path:'/test1/updateCourse/:courseId',
                    name:'updateCourse',
                    component: updateCourse,
                    props: true
                },
                {
                    path:'/test1/courseDetail/:courseId',
                    name:'courseDetail',
                    component: courseDetail,
                    props: true
                },
            ],
        },
        {
            path:'/home',
            name:'Layout_2',
            component: Layout_2,
            redirect:'test',
            children:[
                {
                    path:'/test',
                    name:'test',
                    component: test,  
                },
                {
                    path:'/test/:username',
                    name:'Test',
                    component: Test,  
                },
                {
                    path:'/test/checkUserInfo',
                    name:'checkUserInfo',
                    component: checkUserInfo,
                },
                {
                    path:'/test/updateUserInfo',
                    name:'updateUserInfo',
                    component: updateUserInfo,
                },
            ]
        },
        {
            path:'/',
            redirect: '/superManagerLogin'
        }
        
        
    ]
})

//暴露出去router
export default router