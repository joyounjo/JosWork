<template>
    <div>
        <el-dialog title="要員追加" :visible.sync="dialoginsertWorker" center style="overflow:hidden">  
            <div class="WorkerEnt" style="overflow-y: auto;height: 600px;">
                  <el-form :model="empInfo" ref="empInfo" class="empInfo-ruleForm" >
                    <el-form-item>
                      <label class="warning" prop="emp_nm" >
                        要員名:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.emp_nm" style="width:100px;" auto-complete="off" placeholder="例)田中 太郎"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="emp_nmkn" >
                        要員名(カナ):
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.emp_nmkn" style="width:100px;" auto-complete="off" placeholder="例)せい めい"></el-input>
                    </el-form-item>
                    <el-form-item> 
                      <label class="warning" prop="emp_code" >
                        要員名(短縮表示名):
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.emp_code" style="width:100px;" auto-complete="off" placeholder="例)ST"></el-input>
                    </el-form-item>
                    <el-form-item label="所属" prop="client_staff_dept_nm">
                      <el-select v-model="filters.jobTitleCd">
                        <el-option
                        　style="width:100px;"
                          v-for="item in optionsDept"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="client_staff_cop_nm" >
                        所属先:
                      </label>
                      <el-input :inline="true" type="text" v-model="client_staff_cop_nm" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="役割" prop="client_staff_dept_nm">
                      <el-select v-model="filters.jobTitleCd"  disabled>
                        <el-option
                        　style="width:100px;"
                          v-for="item in optionsDept"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="qualification">
                        個人資格:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.qualification" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="skill" >
                        スキル:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.skill" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="職種" prop="typeofwork">
                      <el-checkbox-group v-model="checkList">
		                    <el-checkbox v-model="typeofwork1" label="SE"></el-checkbox>
		                    <el-checkbox v-model="typeofwork2" label="PG"></el-checkbox>
                        <el-checkbox v-model="typeofwork3" label="コンサルタント"></el-checkbox>
  		                </el-checkbox-group>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="birthday">
                        誕生日:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.qualification" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="年齢:" prop="years">
                      <el-input :inline="true" type="text" v-model="empInfo.years" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="国籍" prop="country">
                      <el-select v-model="filters.jobTitleCd"  disabled>
                        <el-option
                        　style="width:100px;"
                          v-for="item in optionsDept"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="単価:" prop="u_price">
                      <el-input :inline="true" type="text" v-model="empInfo.u_price" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="最寄り駅:" prop="from_home_station_nm">
                      <el-input :inline="true" type="text" v-model="empInfo.from_home_station_nm" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="start_date">
                        傢働開始日:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.start_date" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="担当者:" prop="leader">
                      <el-select v-model="filters.jobTitleCd"  disabled>
                        <el-option
                        　style="width:100px;"
                          v-for="item in optionsDept"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                        </el-option>
                      </el-select>
                      <el-checkbox v-model="checked2" label="要注意フラグ" prop="warning_tag"></el-checkbox>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="landline_number">
                        電話番号:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.landline_number" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="phone_number">
                        携帯番号:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.phone_number" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="landline_eml">
                        E-mail(自宅):
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.landline_eml" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="cop_eml">
                        E-mail(会社):
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.cop_eml" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="phone_eml">
                        携帯メール:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.phone_eml" style="width:150px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="メールアドレス:" prop="mail_adress">
                      <el-input :inline="true" type="text" v-model="empInfo.mail_adress" style="width:150px;" auto-complete="off" placeholder="例)http://"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="postcode">
                        郵便番号:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.postcode" style="width:150px;" auto-complete="off" placeholder="例)000-0000"></el-input>
                    </el-form-item>
                    <el-form-item>
                      <label class="warning" prop="adress">
                        住所:
                      </label>
                      <el-input :inline="true" type="text" v-model="empInfo.adress" style="width: 500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="ビル名:" prop="biud_nm">
                      <el-input :inline="true" type="text" v-model="empInfo.biud_nm" style="width:500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="アサイン可能フラグ:" prop="satus1">
		                  <el-checkbox v-model="checked1_1" label="要員がアサイン可能であればチャックしてください。"></el-checkbox>
                    </el-form-item>
                    <el-form-item label="他社公開フラグ:" prop="satus2">
		                  <el-checkbox v-model="checked1_1" label="チャック状態の場合、本CRM利用する他社のユーザへされます。"></el-checkbox>
                    </el-form-item>
                    <el-form-item label="履歴書:" prop="resume">
                      <el-input :inline="true" type="text" v-model="empInfo.resume" style="width:500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="紹介文:" prop="references">
                      <el-input :inline="true" type="text" v-model="empInfo.references" style="width:500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="人物印書:" prop="sign">
                      <el-input :inline="true" type="text" v-model="empInfo.sign" style="width:500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="備考:" prop="note">
                      <el-input :inline="true" type="text" v-model="empInfo.note" style="width:500px;" auto-complete="off"></el-input>
                    </el-form-item>
                    <!--<span id="submit_button"><el-button size="mini" @click="submitForm" >確定</el-button></span>
                    <span id="close_button"><el-button size="mini" @click="closeDialog" >閉じる</el-button></span>-->
                  </el-form>
                  
                </div>
            </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data(){
        return{
            dialoginsertWorker:false,
            empInfo:{
                emp_nm:'',
                emp_nmkn: '',
                emp_code: '',
                client_staff_dept_nm:'',
                client_staff_cop_nm:'',
                qualification:'',
                skill:'',
                typeofwork:'',
                birthday:'',
                years:'',
                country:'',
                u_price:'',
                from_home_station_nm:'',
                start_date:'',
                leader:'',
                warning_tag:'',
                landline_number:'',
                phone_number:'',
                landline_eml:'',
                cop_eml:'',
                phone_eml:'',
                mail_adress:'',
                postcode:'',
                adress:'',
                biud_nm:'',
                satus1:'',
                satus2:'',
                resume:'',
                references:'',
                sign:'',
                note:'',
            },
        };
    },
    methods:{
        showWE(){
            this.dialoginsertWorker =true;
        },
        /*submitForm() {
      this.$emit('close'); // 关闭对话框
    },
    closeDialog(){
          this.$emit('close');// 关闭对话框
        },*/
    },
  }
  </script>
  
  <style>
    .warning::before {
    content: '(必須)';
    font-size: 8px;
    color: #f00;
    vertical-align: middle;
  }
  </style>
  