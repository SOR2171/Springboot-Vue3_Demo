<script setup>
import {reactive} from "vue";
import {Bell, Lock, Message, User} from "@element-plus/icons-vue";
import router from "../../router/index.js";

const form = reactive({
  username: '',
  password: '',
  passwordRepeat: '',
  email: '',
  code: ''
})

const rule = {
  username: [
    { required: true, message: 'Please input username', trigger: 'blur' },
    { min: 3, max: 20, message: 'Length should be 3 to 20', trigger: 'blur' }
  ],
  password: [
    { required: true, message: 'Please input password', trigger: 'blur' },
    { min: 6, max: 20, message: 'Length should be 6 to 20', trigger: 'blur' }
  ],
  passwordRepeat: [
    { required: true, message: 'Please repeat password', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== form.password) {
          callback(new Error('Passwords do not match'));
        } else {
          callback();
        }
      }, trigger: 'blur'
    }
  ],
  email: [
    { required: true, message: 'Please input email', trigger: 'blur' },
    { type: 'email', message: 'Please input a valid email', trigger: 'blur' }
  ],
  code: [
    { required: true, message: 'Please input verification code', trigger: 'blur' }
  ]
};

</script>

<template>
  <div>
    <div style="text-align: center;margin: 32px">
      <div style="margin-top: 169px; margin-bottom: 50px">
        <div style="font-size: 28px;font-weight: bold">Register</div>
        <div style="font-size: 14px;color: slategray">welcome to join us!</div>
      </div>
      <div style="margin: 32px;">
        <el-form :model="form" :rules="rule">
          <el-form-item>
            <el-input v-model="form.username" maxlength="16" type="text" placeholder="Username">
              <template #prefix>
                <el-icon>
                  <User/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-input v-model="form.password" maxlength="20" type="password" placeholder="Password">
              <template #prefix>
                <el-icon>
                  <Lock/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-input v-model="form.passwordRepeat" maxlength="20" type="password" placeholder="Repeat password">
              <template #prefix>
                <el-icon>
                  <Lock/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-input v-model="form.email" type="text" placeholder="Email">
              <template #prefix>
                <el-icon>
                  <Message/>
                </el-icon>
              </template>
            </el-input>
          </el-form-item>

          <el-form-item>
            <el-row justify="space-between">
              <el-col :span="12">
                <el-input v-model="form.code" maxlength="6" type="text" placeholder="Verify code">
                  <template #prefix>
                    <el-icon>
                      <Bell/>
                    </el-icon>
                  </template>
                </el-input>
              </el-col>
              <el-col :span="8">
                <el-button type="success" plain>Get Code</el-button>
              </el-col>
            </el-row>
          </el-form-item>
        </el-form>
      </div>
      <div style="margin-top: 24px">
        <el-button style="width: 280px" type="primary">Register</el-button>
      </div>
      <el-divider>
        <span style="font-size: 10px;color: slategray">already have a account?</span>
      </el-divider>
      <div style="margin-top: 16px">
        <el-button @click="router.push('/')" style="width: 280px" type="default">
          Sign in
        </el-button>
      </div>
    </div>

  </div>
</template>

<style scoped>

</style>