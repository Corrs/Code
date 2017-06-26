<template>
  <div class="pos">
      <el-row>
          <el-col :md='7' :xs="24" class="pos-order" id="order-list">
              <el-tabs>
                  <el-tab-pane label="点餐">
                      <el-table :data="tableData" border style="width: 100%; height:100%">
                        <el-table-column
                            prop="goodsName"
                            label="商品名称"
                            >
                        </el-table-column>
                        <el-table-column
                            prop="num"
                            label="数量"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="money"
                            label="金额"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            label="操作"
                            width="100"
                            fixed="right">
                            <template scope="scope">
                                <el-button
                                @click.native.prevent="deleteRow(scope)"
                                type="text"
                                size="small">
                                删除
                                </el-button>
                                <el-button
                                @click.native.prevent="addOrderList(scope.row)"
                                type="text"
                                size="small">
                                增加
                                </el-button>
                            </template>
                        </el-table-column>
                      </el-table>
                      <div class="total">
                          <label>数量：</label><span v-text="totalNum"></span>
                          <label>金额：</label><span>{{totalMoney | formatMoney}}</span>
                      </div>
                      <div class="btn">
                          <el-button type="info" size="small">挂单</el-button>
                          <el-button type="danger" size="small" @click="delAll">删除</el-button>
                          <el-button type="success" size="small" @click="checkout">结账</el-button>
                      </div>
                  </el-tab-pane>
                  <el-tab-pane label="挂单">
                      挂单
                  </el-tab-pane>
                  <el-tab-pane label="外卖">
                      外卖
                  </el-tab-pane>
              </el-tabs>
          </el-col>
          <el-col :md="17" :xs="24">
              <div class="often-goods">
                  <div class="title">常用商品</div>
                  <div class="often-goods-list">
                      <ul>
                          <el-row>
                              <el-col v-for="goods in oftenGoods" :key="goods.id" :xs="24" :sm="12" :md="6" :lg="4" >
                                <li @click="addOrderList(goods)">
                                    <span v-text="goods.goodsName"></span>
                                    <span class="price" >{{goods.price | formatMoney}}</span>
                                </li>
                              </el-col>
                          </el-row>
                      </ul>
                  </div>
              </div>

              <div class="goods-type">
                <el-tabs active-name="first">
                    <el-tab-pane label="汉堡" name="first">
                        <div>
                            <ul class="cookList">
                                <el-row>
                                    <el-col v-for="goods in type0Goods" :key="goods.goodId" :xs="12" :sm="8" :md="6" :lg="4">
                                        <li @click="addOrderList(goods)">
                                            <span ><img :src="goods.goodsImg" class="foodImg"></span>
                                            <span class="foodName" v-text="goods.goodsName"></span>
                                            <span class="foodPrice" >{{goods.price | formatMoney}}</span>
                                        </li>
                                    </el-col>
                                </el-row>
                            </ul>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="小食" name="second">
                        <div>
                            <ul class="cookList">
                                <el-row>
                                    <el-col v-for="goods in type1Goods" :key="goods.goodId" :xs="12" :sm="8" :md="6" :lg="4">
                                        <li @click="addOrderList(goods)">
                                            <span ><img :src="goods.goodsImg" class="foodImg"></span>
                                            <span class="foodName" v-text="goods.goodsName"></span>
                                            <span class="foodPrice" >{{goods.price | formatMoney}}</span>
                                        </li>
                                    </el-col>
                                </el-row>
                            </ul>
                        </div>
                    </el-tab-pane>
                    <el-tab-pane label="饮料" name="third"><div>
                            <ul class="cookList">
                                <el-row>
                                    <el-col v-for="goods in type2Goods" :key="goods.goodId" :xs="12" :sm="8" :md="6" :lg="4">
                                        <li @click="addOrderList(goods)">
                                            <span ><img :src="goods.goodsImg" class="foodImg"></span>
                                            <span class="foodName" v-text="goods.goodsName"></span>
                                            <span class="foodPrice" >{{goods.price | formatMoney}}</span>
                                        </li>
                                    </el-col>
                                </el-row>
                            </ul>
                        </div></el-tab-pane>
                    <el-tab-pane label="套餐" name="fourth"><div>
                            <ul class="cookList">
                                <el-row>
                                    <el-col v-for="goods in type3Goods" :key="goods.goodId" :xs="12" :sm="8" :md="6" :lg="4">
                                        <li @click="addOrderList(goods)">
                                            <span ><img :src="goods.goodsImg" class="foodImg"></span>
                                            <span class="foodName" v-text="goods.goodsName"></span>
                                            <span class="foodPrice" >{{goods.price | formatMoney}}</span>
                                        </li>
                                    </el-col>
                                </el-row>
                            </ul>
                        </div></el-tab-pane>
                </el-tabs>
              </div>

          </el-col>
      </el-row>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: "Pos",
  data () {
      return {
         tableData: [],
         type0Goods: [],
         type1Goods: [],
         type2Goods: [],
         type3Goods: [],
         oftenGoods: [],
         totalNum: 0,
         totalMoney: 0
      }
  },
  created () {
      var _this = this;
      axios.get('http://jspang.com/DemoApi/oftenGoods.php').then(function(response) {
        console.log(response);
        _this.oftenGoods=response.data;
      }).catch(function(error){
        console.log(error);
        alert('网络错误，不能访问');
      });

      axios.get('http://jspang.com/DemoApi/typeGoods.php')
      .then(response=>{
         console.log(response);
         //this.oftenGoods=response.data;
         _this.type0Goods=response.data[0];
         _this.type1Goods=response.data[1];
         _this.type2Goods=response.data[2];
         _this.type3Goods=response.data[3];
 
      })
      .catch(error=>{
          console.log(error);
          alert('网络错误，不能访问');
      })
  },
  computed: {
      
  },
  filters: {
      formatMoney(value) {
        return "￥" + value + "元";
      }
  },
  mounted:function() {
      var height = document.body.clientHeight;
      console.log(height);
      document.getElementById("order-list").style.height = height + "px";
  },
  methods: {
      deleteRow(scope) {
        console.log(scope);
        this.tableData.splice(scope.$index, 1);
        this.getTotals();
      },
      delAll(){
        this.tableData = [];
        this.totalMoney = 0;
        this.totalNum = 0;
      },
      addOrderList(goods) {
        var _this = this;
        var isHave = false;
        // debugger;
        // 商品列表中是否已包含选中商品
        _this.tableData.forEach(function(element, i) {
            if(element.goodsId == goods.goodsId) {
                // 如果有+1 
                isHave = true;
                element.num+=1;
                element.money = goods.price*element.num;
                _this.tableData.splice(i, 1, element);
            }
        }, this);
        
        // 如果没有tableData.push(goods)
        if(!isHave) {
            goods.num = 1;
            goods.money = goods.price;
            _this.tableData.push(goods);
        }
        _this.getTotals();
      },
      getTotals() {
        var _this = this;
        _this.totalNum = 0;
        _this.totalMoney = 0;
        _this.tableData.forEach(function(element) {
            _this.totalNum += element.num;
            _this.totalMoney += element.money;
        }, this);
      },
      checkout() {
          if (this.tableData.length>0) {
                this.tableData = [];
                this.totalCount = 0;
                this.totalMoney = 0;
                this.$message.success('结账成功，感谢你又为店里出了一份力!');

          }else{
                this.$message.error('不能空结。老板了解你急切的心情！');
          }
      }
  }
}
</script>

