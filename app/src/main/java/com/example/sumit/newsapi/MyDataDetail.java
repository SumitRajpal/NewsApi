package com.example.sumit.newsapi;

/**
 * Created by sumit on 12/6/2016.
 */

public class MyDataDetail{
        private String title,logo;
        private String url, urlToImage;

        public MyDataDetail(String title, String url, String urlToImage,String logo) {
            this.title = title;
            this.url = url;
            this.urlToImage = urlToImage;
            this.logo=logo;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getLogo() {return logo; }

        public void setLogo(String logo) {this.logo = logo; }
}