package com.svv.jys.code.common.entity;

import java.util.List;

public class SubscriptionEntity {

    private List<CoinListBean> coin_list;
    private List<CoinNamesBean> coin_names;
    private List<CoinBean> coin;

    public List<CoinListBean> getCoin_list() {
        return coin_list;
    }

    public void setCoin_list(List<CoinListBean> coin_list) {
        this.coin_list = coin_list;
    }

    public List<CoinNamesBean> getCoin_names() {
        return coin_names;
    }

    public void setCoin_names(List<CoinNamesBean> coin_names) {
        this.coin_names = coin_names;
    }

    public List<CoinBean> getCoin() {
        return coin;
    }

    public void setCoin(List<CoinBean> coin) {
        this.coin = coin;
    }

    public static class CoinListBean {
        /**
         * ext_data : {"sell_price":"1","is_sub":"1","in_tips":{"zh_cn":""},"pla_price":"200","description":{"zh_cn":"Ethereum（以太坊）是一个平台和一种编程语言，使开发人员能够建立和发布下一代分布式应用。 Ethereum可以用来编程，分散，担保和交易任何事物：投票，域名，金融交易所，众筹，公司管理， 合同和大部分的协议，知识产权，还有得益于硬件集成的智能资产。\n\n \n\n以太坊将使用混合型的安全协议，前期使用工作量证明机制（POW），用于分发以太币，然后会切换到权益证明机制（POS）。自上线时起，每年都将有0.26x，即每年有60102216 * 0.26 = 15626576个以太币被矿工挖出。转成POS后，每年产出的以太币将减少。"},"pla_tips":"","min_sell":"1","c2c_link":"1","sub_price":"100","pla_link":"","c2c_tips":{"zh_cn":"1"},"sub_link":"","full_name":{"zh_cn":"ETH"},"pla_total":"20000","out_tips":{"zh_cn":""},"min_buy":"1","sub_total":"10000","buy_price":"1","sub_tips":"","lang":["full_name","description","out_tips","in_tips","c2c_tips"],"one_total":"2000","max_buy":"1","is_pla":"1","max_sell":"1"}
         * name : eth
         */

        private ExtDataBean ext_data;
        private String name;

        public ExtDataBean getExt_data() {
            return ext_data;
        }