<style scoped>
.pos-order {
    background-color: #f9fafc;
    border-right: 1px solid #c0ccda;
}

.btn {
    margin-top: 10px;
}

.often-goods .title {
    height: 20px;
    border-bottom: 1px solid #d3dce6;
    background-color: #f9fafc;
    text-align: left;
    padding: 10px;
}

.often-goods .often-goods-list ul {
    list-style: none;
}

.often-goods .often-goods-list ul li {
    float: left;
    border: 1px solid #E5E9F2;
    padding: 10px;
    margin: 10px;
    background-color: #f9fafc;
    cursor: pointer;
}

.price {
    color: #58B7FF
}

.goods-type {
    clear: both;
}

.cookList li{
       list-style: none;
       width:100%;
       border:1px solid #E5E9F2;
       height: auto;
       overflow: hidden;
       background-color:#fff;
       padding: 2px;
       float:left;
       margin: 2px;
       cursor: pointer;
   }
   .cookList li span{      
        display: block;
   }
   .foodImg{
       width: 100px;
   }
   .foodName{
       font-size: 18px;
       color:brown;
   }
   .foodPrice{
       font-size: 16px;
       padding-left: 10px;
       padding-right: 10px;
       padding-top:10px;
       text-align: right;
       color: #58B7FF;
   }
   .total {
       text-align: left;
       padding-left: 10px;
   }

   .total span {
       margin: 10px;
   }
</style>


