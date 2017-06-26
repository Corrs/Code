<template>
  <div >
    <el-row>
      <el-col>
        <div class="login_wrapper">
          <div class="animate form login_form">
            <section class="login_content">
              <transition name="slide-fade" mode="out-in">
                <div v-show="isLoginPage">
                  <el-form id="loginForm" :model="loginForm" ref="loginForm" :rules="loginRules" :show-message="true">
                    <h1>用户登录</h1>
                    <input type="hidden" name="mobile" v-model="loginForm.username">
                    <input type="hidden" name="email" v-model="loginForm.username">
                    <el-form-item prop="username">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="username">用户名</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input id="username" type="text" auto-complete="off" name="username" v-model.trim="loginForm.username" placeholder="请输入用户名" autofocus></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="password">密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input id="password" type="password" auto-complete="off" name="password" v-model.trim="loginForm.password" placeholder="请输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="validCode">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="validCode">验证码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-row>
                            <el-col :xs="15" :sm="15" :md="14" :lg="14">
                              <el-input id="validCode" class="serverValidCode" type="text" auto-complete="off" name="validCode" v-model.trim="loginForm.validCode" placeholder="请输入验证码"></el-input>
                            </el-col>
                            <el-col :xs="9" :sm="9" :md="10" :lg="10">
                              <label class="no-padding" for="validCode" @click="getValidCode('#loginCode')"><a title="点击刷新"><img id="loginCode" alt="点击刷新验证码" class="valid_code_img"></a></label>
                            </el-col>
                          </el-row>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item>
                      <el-row>
                        <el-col :xs="12" :md="12">
                          <el-checkbox id="checkedCookie" v-model="checkedCookie">10天内免登录</el-checkbox>
                        </el-col>
                        <el-col :xs="12" :md="12">
                          <a class="right-link">忘记密码？</a>
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col>
                          <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col>
                          <a @click="changePage" class="right-link">立即注册</a>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </el-form>
                </div>
              </transition> 
              <transition name="slide-fade" mode="out-in">
                <div v-show="!isLoginPage">
                  <el-form id="registerForm" :model="registerForm" ref="registerForm" :rules="registerRules" :show-message="true">
                    <h1>用户注册</h1>
                    <el-form-item prop="username">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="username">用户名</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="text" auto-complete="off" name="username" v-model.trim="registerForm.username" placeholder="请输入用户名" autofocus></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="password">密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="password" auto-complete="off" name="password" v-model.trim="registerForm.password" placeholder="请输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="confirmPwd">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="password">确认密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="password" auto-complete="off" name="confirmPwd" v-model.trim="registerForm.confirmPwd" placeholder="请再次输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="mobile">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="mobile">手机号</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="text" auto-complete="off" name="mobile" v-model.number="registerForm.mobile" placeholder="请输入手机号"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="email">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="email">邮箱</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="text" auto-complete="off" name="email" v-model.trim="registerForm.email" placeholder="请输入邮箱">
                            <el-button slot="append" :disabled="disabled" @click="sendMail" v-text="btnText"></el-button>
                          </el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="validCode">
                      <el-row>
                        <el-col :xs=24 :sm="24" :md="6" :lg="6" >
                          <label for="validCode">验证码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input class="serverValidCode" type="text" auto-complete="off" name="validCode" v-model.trim="registerForm.validCode" placeholder="请输入验证码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item>
                      <el-row>
                        <el-col>
                          <el-button type="primary" @click="submitForm('registerForm')">注册</el-button>
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col>
                          <a @click="changePage" class="right-link">已有帐号，请登录</a>
                        </el-col>
                      </el-row>
                    </el-form-item>
                  </el-form>
                </div> 
              </transition>
              <div class="separator company">
                <h2><i><img src="../../assets/img/common/logo.png" alt="logo"></i> 潍坊匠人网络科技有限公司</h2>
                <p>Copyright &copy; 2005-2017 潍坊匠人网络科技有限公司</p>
                <p class="en">(Weifang chinsesjr Network Technology Co., Ltd.) All Rights Reserved</p>
              </div>
            </section>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import router from '@/router'