        public void setExt_data(ExtDataBean ext_data) {
            this.ext_data = ext_data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static class ExtDataBean {
            /**
             * sell_price : 1
             * is_sub : 1
             * in_tips : {"zh_cn":""}
             * pla_price : 200
             * description : {"zh_cn":"Ethereum（以太坊）是一个平台和一种编程语言，使开发人员能够建立和发布下一代分布式应用。 Ethereum可以用来编程，分散，担保和交易任何事物：投票，域名，金融交易所，众筹，公司管理， 合同和大部分的协议，知识产权，还有得益于硬件集成的智能资产。\n\n \n\n以太坊将使用混合型的安全协议，前期使用工作量证明机制（POW），用于分发以太币，然后会切换到权益证明机制（POS）。自上线时起，每年都将有0.26x，即每年有60102216 * 0.26 = 15626576个以太币被矿工挖出。转成POS后，每年产出的以太币将减少。"}
             * pla_tips :
             * min_sell : 1
             * c2c_link : 1
             * sub_price : 100
             * pla_link :
             * c2c_tips : {"zh_cn":"1"}
             * sub_link :
             * full_name : {"zh_cn":"ETH"}
             * pla_total : 20000
             * out_tips : {"zh_cn":""}
             * min_buy : 1
             * sub_total : 10000
             * buy_price : 1
             * sub_tips :
             * lang : ["full_name","description","out_tips","in_tips","c2c_tips"]
             * one_total : 2000
             * max_buy : 1
             * is_pla : 1
             * max_sell : 1
             */

            private String pla_min_limit;
            private String pla_max_limit;
            private String sub_min_limit;
            private String sub_max_limit;
            private String sell_price;
            private String is_sub;
            private InTipsBean in_tips;
            private String pla_price;
            private DescriptionBean description;
            private String pla_tips;
            private String min_sell;
            private String c2c_link;
            private String sub_price;
            private String pla_link;
            private C2cTipsBean c2c_tips;
            private String sub_link;
            private FullNameBean full_name;
            private String pla_total;
            private OutTipsBean out_tips;
            private String min_buy;
            private String sub_total;
            private String buy_price;
            private String sub_tips;
            private String one_total;
            private String max_buy;
            private String is_pla;
            private String max_sell;
            private List<String> lang;
            public String getPla_min_limit() {
                return pla_min_limit;
            }

            public void setPla_min_limit(String pla_min_limit) {
                this.pla_min_limit = pla_min_limit;
            }

            public String getPla_max_limit() {
                return pla_max_limit;
            }

            public void setPla_max_limit(String pla_max_limit) {
                this.pla_max_limit = pla_max_limit;
            }

            public String getSub_min_limit() {
                return sub_min_limit;
            }

            public void setSub_min_limit(String sub_min_limit) {
                this.sub_min_limit = sub_min_limit;
            }

            public String getSub_max_limit() {
                return sub_max_limit;
            }

            public void setSub_max_limit(String sub_max_limit) {
                this.sub_max_limit = sub_max_limit;
            }

            public String getSell_price() {
                return sell_price;
            }

            public void setSell_price(String sell_price) {
                this.sell_price = sell_price;
            }

            public String getIs_sub() {
                return is_sub;
            }

            public void setIs_sub(String is_sub) {
                this.is_sub = is_sub;
            }

            public InTipsBean getIn_tips() {
                return in_tips;
            }

            public void setIn_tips(InTipsBean in_tips) {
                this.in_tips = in_tips;
            }

            public String getPla_price() {
                return pla_price;
            }

            public void setPla_price(String pla_price) {
                this.pla_price = pla_price;
            }

            public DescriptionBean getDescription() {
                return description;
            }

            public void setDescription(DescriptionBean description) {
                this.description = description;
            }

            public String getPla_tips() {
                return pla_tips;
            }

            public void setPla_tips(String pla_tips) {
                this.pla_tips = pla_tips;
            }

            public String getMin_sell() {
                return min_sell;
            }

            public void setMin_sell(String min_sell) {
                this.min_sell = min_sell;
            }

            public String getC2c_link() {
                return c2c_link;
            }

            public void setC2c_link(String c2c_link) {
                this.c2c_link = c2c_link;
            }

            public String getSub_price() {
                return sub_price;
            }

            public void setSub_price(String sub_price) {
                this.sub_price = sub_price;
            }

            public String getPla_link() {
                return pla_link;
            }

            public void setPla_link(String pla_link) {
                this.pla_link = pla_link;
            }

            public C2cTipsBean getC2c_tips() {
                return c2c_tips;
            }

            public void setC2c_tips(C2cTipsBean c2c_tips) {
                this.c2c_tips = c2c_tips;
            }

            public String getSub_link() {
                return sub_link;
            }

            public void setSub_link(String sub_link) {
                this.sub_link = sub_link;
            }

            public FullNameBean getFull_name() {
                return full_name;
            }

            public void setFull_name(FullNameBean full_name) {
                this.full_name = full_name;
            }

            public String getPla_total() {
                return pla_total;
            }

            public void setPla_total(String pla_total) {
                this.pla_total = pla_total;
            }

            public OutTipsBean getOut_tips() {
                return out_tips;
            }

            public void setOut_tips(OutTipsBean out_tips) {
                this.out_tips = out_tips;
            }

            public String getMin_buy() {
                return min_buy;
            }

            public void setMin_buy(String min_buy) {
                this.min_buy = min_buy;
            }

            public String getSub_total() {
                return sub_total;
            }

            public void setSub_total(String sub_total) {
                this.sub_total = sub_total;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public String getSub_tips() {
                return sub_tips;
            }

            public void setSub_tips(String sub_tips) {
                this.sub_tips = sub_tips;
            }

            public String getOne_total() {
                return one_total;
            }

            public void setOne_total(String one_total) {
                this.one_total = one_total;
            }

            public String getMax_buy() {
                return max_buy;
            }

            public void setMax_buy(String max_buy) {
                this.max_buy = max_buy;
            }

            public String getIs_pla() {
                return is_pla;
            }

            public void setIs_pla(String is_pla) {
                this.is_pla = is_pla;
            }

            public String getMax_sell() {
                return max_sell;
            }

            public void setMax_sell(String max_sell) {
                this.max_sell = max_sell;
            }

            public List<String> getLang() {
                return lang;
            }

            public void setLang(List<String> lang) {
                this.lang = lang;
            }

            public static class InTipsBean {
                /**
                 * zh_cn :
                 */

                private String zh_cn;

                public String getZh_cn() {
                    return zh_cn;
                }

                public void setZh_cn(String zh_cn) {
                    this.zh_cn = zh_cn;
                }
            }

            public static class DescriptionBean {
                /**
                 * zh_cn : Ethereum（以太坊）是一个平台和一种编程语言，使开发人员能够建立和发布下一代分布式应用。 Ethereum可以用来编程，分散，担保和交易任何事物：投票，域名，金融交易所，众筹，公司管理， 合同和大部分的协议，知识产权，还有得益于硬件集成的智能资产。



                 以太坊将使用混合型的安全协议，前期使用工作量证明机制（POW），用于分发以太币，然后会切换到权益证明机制（POS）。自上线时起，每年都将有0.26x，即每年有60102216 * 0.26 = 15626576个以太币被矿工挖出。转成POS后，每年产出的以太币将减少。
                 */

                private String zh_cn;

                public String getZh_cn() {
                    return zh_cn;
                }

                public void setZh_cn(String zh_cn) {
                    this.zh_cn = zh_cn;
                }
            }

            public static class C2cTipsBean {
                /**
                 * zh_cn : 1
                 */

                private String zh_cn;

                public String getZh_cn() {
                    return zh_cn;
                }

                public void setZh_cn(String zh_cn) {
                    this.zh_cn = zh_cn;
                }
            }

            public static class FullNameBean {
                /**
                 * zh_cn : ETH
                 */

                private String zh_cn;

                public String getZh_cn() {
                    return zh_cn;
                }

                public void setZh_cn(String zh_cn) {
                    this.zh_cn = zh_cn;
                }
            }

            public static class OutTipsBean {
                /**
                 * zh_cn :
                 */

                private String zh_cn;

                public String getZh_cn() {
                    return zh_cn;
                }

                public void setZh_cn(String zh_cn) {
                    this.zh_cn = zh_cn;
                }
            }
        }
    }

    public static class CoinNamesBean {
        /**
         * name : 认购币
         */

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class CoinBean {
        /**
         * pending : 100.00
         * block_link :
         * in_fee : 0.00
         * type : 2
         * sell_max : 100.00
         * is_outfee : 0
         * public :
         * is_area : 0
         * ext_data : {"lang":["full_name","description","out_tips","in_tips","c2c_tips"],"full_name":{"zh_cn":"ETH"},"description":{"zh_cn":"Ethereum\uff08\u4ee5\u592a\u574a\uff09\u662f\u4e00\u4e2a\u5e73\u53f0\u548c\u4e00\u79cd\u7f16\u7a0b\u8bed\u8a00\uff0c\u4f7f\u5f00\u53d1\u4eba\u5458\u80fd\u591f\u5efa\u7acb\u548c\u53d1\u5e03\u4e0b\u4e00\u4ee3\u5206\u5e03\u5f0f\u5e94\u7528\u3002 Ethereum\u53ef\u4ee5\u7528\u6765\u7f16\u7a0b\uff0c\u5206\u6563\uff0c\u62c5\u4fdd\u548c\u4ea4\u6613\u4efb\u4f55\u4e8b\u7269\uff1a\u6295\u7968\uff0c\u57df\u540d\uff0c\u91d1\u878d\u4ea4\u6613\u6240\uff0c\u4f17\u7b79\uff0c\u516c\u53f8\u7ba1\u7406\uff0c \u5408\u540c\u548c\u5927\u90e8\u5206\u7684\u534f\u8bae\uff0c\u77e5\u8bc6\u4ea7\u6743\uff0c\u8fd8\u6709\u5f97\u76ca\u4e8e\u786c\u4ef6\u96c6\u6210\u7684\u667a\u80fd\u8d44\u4ea7\u3002\n\n \n\n\u4ee5\u592a\u574a\u5c06\u4f7f\u7528\u6df7\u5408\u578b\u7684\u5b89\u5168\u534f\u8bae\uff0c\u524d\u671f\u4f7f\u7528\u5de5\u4f5c\u91cf\u8bc1\u660e\u673a\u5236\uff08POW\uff09\uff0c\u7528\u4e8e\u5206\u53d1\u4ee5\u592a\u5e01\uff0c\u7136\u540e\u4f1a\u5207\u6362\u5230\u6743\u76ca\u8bc1\u660e\u673a\u5236\uff08POS\uff09\u3002\u81ea\u4e0a\u7ebf\u65f6\u8d77\uff0c\u6bcf\u5e74\u90fd\u5c06\u67090.26x\uff0c\u5373\u6bcf\u5e74\u670960102216 * 0.26 = 15626576\u4e2a\u4ee5\u592a\u5e01\u88ab\u77ff\u5de5\u6316\u51fa\u3002\u8f6c\u6210POS\u540e\uff0c\u6bcf\u5e74\u4ea7\u51fa\u7684\u4ee5\u592a\u5e01\u5c06\u51cf\u5c11\u3002"},"out_tips":{"zh_cn":""},"in_tips":{"zh_cn":""},"buy_price":"1","sell_price":"1","min_buy":"1","min_sell":"1","max_buy":"1","max_sell":"1","c2c_link":"1","c2c_tips":{"zh_cn":"1"},"is_sub":"1","sub_total":"10000","one_total":"2000","sub_price":"100","sub_link":"","sub_tips":"","is_pla":"1","pla_total":"20000","pla_price":"200","pla_link":"","pla_tips":""}
         * is_out : 0
         * logo : upload/coin_logo/201906/ad03fd87e5591151fd91455269e42390.PNG
         * course :
         * id : 15
         * out_fee : 1.00000000
         * info :
         * website :
         * is_in : 0
         * out_min : 1.00000000
         * usdt_price : 0.00000000
         * sort : 255
         * confirmations : 0
         * name : eth
         * out_max : 10000.00000000
         * pay_fee : 1
         * add_time : 1561458913
         * is_plat : 0
         * white_paper :
         * status : 1
         */

        private String pending;
        private String block_link;
        private String in_fee;
        private String type;
        private String sell_max;
        private String is_outfee;
        private String is_area;
        private String ext_data;
        private String is_out;
        private String logo;
        private String course;
        private String id;
        private String out_fee;
        private String info;
        private String website;
        private String is_in;
        private String out_min;
        private String usdt_price;
        private String sort;
        private String confirmations;
        private String name;
        private String out_max;
        private String pay_fee;
        private String add_time;
        private String is_plat;
        private String white_paper;
        private String status;

        public String getPending() {
            return pending;
        }

        public void setPending(String pending) {
            this.pending = pending;
        }

        public String getBlock_link() {
            return block_link;
        }

        public void setBlock_link(String block_link) {
            this.block_link = block_link;
        }

        public String getIn_fee() {
            return in_fee;
        }

        public void setIn_fee(String in_fee) {
            this.in_fee = in_fee;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSell_max() {
            return sell_max;
        }

        public void setSell_max(String sell_max) {
            this.sell_max = sell_max;
        }

        public String getIs_outfee() {
            return is_outfee;
        }

        public void setIs_outfee(String is_outfee) {
            this.is_outfee = is_outfee;
        }


        public String getIs_area() {
            return is_area;
        }

        public void setIs_area(String is_area) {
            this.is_area = is_area;
        }

        public String getExt_data() {
            return ext_data;
        }

        public void setExt_data(String ext_data) {
            this.ext_data = ext_data;
        }

        public String getIs_out() {
            return is_out;
        }

        public void setIs_out(String is_out) {
            this.is_out = is_out;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOut_fee() {
            return out_fee;
        }

        public void setOut_fee(String out_fee) {
            this.out_fee = out_fee;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getIs_in() {
            return is_in;
        }

        public void setIs_in(String is_in) {
            this.is_in = is_in;
        }

        public String getOut_min() {
            return out_min;
        }

        public void setOut_min(String out_min) {
            this.out_min = out_min;
        }

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getConfirmations() {
            return confirmations;
        }

        public void setConfirmations(String confirmations) {
            this.confirmations = confirmations;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOut_max() {
            return out_max;
        }

        public void setOut_max(String out_max) {
            this.out_max = out_max;
        }

        public String getPay_fee() {
            return pay_fee;
        }

        public void setPay_fee(String pay_fee) {
            this.pay_fee = pay_fee;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getIs_plat() {
            return is_plat;
        }

        public void setIs_plat(String is_plat) {
            this.is_plat = is_plat;
        }

        public String getWhite_paper() {
            return white_paper;
        }

        public void setWhite_paper(String white_paper) {
            this.white_paper = white_paper;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
