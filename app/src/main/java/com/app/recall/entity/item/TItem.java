package com.app.recall.entity.item;

import java.util.List;

/**
 * Created by KenChan on 17/1/4.
 */

public class TItem extends BaseItem implements Cloneable {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"ret_code":0,"pagebean":{"allPages":"52",
     * "contentlist":[{"id":"/xe/7007659.shtml","title":"内涵漫画：情人节礼物","link":"http://www.hanhande
     * .com/xe/7007659.shtml"},{"id":"/xe/7007658.shtml","title":"内涵漫画：正确的穿衣方式",
     * "link":"http://www.hanhande.com/xe/7007658.shtml"},{"id":"/xe/7007657.shtml",
     * "title":"内涵漫画：手上的魔法","link":"http://www.hanhande.com/xe/7007657.shtml"},{"id":"/xe/7007656
     * .shtml","title":"内涵漫画：专业工具","link":"http://www.hanhande.com/xe/7007656.shtml"},
     * {"id":"/xe/7007655.shtml","title":"内涵漫画：该努力的时候","link":"http://www.hanhande.com/xe/7007655
     * .shtml"},{"id":"/xe/7007561.shtml","title":"内涵漫画：没长对地方","link":"http://www.hanhande
     * .com/xe/7007561.shtml"},{"id":"/xe/7007560.shtml","title":"内涵漫画：数量","link":"http://www
     * .hanhande.com/xe/7007560.shtml"},{"id":"/xe/7007559.shtml","title":"内涵漫画：证据",
     * "link":"http://www.hanhande.com/xe/7007559.shtml"},{"id":"/xe/7007558.shtml",
     * "title":"内涵漫画：迟到的惩罚","link":"http://www.hanhande.com/xe/7007558.shtml"},{"id":"/xe/7007557
     * .shtml","title":"内涵漫画：存在的理由","link":"http://www.hanhande.com/xe/7007557.shtml"},
     * {"id":"/xe/7007428.shtml","title":"内涵漫画：放纵的一天","link":"http://www.hanhande.com/xe/7007428
     * .shtml"},{"id":"/xe/7007427.shtml","title":"内涵漫画：反应不同","link":"http://www.hanhande
     * .com/xe/7007427.shtml"},{"id":"/xe/7007334.shtml","title":"内涵漫画：反省","link":"http://www
     * .hanhande.com/xe/7007334.shtml"},{"id":"/xe/7007333.shtml","title":"内涵漫画：大师",
     * "link":"http://www.hanhande.com/xe/7007333.shtml"},{"id":"/xe/7007332.shtml",
     * "title":"内涵漫画：机智的猴子","link":"http://www.hanhande.com/xe/7007332.shtml"},{"id":"/xe/7007144
     * .shtml","title":"内涵漫画：先忙正事","link":"http://www.hanhande.com/xe/7007144.shtml"},
     * {"id":"/xe/7007143.shtml","title":"内涵漫画：晚上不可以的代价","link":"http://www.hanhande
     * .com/xe/7007143.shtml"},{"id":"/xe/7007048.shtml","title":"内涵漫画：海边的狼狈","link":"http://www
     * .hanhande.com/xe/7007048.shtml"},{"id":"/xe/7007047.shtml","title":"内涵漫画：路过的官人",
     * "link":"http://www.hanhande.com/xe/7007047.shtml"},{"id":"/xe/7007046.shtml",
     * "title":"内涵漫画：变身成女人","link":"http://www.hanhande.com/xe/7007046.shtml"},{"id":"/xe/7007045
     * .shtml","title":"内涵漫画：倒胃口","link":"http://www.hanhande.com/xe/7007045.shtml"},
     * {"id":"/xe/7006954.shtml","title":"内涵漫画：智能","link":"http://www.hanhande.com/xe/7006954
     * .shtml"},{"id":"/xe/7006953.shtml","title":"内涵漫画：魔女课程","link":"http://www.hanhande
     * .com/xe/7006953.shtml"},{"id":"/xe/7006952.shtml","title":"内涵漫画：剧场里的男女","link":"http://www
     * .hanhande.com/xe/7006952.shtml"},{"id":"/xe/7006951.shtml","title":"内涵漫画：不由自主",
     * "link":"http://www.hanhande.com/xe/7006951.shtml"},{"id":"/xe/7006785.shtml",
     * "title":"内涵漫画：兴奋和兴奋","link":"http://www.hanhande.com/xe/7006785.shtml"},{"id":"/xe/7006784
     * .shtml","title":"内涵漫画：正面看","link":"http://www.hanhande.com/xe/7006784.shtml"},
     * {"id":"/xe/7006708.shtml","title":"内涵漫画：谁爱更多","link":"http://www.hanhande.com/xe/7006708
     * .shtml"},{"id":"/xe/7006707.shtml","title":"内涵漫画：做贼所以心虚","link":"http://www.hanhande
     * .com/xe/7006707.shtml"},{"id":"/xe/7006706.shtml","title":"内涵漫画：点痣","link":"http://www
     * .hanhande.com/xe/7006706.shtml"},{"id":"/xe/7006705.shtml","title":"内涵漫画：求雨的真相",
     * "link":"http://www.hanhande.com/xe/7006705.shtml"},{"id":"/xe/7006703.shtml",
     * "title":"内涵漫画：下衣时尚","link":"http://www.hanhande.com/xe/7006703.shtml"},{"id":"/xe/7006615
     * .shtml","title":"内涵漫画：鹿角的作用","link":"http://www.hanhande.com/xe/7006615.shtml"},
     * {"id":"/xe/7006616.shtml","title":"内涵漫画：有个洞","link":"http://www.hanhande.com/xe/7006616
     * .shtml"},{"id":"/xe/7006614.shtml","title":"内涵漫画：温水的等待","link":"http://www.hanhande
     * .com/xe/7006614.shtml"},{"id":"/xe/7006613.shtml","title":"内涵漫画：私人的玩具","link":"http://www
     * .hanhande.com/xe/7006613.shtml"},{"id":"/xe/7006507.shtml","title":"内涵漫画：其他的办法",
     * "link":"http://www.hanhande.com/xe/7006507.shtml"},{"id":"/xe/7006506.shtml",
     * "title":"内涵漫画：自信一跳","link":"http://www.hanhande.com/xe/7006506.shtml"},{"id":"/xe/7006505
     * .shtml","title":"内涵漫画：亚马逊市场","link":"http://www.hanhande.com/xe/7006505.shtml"},
     * {"id":"/xe/7006404.shtml","title":"内涵漫画：所谓10年","link":"http://www.hanhande.com/xe/7006404
     * .shtml"},{"id":"/xe/7006403.shtml","title":"内涵漫画：男女的区别","link":"http://www.hanhande
     * .com/xe/7006403.shtml"},{"id":"/xe/7006312.shtml","title":"内涵漫画：无毛的症状","link":"http://www
     * .hanhande.com/xe/7006312.shtml"},{"id":"/xe/7006311.shtml","title":"内涵漫画：男朋友的毛发",
     * "link":"http://www.hanhande.com/xe/7006311.shtml"},{"id":"/xe/7006309.shtml",
     * "title":"内涵漫画：月圆的精气","link":"http://www.hanhande.com/xe/7006309.shtml"},{"id":"/xe/7006307
     * .shtml","title":"内涵漫画：必胜的挑战","link":"http://www.hanhande.com/xe/7006307.shtml"},
     * {"id":"/xe/7006227.shtml","title":"内涵漫画：证明","link":"http://www.hanhande.com/xe/7006227
     * .shtml"},{"id":"/xe/7006226.shtml","title":"内涵漫画：刑警美女","link":"http://www.hanhande
     * .com/xe/7006226.shtml"},{"id":"/xe/7006225.shtml","title":"内涵漫画：练级","link":"http://www
     * .hanhande.com/xe/7006225.shtml"},{"id":"/xe/7006224.shtml","title":"内涵漫画：我男人的喜好",
     * "link":"http://www.hanhande.com/xe/7006224.shtml"},{"id":"/xe/7006053.shtml",
     * "title":"内涵漫画：不同的癖好","link":"http://www.hanhande.com/xe/7006053.shtml"}],
     * "maxResult":"15"},"currentPage":"1"}
     */

    private int showapi_res_code;
    private String showapi_res_error;
    private ShowapiResBodyBean showapi_res_body;

    public int getShowapi_res_code() { return showapi_res_code;}

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() { return showapi_res_error;}

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public ShowapiResBodyBean getShowapi_res_body() { return showapi_res_body;}

    public void setShowapi_res_body(ShowapiResBodyBean showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }

    public static class ShowapiResBodyBean {
        /**
         * ret_code : 0
         * pagebean : {"allPages":"52","contentlist":[{"id":"/xe/7007659.shtml",
         * "title":"内涵漫画：情人节礼物","link":"http://www.hanhande.com/xe/7007659.shtml"},
         * {"id":"/xe/7007658.shtml","title":"内涵漫画：正确的穿衣方式","link":"http://www.hanhande
         * .com/xe/7007658.shtml"},{"id":"/xe/7007657.shtml","title":"内涵漫画：手上的魔法",
         * "link":"http://www.hanhande.com/xe/7007657.shtml"},{"id":"/xe/7007656.shtml",
         * "title":"内涵漫画：专业工具","link":"http://www.hanhande.com/xe/7007656.shtml"},
         * {"id":"/xe/7007655.shtml","title":"内涵漫画：该努力的时候","link":"http://www.hanhande
         * .com/xe/7007655.shtml"},{"id":"/xe/7007561.shtml","title":"内涵漫画：没长对地方",
         * "link":"http://www.hanhande.com/xe/7007561.shtml"},{"id":"/xe/7007560.shtml",
         * "title":"内涵漫画：数量","link":"http://www.hanhande.com/xe/7007560.shtml"},
         * {"id":"/xe/7007559.shtml","title":"内涵漫画：证据","link":"http://www.hanhande.com/xe/7007559
         * .shtml"},{"id":"/xe/7007558.shtml","title":"内涵漫画：迟到的惩罚","link":"http://www.hanhande
         * .com/xe/7007558.shtml"},{"id":"/xe/7007557.shtml","title":"内涵漫画：存在的理由",
         * "link":"http://www.hanhande.com/xe/7007557.shtml"},{"id":"/xe/7007428.shtml",
         * "title":"内涵漫画：放纵的一天","link":"http://www.hanhande.com/xe/7007428.shtml"},
         * {"id":"/xe/7007427.shtml","title":"内涵漫画：反应不同","link":"http://www.hanhande
         * .com/xe/7007427.shtml"},{"id":"/xe/7007334.shtml","title":"内涵漫画：反省","link":"http://www
         * .hanhande.com/xe/7007334.shtml"},{"id":"/xe/7007333.shtml","title":"内涵漫画：大师",
         * "link":"http://www.hanhande.com/xe/7007333.shtml"},{"id":"/xe/7007332.shtml",
         * "title":"内涵漫画：机智的猴子","link":"http://www.hanhande.com/xe/7007332.shtml"},
         * {"id":"/xe/7007144.shtml","title":"内涵漫画：先忙正事","link":"http://www.hanhande
         * .com/xe/7007144.shtml"},{"id":"/xe/7007143.shtml","title":"内涵漫画：晚上不可以的代价",
         * "link":"http://www.hanhande.com/xe/7007143.shtml"},{"id":"/xe/7007048.shtml",
         * "title":"内涵漫画：海边的狼狈","link":"http://www.hanhande.com/xe/7007048.shtml"},
         * {"id":"/xe/7007047.shtml","title":"内涵漫画：路过的官人","link":"http://www.hanhande
         * .com/xe/7007047.shtml"},{"id":"/xe/7007046.shtml","title":"内涵漫画：变身成女人",
         * "link":"http://www.hanhande.com/xe/7007046.shtml"},{"id":"/xe/7007045.shtml",
         * "title":"内涵漫画：倒胃口","link":"http://www.hanhande.com/xe/7007045.shtml"},
         * {"id":"/xe/7006954.shtml","title":"内涵漫画：智能","link":"http://www.hanhande.com/xe/7006954
         * .shtml"},{"id":"/xe/7006953.shtml","title":"内涵漫画：魔女课程","link":"http://www.hanhande
         * .com/xe/7006953.shtml"},{"id":"/xe/7006952.shtml","title":"内涵漫画：剧场里的男女",
         * "link":"http://www.hanhande.com/xe/7006952.shtml"},{"id":"/xe/7006951.shtml",
         * "title":"内涵漫画：不由自主","link":"http://www.hanhande.com/xe/7006951.shtml"},
         * {"id":"/xe/7006785.shtml","title":"内涵漫画：兴奋和兴奋","link":"http://www.hanhande
         * .com/xe/7006785.shtml"},{"id":"/xe/7006784.shtml","title":"内涵漫画：正面看",
         * "link":"http://www.hanhande.com/xe/7006784.shtml"},{"id":"/xe/7006708.shtml",
         * "title":"内涵漫画：谁爱更多","link":"http://www.hanhande.com/xe/7006708.shtml"},
         * {"id":"/xe/7006707.shtml","title":"内涵漫画：做贼所以心虚","link":"http://www.hanhande
         * .com/xe/7006707.shtml"},{"id":"/xe/7006706.shtml","title":"内涵漫画：点痣","link":"http://www
         * .hanhande.com/xe/7006706.shtml"},{"id":"/xe/7006705.shtml","title":"内涵漫画：求雨的真相",
         * "link":"http://www.hanhande.com/xe/7006705.shtml"},{"id":"/xe/7006703.shtml",
         * "title":"内涵漫画：下衣时尚","link":"http://www.hanhande.com/xe/7006703.shtml"},
         * {"id":"/xe/7006615.shtml","title":"内涵漫画：鹿角的作用","link":"http://www.hanhande
         * .com/xe/7006615.shtml"},{"id":"/xe/7006616.shtml","title":"内涵漫画：有个洞",
         * "link":"http://www.hanhande.com/xe/7006616.shtml"},{"id":"/xe/7006614.shtml",
         * "title":"内涵漫画：温水的等待","link":"http://www.hanhande.com/xe/7006614.shtml"},
         * {"id":"/xe/7006613.shtml","title":"内涵漫画：私人的玩具","link":"http://www.hanhande
         * .com/xe/7006613.shtml"},{"id":"/xe/7006507.shtml","title":"内涵漫画：其他的办法",
         * "link":"http://www.hanhande.com/xe/7006507.shtml"},{"id":"/xe/7006506.shtml",
         * "title":"内涵漫画：自信一跳","link":"http://www.hanhande.com/xe/7006506.shtml"},
         * {"id":"/xe/7006505.shtml","title":"内涵漫画：亚马逊市场","link":"http://www.hanhande
         * .com/xe/7006505.shtml"},{"id":"/xe/7006404.shtml","title":"内涵漫画：所谓10年",
         * "link":"http://www.hanhande.com/xe/7006404.shtml"},{"id":"/xe/7006403.shtml",
         * "title":"内涵漫画：男女的区别","link":"http://www.hanhande.com/xe/7006403.shtml"},
         * {"id":"/xe/7006312.shtml","title":"内涵漫画：无毛的症状","link":"http://www.hanhande
         * .com/xe/7006312.shtml"},{"id":"/xe/7006311.shtml","title":"内涵漫画：男朋友的毛发",
         * "link":"http://www.hanhande.com/xe/7006311.shtml"},{"id":"/xe/7006309.shtml",
         * "title":"内涵漫画：月圆的精气","link":"http://www.hanhande.com/xe/7006309.shtml"},
         * {"id":"/xe/7006307.shtml","title":"内涵漫画：必胜的挑战","link":"http://www.hanhande
         * .com/xe/7006307.shtml"},{"id":"/xe/7006227.shtml","title":"内涵漫画：证明","link":"http://www
         * .hanhande.com/xe/7006227.shtml"},{"id":"/xe/7006226.shtml","title":"内涵漫画：刑警美女",
         * "link":"http://www.hanhande.com/xe/7006226.shtml"},{"id":"/xe/7006225.shtml",
         * "title":"内涵漫画：练级","link":"http://www.hanhande.com/xe/7006225.shtml"},
         * {"id":"/xe/7006224.shtml","title":"内涵漫画：我男人的喜好","link":"http://www.hanhande
         * .com/xe/7006224.shtml"},{"id":"/xe/7006053.shtml","title":"内涵漫画：不同的癖好",
         * "link":"http://www.hanhande.com/xe/7006053.shtml"}],"maxResult":"15"}
         * currentPage : 1
         */

        private int ret_code;
        private PagebeanBean pagebean;
        private String currentPage;

        public int getRet_code() { return ret_code;}

        public void setRet_code(int ret_code) { this.ret_code = ret_code;}

        public PagebeanBean getPagebean() { return pagebean;}

        public void setPagebean(PagebeanBean pagebean) { this.pagebean = pagebean;}

        public String getCurrentPage() { return currentPage;}

        public void setCurrentPage(String currentPage) { this.currentPage = currentPage;}

        public static class PagebeanBean {
            /**
             * allPages : 52
             * contentlist : [{"id":"/xe/7007659.shtml","title":"内涵漫画：情人节礼物","link":"http://www
             * .hanhande.com/xe/7007659.shtml"},{"id":"/xe/7007658.shtml","title":"内涵漫画：正确的穿衣方式",
             * "link":"http://www.hanhande.com/xe/7007658.shtml"},{"id":"/xe/7007657.shtml",
             * "title":"内涵漫画：手上的魔法","link":"http://www.hanhande.com/xe/7007657.shtml"},
             * {"id":"/xe/7007656.shtml","title":"内涵漫画：专业工具","link":"http://www.hanhande
             * .com/xe/7007656.shtml"},{"id":"/xe/7007655.shtml","title":"内涵漫画：该努力的时候",
             * "link":"http://www.hanhande.com/xe/7007655.shtml"},{"id":"/xe/7007561.shtml",
             * "title":"内涵漫画：没长对地方","link":"http://www.hanhande.com/xe/7007561.shtml"},
             * {"id":"/xe/7007560.shtml","title":"内涵漫画：数量","link":"http://www.hanhande
             * .com/xe/7007560.shtml"},{"id":"/xe/7007559.shtml","title":"内涵漫画：证据",
             * "link":"http://www.hanhande.com/xe/7007559.shtml"},{"id":"/xe/7007558.shtml",
             * "title":"内涵漫画：迟到的惩罚","link":"http://www.hanhande.com/xe/7007558.shtml"},
             * {"id":"/xe/7007557.shtml","title":"内涵漫画：存在的理由","link":"http://www.hanhande
             * .com/xe/7007557.shtml"},{"id":"/xe/7007428.shtml","title":"内涵漫画：放纵的一天",
             * "link":"http://www.hanhande.com/xe/7007428.shtml"},{"id":"/xe/7007427.shtml",
             * "title":"内涵漫画：反应不同","link":"http://www.hanhande.com/xe/7007427.shtml"},
             * {"id":"/xe/7007334.shtml","title":"内涵漫画：反省","link":"http://www.hanhande
             * .com/xe/7007334.shtml"},{"id":"/xe/7007333.shtml","title":"内涵漫画：大师",
             * "link":"http://www.hanhande.com/xe/7007333.shtml"},{"id":"/xe/7007332.shtml",
             * "title":"内涵漫画：机智的猴子","link":"http://www.hanhande.com/xe/7007332.shtml"},
             * {"id":"/xe/7007144.shtml","title":"内涵漫画：先忙正事","link":"http://www.hanhande
             * .com/xe/7007144.shtml"},{"id":"/xe/7007143.shtml","title":"内涵漫画：晚上不可以的代价",
             * "link":"http://www.hanhande.com/xe/7007143.shtml"},{"id":"/xe/7007048.shtml",
             * "title":"内涵漫画：海边的狼狈","link":"http://www.hanhande.com/xe/7007048.shtml"},
             * {"id":"/xe/7007047.shtml","title":"内涵漫画：路过的官人","link":"http://www.hanhande
             * .com/xe/7007047.shtml"},{"id":"/xe/7007046.shtml","title":"内涵漫画：变身成女人",
             * "link":"http://www.hanhande.com/xe/7007046.shtml"},{"id":"/xe/7007045.shtml",
             * "title":"内涵漫画：倒胃口","link":"http://www.hanhande.com/xe/7007045.shtml"},
             * {"id":"/xe/7006954.shtml","title":"内涵漫画：智能","link":"http://www.hanhande
             * .com/xe/7006954.shtml"},{"id":"/xe/7006953.shtml","title":"内涵漫画：魔女课程",
             * "link":"http://www.hanhande.com/xe/7006953.shtml"},{"id":"/xe/7006952.shtml",
             * "title":"内涵漫画：剧场里的男女","link":"http://www.hanhande.com/xe/7006952.shtml"},
             * {"id":"/xe/7006951.shtml","title":"内涵漫画：不由自主","link":"http://www.hanhande
             * .com/xe/7006951.shtml"},{"id":"/xe/7006785.shtml","title":"内涵漫画：兴奋和兴奋",
             * "link":"http://www.hanhande.com/xe/7006785.shtml"},{"id":"/xe/7006784.shtml",
             * "title":"内涵漫画：正面看","link":"http://www.hanhande.com/xe/7006784.shtml"},
             * {"id":"/xe/7006708.shtml","title":"内涵漫画：谁爱更多","link":"http://www.hanhande
             * .com/xe/7006708.shtml"},{"id":"/xe/7006707.shtml","title":"内涵漫画：做贼所以心虚",
             * "link":"http://www.hanhande.com/xe/7006707.shtml"},{"id":"/xe/7006706.shtml",
             * "title":"内涵漫画：点痣","link":"http://www.hanhande.com/xe/7006706.shtml"},
             * {"id":"/xe/7006705.shtml","title":"内涵漫画：求雨的真相","link":"http://www.hanhande
             * .com/xe/7006705.shtml"},{"id":"/xe/7006703.shtml","title":"内涵漫画：下衣时尚",
             * "link":"http://www.hanhande.com/xe/7006703.shtml"},{"id":"/xe/7006615.shtml",
             * "title":"内涵漫画：鹿角的作用","link":"http://www.hanhande.com/xe/7006615.shtml"},
             * {"id":"/xe/7006616.shtml","title":"内涵漫画：有个洞","link":"http://www.hanhande
             * .com/xe/7006616.shtml"},{"id":"/xe/7006614.shtml","title":"内涵漫画：温水的等待",
             * "link":"http://www.hanhande.com/xe/7006614.shtml"},{"id":"/xe/7006613.shtml",
             * "title":"内涵漫画：私人的玩具","link":"http://www.hanhande.com/xe/7006613.shtml"},
             * {"id":"/xe/7006507.shtml","title":"内涵漫画：其他的办法","link":"http://www.hanhande
             * .com/xe/7006507.shtml"},{"id":"/xe/7006506.shtml","title":"内涵漫画：自信一跳",
             * "link":"http://www.hanhande.com/xe/7006506.shtml"},{"id":"/xe/7006505.shtml",
             * "title":"内涵漫画：亚马逊市场","link":"http://www.hanhande.com/xe/7006505.shtml"},
             * {"id":"/xe/7006404.shtml","title":"内涵漫画：所谓10年","link":"http://www.hanhande
             * .com/xe/7006404.shtml"},{"id":"/xe/7006403.shtml","title":"内涵漫画：男女的区别",
             * "link":"http://www.hanhande.com/xe/7006403.shtml"},{"id":"/xe/7006312.shtml",
             * "title":"内涵漫画：无毛的症状","link":"http://www.hanhande.com/xe/7006312.shtml"},
             * {"id":"/xe/7006311.shtml","title":"内涵漫画：男朋友的毛发","link":"http://www.hanhande
             * .com/xe/7006311.shtml"},{"id":"/xe/7006309.shtml","title":"内涵漫画：月圆的精气",
             * "link":"http://www.hanhande.com/xe/7006309.shtml"},{"id":"/xe/7006307.shtml",
             * "title":"内涵漫画：必胜的挑战","link":"http://www.hanhande.com/xe/7006307.shtml"},
             * {"id":"/xe/7006227.shtml","title":"内涵漫画：证明","link":"http://www.hanhande
             * .com/xe/7006227.shtml"},{"id":"/xe/7006226.shtml","title":"内涵漫画：刑警美女",
             * "link":"http://www.hanhande.com/xe/7006226.shtml"},{"id":"/xe/7006225.shtml",
             * "title":"内涵漫画：练级","link":"http://www.hanhande.com/xe/7006225.shtml"},
             * {"id":"/xe/7006224.shtml","title":"内涵漫画：我男人的喜好","link":"http://www.hanhande
             * .com/xe/7006224.shtml"},{"id":"/xe/7006053.shtml","title":"内涵漫画：不同的癖好",
             * "link":"http://www.hanhande.com/xe/7006053.shtml"}]
             * maxResult : 15
             */

            private String allPages;
            private String maxResult;
            private List<ContentlistBean> contentlist;

            public String getAllPages() { return allPages;}

            public void setAllPages(String allPages) { this.allPages = allPages;}

            public String getMaxResult() { return maxResult;}

            public void setMaxResult(String maxResult) { this.maxResult = maxResult;}

            public List<ContentlistBean> getContentlist() { return contentlist;}

            public void setContentlist(List<ContentlistBean> contentlist) { this.contentlist =
                    contentlist;}

            public static class ContentlistBean extends BaseItem {
                /**
                 * id : /xe/7007659.shtml
                 * title : 内涵漫画：情人节礼物
                 * link : http://www.hanhande.com/xe/7007659.shtml
                 */

                private String id;
                private String title;
                private String link;

                public String getId() { return id;}

                public void setId(String id) { this.id = id;}

                public String getTitle() { return title;}

                public void setTitle(String title) { this.title = title;}

                public String getLink() { return link;}

                public void setLink(String link) { this.link = link;}
            }
        }
    }
}
