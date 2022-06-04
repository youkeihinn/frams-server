package com.projects.modular.attendance.model.result;

import java.util.List;

public class Result {


    /**
     * result : {"face_token":"711afb46293b5b4508917b428afb4596","user_list":[{"score":96.397689819336,"group_id":"face","user_id":"1","user_info":"BASE64"}]}
     * log_id : 4500105352018
     * error_msg : SUCCESS
     * cached : 0
     * error_code : 0
     * timestamp : 1588929731
     */

    private ResultBean result;
    private long log_id;
    private String error_msg;
    private int cached;
    private int error_code;
    private int timestamp;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResultBean {
        /**
         * face_token : 711afb46293b5b4508917b428afb4596
         * user_list : [{"score":96.397689819336,"group_id":"face","user_id":"1","user_info":"BASE64"}]
         */

        private String face_token;
        private List<UserListBean> user_list;

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public List<UserListBean> getUser_list() {
            return user_list;
        }

        public void setUser_list(List<UserListBean> user_list) {
            this.user_list = user_list;
        }

        public static class UserListBean {
            /**
             * score : 96.397689819336
             * group_id : face
             * user_id : 1
             * user_info : BASE64
             */

            private double score;
            private String group_id;
            private String user_id;
            private String user_info;

            public double getScore() {
                return score;
            }

            public void setScore(double score) {
                this.score = score;
            }

            public String getGroup_id() {
                return group_id;
            }

            public void setGroup_id(String group_id) {
                this.group_id = group_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_info() {
                return user_info;
            }

            public void setUser_info(String user_info) {
                this.user_info = user_info;
            }
        }
    }
}