export default {
  data () {
    var validateUsername = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'));
      } else {
        callback();
      }
    };

    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        callback();
      }
    };

    var validateValidCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入验证码'));
      } else if (value !== this.loginCode.toLowerCase()) {
        callback(new Error('验证码不正确，请重新输入'));
      } else {
        callback();
      }
    };
    return {
      loginForm: {
        username: '',
        password: '',
        validCode: ''
      },
      registerForm: {
        username: '',
        password: '',
        validCode: '',
        confirmPwd: '',
        mobile: '',
        email: ''
      },
      loginCode: '',
      loginRules: {
        username: [
          { validator: validateUsername, trigger: 'blur' }
        ],
        validCode: [
          {validator: validateValidCode, trigger: 'blur' }
        ],
        password: [
          {validator: validatePassword, trigger: 'blur' }
        ]
      },
      registerRules: {

      },
      disabled: false,
      btnText: "获取验证码",
      checkedCookie: false,
      wait: 60,
      isLoginPage: true
    }
  },
  created () {
    this.getValidCode("#loginCode");
  },
  mounted() {
    this.initValidCodeInput();
  },
  methods: {
    getValidCode(elementId) {
      // 从服务器获取验证码
      var _this = this;
      this.$axios.get(jr.server.url + "/login/validCode").then(function(response){
        $(elementId).prop("src", "data:image/png;base64," + response.data.validCode);
        _this.loginCode = response.data.code;
      }).catch(function(error){
      });
    },
    initValidCodeInput() {
      // 验证码输入框取消右边框圆角
      $(".serverValidCode input").css("borderTopRightRadius",0).css("borderBottomRightRadius",0);
    },
    submitForm(formName) {
      var _this = this;
      _this.$refs[formName].validate((valid) => {
        if (valid) {
          //alert('submit!');
          $("#loginForm").serialize();
          _this.$axios.post(jr.server.url + "/login/loginUser",$("#loginForm").serialize()).then(function(response){
            // debugger;
            console.log(router);
            if(response.data.success) {
              router.push("/");
            }
          }).catch(function(error){

          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      // debugger; 
      // 重置表单数据 必须在el-form-item中指定prop属性  该属性值为form对应:model对象的属性名
      this.$refs[formName].resetFields();
    },
    changePage() {
      this.isLoginPage = !this.isLoginPage;
      this.loginCode = '';
      this.isLoginPage ? this.$refs["loginForm"].resetFields() : this.$refs["registerForm"].resetFields();
    }, 
    time() {
      var _this = this;
      if (_this.wait == 0) {
        _this.disabled = false;
        _this.btnText = "获取验证码";
        _this.wait = 60;
      } else { 
        _this.disabled = true;
        _this.btnText = "重新发送(" + _this.wait + ")";
        _this.wait--;
        setTimeout(function() {
            _this.time();
        },1000);
      }
    },
    sendMail() {
      // debugger;
      var _this = this;
      _this.disabled = true;
      if(!_this.registerForm.email) {
        _this.$refs["registerForm"].validateField("email");
        _this.disabled = false;
        return false;
      }
      _this.time();
      _this.$axios.get(jr.server.url + "/login/validEmail?email=" + _this.registerForm.email).then(function(response) {
        // debugger;
        console.log(response);
      }).catch(function(error) {
        
      });
    }
  }
}
</script>

<style>
a {
  cursor: pointer;
}

*, :after, :before {
    box-sizing: border-box;
}

.valid_code_img {
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  height: 36px;
  width:100%;
}

.login_wrapper {
    right: 0;
    margin: 5% auto 0;
    max-width: 350px;
    position: relative;
}

.login_form {
    z-index: 22;
    padding: 10px;
}

.login_form, .registration_form {
    position: absolute;
    top: 0;
    width: 100%;
}

.animate {
    -webkit-animation-duration: .5s;
    -webkit-animation-timing-function: ease;
    -webkit-animation-fill-mode: both;
    -moz-animation-duration: .5s;
    -moz-animation-timing-function: ease;
    -moz-animation-fill-mode: both;
    -o-animation-duration: .5s;
    -o-animation-timing-function: ease;
    -o-animation-fill-mode: both;
    -ms-animation-duration: .5s;
    -ms-animation-timing-function: ease;
    -ms-animation-fill-mode: both;
    animation-duration: .5s;
    animation-timing-function: ease;
    animation-fill-mode: both;
}

.login_content {
    margin: 0 auto;
    padding: 25px 0 0;
    position: relative;
    text-align: center;
    text-shadow: 0 1px 0 #fff;
}

.login_content form {
    margin: 20px 0;
    position: relative;
}

.login_content h1 {
    font: 400 25px Helvetica,Arial,sans-serif;
    letter-spacing: -.05em;
    line-height: 20px;
    margin: 10px 0 30px;
}

.login_content h1:after, .login_content h1:before {
    content: "";
    height: 1px;
    position: absolute;
    top: 10px;
    width: 20%;
}

.login_content h1:after {
    background: #7e7e7e;
    background: linear-gradient(left,#7e7e7e 0,#fff 100%);
    right: 0;
}

.login_content h1:before {
    background: #7e7e7e;
    background: linear-gradient(right,#7e7e7e 0,#fff 100%);
    left: 0;
}


a.right-link {
  text-decoration: none;
  color: #2e7fdb;
  display: block;
  text-align: right;
}

a.right-link:hover {
  text-decoration: underline;
}

label {
  display: block;
  text-align: right;
  padding-right: 10px;
}

label.no-padding{
  padding: 0;
}

button {
  width: 100%;
}

div.el-form-item__error {
  left: 83px !important;
}

.el-checkbox__label {
  font-size: 12px;
}

.separator {
    border-top: 1px solid #D8D8D8;
    margin-top: 10px;
    padding-top: 10px;
}

.company h2 {
  letter-spacing: -0.05em;
  font: 400 20px/8px Helvetica, Arial, sans-serif;
  margin: 10px 0px 30px;
  line-height: 30px;
}

.company img {
  width: 50px;
}

.company p.en {
  font-size: 14px !important;
}


/* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all .3s ease;
}
.slide-fade-leave-active {
  transition: all .0s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-active {
  transform: translateX(10px);
  opacity: 0;
}

@media all and (max-width: 991px) {
  label {
    text-align: left;
  }

  div.el-form-item__error {
    left: 0 !important;
  }

  /*.company {
    display: none;
  }*/
}

</style>


