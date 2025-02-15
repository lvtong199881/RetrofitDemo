package com.lvtong.retrofitdemo.mid;

/**
 * @author tong.lv
 * @date 2020/1/10
 */
public class Translation {

    private int status;

    private content content;

    public static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private String ph_en_mp3;
        private int errNo;

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getVendor() {
            return vendor;
        }

        public String getOut() {
            return out;
        }

        public int getErrNo() {
            return errNo;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }
    }

    /**
     * 定义 输出返回数据 的方法
     */
    public void show() {
        System.out.println(status);

        System.out.println(content.from);
        System.out.println(content.to);
        System.out.println(content.vendor);
        System.out.println(content.out);
        System.out.println(content.errNo);
        System.out.println(content.ph_en_mp3);
    }

    public int getStatus() {
        return status;
    }

    public Translation.content getContent() {
        return content;
    }
}