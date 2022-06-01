package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class IndexEntity extends BaseEntity {

    private List<BannerBean> banner;
    private List<NoticeBean> notice;
    private List<MarketListEntity> market;
    private List<List<MarketListEntity>> marketList;

    private List<?> message;
    private HomeAssatEntity user_coin;

    public HomeAssatEntity getUser_coin() {
        return user_coin;
    }

    public void setUser_coin(HomeAssatEntity user_coin) {
        this.user_coin = user_coin;
    }

    public List<List<MarketListEntity>> getMarketList() {
        return marketList;
    }

    public void setMarketList() {
        this.marketList = new ArrayList<>();
        int size = market.size() / 3 +1;
        for(int i = 0;i<size;i++){
            List<MarketListEntity> list = new ArrayList<>();
            if(i * 3 < market.size()) {
                list.add(market.get(i * 3));
            }
            if(i * 3 + 1 <market.size()){
                list.add(market.get(i * 3 +1));
            }
            if(i * 3 + 2 <market.size()){
                list.add(market.get(i * 3 + 2));
            }
            marketList.add(list);
        }
    }

    public List<BannerBean> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerBean> banner) {
        this.banner = banner;
    }

    public List<NoticeBean> getNotice() {
        return notice;
    }

    public void setNotice(List<NoticeBean> notice) {
        this.notice = notice;
    }

    public List<MarketListEntity> getMarket() {
        return market;
    }

    public void setMarket(List<MarketListEntity> market) {
        this.market = market;
        setMarketList();
    }



    public List<?> getMessage() {
        return message;
    }

    public void setMessage(List<?> message) {
        this.message = message;
    }

    public static class BannerBean {
        /**
         * link :  href="swiper_banner_2"
         * pic : http://192.168.1.128/yaf_all/6.1_digital/public/static/upload/image/201812/04278a044919232acad51f861ca73ac2.PNG
         */

        private String link;
        private String pic;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }
    }

    public static class NoticeBean {
        /**
         * id : 46
         * title : 新版系统
         * sec_title : 新版系统
         * add_time : -
         */

        private String id;
        private String title;
        private String sec_title;
        private String add_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSec_title() {
            return sec_title;
        }

        public void setSec_title(String sec_title) {
            this.sec_title = sec_title;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
    }

    public static class CoinBean {
        /**
         * usdt_price : 4.2358
         * market : {"market":"btc_usdt","rate":0}
         * name : btc
         */

        private String usdt_price;
        private CoinMarketBean market;
        private String name;

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }

        public CoinMarketBean getMarket() {
            return market;
        }

        public void setMarket(CoinMarketBean market) {
            this.market = market;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
    public static class CoinMarketBean {
        /**
         * market : btc_usdt
         * rate : 0
         */

        private String market;
        private String rate;

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }
    }
}
