package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class WalletDataEntity extends BaseEntity {

    /**
     * rows : [{"id":"10742","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.04589640","memo":"交易usdt_btc匹配卖家增加0.04589640","add_time":"2019-04-02 10:57:47","b_money":"110907.99453522"},{"id":"10739","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.03799521","memo":"交易usdt_btc匹配卖家增加0.03799521","add_time":"2019-04-02 10:57:47","b_money":"110907.95654001"},{"id":"10736","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.04928091","memo":"交易usdt_btc匹配卖家增加0.04928091","add_time":"2019-04-02 10:57:47","b_money":"110907.90725910"},{"id":"10709","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.04739622","memo":"交易usdt_btc匹配卖家增加0.04739622","add_time":"2019-04-02 10:57:47","b_money":"110907.85986288"},{"id":"10684","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.03686178","memo":"交易usdt_btc匹配卖家增加0.03686178","add_time":"2019-04-02 10:57:47","b_money":"110907.82300110"},{"id":"10681","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.04325617","memo":"交易usdt_btc匹配卖家增加0.04325617","add_time":"2019-04-02 10:57:47","b_money":"110907.77974493"},{"id":"10580","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.03836231","memo":"交易usdt_btc匹配卖家增加0.03836231","add_time":"2019-04-02 10:57:47","b_money":"110907.74138262"},{"id":"10512","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.03046098","memo":"交易usdt_btc匹配卖家增加0.03046098","add_time":"2019-04-02 10:57:47","b_money":"110907.71092164"},{"id":"10149","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.05375272","memo":"交易usdt_btc匹配卖家增加0.05375272","add_time":"2019-04-02 10:57:47","b_money":"110907.65716892"},{"id":"10143","user_id":"1","operation":"买卖交易","type":"0","coin":"btc","num":"0.03044093","memo":"交易usdt_btc匹配卖家增加0.03044093","add_time":"2019-04-02 10:57:47","b_money":"110907.62672799"}]
     * total : 995
     * where : {"user_id":"1","coin":"btc","LIMIT":[0,10],"ORDER":{"id":"DESC"}}
     * user_coin : {"name":"btc","able":"110908.04043162","freeze":"60.50114831","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","address":"896882da2c5d270b8d4c615fa1715198","memo":"","total":3,"usdt_price":"470040.54842426","rows":[{"name":"btc","able":"110908.04043162","freeze":"60.50114831","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","address":"896882da2c5d270b8d4c615fa1715198","memo":"","total":"110968.54157993","usdt_price":"470040.54842426"}],"sum_total":"470040.54842426"}
     */

    private String total;
    private WhereBean where;
    private UserCoinBean user_coin;
    private List<RowsBean> rows;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public WhereBean getWhere() {
        return where;
    }

    public void setWhere(WhereBean where) {
        this.where = where;
    }

    public UserCoinBean getUser_coin() {
        return user_coin;
    }

    public void setUser_coin(UserCoinBean user_coin) {
        this.user_coin = user_coin;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class WhereBean {
        /**
         * user_id : 1
         * coin : btc
         * LIMIT : [0,10]
         * ORDER : {"id":"DESC"}
         */

        private String user_id;
        private String coin;
        private ORDERBean ORDER;
        private List<Integer> LIMIT;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public ORDERBean getORDER() {
            return ORDER;
        }

        public void setORDER(ORDERBean ORDER) {
            this.ORDER = ORDER;
        }

        public List<Integer> getLIMIT() {
            return LIMIT;
        }

        public void setLIMIT(List<Integer> LIMIT) {
            this.LIMIT = LIMIT;
        }

        public static class ORDERBean {
            /**
             * id : DESC
             */

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }

    public static class UserCoinBean {
        /**
         * name : btc
         * able : 110908.04043162
         * freeze : 60.50114831
         * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
         * address : 896882da2c5d270b8d4c615fa1715198
         * memo :
         * total : 3
         * usdt_price : 470040.54842426
         * rows : [{"name":"btc","able":"110908.04043162","freeze":"60.50114831","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","address":"896882da2c5d270b8d4c615fa1715198","memo":"","total":"110968.54157993","usdt_price":"470040.54842426"}]
         * sum_total : 470040.54842426
         */

        private String name;
        private String able;
        private String freeze;
        private String logo;
        private String address;
        private String memo;
        private String total;
        private String usdt_price;
        private String sum_total;
        private List<RowsBean> rows;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAble() {
            return able;
        }

        public void setAble(String able) {
            this.able = able;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }

        public String getSum_total() {
            return sum_total;
        }

        public void setSum_total(String sum_total) {
            this.sum_total = sum_total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {
            /**
             * name : btc
             * able : 110908.04043162
             * freeze : 60.50114831
             * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
             * address : 896882da2c5d270b8d4c615fa1715198
             * memo :
             * total : 110968.54157993
             * usdt_price : 470040.54842426
             */

            private String name;
            private String able;
            private String freeze;
            private String logo;
            private String address;
            private String memo;
            private String total;
            private String usdt_price;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAble() {
                return able;
            }

            public void setAble(String able) {
                this.able = able;
            }

            public String getFreeze() {
                return freeze;
            }

            public void setFreeze(String freeze) {
                this.freeze = freeze;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getUsdt_price() {
                return usdt_price;
            }

            public void setUsdt_price(String usdt_price) {
                this.usdt_price = usdt_price;
            }
        }
    }

    public static class RowsBean {
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getOperation_type() {
            return operation_type;
        }

        public void setOperation_type(int operation_type) {
            this.operation_type = operation_type;
        }

        /**
         * id : 10742
         * user_id : 1
         * operation : 买卖交易
         * type : 0
         * coin : btc
         * num : 0.04589640
         * memo : 交易usdt_btc匹配卖家增加0.04589640
         * add_time : 2019-04-02 10:57:47
         * b_money : 110907.99453522
         */
        private int operation_type;
        private int status;
        private String id;
        private String user_id;
        private String operation;
        private String type;
        private String coin;
        private String num;
        private String memo;
        private String add_time;
        private String b_money;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getB_money() {
            return b_money;
        }

        public void setB_money(String b_money) {
            this.b_money = b_money;
        }
    }
}
