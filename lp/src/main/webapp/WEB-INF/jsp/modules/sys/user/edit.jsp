<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/static.jsp" %>
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
	<link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css" rel="stylesheet">
	<!-- PNotify -->
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="<c:out value="${sessionScope.basePath }"></c:out>/plugins/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    <div class="x_panel jrwl_custom_panel">
     <div class="col-md-12 col-sm-12 col-xs-12">
       <div class="x_panel">
         <div class="x_title">
           <h2>编辑用户信息</h2>
           <ul class="nav navbar-right panel_toolbox">
             <!-- <li><a class="save-link" title="保存"><i class="fa fa-floppy-o text-success"></i></a></li>
             <li><a class="reset-link" title="重置"><i class="fa fa-undo text-info"></i></a></li> -->
             <li class="right"><a class="return-link" title="返回"><i class="fa fa-reply text-black"></i></a></li>
           </ul>
           <div class="clearfix"></div>
         </div>
         <div class="x_content jrwl_custom_content" >
         	<div class="" role="tabpanel" data-example-id="togglable-tabs">
              <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">基本信息</a>
                </li>
                <!-- <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab" aria-expanded="false">头像照片</a>
                </li> -->
                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab" aria-expanded="false">更多个人信息</a>
                </li>
              </ul>
              <div id="myTabContent" class="tab-content">
	             <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
	                <form class="form-horizontal form-label-left bv-form" id="basicInfoForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/sys/user/save">
	              		<input type="hidden" name="id" value="<c:out value="${model.id }"></c:out>">
	              		<input type="hidden" name="username" value="<c:out value="${model.username }"></c:out>">
	                   	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="username">用户名 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <label class="control-label"><c:out value="${model.username }"></c:out></label>
		                 </div>
		               	</div>
		               	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >性别 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <div class="radio">
		                     <label>
		                       <input type="radio" <c:if test="${model.sex==0 }">checked=""</c:if> value="0" name="sex"> 男
		                     </label>
		                     <label>
		                       <input type="radio" <c:if test="${model.sex==1 }">checked=""</c:if> value="1" name="sex"> 女
		                     </label>
		                     <label>
		                       <input type="radio" <c:if test="${model.sex==2 }">checked=""</c:if> value="2" name="sex"> 保密
		                     </label>
		                   </div>
		                 </div>
		               	</div>
		               	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >生日 &nbsp;
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <fieldset>
		                     <div class="control-group">
		                       <div class="controls">
		                         <div class="input-prepend input-group" >
		                           <label for="birthday" class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></label>
		                           <input type="text" autocomplete="off" class="form-control col-md-7 col-xs-12 has-feedback-left" id="birthday" name="birthday" placeholder="选择生日" aria-describedby="inputSuccess2Status4" value='<c:out value="${model.birthday }"></c:out>'>
		                         </div>
		                       </div>
		                     </div>
		                   </fieldset>
		                 </div>
		               	</div>
		               	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">新密码 &nbsp;
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <input type="password" class="form-control col-md-7 col-xs-12" autocomplete="off" name="password" id="password">
		                 </div>
		               	</div>
		               	<%-- <div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="mobile">手机号 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <input type="hidden" class="form-control col-md-7 col-xs-12" name="mobile" id="mobile" value="<c:out value="${model.mobile }"></c:out>">
		                   <label class="control-label"><c:out value="${model.mobile }"></c:out></label>
		                 </div>
		               	</div>
		               	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">邮箱 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <input type="hidden" class="form-control col-md-7 col-xs-12" name="email" id="email" value="<c:out value="${model.email }"></c:out>">
		                   <label class="control-label"><c:out value="${model.email }"></c:out></label>
		                 </div>
		               	</div>
		               	<div class="item form-group">
		                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="truename">真实姓名 <i class="required text-danger">*</i>
		                 </label>
		                 <div class="col-md-6 col-sm-6 col-xs-12">
		                   <input type="text" class="form-control col-md-7 col-xs-12" autocomplete="off" name="truename" id="truename" value="<c:out value="${model.truename }"></c:out>">
		                 </div>
		               	</div> --%>
		               	<div class="ln_solid"></div>
		               	<div class="form-group">
		                 <div class="col-md-6 col-md-offset-3">
		                   <button id="biReset" type="reset" class="btn btn-primary">重置</button>
		                   <button id="biSend" name="send" type="submit" class="btn btn-success">保存</button>
		                   <button id="biSendBack" name="sendBack" type="submit" class="btn btn-success">保存并返回</button>
		                 </div>
		               	</div>
		               </form>
	                </div>
	                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab2">
	                	<form class="form-horizontal form-label-left bv-form" id="moreInfoForm" method="post" action="<c:out value="${sessionScope.basePath }"></c:out>/sys/user/save">
		                	<div class="item form-group">
			                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >婚姻状况 &nbsp;
			                 </label>
			                 <div class="col-md-6 col-sm-6 col-xs-12">
			                   <div class="radio">
			                     <label>
			                       <input type="radio" <c:if test="${model.marry==0 }">checked=""</c:if> value="0" name="marry"> 未婚
			                     </label>
			                     <label>
			                       <input type="radio" <c:if test="${model.marry==1 }">checked=""</c:if> value="1" name="marry"> 已婚
			                     </label>
			                     <label>
			                       <input type="radio" <c:if test="${model.marry==2 }">checked=""</c:if> value="2" name="marry"> 保密
			                     </label>
			                   </div>
			                 </div>
			               	</div>
			               	<div class="item form-group">
			                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >月收入 &nbsp;
			                 </label>
			                 <div class="col-md-6 col-sm-6 col-xs-12">
			                   <select class="form-control col-md-7 col-xs-12" name="monthincome" >
			                     <option value="0" <c:if test="${model.monthincome==null or model.monthincome==0 }">selected=""</c:if> >请选择</option>
			                     <option value="1" <c:if test="${model.monthincome==1 }">selected=""</c:if>>2000元以下</option>
			                     <option value="2" <c:if test="${model.monthincome==2 }">selected=""</c:if>>2000-3999元</option>
			                     <option value="3" <c:if test="${model.monthincome==3 }">selected=""</c:if>>4000-5999元</option>
			                     <option value="4" <c:if test="${model.monthincome==4 }">selected=""</c:if>>6000-7999元</option>
			                     <option value="5" <c:if test="${model.monthincome==5 }">selected=""</c:if>>8000元以上</option>
			                   </select>
			                 </div>
			               	</div>
			               	<div class="item form-group">
			                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >身份证号码 &nbsp;
			                 </label>
			                 <div class="col-md-6 col-sm-6 col-xs-12">
			                   <div class="radio">
			                   	 <input type="text" class="form-control col-md-7 col-xs-12" autocomplete="off" name="personcode" value="<c:out value="${model.personcode }"></c:out>">
			                   </div>
			                 </div>
			               	</div>
			               	<div class="item form-group">
			                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >教育程度 &nbsp;
			                 </label>
			                 <div class="col-md-6 col-sm-6 col-xs-12">
			                   <select class="form-control col-md-7 col-xs-12" name="education" >
			                     <option value="0" <c:if test="${model.education==null or model.education==0 }">selected=""</c:if>>请选择</option>
			                     <option value="1" <c:if test="${model.education==1 }">selected=""</c:if>>初中</option>
			                     <option value="2" <c:if test="${model.education==2 }">selected=""</c:if>>高中</option>
			                     <option value="3" <c:if test="${model.education==3 }">selected=""</c:if>>中专</option>
			                     <option value="4" <c:if test="${model.education==4 }">selected=""</c:if>>大专</option>
			                     <option value="5" <c:if test="${model.education==5 }">selected=""</c:if>>本科</option>
			                     <option value="6" <c:if test="${model.education==6 }">selected=""</c:if>>硕士</option>
			                     <option value="7" <c:if test="${model.education==7 }">selected=""</c:if>>博士</option>
			                     <option value="8" <c:if test="${model.education==8 }">selected=""</c:if>>其他</option>
			                   </select>
			                 </div>
			               	</div>
			               	<div class="item form-group">
			                 <label class="control-label col-md-3 col-sm-3 col-xs-12" >所在行业 &nbsp;
			                 </label>
			                 <div class="col-md-6 col-sm-6 col-xs-12">
			                   <select class="form-control col-md-7 col-xs-12" name="business" >
			                     <option value="0" <c:if test="${model.business==null or model.business==0 }">selected=""</c:if>>请选择</option>
			                     <option value="1" <c:if test="${model.business==1 }">selected=""</c:if>>计算机硬件及网络设备</option>
			                     <option value="2" <c:if test="${model.business==2 }">selected=""</c:if>>计算机软件</option>
			                     <option value="3" <c:if test="${model.business==3 }">selected=""</c:if>>IT服务（系统/数据/维护）/多领域经营</option>
			                     <option value="4" <c:if test="${model.business==4 }">selected=""</c:if>>互联网/电子商务</option>
			                     <option value="5" <c:if test="${model.business==5 }">selected=""</c:if>>网络游戏</option>
			                     <option value="6" <c:if test="${model.business==6 }">selected=""</c:if>>通讯（设备/运营/增值服务）</option>
			                     <option value="7" <c:if test="${model.business==7 }">selected=""</c:if>>电子技术/半导体/集成电路</option>
			                     <option value="8" <c:if test="${model.business==8 }">selected=""</c:if>>仪器仪表及工业自动化</option>
			                     <option value="9" <c:if test="${model.business==9 }">selected=""</c:if>>金融/银行/投资/基金/证券</option>
			                     <option value="10" <c:if test="${model.business==10 }">selected=""</c:if>>保险</option>
			                     <option value="11" <c:if test="${model.business==17 }">selected=""</c:if>>房地产/建筑/建材/工程</option>
			                     <option value="12" <c:if test="${model.business==15 }">selected=""</c:if>>家居/室内设计/装饰装潢</option>
			                     <option value="13" <c:if test="${model.business==13 }">selected=""</c:if>>物业管理/商业中心</option>
			                     <option value="14" <c:if test="${model.business==14 }">selected=""</c:if>>广告/会展/公关/市场推广</option>
			                     <option value="15" <c:if test="${model.business==15 }">selected=""</c:if>>媒体/出版/影视/文化/艺术</option>
			                     <option value="17" <c:if test="${model.business==17 }">selected=""</c:if>>咨询/管理产业/法律/财会</option>
			                     <option value="16" <c:if test="${model.business==16 }">selected=""</c:if>>印刷/包装/造纸</option>
			                     <option value="19" <c:if test="${model.business==19 }">selected=""</c:if>>检验/检测/认证</option>
			                     <option value="18" <c:if test="${model.business==18 }">selected=""</c:if>>教育/培训</option>
			                     <option value="21" <c:if test="${model.business==21 }">selected=""</c:if>>贸易/进出口</option>
			                     <option value="20" <c:if test="${model.business==20 }">selected=""</c:if>>中介服务</option>
			                     <option value="23" <c:if test="${model.business==23 }">selected=""</c:if>>快速消费品（食品/饮料/烟酒/化妆品</option>
			                     <option value="22" <c:if test="${model.business==22 }">selected=""</c:if>>零售/批发</option>
			                     <option value="25" <c:if test="${model.business==25 }">selected=""</c:if>>办公用品及设备</option>
			                     <option value="24" <c:if test="${model.business==24 }">selected=""</c:if>>耐用消费品（服装服饰/纺织/皮革/家具/家电）</option>
			                     <option value="27" <c:if test="${model.business==27 }">selected=""</c:if>>大型设备/机电设备/重工业</option>
			                     <option value="26" <c:if test="${model.business==26 }">selected=""</c:if>>礼品/玩具/工艺美术/收藏品</option>
			                     <option value="29" <c:if test="${model.business==29 }">selected=""</c:if>>汽车/摩托车（制造/维护/配件/销售/服务）</option>
			                     <option value="28" <c:if test="${model.business==28 }">selected=""</c:if>>加工制造（原料加工/模具）</option>
			                     <option value="31" <c:if test="${model.business==31 }">selected=""</c:if>>医药/生物工程</option>
			                     <option value="30" <c:if test="${model.business==30 }">selected=""</c:if>>交通/运输/物流</option>
			                     <option value="34" <c:if test="${model.business==34 }">selected=""</c:if>>酒店/餐饮</option>
			                     <option value="35" <c:if test="${model.business==35 }">selected=""</c:if>>娱乐/体育/休闲</option>
			                     <option value="32" <c:if test="${model.business==32 }">selected=""</c:if>>医疗/护理/美容/保健</option>
			                     <option value="33" <c:if test="${model.business==33 }">selected=""</c:if>>医疗设备/器械</option>
			                     <option value="38" <c:if test="${model.business==38 }">selected=""</c:if>>能源/矿产/采掘/冶炼</option>
			                     <option value="39" <c:if test="${model.business==39 }">selected=""</c:if>>电气/电力/水利</option>
			                     <option value="36" <c:if test="${model.business==36 }">selected=""</c:if>>旅游/度假</option>
			                     <option value="37" <c:if test="${model.business==37 }">selected=""</c:if>>石油/石化/化工</option>
			                     <option value="42" <c:if test="${model.business==42 }">selected=""</c:if>>政府/公共事业/非盈利机构</option>
			                     <option value="43" <c:if test="${model.business==43 }">selected=""</c:if>>环保</option>
			                     <option value="40" <c:if test="${model.business==40 }">selected=""</c:if>>航空/航天</option>
			                     <option value="41" <c:if test="${model.business==41 }">selected=""</c:if>>学术/科研</option>
			                     <option value="44" <c:if test="${model.business==44 }">selected=""</c:if>>农/林/牧/渔</option>
			                     <option value="45" <c:if test="${model.business==45 }">selected=""</c:if>>跨领域经营</option>
			                     <option value="46" <c:if test="${model.business==46 }">selected=""</c:if>>其它</option>
			                   </select>
			                 </div>
			               	</div>
			               	<div class="ln_solid"></div>
		               		<div class="form-group">
			                 <div class="col-md-6 col-md-offset-3">
			                   <button id="mReset" type="reset" class="btn btn-primary">重置</button>
			                   <button id="mSend" name="send" type="submit" class="btn btn-success">保存</button>
			                   <button id="mSendBack" name="sendBack" type="submit" class="btn btn-success">保存并返回</button>
			                 </div>
			               	</div>
			               	<input type="hidden" name="id" value="<c:out value="${model.id }"></c:out>">
	              			<input type="hidden" name="username" value="<c:out value="${model.username }"></c:out>">
		               </form>
	                </div>
              </div>
            </div>
         </div>
       </div>
     </div>
   </div>
   
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/jquery.form/jquery.form.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/bootstrapValidator.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-validator/dist/js/language/zh_CN.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/bootstrap-datepicker/dist/locales/bootstrap-datepicker.zh-CN.min.js"></script>
   <!-- Dropzone.js -->
    <script src="<c:out value="${sessionScope.basePath }"></c:out>/plugins/dropzone/dist/min/dropzone.min.js"></script>
   <script type="text/javascript" src="<c:out value="${sessionScope.basePath }"></c:out>/modules/sys/user/save.js"></script>