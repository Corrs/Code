<template>
  <div >
    <el-row>
      <el-col>
        <div class="login_wrapper">
          <div class="animate form login_form">
            <section class="login_content">
              <transition name="slide-fade" mode="out-in">
                <div v-show="isLoginPage">
                  <el-form id="loginForm" :action="loginForm.action" :model="loginForm" ref="loginForm" :rules="loginRules" :show-message="true">
                    <h1>用户登录</h1>
                    <el-form-item prop="username">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="username">用户名</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input id="username" type="text" auto-complete="off" name="username" v-model.trim="loginForm.username" @input="inputLoginUsername" placeholder="请输入用户名" autofocus></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="password">密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input id="password" type="password" auto-complete="off" name="password" v-model.trim="loginForm.password" placeholder="请输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="validCode">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
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
                          <el-checkbox id="checkedCookie" @click="this.checkedCookie = !this.checkedCookie" v-model="checkedCookie">10天内免登录</el-checkbox>
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
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="username">用户名</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="text" auto-complete="off" name="username" v-model.trim="registerForm.username" placeholder="请输入用户名" autofocus></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="password">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="password">密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="password" auto-complete="off" name="password" v-model.trim="registerForm.password" placeholder="请输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="confirmPwd">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="password">确认密码</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="password" auto-complete="off" name="confirmPwd" v-model.trim="registerForm.confirmPwd" placeholder="请再次输入密码"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="mobile">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
                          <label for="mobile">手机号</label>
                        </el-col>
                        <el-col :xs="24" :sm="24" :md="18" :lg="18">
                          <el-input type="text" auto-complete="off" name="mobile" v-model.number="registerForm.mobile" placeholder="请输入手机号"></el-input>
                        </el-col>
                      </el-row>
                    </el-form-item>
                    <el-form-item prop="email">
                      <el-row>
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
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
                        <el-col :xs="24" :sm="24" :md="6" :lg="6" >
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
  name: "login",
  data () {
    return {
      loginForm: {
        username: '',
        password: '',
        validCode: '',
        email: '',
        mobile: '',
        action: jr.server.url + '/login/loginUser'
      },
      registerForm: {
        username: '',
        password: '',
        validCode: '',
        confirmPwd: '',
        mobile: '',
        email: '',
        action: jr.server.url + "/login/register"
      },
      loginCode: '',
      endTime: '',
      loginRules: {},
      registerRules: {},
      disabled: false,
      btnText: "获取验证码",
      checkedCookie: false,
      wait: 60,
      isLoginPage: true
    }
  },
  created () {
    this.getValidCode("#loginCode");
    this.initLoginFormValidator();
    this.initRegisterFormValidator();
  },
  mounted() {
    this.initValidCodeInput();

    this.initCheckCookie();
  },
  methods: {
    initCheckCookie() {
      if(jr.getCookie("username") != "") {
        this.loginForm.username = jr.getCookie("username");
        this.inputLoginUsername();
        this.checkedCookie = true;
      }
    },
    inputLoginUsername() {
      this.loginForm.mobile = this.loginForm.email = this.loginForm.username;
    },
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
    initRegisterFormValidator() {
      let _this = this;
      // 注册验证
      let registerUsername = (rule, value, callback) => {
        //debugger;
        if (value === '') {
          callback(new Error('请输入用户名'));
        } else if (value.length<6 || value.length > 20) {
          callback(new Error('用户名长度为6-20个字符'));
        } else {
          _this.checkUser({username: value}, callback);
          //callback();
        }
      };

      let registerEmail = (rule, value, callback) => {
        //debugger;
        if (value === '') {
          callback(new Error('请输入邮箱'));
        } else if (value.length > 45) {
          callback(new Error('邮箱长度不能超过45个字符'));
        } else {
          _this.checkEmail({email:value}, callback);
        }
      };

      let registerMobile = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入手机号'));
        } else if (value.length > 11) {
          callback(new Error('手机号长度不能超过11个数字'));
        } else if(!(/^\d+$/.test(value))) {
          callback(new Error('手机号必须为数字'));
        } else {
          _this.checkMobile({mobile:value}, callback);
        }
      };

      let registerPwd = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else if (value.length > 20 || value.length < 6) {
          callback(new Error('密码长度为6-20个字符'));
        } else if(!(/^[A-Z]/.test(value)&&/[0-9]/.test(value)&&/[a-z]/.test(value))) {
          callback(new Error('必须以大写字母开头，且是字母和数字组合'));
        } else {
          callback();
        }
      };

      let registerCheckpwd = (rule, value, callback) => {
        if (value !== _this.registerForm.password) {
          callback(new Error('两次输入的密码不一致'));
        } else {
          callback();
        }
      };

      let validateValidCode = (rule, value, callback) => {
        let now = new Date();
        if (value === '') {
          callback(new Error('请输入验证码'));
        } else if (value.toLowerCase() !== _this.loginCode.toLowerCase()) {
          callback(new Error('验证码不正确，请重新输入'));
        } else if (now.getTime() - _this.endTime > 0) {
          callback(new Error('验证码已过期，请重新获取'));
        } else {
          callback();
        }
      };

      _this.registerRules.username = [{ validator: registerUsername, trigger: 'blur'}];
      _this.registerRules.email = [{ validator: registerEmail, trigger: 'blur'}];
      _this.registerRules.mobile = [{ validator: registerMobile, trigger: 'blur'}];
      _this.registerRules.password = [{ validator: registerPwd, trigger: 'blur'}];
      _this.registerRules.confirmPwd = [{ validator: registerCheckpwd, trigger: 'blur'}];
      _this.registerRules.validCode = [{ validator: validateValidCode, trigger: 'blur'}];
      
    },
    initLoginFormValidator() {
      // 登录验证
      let validateUsername = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入用户名'));
        } else {
          callback();
        }
      };

      let validatePassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          callback();
        }
      };

      let validateValidCode = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入验证码'));
        } else if (value.toLowerCase() !== this.loginCode.toLowerCase()) {
          callback(new Error('验证码不正确，请重新输入'));
        } else {
          callback();
        }
      };

      this.loginRules.username = [{ validator: validateUsername, trigger: 'blur' }];
      this.loginRules.validCode = [{ validator: validateValidCode, trigger: 'blur' }];
      this.loginRules.password = [{ validator: validatePassword, trigger: 'blur' }];
    },
    checkEmail(data, callback) {
      let _this = this;
      _this.$axios.post(jr.server.url + "/login/checkUser",$.param(data)).then(function(response){  
      //debugger;
        if(!response.data.valid) {
          callback(new Error('此邮箱已绑定账号，请更换邮箱！'));
        } else {
          callback();
        }
      }).catch(function(error){
        _this.$message.error("网络错误，访问失败！");
      });
    },
    checkMobile(data, callback) {
      let _this = this;
      _this.$axios.post(jr.server.url + "/login/checkMobile",$.param(data)).then(function(response){  
      // debugger;
        if(!response.data.success) {
          callback(new Error('网络错误，访问失败！'));
        } else if (response.data.success && !response.data.valid) {
          callback(new Error("请输入有效的手机号"));
        } else if (response.data.success && response.data.isExist) {
          callback(new Error("该手机号已绑定账户，请更换手机号！"));
        } else {
          callback();
        }
      }).catch(function(error){
        _this.$message.error("网络错误，访问失败！");
      });
    },
    checkUser(data, callback) {
      let _this = this;
      _this.$axios.post(jr.server.url + "/login/checkUser",$.param(data)).then(function(response){  
        if(!response.data.valid) {
          callback(new Error('用户名已注册！'));
        } else {
          callback();
        }
      }).catch(function(error){
        _this.$message.error("网络错误，访问失败！");
      });
    },
    submitForm(formName) {
      let _this = this;
      let $form = _this.$refs[formName];
      $form.validate((valid) => {
        if (valid) {
          _this.$axios.post($form.model.action,$.param($form.model)).then(function(response){
            console.log(router);
            if(response.data.success) {
              debugger;
              if(jr.getCookie("username") != "" ||  !_this.checkedCookie) {
                jr.setCookie("username", $form.model.username, 0);
              } else {
                jr.setCookie("username", $form.model.username, 10);
              }
              router.push("/");
            } else {
              _this.$message.error(response.data.msg);
            }
          }).catch(function(error){
            _this.$message.error("网络错误，访问失败！");
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      // 重置表单数据 必须在el-form-item中指定prop属性  该属性值为form对应:model对象的属性名
      this.$refs[formName].resetFields();
    },
    changePage() {
      this.isLoginPage = !this.isLoginPage;
      this.loginCode = '';
      if(this.isLoginPage) {
        this.$refs["loginForm"].resetFields();
        this.initCheckCookie();
      } else {
        this.$refs["registerForm"].resetFields();
        this.checkedCookie = false;
      }
    }, 
    time() {
      let _this = this;
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
      let _this = this;
      _this.disabled = true;
      _this.$refs["registerForm"].validateField("email",(valid) => {
        if(valid != "") {
          _this.disabled = false;
        } else {
          _this.time();
          _this.$axios.get(jr.server.url + "/login/validEmail?email=" + _this.registerForm.email).then(function(response) {
            if(response.data.sendMail) {
              _this.loginCode = response.data.emailCode;
              _this.endTime = response.data.endTime;
            } else {
              _this.$message.error("邮件发送失败，请确认邮箱是否正确！");
            }
          }).catch(function(error) {
            _this.$message.error("网络错误，访问失败！");
          });
        }
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


