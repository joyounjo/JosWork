<template>
  <div id="WorkManager">
     <div class="WorkManager_top">
        <el-col class="WorkManager_top" :span="24">
          <el-form class="WorkManager_top_form" :inline="true" :model="filters">
            <el-row>
              <el-form-item class="WorkManager_top_form_name" label="要員氏名:" prop="emp_nm">
                <el-input class="WorkManager_top_form_name_frame" v-model="filters.emp_nm"></el-input>
              </el-form-item>
              <el-form-item label="最寄り駅:" prop="from_home_station_nm">
                <el-input
                v-model="filters.from_home_station_nm" disabled>
              </el-input>
              </el-form-item>
              <el-form-item label="所属:" prop="client_staff_dept_nm">
                <el-select v-model="filters.jobTitleCd"  disabled>
                  <el-option
                    v-for="item in optionsDept"
                    :label="item.label"
                    :value="item.value"
                    :key="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="所属団体名:" prop="client_staff_cop_nm">
                <el-input></el-input>
              </el-form-item>
              <el-form-item label="スキル:" prop="skill">
                <el-input></el-input>
              </el-form-item>
              <el-form-item>
		            <el-checkbox v-model="checked1" label="アサイン可能フラグ"></el-checkbox>
		            <el-checkbox v-model="checked2" label="要注意フラグ"></el-checkbox>
  		        </el-form-item>
              <el-form-item>
                <el-button type="primary" round @click="searchData" icon="search">検索</el-button>
                <el-button type="warning" @click="resetData" round icon="refresh-left">クリア</el-button>
              </el-form-item>
            </el-row>
          </el-form>
        </el-col>
      </div>
      <div class="WorkManager_middle">
        <el-col class="WorkManager_middle" :span="24">
          <el-form class="WorkManager_middle_form">
            <el-button type="success" @click="insertWorker" icon="plus">新規登録</el-button>
            <el-button type="primary" @click="searchData" icon="edit" plain disabled>一括メール</el-button>
            <el-button type="danger" @click="resetData" icon="close" plain disabled>一括削除</el-button>
            <el-button-group style="float: right;" size="mini">
              <el-button icon="search"></el-button>
              <el-button icon="refresh"></el-button>
            </el-button-group>
            <el-row>
              <el-dialog title="要員追加" :visible.sync="dialoginsertWorker" center style="overflow:hidden">
                <div style="overflow-y: auto;height: 600px;">
                  <el-form :rules="rules1" :model="empInfo" ref="empInfo" class="empInfo-ruleForm">
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
                      <el-col><el-button size="mini" type="success" @click="">新規取引先追加</el-button></el-col>
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
                      <el-input :inline="true" type="text" v-model="empInfo.skill" style="width:150px;" auto-complete="off">
                      </el-input>
                      <el-dialog
                        width="30%"
                        title="スキル"
                        :visible.sync="innerVisible"
                        append-to-body>
                          <form class="skill">
                            <label>言語</label>
                            <div class="form-group withbox">
                              <el-checkbox-group v-model="skill_checked" style="display:block">
                                  <div>
                                    <label class="checkbox" style="vertical-align:middle;">
                                      <input type="checkbox" style="height: 20px;width: 20px;vertical-align:middle;" value="option1">
                                      <span style="vertical-align:middle;">JAVA</span>
                                    </label>
				                            <select name="queUserZairyushikaku" class="form-control">
					                            <option value="重要客">----</option>
					                            <option value="通常客">1～3ヶ月</option>
					                            <option value="低ポテンシャル">6～9ヶ月</option>
					                            <option value="取引停止">10～12ヶ月</option>
				                            </select>
                                  </div>
			                      
                                  <div>
                                    <label class="checkbox" style="vertical-align:middle;">
                                      <input type="checkbox" style="height: 20px;width: 20px;vertical-align:middle;" value="option1">
                                      <span style="vertical-align:middle;">C</span>
                                    </label>
                                    <select name="queUserZairyushikaku" class="form-control">
					                            <option value="重要客">----</option>
					                            <option value="通常客">1～3ヶ月</option>
					                            <option value="低ポテンシャル">6～9ヶ月</option>
					                            <option value="取引停止">10～12ヶ月</option>
				                            </select>
                                  </div>
                              </el-checkbox-group>
                              <label>DB</label>
                              <el-checkbox-group v-model="skill_checked" style="display:block">
                                <div>
                                  <label class="checkbox" style="vertical-align:middle;">
                                    <input type="checkbox" style="height: 20px;width: 20px;vertical-align:middle;" value="option1">
                                    <span style="vertical-align:middle;">ORACLE</span>
                                  </label>
				                          <select name="queUserZairyushikaku" class="form-control">
					                          <option value="重要客">----</option>
					                          <option value="通常客">1～3ヶ月</option>
					                          <option value="低ポテンシャル">6～9ヶ月</option>
					                          <option value="取引停止">10～12ヶ月</option>
				                          </select>
                                </div>
                              </el-checkbox-group>
                            </div>
                          </form>
                          <div slot="footer" class="dialog-footer">
                            <span id="save_button"><el-button type="success" @click="save" >確定</el-button></span>
                            <span id="accept_button"><el-button @click="closeinner" >閉じる</el-button></span>
                          </div>
                      </el-dialog>
                      <el-col><el-button size="mini" type="success" @click="innerVisible = true">選択</el-button></el-col>
                    </el-form-item>
                    <el-form-item label="職種">
                      <el-checkbox-group v-model="checkList">
		                    <el-checkbox label="SE"></el-checkbox>
		                    <el-checkbox label="PG"></el-checkbox>
                        <el-checkbox label="コンサルタント"></el-checkbox>
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
                      <el-checkbox v-model="checked2" label="要注意フラグ"></el-checkbox>
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
                      <el-col><el-button size="mini" type="success" @click="">住所検索</el-button></el-col>
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
                    <el-form-item label="アサイン可能フラグ:">
		                  <el-checkbox v-model="checked1_1" label="要員がアサイン可能であればチャックしてください。"></el-checkbox>
                    </el-form-item>
                    <el-form-item label="他社公開フラグ:">
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
                  </el-form>
                </div>
                <div slot="footer" class="dialog-footer">
                  
                    <span id="save_button"><el-button type="success" @click="save" >確定</el-button></span>
                    <span id="accept_button"><el-button @click="close" >閉じる</el-button></span>
                
              </div>
              </el-dialog>
          
          <el-table :data="pageinfo.pageData" id="WorkManager_middle" >
          
          
          <el-table-column  width="60px" label="#">
          <template slot-scope="scope">
          {{(scope.$index +  1)+ (pageinfo.pageNo - 1) *pageinfo.pageSize}}
          </template>
          </el-table-column>
          
          <el-table-column prop="status" label="状態" width="98px"/>
          <el-table-column prop="epm_name" label="要員名" width="100px"/>
          <el-table-column prop="role_nm" label="要員名()" width="100px"/>
          <el-table-column prop="client_staff_dept_nm" label="所属" width="170px"/>
          <el-table-column prop="skill" label="スキル" width="100px"/>
          <el-table-column prop="time" label="稼働" width="98px" />
          <el-table-column prop="u_price" label="単価" width="98px" />
          <el-table-column prop="sexsual" label="性別" width="98px" />
          <el-table-column prop="login_date" label="登録日" width="98px" />
          <el-table-column prop="leader" label="営業担当" width="98px" />
          <el-table-column prop="anken_search" label="案件検索" width="98px" />
          <el-table-column prop="resume" label="履歴書" width="98px" />
          <el-table-column label="操作" >
              <template slot-scope="scope">
              <el-button type="primary"
              @click="handleEdit(scope.$index, scope.row)" size="mini" icon="edit">修正</el-button>
              <el-button type="warning"
              @click="handleEdit(scope.$index, scope.row)" size="mini" icon="search">照会</el-button>
              <el-button type="danger"
              @click="handleEdit(scope.$index, scope.row)" size="mini" icon="close">削除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        </el-form>
        </el-col>
        
        </div>
      <el-pagination
          layout="prev, pager, next"
          :current-page="pageinfo.pageNo"
          :total="pageinfo.total"
          :page-size="pageinfo.pageSize"
           @current-change="handleCurrentChange">
       </el-pagination>
      <el-dialog title="ユーザー詳細情報‐修正" v-model="dialogVisible" width="3000">
          <el-form :inline="true" :rules="rules1" :model="userInfo" ref="userInfo" label-width="110px" class="userInfo-ruleForm">
        <el-row>
          <el-col :span="8">
              <el-form-item label="ユーザーID" prop="account" >
                  <el-input type="text" v-model="userInfo.account" style="width:100px;" auto-complete="off" :disabled="true" ></el-input>
              </el-form-item>
        </el-col>
        <el-col :span="16">
              <el-form-item label="ユーザー名" prop="name" >
                  <el-input type="text" v-model="userInfo.name" auto-complete="off" :disabled="false"></el-input>
              </el-form-item>
        </el-col>
        </el-row>
       <el-row>
          <el-col :span="8">
              <el-form-item label="状態" prop="status" >
                   <el-radio-group v-model="userInfo.status" >
                      <el-radio label="1">利用中</el-radio>
                      <el-radio label="2">终止中</el-radio>
                  </el-radio-group>
              </el-form-item>
        </el-col>
        <el-col :span="16">
              <el-form-item label="ロール" prop="roleid"  >
                  <el-select style="width:180px" v-model="userInfo.roleid" filterable placeholder="すべて"  :disabled="false">
                      <el-option
                          v-for="item in optionsrole"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                      </el-option>
                  </el-select>
              </el-form-item>
       </el-col>
        </el-row>
      <el-row>
          <el-col :span="8">
              <el-form-item label="部署" prop="deptid" >
                  <el-select style="width:150px" v-model="userInfo.deptid" filterable placeholder="すべて">
                      <el-option
                          v-for="item in optionsdept"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                      </el-option>
                  </el-select>
              </el-form-item>
        </el-col>
        <el-col :span="16">
              <el-form-item label="最寄り駅" prop="修正待ち" >
                  <el-select v-model="userInfo.jobTitleCd" filterable placeholder="すべて">
                      <el-option
                          v-for="item1 in optionsjob"
                          :label="item1.label"
                          :value="item1.value"
                          :key="item1.value">
                      </el-option>
                  </el-select>
              </el-form-item>
       </el-col>
        </el-row>
          </el-form>
          <h3>ユーザー承認経路</h3>
          <el-table :data="userInfo.userwfconfirmroute" style="width: 537px" id="AcceptRoute" >
              <el-table-column label="表示順" width="90">
                  <template slot-scope="scope">
                      <el-input type="number"
                        auto-complete="off" v-model="scope.row.display_order" :disabled="false" max=99 min=1 size="mini"></el-input>
                  </template>
              </el-table-column>
              <el-table-column  prop="accept_user_name" label="承認者" width="225"/>
              <el-table-column  prop="job_title_name" label="職位" width="120"/>
              <el-table-column label="操作" width="100">
                  <template slot-scope="scope">
                      <el-button type="danger"
                      @click="acceptDelete(scope.$index, scope.row)" size="mini" icon="delete">削除</el-button>
                  </template>
              </el-table-column>
          </el-table>
          <br/>
          <el-col :span="10">
          <span id="save_button"><el-button type="success" @click="save" >保存</el-button></span>
          <span id="accept_button"><el-button type="primary" @click="insertAcceptRoute" >追加承認者</el-button></span>
          </el-col>
          <br/>
      </el-dialog>
      <el-dialog title="ユーザー承認者" v-model="dialogAcceptModVisible" >
       <el-form :inline="true" :model="acceptUserMod" :rules="wfRules" ref="acceptUserMod" label-width="100px" class="acceptUserModForm">
          <el-form-item label="表示順" prop="display_order" >
            <el-input-number v-model="acceptUserMod.display_order" auto-complete="off" :disabled="false" ></el-input-number>
          </el-form-item>
          <el-form-item label="ユーザー" prop="accept_user_id" >
             <el-select v-model="acceptUserMod.accept_user_id" filterable placeholder="選択してください">
                      <el-option
                          v-for="item in optionsuser"
                          :label="item.label"
                          :value="item.value"
                          :key="item.value">
                      </el-option>
             </el-select>
          </el-form-item>
       </el-form>
       <span id="save_button1"><el-button type="success" @click="addAcceptUser">追加</el-button></span>
       
      </el-dialog>
    </div>
  </template>
  
  <script>
  export default {
    data(){
          var validateName= (rule, value, callback) => {
              if(value.length>15) {
                 callback(new Error('ユーザー名は15桁以内で入力してください'));
              }
              callback();
          };
        var validateDispOrder= (rule, value, callback) => {
              if (value ===0) {
                callback(new Error('表示順が1以上で入力してください'));
              }
  
              if (this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== undefined
              && this.userInfo.userwfconfirmroute.filter(o => o.display_order === value)[0] !== 'undefined') {
                   callback(new Error('表示順が承認一覧中に存在するので、再入力してください'));
              }
              callback();
          };
          var validateAcceptUserid= (rule, value, callback) => {
              if (this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== undefined
              && this.userInfo.userwfconfirmroute.filter(o => o.accept_user_id === value)[0] !== 'undefined') {
                   callback(new Error('承認者が承認一覧中に存在するので、再入力してください'));
              }
              callback();
          };
        return{
          skill_checked:[],
           filters: {
                      name: '',
                      jobTitleCd: ''
                  },
                  checked1: false,
                  checked2: false,
                  checkList: [],
          userInfo: {
            account:'',
            name: '',
            status: '',
            roleid:'',
            deptid:'',
            jobTitleCd:'',
            userwfconfirmroute:[]
          },
          empInfo: {
            emp_nm:'',
            emp_nmkn: '',
            emp_code: '',
            qualification:'',
            skill:'',
            jobTitleCd:'',
            userwfconfirmroute:[]
          },
          pageinfo:{
              pageNo:1,
              pageSize:10,
              total:20,
              pageData:[]
          },
          dialoginsertWorker:false,
          dialogVisible:false,
          dialogAcceptModVisible:false,
          innerVisible: false,
          acceptUserMod: {
            display_order:'',
            accept_user_id: '',
          },
      optionsdept:[],
      optionsrole:[],
      optionsuser:[],
      optionsjob:[],
      rules1: {
            name: [
              { required: true, message: 'ユーザー名が入力必須です。', trigger: 'blur' },
                    { validator: validateName, trigger: 'blur' }
            ],
            roleid: [
              { required: true, message: 'ロールが入力必須です。', trigger: 'blur' }
            ],
            deptid: [
              { required: true, message: '部署が入力必須です。', trigger: 'blur' }
            ],
            jobTitleCd: [
              { required: true, message: '職位が入力必須です。', trigger: 'blur' }
            ],
             status: [
              { required: true, message: '状態が入力必須です。', trigger: 'blur' }
            ]
          },
      wfRules:{
              display_order: [
              { validator: validateDispOrder, trigger: 'blur' }
              ],
            accept_user_id: [
              { required: true, message: '承認者が入力必須です。', trigger: 'change' },
              { validator: validateAcceptUserid, trigger: 'blur' }
            ]
          }
        }
    },
    methods:{
      handleCheckedSkillsChange(val){
        this.skill_checked = val ? skillOptions : [];
      },
      close(){
        this.dialoginsertWorker=false;
      },
      closeinner(){
        this.innerVisible=false;
      },
	   //要員追加
      insertWorker(){
			this.dialoginsertWorker=true;
			this.getUserList('');
      },
          //取得職位
        getDept(){
            var _this = this;
              this.$http({
                   method:'get',
                   url:'/api/dept/dcitList'
               })
               .then(function (response) {
                 var errorcode=response.data.errorcode;
                    if(errorcode=="200"){
                       _this.optionsdept =response.data.data;
                   }else if(errorcode=="500"){
                       // console.debug(response);
                       _this.errormsg(response.data.errormsg);
                   }
                 })
               .catch(function (error) {
                   console.log(error);
               });
        },
         //ロール
         getRole(){
            var _this = this;
              this.$http({
                   method:'get',
                   url:'/api/role/roleList'
               })
               .then(function (response) {
                 var errorcode=response.data.errorcode;
                    if(errorcode=="200"){
                       _this.optionsrole =response.data.data;
                   }else if(errorcode=="500"){
                       // console.debug(response);
                       _this.errormsg(response.data.errormsg);
                   }
                 })
               .catch(function (error) {
                   console.log(error);
               });
        },
        getJob(){
            var _this = this;
              this.$http({
                   method:'get',
                   url:'/api/common/joblist'
               })
               .then(function (response) {
                 var errorcode=response.data.errorcode;
                    if(errorcode=="200"){
                       _this.optionsjob =response.data.data;
                   }else if(errorcode=="500"){
                       // console.debug(response);
                       _this.errormsg(response.data.errormsg);
                   }
                 })
               .catch(function (error) {
                   console.log(error);
               });
        },
          getUserList(account){
  
            var _this = this;
              this.$http({
                   method:'get',
                   url:'/api/user/userList/'+account
               })
               .then(function (response) {
                 var errorcode=response.data.errorcode;
                    if(errorcode=="200"){
                       _this.optionsuser =response.data.data;
                   }else if(errorcode=="500"){
                       // console.debug(response);
                       _this.errormsg(response.data.errormsg);
                   }
                 })
               .catch(function (error) {
                   console.log(error);
               });
        },
  
        //查询（获取用户列表）
        getUsers(){
           var _this = this;
              this.$http.get('/api/user/selectall'
              ,{
                params:{
                  username:_this.filters.name,
                  jobTitleCd:_this.filters.jobTitleCd,
                  page:_this.pageinfo.pageNo
                },
              }
              )
              .then(function (response) {
                var errorcode=response.data.errorcode;
                   if(errorcode=="200"){
                      _this.pageinfo=response.data.data;
  
                  }else if(errorcode=="500"){
                     _this.$message.error(response.data.errormsg);
                  }
                })
              .catch(function (error) {
                  console.log(error);
              });
  
        },
        //点击页数
        handleCurrentChange(val) {
          this.pageinfo.pageNo = val;
          this.getUsers();
        },
         searchData() {
          this.pageinfo.pageNo = 1;
          this.getUsers();
        },
        //新規承認者
        insertAcceptRoute(){
          this.dialogAcceptModVisible=true;
          this.getUserList(this.userInfo.account);
        },
              //新規承認者
        addAcceptUser(){
          this.$refs.acceptUserMod.validate((valid) => {
          if (valid){
              var selObj = this.optionsuser.filter(o => o.value === this.acceptUserMod.accept_user_id)[0];
              var addData =  { display_order: this.acceptUserMod.display_order ,accept_user_id:this.acceptUserMod.accept_user_id,accept_user_name:selObj.label,"job_title_name":selObj.fullname};
              this.userInfo.userwfconfirmroute.push(addData);
              this.dialogAcceptModVisible=false;
              }
          })
        },
        //删除
        acceptDelete(index, row) {
            var _this = this;
            _this.$confirm('このユーザーが削除しますか？', '提示', {
                confirmButtonText: 'はい',
                cancelButtonText: 'いいえ',
                type: 'warning'
              }).then(() => {
               _this.userInfo.userwfconfirmroute.splice(index,1);
                  }
              );
        },
        //编辑
        handleEdit(index, row) {
          var _this = this;
              this.$http.get('/api/user/select'
              ,{
                params:{
                  account:row.account
                },
              }
              )
              .then(function (response) {
                var errorcode=response.data.errorcode;
                   if(errorcode=="200"){
                    _this.userInfo=response.data.data;
  
                  }else if(errorcode=="500"){
                     _this.$message.error(response.data.errormsg);
                  }
                })
              .catch(function (error) {
                  console.log(error);
              });
            this.dialogVisible=true;
        },
        //保存
       save(){
              var _this = this;
               this.$refs.userInfo.validate((valid) => {
              if (valid){
              _this.$confirm('更新するはよろしいですか？', '提示', {
              confirmButtonText: 'はい',
              cancelButtonText: 'いいえ',
              type: 'success'
              }).then(() => {
                  this.$http.put('/api/user/edit',_this.userInfo)
                  .then(function (response) {
                    var errorcode=response.data.errorcode;
                      if(errorcode=="200"){
                          _this.$alert('更新することに成功します', '提示', {
                                        confirmButtonText: '確認',
                                        callback: action => {
                                         _this.dialogVisible=false;
                                        _this.getUsers();
                                        }
                                    });
                      }else if(errorcode=="500"){
                        _this.$message.error(response.data.errormsg);
                           }
                        })
                      .catch(function (error) {
                          console.log(error);
                      });
                }).catch(() => {
                      _this.$notify({
                          title: '提示',
                          type: 'error',
                          message: 'データが格納しません！',
                          offset: 100
                       });
                 });
              }
          })
       }
    },
    mounted(){
      this.getUsers();
      this.getDept();
      this.getRole();
      this.getJob();
    }
  }
  </script>
  
  <style>
#WorkManager{
  width: 95%;
  margin-top: 25px;
  margin-left:30px;
}

.WorkManager_top{
  height: 120px;
  border:50px;
  background-color: #F2F2F2;
}
.WorkManager_top_form{
  margin-top: 12px;

  margin-left: 10px;
}
#WorkManager_middle{
  margin-top: 5px;
}

.WorkManager_middle{
  margin-top: 5px;
  background-color: #F2F2F2;
}

.WorkManager_middle_form{
  margin-top: 12px;

  margin-left: 10px;
}
.warning::before {
  content: '(必須)';
  font-size: 8px;
  color: #f00;
  vertical-align: middle;
}
  </style>
  